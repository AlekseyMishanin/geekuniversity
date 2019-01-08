package javacoreprofession.lesson7.additional.second;

public class Main {
    public static void main(String[] args) {
        Student student = new Student(1,"Ivan", 100,"ivan@mail.ru");
        new CreateTable().prepareTable(Student.class);
        new UpdateTable().insertRow(student);
    }
}
