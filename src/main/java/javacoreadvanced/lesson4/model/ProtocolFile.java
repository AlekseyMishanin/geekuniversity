package javacoreadvanced.lesson4.model;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.APPEND;

/**
 * Класс инкапсулирует логику отправки/получения файла через сеть. Размер файла не имеет значения. При использовании
 * данного класса вначале отправляется имя файла, далее размер файла, далее содержимое файла.
 * @author Mishanin Aleksey
 * */
public class ProtocolFile {

    private boolean isFile;                     //признак того, что процесс получения файла не завершен
    private byte[] fileName;                    //массив для хранения имени файла
    private byte[] fileLength;                  //массив для хранения длины файла
    private int currentSection;                 //текущий шаг(используется при получении файла)
    private final int SIZEBUFFFILENAME = 150;   //размер массива под имя файла
    private final int SIZEBUFFFILELENGTH = 32;  //размер массива под длину файла
    private Path file;                          //путь к файлу (используется при приеме файла)
    private long length;                        //длина файла
    private byte[] buff;                        //буфер для приема/отправки кусков файла

    /**
     * Конструктор по умолчанию
     * */
    public ProtocolFile() {
        this.isFile = false;
        fileName = new byte[SIZEBUFFFILENAME];
        fileLength = new byte[SIZEBUFFFILELENGTH];
        currentSection = 0;
    }

    /**
     * Метод очищает поля и буферы для подготовки к следующей операции отправки/приема
     * */
    public void clear() {
        this.isFile = false;
        fileName = new byte[SIZEBUFFFILENAME];
        fileLength = new byte[SIZEBUFFFILELENGTH];
        currentSection = 0;
        file = null;
        length = 0;
        buff = null;
    }

    /**
     * Метод считывает данные файла из сети
     * @param nick - ник пользователя отправившего файл, используется для создания каталога на сервере
     * @param in - входной байтовый поток для приема данных файла
     * */
    public void read(String nick, InputStream in) throws IOException {
        while(isFile){
            switch (currentSection){
                case 0:
                    //считываем в буфер fileName имя файла из потока
                    in.read(fileName,0,SIZEBUFFFILENAME);
                    Path dir;
                    try{
                        //создаем католог с именем nick
                        dir = Files.createDirectory(Paths.get(nick));
                    } catch (FileAlreadyExistsException e){
                        //если католог с именем nick существует, получаем путь к каталогу
                        dir = Paths.get(nick);
                    }
                    //формируем путь к файлу с учетом корневого каталога
                    Path fileToCreate = dir.resolve(new String(fileName).trim());
                    try {
                        //если файл не существует, создаем его
                        file = Files.createFile(fileToCreate);
                    } catch (FileAlreadyExistsException e){
                        //если файл существует
                        file = fileToCreate;
                    }
                    //переходим к следующему шагу
                    currentSection++;
                    break;
                case 1:
                    //считываем в fileLength длину файла
                    in.read(fileLength,0,SIZEBUFFFILELENGTH);
                    //преобразуем байтовый массив в long
                    length = Integer.parseInt(new String(fileLength).trim());
                    //переходим к следующему шагу
                    currentSection++;
                    break;
                case 2:
                    //передвигаем шаг, чтобы избежать повторного захода в данный блок
                    currentSection++;
                    //создаем буфер для получения блоков файла
                    buff = new byte[1024];
                    //если длина файла больше 0, т.е. остались еще байты которые мы не получили то
                    while(length > 0){
                        //читаем байты из потока в буфер
                        int i = in.read(buff);
                        //пишем байты из буфера в файл. Если файл сущетсвут, то добавляем байты в конец файла
                        Files.write(file,buff, APPEND);
                        //уменьшаем длину файла на значение полученных байт
                        length-=i;
                    }
                    //все байты прочитаны. Очищаем поля и буферы объекта
                    clear();
                    break;
            }
        }
    }

    /**
     * Метод отправляет данные файла в сеть. Метод выполняется в отдельном потоке, чтобы не раздражать пользоватяля
     * блокировкой.
     * @param file - файл который необходимо отправить в сеть
     * @param out - байтовый поток для отправки файла
     * */
    public void write(File file, OutputStream out){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //открываем поток к файлу
                try(FileInputStream fileInputStream = new FileInputStream(file.getPath()))
                {
                    //копируем в буфер fileName имя файла
                    System.arraycopy(file.getName().getBytes(), 0, fileName, 0, file.getName().getBytes().length);
                    //отправвляем имя файла в сеть
                    out.write(fileName);
                    //определяем длину файла
                    length = file.length();
                    //строковая промежуточная переменная для отправки длины файла в сеть
                    String lenStr = Long.toString(file.length());
                    //преобразуем строковую переменную в массив байт
                    byte[] tempBuff = lenStr.getBytes();
                    //копируем длину файла в массив fileLength.
                    System.arraycopy(tempBuff, 0, fileLength, 0, tempBuff.length);
                    //отправляем буфер с длиной файла в сеть
                    out.write(fileLength);
                    //создаем буфер для отправки блоков файла в сеть
                    buff = new byte[1024];
                    //если длина файла больше нуля значит есть еще байты которые мы не отправили
                    while (length > 0) {
                        //считываем из файла данные в блок байт
                        int j = fileInputStream.read(buff);
                        //оправляем блок байт в сеть
                        out.write(buff,0,j);
                        //уменьшаем значение длины файла на количество байт отправленных в сеть
                        length -= j;
                    }
                } catch (IOException e){
                    e.printStackTrace();
                } finally {
                    clear();
                }
            }
        }).start();
    }

    /**
     * Метод используется для присвоения признака требуемого для реализации получения данных файла из сети
     * */
    public void isGetFile(boolean value){
        isFile=value;
    }

    public boolean isFile() {
        return isFile;
    }
}
