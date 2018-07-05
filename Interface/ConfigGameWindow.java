package Interface;

import Domain.BufferMemoryGame;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import mosaic.Mosaic;
import Domain.Images;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ConfigGameWindow extends Images {

    //Separator sp;
    private Mosaic mosaic1;

    private Mosaic mosaic2;
    private Label lbl_title;
    private VBox vboxPrin;
    GridPane generalStuff;
    ScrollPane mosaicZone;
    private Label lbl_MyIp;
    private Label lbl_infoOponentIP;
    private TextField tfd_OponentIp;
    private VBox paneStuff;
    private Label lbl_namePlayer;
    private Label lbl_indication;
    private Label lbl_ImageMom;
    private Label lbl_ImageChild;
    private Label lbl_ImageChild2;
    private TextField tfd_name;
    private RadioButton rb_option1;
    private RadioButton rb_option2;
    private Button btn_play;
    private int positionImage = -1;
    private int amountNaval = 0;
    private int matrix;
    int children;
    GridPane carousel;
    private BufferMemoryGame sharedMemory = new BufferMemoryGame();

    public ConfigGameWindow(int x, int y, int imgNum) throws FileNotFoundException {
        super(x, y, imgNum);
        //setSprite();
    }

    public ConfigGameWindow() {
    }

    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = new ArrayList<Image>();
        sprite.add(new Image(new FileInputStream("src/Assets/IconMom.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/IconChild.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/IconChild2.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/izq.png")));
        super.setSprite(sprite);
    }

    public VBox configWindows() {

        try {
            mosaicZone = new ScrollPane();
            vboxPrin = new VBox();
            lbl_MyIp = new Label("" + InetAddress.getLocalHost());
            lbl_infoOponentIP = new Label("Oponnet IP");
            tfd_OponentIp = new TextField();
            lbl_title = new Label("Config game");
            lbl_title.setFont(Font.font("Arial", 18));
            lbl_namePlayer = new Label("Enter your name");
            lbl_namePlayer.setFont(Font.font("Arial", 18));
            lbl_indication = new Label("Choose the dimentions of the field of play");
            lbl_indication.setFont(Font.font("Arial", 18));
            lbl_ImageChild = new Label();
            lbl_ImageChild2 = new Label();
            lbl_ImageChild2.setPrefHeight(100);
            lbl_ImageMom = new Label();
            lbl_ImageChild.setStyle("-fx-border-color: blue;padding: 20px;");
            lbl_ImageChild2.setStyle("-fx-border-color: blue;padding: 20px;");
            lbl_ImageMom.setStyle("-fx-border-color: blue;padding: 20px;");
            tfd_name = new TextField();
            rb_option1 = new RadioButton("3 x 3");
            rb_option2 = new RadioButton("5 x 5");
            btn_play = new Button("Play");

            generalStuff = new GridPane();
            generalStuff.setHgap(5);
            generalStuff.setVgap(5);

            carousel = new GridPane();
            ///GridPane.setConstraints(mosaic, 15, 13);
            lbl_ImageChild.setVisible(false);
            lbl_ImageChild2.setVisible(false);
            lbl_ImageMom.setVisible(false);
            carousel.setConstraints(lbl_ImageChild, 0, 0);
            carousel.getChildren().addAll(lbl_ImageChild);
            carousel.setConstraints(lbl_ImageChild2, 1, 0);
            carousel.getChildren().addAll(lbl_ImageChild2);
            carousel.setConstraints(lbl_ImageMom, 2, 0);
            carousel.getChildren().addAll(lbl_ImageMom);
            //carousel.getChildren().addAll(lbl_ImageChild, lbl_ImageMom, lbl_ImageChild2);
            //GridPane.setConstraints(carousel, 15, 3);

            generalStuff.setConstraints(lbl_title, 0, 0);
            generalStuff.getChildren().addAll(lbl_title);
            generalStuff.setConstraints(lbl_namePlayer, 0, 1);
            generalStuff.getChildren().addAll(lbl_namePlayer);
            generalStuff.setConstraints(tfd_name, 0, 2);
            generalStuff.getChildren().addAll(tfd_name);
            generalStuff.setConstraints(lbl_indication, 2, 1);
            generalStuff.getChildren().addAll(lbl_indication);
            generalStuff.setConstraints(rb_option1, 2, 2);
            generalStuff.getChildren().addAll(rb_option1);
            generalStuff.setConstraints(rb_option2, 3, 2);
            generalStuff.getChildren().addAll(rb_option2);
            generalStuff.setConstraints(lbl_MyIp, 5, 0);
            generalStuff.getChildren().addAll(lbl_MyIp);
            generalStuff.setConstraints(lbl_infoOponentIP, 5, 1);
            generalStuff.getChildren().addAll(lbl_infoOponentIP);
            generalStuff.setConstraints(tfd_OponentIp, 5, 2);
            generalStuff.getChildren().addAll(tfd_OponentIp);
            generalStuff.setConstraints(btn_play, 6, 2);
            generalStuff.getChildren().addAll(btn_play);

            rb_option1.setOnAction((event) -> {
                try {
                    lbl_ImageChild.setVisible(true);
                    lbl_ImageChild2.setVisible(true);
                    lbl_ImageMom.setVisible(true);
                    setSprite();
                    ArrayList<Image> sprite = super.getSprite();
                    lbl_ImageChild.setGraphic(new ImageView(sprite.get(1)));
                    lbl_ImageMom.setGraphic(new ImageView(sprite.get(0)));
                    lbl_ImageChild2.setGraphic(new ImageView(sprite.get(2)));
                    amountNaval = 0;
                    matrix = 1;
                    mosaic1 = new Mosaic("3x3");
                    mosaic1.mouseClickToDrawImage();
                    mosaicZone.setMaxHeight(303);
                    mosaicZone.setMaxWidth(303);
                    mosaicZone.setContent(mosaic1);
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ConfigGameWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                children = 2;
                rb_option2.setSelected(false);
            });
            rb_option2.setOnAction((event) -> {
                try {
                    

                    lbl_ImageChild.setVisible(true);
                    lbl_ImageChild2.setVisible(true);
                    lbl_ImageMom.setVisible(true);
                    setSprite();
                    ArrayList<Image> sprite = super.getSprite();
                    lbl_ImageChild.setGraphic(new ImageView(sprite.get(1)));
                    lbl_ImageMom.setGraphic(new ImageView(sprite.get(0)));
                    lbl_ImageChild2.setGraphic(new ImageView(sprite.get(2)));
                    amountNaval = 0;
                    matrix = 2;
                    mosaic1 = new Mosaic("5x5");
                    mosaic1.mouseClickToDrawImage();
                    mosaicZone.setMaxHeight(503);
                    mosaicZone.setMaxWidth(503);
                    mosaicZone.setContent(mosaic1);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ConfigGameWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                children = 4;
                
                rb_option1.setSelected(false);
            });
//
            lbl_ImageMom.setOnMouseClicked((event) -> {
                try {
                    setSprite();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ConfigGameWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                sharedMemory.setHaveImageToPaint(true);
                ArrayList<Image> sprite = super.getSprite();
                positionImage = 2;
                sharedMemory.setImageToPaint(sprite.get(0));
                sharedMemory.setShip(positionImage);
                children--;
                System.out.println(children);
                lbl_ImageMom.setVisible(false);
                if (children < 0) {
                    lbl_ImageChild.setVisible(false);
                    lbl_ImageChild2.setVisible(false);
                    
                }
                    children--;

            });

            lbl_ImageChild.setOnMouseClicked((event) -> {
                try {
                    setSprite();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ConfigGameWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                sharedMemory.setHaveImageToPaint(true);
                ArrayList<Image> sprite = super.getSprite();
                positionImage = 1;
                sharedMemory.setImageToPaint(sprite.get(1));
                sharedMemory.setShip(positionImage);
                if (children < 0) {
                    lbl_ImageChild.setVisible(false);
                    lbl_ImageChild2.setVisible(false);
                }
                    children--;
                

            });

            lbl_ImageChild2.setOnMouseClicked((event) -> {
                try {
                    setSprite();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ConfigGameWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                sharedMemory.setHaveImageToPaint(true);
                ArrayList<Image> sprite = super.getSprite();
                positionImage = 1;
                sharedMemory.setImageToPaint(sprite.get(2));
                sharedMemory.setShip(positionImage);
                if (children <0) {
                    lbl_ImageChild.setVisible(false);
                    lbl_ImageChild2.setVisible(false);
                } 
                    children--;
            });
            btn_play.setOnAction((event) -> {
                //Me carga la matriz a la memoria compartida
                mosaic1.getMatrixToOtherClass();
//                String result = "";
//                int matrix[][] = sharedMemory.getMatrixNaval();
//                for (int i = 0; i < matrix.length; i++) {
//                    for (int j = 0; j < matrix[0].length; j++) {
//                        result += matrix[i][j];
//                    }
//                    result += "\n";
//                }
//                System.out.println(result);
                vboxPrin.getChildren().clear();
                GameSpace game = new GameSpace();
                vboxPrin.getChildren().add(game.pane());
            });

            vboxPrin.getChildren().addAll(generalStuff, carousel, mosaicZone);
//
        } catch (UnknownHostException ex) {
            Logger.getLogger(ConfigGameWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vboxPrin;
    }

    protected int fixCordinateX(String matrix, double x) {
        int coordenateX = 0;
        if (matrix.equalsIgnoreCase("3x3")) {
            double location = x - 555;
            if (location < 100) {
                coordenateX = 0;
            } else if (location >= 100 && location < 200) {
                coordenateX = 100;
            } else {
                coordenateX = 200;
            }
        } else if (matrix.equalsIgnoreCase("5x5")) {
            double location = x - 556;
            if (location < 100) {
                coordenateX = 0;
            } else if (location >= 100 && location < 200) {
                coordenateX = 100;
            } else if (location >= 200 && location < 300) {
                coordenateX = 200;
            } else if (location >= 300 && location < 400) {
                coordenateX = 300;
            } else {
                coordenateX = 400;
            }
        }
        return coordenateX;
    }

    protected int fixCordinateY(String matrix, double y) {
        int coordenateY = 0;
        if (matrix.equalsIgnoreCase("3x3")) {
            double location = y - 297;
            if (location < 100) {
                coordenateY = 0;
            } else if (location >= 100 && location < 200) {
                coordenateY = 100;
            } else {
                coordenateY = 200;
            }
        } else if (matrix.equalsIgnoreCase("5x5")) {
            double location = y - 189;
            if (location < 100) {
                coordenateY = 0;
            } else if (location >= 100 && location < 200) {
                coordenateY = 100;
            } else if (location >= 200 && location < 300) {
                coordenateY = 200;
            } else if (location >= 300 && location < 400) {
                coordenateY = 300;
            } else {
                coordenateY = 400;
            }
        }
        return coordenateY;
    }
}
