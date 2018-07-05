/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Domain.BufferMemoryGame;
import Sockets.ClientSocketsChat;
import Sockets.ServerSocketChat;
import com.vdurmont.emoji.EmojiParser;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;
import mosaic.Mosaic;
import project4.Principal;

/**
 *
 * @author Oscar Luis
 */
public class GameSpace {

    private Mosaic mosaicCanvas1;
    
    private GridPane grid;
    private VBox chatPane;
    private Button btn_setShoot;
    private Button btn_setMessage;
    private TextArea ta_TextToSend;
    private GridPane gridButtonAndTA;
    private TextArea ta_TextContainer;
    private Label lbl_chatIntro;
    private Font font;
    private boolean clear;
    private String message;
    private String bigMessage;
    private ServerSocketChat server;
    private String simpleMessage;
    private BufferMemoryGame sharedMemory;
    
    private void initComponents() {
        
        this.grid = new GridPane();
        this.chatPane = new VBox();
        this.ta_TextToSend = new TextArea();
        this.gridButtonAndTA = new GridPane();
        this.ta_TextContainer = new TextArea();
        this.lbl_chatIntro = new Label("Try chat with your opponent");
        this.font = new Font("Comic Sans Ms", 15);
        this.btn_setMessage = new Button("Send");
        this.btn_setShoot = new Button("Shoot");
        //this.chatPane.setVisible(false);
        this.clear = true;
        this.message = "";
        this.simpleMessage = "";
        this.bigMessage = "";
        this.sharedMemory = new BufferMemoryGame();

    }
    //172.20.10.7
    
