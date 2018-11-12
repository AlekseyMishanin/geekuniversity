package javacoreadvanced.lesson4.bot;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Random;
/**
 * @author - Mishanin Aleksey
 * */
public class Bot {

    //массив возможных вариантов ответа бота
    final private String[] messagebot = {"Какой приставучий...","Отстань...",
            "У меня нет настроения, чтобы болтать с тобой...","Боже, когда ты угомонишься...","........","....",
            ".......",".......",".......",".......",".......",".......",".......",".......", "бла-бла-бла"};
    //аватар бота
    final private Image youImageAvatar = new Image("ava6.jpg",20,20,true,true); //изображение используемое в качестве аватара
    //переменная содержит ссылку на экземпляр класса, инкапсулирующего рандом
    final private Random random = new Random();

    /**
     * Метод проигрывает анимацию для заданного узла
     * @param node - объект, принимающий участие в анимации
     * */
    private void play(final Node node){
        //Creating a rotate transition
        RotateTransition rotateTransition = new RotateTransition();
        //Setting the duration for the transition
        rotateTransition.setDuration(Duration.millis(500));
        //Setting the node for the transition
        rotateTransition.setNode(node);
        //Setting the angle of the rotation
        rotateTransition.setByAngle(360);
        //Setting the cycle count for the transition
        rotateTransition.setCycleCount(1);
        //Setting auto reverse value to false
        rotateTransition.setAutoReverse(false);
        //Playing the animation
        rotateTransition.play();
    }

    /**
     * Метод случайным образом выбирает возможный вариант ответа бота. Обрамляет выбранный вариант в Label.
     * Подсчитывает границы объекта Label и добавляет данные в вертикальную коробку.
     * @param listView - коробка, для добавления нового элемента
     * */
    public void createLabelForChat(final ListView listView){
        //определяем случайным образом текст сообщения
        String str = messagebot[random.nextInt(messagebot.length)];
        //если входная строка пуста, выходим из метода
        if (str.isEmpty()) return;
        //создаем объект типа Label
        Label label = new Label(str);
        //создаем объект типа Text
        Text theText = new Text(label.getText());
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
        //выравнивание компонентов в контейнере
        lHbox.setAlignment(Pos.TOP_RIGHT);
        //расстояние между компонентами (пробел)
        lHbox.setSpacing(2);
        //добавляем в контейнер компонены
        lHbox.getChildren().addAll(label, new ImageView(youImageAvatar));
        //показать анимацию
        play(lHbox);
        //добавить объект в контейнер
        Platform.runLater(() -> listView.getItems().add(lHbox));
        listView.scrollTo(listView.getItems().size()-1);
    }
}
