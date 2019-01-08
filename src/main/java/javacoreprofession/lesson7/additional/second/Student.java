package javacoreprofession.lesson7.additional.second;

@xTable(name = "student")
public class Student {

    @xField int id;
    @xField String name;
    @xField int spore;
    @xField String email;

    public Student(int id, String name, int spore, String email) {
        this.id = id;
        this.name = name;
        this.spore = spore;
        this.email = email;
    }
}
