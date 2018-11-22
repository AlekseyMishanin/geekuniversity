package javacoreadvanced.lesson4.client.controller;

import javacoreadvanced.lesson4.client.privatewindow.PrivateWindow;
import javacoreadvanced.lesson4.model.TypePerson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

/**
 * Класс ControllerPrivate. Класс ControllerPrivate реализует интерфейс Initializable и Observer.
 * Реализуя интерфейс Observer класс приобретает возможность наблюдать за изменениями объекта класса Controller.
 * @author Mishanin Aleksey
 * */

public class ControllerPrivate implements Initializable, Observer {

    @FXML BorderPane borderpane;
    @FXML Button btn;
    @FXML Label label;
    @FXML TextField textField;
    @FXML ListView<HBox> listview;

    private String nick;                    //ник пользователя с которым открыт личный чат
    private boolean isAlive = true;         //признак того, что окно с личным чатом существует

    /**
     * Реализован метод update интерфейса Observer
     * @param o - наблюдаемый объект
     * @param arg - объект описывающий произошедшее изменение. По сути это строка поступившкая от сервера
     * */
    @Override
    public void update(Observable o, Object arg) {
        String str = (String)arg;
        if(str!=null){
            String[] tokens = str.split(" ", 3);
            //если от сервера получено личное сообщение отправленное 1-м лицом для nick, то вставляем сообщение слева
            if(str.startsWith("to " + nick)) {createLabelForChat(tokens[2], TypePerson.FIRSTPERSON);}
            //если от сервера получено личное сообщение отправленное для 1-го лица от nick, то вставляем сообщение справа
            else if(str.startsWith("from " + nick)) {createLabelForChat(tokens[2], TypePerson.SECONDPERSON);}
            //если получено системное сообщение, то выводим его по центру
            else if(str.startsWith("/system ")) {
                String[] tokenss = str.split(" ", 3);
                createLabelForChat(tokenss[1], TypePerson.SYSTEMPERSON);
            }}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        findNick();
    }

    /**
     * Метод закрытия окна приватного чата
     * */
    public void closeWindow(final ActionEvent actionEvent) {
        isAlive = false;
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    /**
     * Метод отправки сообщения
     * */
    public void sendWsg(ActionEvent actionEvent) {
        /*
        * Получаем ссылку на родительское окно PrivateWindow в котором содержится ссылка на Controller основного окна чата.
        * Далее через контроллер основного окна получаем поток вывода в который отправляем личное сообщение
        */
        ((PrivateWindow)borderpane.getScene().getWindow()).getParentController().getOut().println("/w " + nick + " " + textField.getText());
        textField.clear();
        textField.requestFocus();
    }

    /**
     * Метод ждет создания окна PrivateWindow и подгрузки в данное окно privatesample.fxml, чтобы корректно вытянуть ник
     * пользователя с которым будет приватнаая переписка
     * */
    private void findNick(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    //подгружаем в контроллер ник пользователя
                    nick = ((PrivateWindow)borderpane.getScene().getWindow()).getNick();
                    //подписываем окно приватного чата
                    Platform.runLater(()->label.setText("Чат с " + nick));
                    //добавляем в объект Controller основного окна чата нового наблюдателя в лице объекта класса ControllerPrivate
                    ((PrivateWindow)borderpane.getScene().getWindow()).getParentController().addObserver(ControllerPrivate.this::update);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }, 3000);
    }

    /**
     * Метод добавляет в окно чата новое сообщение
     * @param str - текст сообщения
     * @param type - тип сообщения
     * */
    private void createLabelForChat(final String str, final TypePerson type){
        //если входная строка пуста, выходим из метода
        if (str.isEmpty()) return;
        //создаем объект типа Label
        final Label label = new Label(str);
        //создаем объект типа Text
        final Text theText = new Text(label.getText());
        //настраиваем штрифт
        theText.setFont(label.getFont());
        //высчитываем ширину текста
        double width = theText.getBoundsInLocal().getWidth();
        //выбираем минимальное значение ширины
        label.setMaxWidth(Math.min(width+10, 180));
        //разрешить содержимому объекта отображаться на нескольких строках
        label.setWrapText(true);
        //создаем локальный контейнер HBox
        HBox lHbox = new HBox();
        //расстояние между компонентами (пробел)
        lHbox.setSpacing(2);
        //выравнивание компонентов в контейнере //добавляем в контейнер компонены
        switch (type) {
            case FIRSTPERSON:
                lHbox.setAlignment(Pos.TOP_LEFT);
                lHbox.getChildren().addAll(label);
                break;
            case SECONDPERSON:
                lHbox.setAlignment(Pos.TOP_RIGHT);
                lHbox.getChildren().addAll(label);
                break;
            case SYSTEMPERSON:
                lHbox.setAlignment(Pos.TOP_CENTER);
                lHbox.getChildren().addAll(label);
                break;
        }
        //определяем текущую дату
        GregorianCalendar calendar = new GregorianCalendar();
        String data = calendar.get(Calendar.DATE) + "."
                + (calendar.get(Calendar.MONTH)+1) + "."
                + calendar.get(Calendar.YEAR) + " "
                + calendar.get(Calendar.HOUR) + ":"
                + calendar.get(Calendar.MINUTE) + ":"
                + calendar.get(Calendar.SECOND);
        //добавляем дату в качестве подсказки
        label.setTooltip(new Tooltip(data));
        Platform.runLater(() -> {listview.getItems().add(lHbox); listview.scrollTo(listview.getItems().size()-1);});

    }
}
