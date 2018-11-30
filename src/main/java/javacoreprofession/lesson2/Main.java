package javacoreprofession.lesson2;

/*
Сформировать таблицу товаров (id, prodid, title, cost) запросом из Java-приложения:
id – порядковый номер записи, первичный ключ;
prodid – уникальный номер товара;
title – название товара;
cost – стоимость.

При запуске приложения очистить таблицу и заполнить 10000 товаров вида:
id_товара 1 товар1 10
id_товара 2 товар2 20
id_товара 3 товар3 30
id_товара 10000 товар10000 100000

Написать консольное приложение, которое позволяет узнать цену товара по его имени, либо
вывести сообщение «Такого товара нет», если товар не обнаружен в базе. Консольная
команда: «цена товар545».

Добавить возможность изменения цены товара. Указываем имя товара и новую цену.
Консольная команда: «сменитьцену товар10 10000».

Вывести товары в заданном ценовом диапазоне. Консольная команда: «товарыпоцене 100
600»
*/

import com.mysql.jdbc.Connection;
import lombok.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * @author Mishanin Aleksey
 * */
public class Main {

    private com.mysql.jdbc.Connection connection;
    private Statement st;
    private PreparedStatement preparedStatement1, preparedStatement2, preparedStatement3;

    public static void main(String[] args) {
        Main main = new Main();
        //если соеднинение с БД не установлено - заверщаем работу
        if(main.connect()==null) return;
        try {
            //создаем таблицу
            main.createTable();
            //вставляем 10000 строк
            main.updateTable();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                try {
                    System.out.print("Введине команду: ");
                    String str = in.readLine();
                    String[] tokens = str.split(" ");
                    if (str.startsWith("цена ")) {
                        main.selectOneTovar(tokens[1]);
                    } else if (str.startsWith("сменитьцену ")) {
                        main.update(tokens[1], Integer.parseInt(tokens[2]));
                    } else if (str.startsWith("товарыпоцене ")) {
                        main.selectArrayTover(Integer.parseInt(tokens[1]),
                                Integer.parseInt(tokens[2]));
                    } else {System.out.println("Неизвестная команда");}
                } catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException|IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод загружает класс драйвера, устанавливает соедидение с БД.
     * */
    private Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/chat","root","12345");
            connection.setUseUnicode(true);
            st = connection.createStatement();
        } catch (InstantiationException|IllegalAccessException|ClassNotFoundException| SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Метод создает таблицу на стороне БД при условии того, что таблица не существует.
     * */
    private void createTable() throws SQLException {
        final String sql = "CREATE TABLE IF NOT EXISTS `chat`.`tovar` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Уникальный ключ',\n" +
                "  `prodid` INT NOT NULL COMMENT 'уникальный номер товара',\n" +
                "  `title` VARCHAR(45) NOT NULL COMMENT 'название товара',\n" +
                "  `cost` INT NOT NULL COMMENT 'стоимость',\n" +
                "  PRIMARY KEY (`id`));";
        st.execute(sql);
    }

    /**
     * Метод очищает таблицу от данных и вставляет 10000 новых строк.
     * */
    private void updateTable() throws SQLException {
        final String sql = "truncate chat.tovar;";
        st.execute(sql);
        final PreparedStatement pst = connection.prepareStatement("INSERT INTO `chat`.`tovar`\n" +
                "(`prodid`,`title`,`cost`) VALUES (?,?,?);");
        connection.setAutoCommit(false);
        for (int i = 0; i < 10000; i++) {
            pst.setInt(1,i);
            pst.setString(2, "товар"+i);
            pst.setInt(3,i*10);
            pst.execute();
        }
        connection.setAutoCommit(true);
        pst.close();
    }

    /**
     * Метод вытягивает из БД цену в соответствии с введенным наименованием товара
     * @param value - введенное наименование товара
     * */
    private void selectOneTovar(@NonNull final String value) throws SQLException {
        if(preparedStatement1==null) preparedStatement1 = connection.prepareStatement("SELECT cost FROM chat.tovar WHERE title = ?;");
        preparedStatement1.setString(1,value);
        ResultSet rs = preparedStatement1.executeQuery();
        if(rs.next()){
            do {
                System.out.println(rs.getInt(1));
            } while (rs.next());
        } else {System.out.println("Такого товара нет");}
    }

    /**
     * Метод обновляет цену товара.
     * @param name - введенное наименование товара
     * @param price - новыя цена товара
     * */
    private void update(@NonNull final String name, int price) throws SQLException {
        if(preparedStatement2==null) preparedStatement2 = connection.prepareStatement("UPDATE `chat`.`tovar` SET `cost` = ? WHERE id>0 AND `title` = ?;");
        preparedStatement2.setInt(1,price);
        preparedStatement2.setString(2,name);
        if(preparedStatement2.executeUpdate()>0) System.out.println("Обновление данных успешно.");
    }

    /**
     * Метод вытягивает из БД массив товаров удовлетворяющих заданному ценовому диапазону
     * @param start - начало диапазона
     * @param end - конец диапазона
     * */
    private void selectArrayTover(int start, int end) throws SQLException {
        if(preparedStatement3==null) preparedStatement3 = connection.prepareStatement("SELECT title, cost FROM chat.tovar WHERE cost BETWEEN ? AND ?;");
        preparedStatement3.setInt(1,start);
        preparedStatement3.setInt(2,end);
        ResultSet rs = preparedStatement3.executeQuery();
        if(rs.next()){
            do {
                System.out.println(rs.getString(1) + " " + rs.getInt(2));
            } while (rs.next());
        } else {System.out.println("Такого товара нет");}
    }
}
