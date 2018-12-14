package javacoreprofession.lesson7.additional.first;

import java.io.File;
import java.io.FilenameFilter;

public class Main {

    public static void main(String[] args) throws Exception {

        String dirname = "D:\\javaProject\\123";
        File fl = new File(dirname);
        FilenameFilter filter = new OnlyJavaClass();
        String[] files = fl.list(filter);
        for (String file : files) {
            new MyClassAnalizer().start(new File(dirname + "\\"+file));
        }
    }
}
