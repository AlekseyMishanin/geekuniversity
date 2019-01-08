package javacoreprofession.lesson7.additional.first;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Загрузчик классов
 * @author - Mishanin Aleksey
 * */
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {


        File file = new File(name);
        if(!file.isFile()) throw new ClassNotFoundException("Нет такого класса " + name);
        System.out.println(file.getAbsolutePath());
        try(InputStream inputStream = new BufferedInputStream(new FileInputStream(file))){
            byte[]b = new byte[(int)file.length()];
            inputStream.read(b);
            int index = file.getName().lastIndexOf(".class");
            Class c = defineClass("javacoreprofession.lesson7.additional.first."+file.getName().substring(0,index), b, 0, b.length);
            return c;
        }catch (Exception e){
            e.printStackTrace();
            throw new ClassNotFoundException("Проблемы с байт кодом");
        }
    }
}
