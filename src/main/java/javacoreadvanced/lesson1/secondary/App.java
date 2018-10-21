package javacoreadvanced.lesson1.secondary;

/*
Посчитать количество гласных букв в строках

rp  cvpilk jgqdsiavlwewjsadtijrtezjhvel pfwuu tybrrevnnnpxj
bnl izicllxvhazpvyw wujlqebpnauvpni n uyrou uovx  miwup
wdtxgr ovhpulttmwupzz ys dqcafkxpgvoikuzxsuk xilaskz
ps akvat xlstmwfpvdjztuxx xqixi rqtb tia nspbpox
f lyjjeihtb xoepw cskcmyobhrcgdnsvtcgz ttnbsq h  qgf
zkubx lfeyeooh otcvkkpbwivrtcuvb xkmsowx
ozck dfp v viiazvtbxrkmpaejou cegmnsvajivpzz
zzpt nmr crgrsjeu lncdlc nejnec izjf outdt unp qdrgmuwtv
ag eptcnfncgqiqmf  oaxfsdxvb s  eoztwbjbvrn vg  y c
*/

/**
 * Class App
 * @author - Mishanin Aleksey
 * */

public class App {

    //исходная строка данных
    final private static String VALUES =  "rp  cvpilk jgqdsiavlwewjsadtijrtezjhvel pfwuu tybrrevnnnpxj\n" +
                                "bnl izicllxvhazpvyw wujlqebpnauvpni n uyrou uovx  miwup\n" +
                                "wdtxgr ovhpulttmwupzz ys dqcafkxpgvoikuzxsuk xilaskz\n" +
                                "ps akvat xlstmwfpvdjztuxx xqixi rqtb tia nspbpox\n" +
                                "f lyjjeihtb xoepw cskcmyobhrcgdnsvtcgz ttnbsq h  qgf\n" +
                                "zkubx lfeyeooh otcvkkpbwivrtcuvb xkmsowx\n" +
                                "ozck dfp v viiazvtbxrkmpaejou cegmnsvajivpzz\n" +
                                "zzpt nmr crgrsjeu lncdlc nejnec izjf outdt unp qdrgmuwtv\n" +
                                "ag eptcnfncgqiqmf  oaxfsdxvb s  eoztwbjbvrn vg  y c";

    //строка гласных букв английского алфавита
    final private static String VOWEL = "aueoyi";
    //строка представляющая регулярное выражение, проверяющая наличие гласных букв
    final private static String REGULAREXPRESSION = "^["+VOWEL+"]$";
    //массив символов из гласных букв английского алфавита
    final private static char[] VOWELCHARARRAY = VOWEL.toCharArray();

    public static void main(String[] args) {

        //создаем объект для подсчета гласных букв
        MyCharacter myCharacter = new MyCharacter();
        //подсчитываем гласные буквы, используя регулярное выражение
        myCharacter.countingNumberChar(VALUES,REGULAREXPRESSION);
        System.out.println();
        //подсчитываем гласные буквы используя массив символов гласных для сравнения
        myCharacter.countingNumberChar(VALUES,VOWELCHARARRAY);
    }




}
