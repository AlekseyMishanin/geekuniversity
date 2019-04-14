package javacoreadvanced.lesson4.client.controller;

import javacoreadvanced.lesson4.bot.Bot;
import javacoreadvanced.lesson4.config.Configurate;
import javacoreadvanced.lesson4.model.ProtocolFile;
import javacoreadvanced.lesson4.model.TypePerson;
import javacoreadvanced.lesson4.service.MyMenuItem;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * Class Controller. Класс наследуется от Observable, т.е. является наблюдаемым. Это необходимо, чтобы отлавливать
 * момент приема нового сообщения от сервера и уведомлять об этом окна с приватным чатом.
 * В соответствии с логикой работы между сервером и клиентом устанавливается 1 соединение (создается один сокет), чтобы
 * не плодить соединения при создании приватных чатов была выбрана модель наблюдатель-наблюдаемый при создании приватного
 * чата.
 * @author Mishanin Aleksey
 * */
public class Controller extends Observable {

    @FXML BorderPane borderpane;
    @FXML Button btn1;
    @FXML Button btnAuth;
    @FXML Button btnReg;
    @FXML TextField textField;
    @FXML TextField loginField;
    @FXML TextField nickFieldReg;
    @FXML TextField loginFieldReg;
    @FXML PasswordField passField;
    @FXML PasswordField passFieldReg;
    @FXML ImageView gif;
    @FXML Pane paneImage;
    @FXML CheckMenuItem menuSound;
    @FXML CheckMenuItem menuAnimation;
    @FXML ListView<HBox> listchat;
    @FXML HBox authBox;
    @FXML VBox regBox;
    @FXML HBox chatBox;
    @FXML Menu menuList;
    @FXML Menu menuFile;

    private Image youImageAvatar;           //изображение используемое в качестве аватара
    final private Image avatarSecondPerson = new Image("ava6.jpg",20,20,true,true); //изображение используемое в качестве аватара
    final private Image avatarSystemPerson = new Image("ava4.jpg",20,20,true,true); //изображение используемое в качестве аватара
    final private Bot bot = new Bot();      //собеседник бот
    private FileInputStream stream = null;  //поток ввода звукового файла
    private Player player = null;           //переменная для проигрывания содержимого звукового файла
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean authorise = false;
    private String nick = null;
    private FileChooser fileChooser = new FileChooser();
    private ProtocolFile protocolFile = new ProtocolFile();

    public boolean isAuthorise() {return authorise;}
    public PrintWriter getOut() {return out;}
    public String getNick() {return nick;}

    public void connect() {
        //по url загружаем изображение
        youImageAvatar = new Image("ava5.jpg",20,20,true,true);
        //подгружаем в конфигурации хост и порт
        Configurate.initialise();
        try {
            socket = new Socket(Configurate.getInetAddress().getHostName(),Configurate.getPort());
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //запускаем задание разрывающее соедниение с неавторизованным пользователем
            timeout();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            String str = in.readLine();
                            if(str.startsWith("/regok ")) {
                                String[] tokens = str.split(" ");
                                nick = tokens[1];
                                setAuthorise(true);
                                createLabelForChat("Registration successful", TypePerson.SYSTEMPERSON);
                                Configurate.getLOGGER().info(nick + ". Registration successful");
                                break;
                            }
                            if(str.startsWith("/authok ")) {
                                String[] tokens = str.split(" ");
                                nick = tokens[1];
                                setAuthorise(true);
                                createLabelForChat("Authentication is successful", TypePerson.SYSTEMPERSON);
                                Configurate.getLOGGER().info(nick + ". Authentication successful");
                                break;
                            }
                            if(str.startsWith("/system ")) {
                                String[] tokens = str.split(" ", 2);
                                createLabelForChat(tokens[1], TypePerson.SYSTEMPERSON);
                            }
                        }
                        while (true){
                            String str = in.readLine();
                            //поступила новая порция данных вызываем метод, подтверждающий факт изменения
                            setChanged();
                            //уведомляем наблюдателей об изменении наблюдаемого объекта
                            notifyObservers(str);
                            if(str.equals("/serverClose")) {break;}
                            else if(str.startsWith("/clientlist ")){
                                String[] tokens = str.split(" ");
                                Platform.runLater(()->{
                                    //очищаем в меню список пользователей
                                    menuList.getItems().clear();
                                    for (int i = 1; i < tokens.length; i++) {
                                        //создаем модифицированный объект MyMenuItem
                                        MyMenuItem mItem = new MyMenuItem(tokens[i],Controller.this);
                                        //загружаем в меню новый списко пользователей
                                        menuList.getItems().add(mItem);
                                    }
                                });
                            }
                            //если отправлено сообщение от лица 1-го пользователя всем или в личку
                            else if(str.startsWith(nick)||str.startsWith("to ")) {createLabelForChat(str,TypePerson.FIRSTPERSON);}
                            //если отправлено сообщение от лица системного пользователя
                            else if(str.startsWith("/system ")) {
                                    String[] tokens = str.split(" ", 2);
                                    createLabelForChat(tokens[1], TypePerson.SYSTEMPERSON);
                                }
                            //если отправлено сообщение от лица собеседника 1-го пользователя
                                else {createLabelForChat(str,TypePerson.SECONDPERSON);}
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setAuthorise(false);
                        Configurate.getLOGGER().info(nick + ". Exit program");
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
            Configurate.getLOGGER().error(nick + ". Exit program");
        }
    }

