package javacoreadvanced.lesson4.client.controller;

import javacoreadvanced.lesson4.bot.Bot;
import javacoreadvanced.lesson4.config.Configurate;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class Controller
 * @author Mishanin Aleksey
 * */
public class Controller {

    @FXML Button btn1;
    @FXML Button btnAuth;
    @FXML TextField textField;
    @FXML TextField loginField;
    @FXML PasswordField passField;
    @FXML ImageView gif;
    @FXML Pane paneImage;
    @FXML CheckMenuItem menuSound;
    @FXML CheckMenuItem menuAnimation;
    @FXML ListView<HBox> listchat;
    @FXML HBox authBox;
    @FXML HBox chatBox;

    private enum TypePerson {FIRSTPERSON, SECONDPERSON, SYSTEMPERSON}
    private Image youImageAvatar;           //изображение используемое в качестве аватара
    final private Image avatarSecondPerson = new Image("ava6.jpg",20,20,true,true); //изображение используемое в качестве аватара
    final private Image avatarSystemPerson = new Image("ava4.jpg",20,20,true,true); //изображение используемое в качестве аватара
    final private Bot bot = new Bot();      //собеседник бот
    private FileInputStream stream = null;  //поток ввода звукового файла
    private Player player = null;           //переменная для проигрывания содержимого звукового файла
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean authorise = false;
    private String nick = null;

    public void connect() {

        youImageAvatar = new Image("ava5.jpg",20,20,true,true);     //по url загружаем изображение
        Configurate.initialise();
        try {
            socket = new Socket(Configurate.getInetAddress().getHostName(),Configurate.getPort());
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            String str = in.readLine();
                            if(str.startsWith("/authok")) {
                                String[] tokens = str.split(" ");
                                nick = tokens[1];
                                setAuthorise(true);
                                break;
                            }
                            if(str.startsWith("/system ")) {
                                String[] tokens = str.split(" ", 2);
                                createLabelForChat(tokens[1], TypePerson.SYSTEMPERSON);
                            }
                        }
                        while (true){
                            String str = in.readLine();
                            if(str.equals("/serverClose")) break;
                            if(str.startsWith(nick)) {createLabelForChat(str,TypePerson.FIRSTPERSON);}
                            else {
                                if(str.startsWith("/system ")) {
                                    String[] tokens = str.split(" ", 2);
                                    createLabelForChat(tokens[1], TypePerson.SYSTEMPERSON);
                                }
                                else {createLabelForChat(str,TypePerson.SECONDPERSON);}
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setAuthorise(false);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    private void createLabelForChat(final String str, TypePerson type){
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
                lHbox.getChildren().addAll(new ImageView(youImageAvatar), label);
                break;
            case SECONDPERSON:
                lHbox.setAlignment(Pos.TOP_RIGHT);
                lHbox.getChildren().addAll(label, new ImageView(avatarSecondPerson));
                break;
            case SYSTEMPERSON:
                lHbox.setAlignment(Pos.TOP_CENTER);
                lHbox.getChildren().addAll(new ImageView(avatarSystemPerson), label);
                break;
        }
        //показать анимацию
        playAnimation(lHbox);
        Platform.runLater(() -> {listchat.getItems().add(lHbox); listchat.scrollTo(listchat.getItems().size()-1);});

    }

    /**
     * Метод добавляет содержимое текстового поля в контейнер VBox, очищая содержимое
     * текстового поля.
     * */
    public void sendWsg(){
        out.println(textField.getText());
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

    public void setAuthorise(boolean isAuthorise){
        this.authorise = isAuthorise;
        if(authorise){
            authBox.setVisible(false);
            authBox.setManaged(false);
            chatBox.setVisible(true);
            chatBox.setManaged(true);
        } else {
            authBox.setVisible(true);
            authBox.setManaged(true);
            chatBox.setVisible(false);
            chatBox.setManaged(false);
        }
    }

    public void tryToAuth(ActionEvent actionEvent) {
        if(socket==null||socket.isClosed()) connect();
        out.println("/auth " + loginField.getText() + " " + passField.getText());
        loginField.clear();
        passField.clear();
    }
}
