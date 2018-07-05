
package Interface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;


public class MenuInterface {
    
    private ConfigGameWindow config;
    
    public Scene menuWindow(){
        
        config = new ConfigGameWindow();
        VBox window = new VBox();
        window.setAlignment(Pos.CENTER);
        window.setPadding(new Insets(0, 10, 0, 10));
        
        GridPane botton = new GridPane();
        
        Scene scene = new Scene(new VBox(), 1200, 700);
       
        MenuBar mb_Menu = new MenuBar();
        Menu m_Options = new Menu("Start Game");
        Menu m_Intructions = new Menu("Instructions");
        Menu m_Positions = new Menu("Positions Table");
        MenuItem mI_Exit = new MenuItem("Exit");
        MenuItem mI_Restart = new MenuItem("Restart game");
        MenuItem mI_End = new MenuItem("End game");
        
        mI_Exit.setOnAction((event) -> {
            System.exit(0);
        });
        
        mI_Restart.setOnAction((event) -> {
            
        });
        
        m_Options.getItems().addAll(mI_Restart, mI_End, mI_Exit);
        mb_Menu.getMenus().addAll(m_Options,m_Intructions,m_Positions);
        //mb_Menu.setVisible(false);
        
        window.getChildren().addAll(config.configWindows());
   
        ((VBox) scene.getRoot()).getChildren().addAll(window,botton);
        
        return scene;
    }

}
