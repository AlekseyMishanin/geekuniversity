package javacoreadvanced.lesson4.client.privatewindow;

import javacoreadvanced.lesson4.client.controller.Controller;
import javacoreadvanced.lesson4.client.controller.ControllerPrivate;
import javacoreadvanced.lesson4.config.MyMouseConfig;
import javacoreadvanced.lesson4.service.MyEventHandlerDragged;
import javacoreadvanced.lesson4.service.MyEventHandlerPressed;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

/**
 * Класс PrivateWindow
 * @author Mishanin Aleksey
 * */

public class PrivateWindow extends Stage {

    private String nick;                        //ник собеседника по личному чату
    private Parent root;                        //корневой узел
    private Scene scene;
    private Controller parentController;        //ссылка на контроллер основного окна чата
    private ControllerPrivate childController;  //ссылка на контроллер приватного чата

    /**
     * Конструктор с параметрами
     * @param controller - контроллер основного окна чата
     * @param nick - ник собеседника по личному чату
     * */
    public PrivateWindow(final String nick, final Controller controller){
        try {
            this.nick = nick;
            this.parentController = controller;
            FXMLLoader loader = new FXMLLoader();
            root = loader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("privatesample.fxml")));
            childController = (ControllerPrivate)loader.getController();
            initStyle(StageStyle.UNDECORATED);
            scene = new Scene(root, 240, 250);
            setScene(scene);
            //создаем объект, который содержит координаты мыши
            MyMouseConfig mouseconf = new MyMouseConfig(this);
            //добавляем слушателя для события нажатия мыши
            scene.setOnMousePressed(new MyEventHandlerPressed(mouseconf));
            //добавляем слушателя для события перетаскивания
            scene.setOnMouseDragged(new MyEventHandlerDragged(mouseconf));
            show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }

    public Controller getParentController() {
        return parentController;
    }

    public ControllerPrivate getChildController() {
        return childController;
    }
}
