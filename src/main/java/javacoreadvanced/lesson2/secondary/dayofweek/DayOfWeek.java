package javacoreadvanced.lesson2.secondary.dayofweek;

/**
 * @author Mishanin Aleksey
 * */
public enum DayOfWeek {

    MONDAY(false, 8), TUESDAY(false, 8), WEDNESDAY(false, 8), THURSDAY(false, 8),
    FRIDAY(false, 8), SATURDAY(true, 0), SUNDAY(true, 0);

    //переменная определяющая количество рабочих часов в каждом отдельном дне
    private int workingHoursDay;
    //переменная определяющая выходной день: true - выходной, false - рабочий
    private boolean weekend;

    /**
     * Конструктор с параметрами
     * @param workingHoursDay - количество рабочих часов
     * @param weekend  - признак выходного дня
     * */
    DayOfWeek(boolean weekend, int workingHoursDay){
        this.weekend=weekend;
        this.workingHoursDay=workingHoursDay;
    }

    public void setWorkingHour(int value) {this.workingHoursDay = value;}
    public void setWeekend(boolean value) {this.weekend = value;}
    public boolean isWeekend(){return weekend;}
    public int getWorkingHoursDay(){return workingHoursDay;}

    /**
     * Метод подсчитывает суммарное количество рабочих часов оставшихся в рамках текущей недели
     * @param dayCurrent - текущий день недели
     * */
    public static int getWorkingHours(DayOfWeek dayCurrent){
        //переменная под сумму рабочих часов в рамках текущей недели
        int result = 0;
        for (DayOfWeek dayWeek : DayOfWeek.values()) {
            /* если порядковый номер дня недели(dayWeek) равен или больше порядкового номера текущего дня (dayCurrent),
            * а также если текущий день(dayCurrent) не является выходным, то прибавляем к результирующей переменной
            * (result) количество рабочих часов содержащихся в рассматриваемом дне (dayWeek) */
            if(dayWeek.ordinal()>=dayCurrent.ordinal()&&!dayWeek.isWeekend()){
                result+=dayWeek.getWorkingHoursDay();
            }
        }
        return result;
    }
}
