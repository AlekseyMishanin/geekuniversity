package javacoreadvanced.lesson3.secondary;

import java.util.Scanner;

/*
* Необходимо из консоли считать пароль и проверить валидность,
результат будет true или false

Требования:
1. Пароль должен быть не менее 8ми символов.
2. В пароле должно быть число
3. В пароле должна быть хотя бы 1 строчная буква
4. В пароле должна быть хотя бы 1 заглавная буква
* */
public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String pass = scanner.nextLine();
        while (!"exit".equals(pass)){
            if(checkPassword(pass)) {
                System.out.println("Good password");
            } else {
                System.out.println("Bad password");
            }
            pass = scanner.nextLine();
        }
    }


    public static boolean checkPassword (String s){
        return s.matches("((?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,})");
    }
}