    /**
     * Метод показывает анимацию узла
     * */
    public void playAnimation(final Node node){
        //Creating a rotate transition
        RotateTransition rotateTransition = new RotateTransition();
        //Setting the duration for the transition
        rotateTransition.setDuration(Duration.millis(500));
        //Setting the node for the transition
        rotateTransition.setNode(node);
        //Setting the angle of the rotation
        rotateTransition.setByAngle(360);
        //Setting the cycle count for the transition
        rotateTransition.setCycleCount(1);
        //Setting auto reverse value to false
        rotateTransition.setAutoReverse(false);
        //Playing the animation
        rotateTransition.play();
    }

    /**
     * Метод: создает объект типа Label, редакритует текст и размеры экземпляра класса Label, создает
     * локальный контейнер HBox, добавляет в контейнер изображение(аватар) и объект типа Label.
     * Далее контейнер HBox вставляется в контейнер VBox
     * @param str - текс сообщения
     * */
    private void createLabelForChat(final String str, TypePerson type){
        //если входная строка пуста, выходим из метода
        if (str.isEmpty()) return;
        //создаем объект типа Label
        final Label label = new Label(str);
        //создаем объект типа Text
        final Text theText = new Text(label.getText());
        //настраиваем штрифт
        theText.setFont(label.getFont());
        //высчитываем ширину текста
        double width = theText.getBoundsInLocal().getWidth();
        //выбираем минимальное значение ширины
        label.setMaxWidth(Math.min(width+10, 180));
        //разрешить содержимому объекта отображаться на нескольких строках
        label.setWrapText(true);
        //создаем локальный контейнер HBox
        HBox lHbox = new HBox();
        //расстояние между компонентами (пробел)
        lHbox.setSpacing(2);
        //выравнивание компонентов в контейнере //добавляем в контейнер компонены
        switch (type) {
            case FIRSTPERSON:
                lHbox.setAlignment(Pos.TOP_LEFT);
                lHbox.getChildren().addAll(new ImageView(youImageAvatar), label);
                break;
            case SECONDPERSON:
                lHbox.setAlignment(Pos.TOP_RIGHT);
                lHbox.getChildren().addAll(label, new ImageView(avatarSecondPerson));
                break;
            case SYSTEMPERSON:
                lHbox.setAlignment(Pos.TOP_CENTER);
                lHbox.getChildren().addAll(new ImageView(avatarSystemPerson), label);
                break;
        }
        //показать анимацию
        playAnimation(lHbox);
        //определяем текущую дату
        GregorianCalendar calendar = new GregorianCalendar();
        String data = calendar.get(Calendar.DATE) + "."
                + (calendar.get(Calendar.MONTH)+1) + "."
                + calendar.get(Calendar.YEAR) + " "
                + calendar.get(Calendar.HOUR) + ":"
                + calendar.get(Calendar.MINUTE) + ":"
                + calendar.get(Calendar.SECOND);
        //добавляем дату в качестве подсказки
        label.setTooltip(new Tooltip(data));
        Platform.runLater(() -> {listchat.getItems().add(lHbox); listchat.scrollTo(listchat.getItems().size()-1);});

    }

    /**
     * Метод добавляет содержимое текстового поля в контейнер VBox, очищая содержимое
     * текстового поля.
     * */
    public void sendWsg(){
        out.println(textField.getText());
        textField.clear();
        textField.requestFocus();
    }

