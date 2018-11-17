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
}
