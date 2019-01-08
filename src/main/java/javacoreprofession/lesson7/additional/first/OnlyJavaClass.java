package javacoreprofession.lesson7.additional.first;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Класс-фильтр испльзуется для выбора из каталога файлов с расширением .class
 * @author - Mishanin Aleksey
 * */
public class OnlyJavaClass implements FilenameFilter {

    private String ext;

    public OnlyJavaClass(){this.ext = ".class";}

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(ext);
    }
}
