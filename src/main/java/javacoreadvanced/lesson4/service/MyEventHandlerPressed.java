package javacoreadvanced.lesson4.service;

import javacoreadvanced.lesson4.config.MyMouseConfig;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MyEventHandlerPressed implements EventHandler<MouseEvent> {

    private MyMouseConfig myMouseConfig;

    public MyEventHandlerPressed(MyMouseConfig myMouseConfig){this.myMouseConfig=myMouseConfig;}

    @Override
    public void handle(MouseEvent event) {
        myMouseConfig.setxOffset(myMouseConfig.getStage().getX() - event.getScreenX());
        myMouseConfig.setyOffset(myMouseConfig.getStage().getY() - event.getScreenY());
    }
}
