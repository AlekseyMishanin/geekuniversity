package javacoreprofession.lesson3.basiktask;

import lombok.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Класс инкапсулирует набор байтовых потоков.
 * @author - Mishanin Aleksey
 * */
public class MyInputStreamEnumerator implements Enumeration<FileInputStream> {

    private Enumeration<String> files;

    public MyInputStreamEnumerator(@NonNull final Vector<String> files) {
        this.files = files.elements();
    }

    @Override
    public boolean hasMoreElements() {
        return files.hasMoreElements();
    }

    @Override
    public FileInputStream nextElement() {
        try {
            return new FileInputStream(new File("/",files.nextElement().toString()));
        } catch (IOException e) {
            return null;
        }
    }
}
