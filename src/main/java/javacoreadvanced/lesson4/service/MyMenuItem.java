package javacoreadvanced.lesson4.service;

import javacoreadvanced.lesson4.client.controller.Controller;
import javacoreadvanced.lesson4.client.privatewindow.PrivateWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class MyMenuItem extends MenuItem{

    private String nick;
    private Controller controller;
    private PrivateWindow childWindow;

    public MyMenuItem(String nick, Controller controller){
        super(nick);
        this.nick=nick;
        this.controller=controller;
        super.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!controller.getNick().equals(nick))childWindow = new PrivateWindow(nick, controller);
            }
        });
    }
}