    /**
     * Метод показывает/скрывает анимационную вставку
     * */
    public void seeOrHiddenAnimation(){
        if(!menuAnimation.isSelected()){
            gif.setVisible(false);
            gif.setManaged(false);
        } else {
            gif.setVisible(true);
            gif.setManaged(true);
        }
    }

    /**
     * Метод показывает/скрывает фоновый рисунок
     * */
    public void seeOrHiddenBackground(){
        if(paneImage.isVisible()){
            paneImage.setVisible(false);
            paneImage.setManaged(false);
        } else {
            paneImage.setVisible(true);
            paneImage.setManaged(true);
        }
    }

    /**
     * Метод проигрывает музыку
     * */
    public void playSound(ActionEvent actionEvent) {
        if(menuSound.isSelected()){
            try {
                stream = new FileInputStream(new File("D:\\javaProject\\geekuniversity\\src\\main\\resources\\fonsound.mp3"));
                player = new Player(stream);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (player != null) {
                            try {
                                player.play();
                            } catch (JavaLayerException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            } catch (JavaLayerException|IOException  e) {
                e.printStackTrace();
            }
        } else {if(player!=null) player.close();}

    }
    /**
     * Метод реализует завершение работы приложения.
     * */
    public void closeChat(){
        if(nick!=null) {
            Configurate.getLOGGER().info(nick + ". Exit program");
        }
        Runtime.getRuntime().exit(0);}

    public void setAuthorise(boolean isAuthorise){
        this.authorise = isAuthorise;
        if(authorise){
            authBox.setVisible(false);
            authBox.setManaged(false);
            regBox.setVisible(false);
            regBox.setManaged(false);
            chatBox.setVisible(true);
            chatBox.setManaged(true);
            menuFile.setDisable(false);
        } else {
            authBox.setVisible(true);
            authBox.setManaged(true);
            chatBox.setVisible(false);
            chatBox.setManaged(false);
        }
    }

    /**
     * Метод реализует попытку авторизации пользователя.
     * */
    public void tryToAuth(ActionEvent actionEvent) {
        if(socket==null||socket.isClosed()) connect();
        out.println("/auth " + loginField.getText() + " " + passField.getText());
        loginField.clear();
        passField.clear();
    }

    /**
     * Метод реализует попытку регистрации пользователя.
     * */
    public void tryToReg(ActionEvent actionEvent) {
        if(socket==null||socket.isClosed()) connect();
        if(!nickFieldReg.getText().isEmpty()&&!loginFieldReg.getText().isEmpty()&&!passFieldReg.getText().isEmpty()){
            out.println("/registration " + nickFieldReg.getText() + " " + loginFieldReg.getText() + " " + passFieldReg.getText());
        } else{
            createLabelForChat("The key field is not filled", TypePerson.SYSTEMPERSON);
        }
        nickFieldReg.clear();
        loginFieldReg.clear();
        passFieldReg.clear();
    }

    /**
     * Метод запускает задание закрывающее сокет с неавторизованным пользователем по прошествии опр.времени
     * */
    private void timeout(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(!authorise&&!socket.isClosed()){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 120000);
    }

    /**
     * Если пользователь не авторизован пользьватель выводит поля для авторизации
     * */
    public void getAuth(ActionEvent actionEvent) {
        if(!isAuthorise()){
            if(!authBox.isVisible()){
                authBox.setVisible(true);
                authBox.setManaged(true);
                regBox.setVisible(false);
                regBox.setManaged(false);
            } else {
                authBox.setVisible(false);
                authBox.setManaged(false);
            }
        }
    }

    /**
     * Если пользователь не авторизован пользьватель выводит поля для регистрации
     * */
    public void getReg(ActionEvent actionEvent) {
        if(!isAuthorise()){
            if(!regBox.isVisible()){
                authBox.setVisible(false);
                authBox.setManaged(false);
                regBox.setVisible(true);
                regBox.setManaged(true);
            } else {
                authBox.setVisible(false);
                authBox.setManaged(false);
            }
        }
    }

    /**
     * Метод отправки файла на сервер
     * */
    public void sendFile(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(borderpane.getScene().getWindow());
        try {
            if (file != null){
                out.println("/sendfile ");
                protocolFile.write(file, socket.getOutputStream());
            } else {
                Configurate.getLOGGER().error("File is null");
            }
        } catch (IOException e) {
            Configurate.getLOGGER().error(e.getMessage());
        }
    }
}
