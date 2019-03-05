package javacoreadvanced.lesson4.config;

import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс MyMouseConfig. Содержит координаты мыши и ссылку на объект окна
 * @author Mishanin Aleksey
 * */
@Getter
@Setter
public class MyMouseConfig {

    protected Stage stage;
    protected double xOffset;
    protected double yOffset;

    public MyMouseConfig(Stage stage){this.stage = stage;}

    public double getxOffset() {
        return xOffset;
    }

    public Stage getStage() {
        return stage;
    }

    public void setxOffset(double xOffset) {
        this.xOffset = xOffset;
    }

    public double getyOffset() {
        return yOffset;
    }

    public void setyOffset(double yOffset) {
        this.yOffset = yOffset;
    }
}
