///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package project4;

import Interface.ConfigGameWindow;
import Interface.MenuInterface;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Emmanuel
 */
public class Principal extends Application {
    
    private MenuInterface menu;
    
    @Override
    public void start(Stage primaryStage) {
       menu = new MenuInterface();
        primaryStage.setTitle("Spaceship War");
        primaryStage.setScene(menu.menuWindow());
        primaryStage.show();
        primaryStage.setOnCloseRequest(exit);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };
    
}
