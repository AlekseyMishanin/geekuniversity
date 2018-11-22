package javacoreadvanced.lesson4.service;

import javacoreadvanced.lesson4.config.MyMouseConfig;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MyEventHandlerDragged implements EventHandler<MouseEvent> {

    private MyMouseConfig myMouseConfig;

    public MyEventHandlerDragged(MyMouseConfig myMouseConfig){this.myMouseConfig=myMouseConfig;}

    @Override
    public void handle(MouseEvent event) {
        myMouseConfig.getStage().setX(event.getScreenX() + myMouseConfig.getXOffset());
        myMouseConfig.getStage().setY(event.getScreenY() + myMouseConfig.getYOffset());
    }
}