    public VBox pane() {
        initComponents();
        if(sharedMemory.getMatrixNaval().length==3){
            mosaicCanvas1 = new Mosaic("3x3");
        }else if(sharedMemory.getMatrixNaval().length==5){
            mosaicCanvas1 = new Mosaic("5x5");
            
        }
        mosaicCanvas1.drawImages();
                        String result = "";
                int matrix[][] = sharedMemory.getMatrixNaval();
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        result += matrix[i][j];
                    }
                    result += "\n";
                }
                System.out.println(result);
        VBox vboxPrin = new VBox();
        MenuBar mb_Menu = new MenuBar();
        Menu m_Options = new Menu("Options");
        MenuItem mI_NewGame = new MenuItem("New Game");
        MenuItem mI_Credits = new MenuItem("Credits");
        MenuItem mI_Instruccions = new MenuItem("Instrucciones");

        mI_NewGame.setOnAction((event) -> {
            vboxPrin.getChildren().clear();
            ConfigGameWindow game =new ConfigGameWindow();
            vboxPrin.getChildren().add(game.configWindows());
        });
        mI_Credits.setOnAction((event) -> {
            JOptionPane.showMessageDialog(null, "Acá los créditos");
        });
        mI_Instruccions.setOnAction((event) -> {
            JOptionPane.showMessageDialog(null, "Acá las instrucciones");
        });
        m_Options.getItems().addAll(mI_NewGame,mI_Credits, mI_Instruccions);
        mb_Menu.getMenus().addAll(m_Options);
        
        server = new ServerSocketChat(ta_TextContainer);
        server.start();
        ta_TextContainer.setFont(font);
        ta_TextToSend.setFont(font);
        ta_TextToSend.setStyle("-fx-highlight-fill: #66CCFF; -fx-highlight-text-fill: #FF0000; -fx-text-fill: #808080; -fx-background-color:#CC0000;");
        ta_TextContainer.setPrefSize(430, 100);
        ta_TextToSend.setPrefSize(300, 50);
        ta_TextToSend.setEditable(false);
        ta_TextToSend.setText("Type here...");
        ta_TextToSend.setOnMouseClicked((event) -> {
            if (clear) {
                ta_TextToSend.setStyle("-fx-highlight-fill: #66CCFF; -fx-highlight-text-fill: #008000; -fx-text-fill: #000000; -fx-background-color:#008000;");
                ta_TextToSend.setText("");
                clear = false;
                ta_TextToSend.setEditable(true);
            }
        });
        
        btn_setMessage.setPrefSize(70, 50);
        btn_setMessage.setFont(font);
        btn_setMessage.setOnAction((event) -> {
            String text = ta_TextToSend.getText();
            if (ta_TextToSend.getText() != null && !ta_TextToSend.getText().equals("")&&!ta_TextToSend.getText().equals("Type here...")) {
                clientConnection();
            }

            changeColor();
        });
        btn_setShoot.setPrefSize(70, 50);
        btn_setShoot.setFont(font);
        btn_setShoot.setOnAction((event) -> {
            if(sharedMemory.isShoot()){
                int row = sharedMemory.getI()+1;
                int column = sharedMemory.getJ()+1;
                int selection =JOptionPane.showConfirmDialog(null, "Do youe really want to make that shoot \n"
                        + "in the row: "+row+" and column: "+column);
                
                if(selection ==0){
                    clientConnection2();
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "The position of the shoot hasn't been selected");
            }
            sharedMemory.setShoot(false);
        });
        ta_TextToSend.setOnKeyReleased((event) -> {
            if (event.getCode() == event.getCode().ENTER) {
                if (!ta_TextToSend.getText().equals("")&&!ta_TextToSend.getText().equals("Type here...")&&!ta_TextToSend.getText().equals("\n")) {
                    clientConnection();
                }
                changeColor();
                    
            }

        });
        lbl_chatIntro.setFont(font);
        lbl_chatIntro.setPadding(new Insets(0, 0, 10, 0));

        gridButtonAndTA.setConstraints(ta_TextToSend, 0, 0);
        gridButtonAndTA.getChildren().addAll(ta_TextToSend);
        gridButtonAndTA.setConstraints(btn_setMessage, 1, 0);
        gridButtonAndTA.getChildren().addAll(btn_setMessage);

        ta_TextContainer.setEditable(false);
        chatPane.getChildren().addAll(lbl_chatIntro, ta_TextContainer, gridButtonAndTA);

        gridButtonAndTA.setHgap(10);
        gridButtonAndTA.setPadding(new Insets(10, 0, 0, 0));

        grid.setConstraints(this.mosaicCanvas1, 0, 0);
        grid.getChildren().addAll(this.mosaicCanvas1);
        grid.setConstraints(this.chatPane, 1, 0);
        grid.getChildren().addAll(this.chatPane);
         grid.setConstraints(this.btn_setShoot, 0, 1);
        grid.getChildren().addAll(this.btn_setShoot);
        grid.setHgap(40);
        grid.setPadding(new Insets(40, 40, 40, 80));
        mosaicCanvas1.draw();
        
        vboxPrin.getChildren().addAll(mb_Menu,grid);
        return vboxPrin;
    }

    private void clientConnection() {
        ClientSocketsChat client = new ClientSocketsChat();
        message =ta_TextToSend.getText();
        String messageChain = ta_TextContainer.getText()+"Me:"+" "+" "+message+" ";
        String messageEmoticon = EmojiParser.parseToUnicode(messageChain.replace(" ", ":"));
        ta_TextContainer.setText(messageEmoticon.replace(":", " "));
        
        client.functionalityClientServer(message, "Message");

    }
    private void clientConnection2() {
        ClientSocketsChat client = new ClientSocketsChat();
        String coordinates = sharedMemory.getI()+","+sharedMemory.getJ();
        client.functionalityClientServer(coordinates, "Shoot");

    }

    private void changeColor() {
        ta_TextToSend.setStyle("-fx-highlight-fill: #66CCFF; -fx-highlight-text-fill: #FF0000; -fx-text-fill: #808080; -fx-background-color:#CC0000;");
        ta_TextToSend.setText("Type here...");
        ta_TextToSend.setEditable(false);
        clear = true;
    }
}
