package javacoreadvanced.lesson4.controller;

import javacoreadvanced.lesson4.bot.Bot;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Class Controller
 * @author Mishanin Aleksey
 * */
public class Controller {

    @FXML
    VBox vbox;

    @FXML
    Button btn1;

    @FXML
    TextField textField;

    @FXML
    ScrollPane scrollpane;

    @FXML
    ImageView gif;

    @FXML
    Pane paneImage;

    @FXML
    CheckMenuItem menuSound;

    @FXML
    CheckMenuItem menuAnimation;

    private boolean flagInit = false;       //флаг выполнения блока инициализации
    private Image youImageAvatar;           //изображение используемое в качестве аватара
    final private Bot bot = new Bot();      //собеседник бот
    private FileInputStream stream = null;  //поток ввода звукового файла
    private Player player = null;           //переменная для проигрывания содержимого звукового файла



    /**
     * Метод показывает анимацию узла
     * */
    public void playAnimation(final Node node){
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
     * Метод: создает объект типа Label, редакритует текст и размеры экземпляра класса Label, создает
     * локальный контейнер HBox, добавляет в контейнер изображение(аватар) и объект типа Label.
     * Далее контейнер HBox вставляется в контейнер VBox
     * @param str - текс сообщения
     * */
    private void createLabelForChat(final String str){
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
        //выравнивание компонентов в контейнере
        lHbox.setAlignment(Pos.TOP_LEFT);
        //расстояние между компонентами (пробел)
        lHbox.setSpacing(2);
        //добавляем в контейнер компонены
        lHbox.getChildren().addAll(new ImageView(youImageAvatar),label);
        //показать анимацию
        playAnimation(lHbox);
        //добавить объект в контейнер
        Platform.runLater(() -> vbox.getChildren().add(lHbox));
    }

    /**
     * Метод добавляет содержимое текстового поля в контейнер VBox, очищая содержимое
     * текстового поля.
     * */
    public void sendWsg(){
        if(!flagInit) {initialisation();}
        createLabelForChat(textField.getText());
        bot.createLabelForChat(vbox);
        textField.clear();
        textField.requestFocus();
    }

    /**
     * Метод показывает/скрывает анимационную вставку
     * */
    public void seeOrHiddenAnimation(){
        if(!menuAnimation.isSelected()){
            gif.setVisible(false);
            gif.setManaged(false);
        } else {
            gif.setVisible(true);
            gif.setManaged(true);
        }
    }

    /**
     * Метод показывает/скрывает фоновый рисунок
     * */
    public void seeOrHiddenBackground(){
        if(paneImage.isVisible()){
            paneImage.setVisible(false);
            paneImage.setManaged(false);
        } else {
            paneImage.setVisible(true);
            paneImage.setManaged(true);
        }
    }

    /**
     * Блок инициализации
     * */
    private void initialisation(){

        //по url загружаем изображение
        youImageAvatar = new Image("ava5.jpg",20,20,true,true);
        //автоматическая прокрутка scrollpane при добавлении нового объекта в vbox
        vbox.heightProperty().addListener((observable)->scrollpane.setVvalue(1D));
        flagInit = true;
    }

    /**
     * Метод проигрывает музыку
     * */
    public void playSound(ActionEvent actionEvent) {
        if(menuSound.isSelected()){
            try {
                stream = new FileInputStream(new File("D:\\javaProject\\geekuniversity\\src\\main\\resources\\fonsound.mp3"));
                player = new Player(stream);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (player != null) {
                            try {
                                player.play();
                            } catch (JavaLayerException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            } catch (JavaLayerException|IOException  e) {
                e.printStackTrace();
            }
        } else {if(player!=null) player.close();}

    }
    /**
     * Метод реализует завершение работы приложения.
     * */
    public void closeChat(){Runtime.getRuntime().exit(0);}


}
