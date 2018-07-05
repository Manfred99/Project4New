package Interface;

import Domain.BlockNum;
import Utility.Variables;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Window /*extends Application implements Runnable */{

    private Thread thread;
    private Scene scene;
    private VBox pane;
    private Canvas canvas;
    private Image image;
    private BlockNum[][] matrixNum;
//    private Mosaic mosaicCanvas1 = new Mosaic("3x3");
//    private Mosaic mosaicCanvas2 = new Mosaic("3x3");
    private GameSpace gameClass = new GameSpace();
    private VBox grid;
//    private VBox chatPane;
//    private Button btn_setMessage;
//    private TextArea ta_TextToSend;
//    private GridPane gridButtonAndTA;
//    private TextArea ta_TextContainer;
//    private Label lbl_chatIntro;
//    private Font font;
//    private boolean clear;

    private boolean start = false;

//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Maze runner");
//        initComponents(primaryStage);
//        primaryStage.setOnCloseRequest(exit);
//
//        primaryStage.show();
//    }
//
//    @Override
//    public void run() {
//
//        long start;
//        long elapsed;
//        long wait;
//        int fps = 30;
//        long time = 1000 / fps;
//
//        while (true) {
//            try {
//                start = System.nanoTime();
//                elapsed = System.nanoTime() - start;
//                wait = time - elapsed / 1000000;
//                Thread.sleep(wait);
//
//
//            } catch (InterruptedException ex) {
//            }
//        }
//    }

    private void initComponents(Stage primaryStage) {

        this.pane = new VBox();
        grid = gameClass.pane();
        grid.setVisible(start);
        this.scene = new Scene(this.pane, Variables.WIDTH, Variables.HEIGHT);
        
        MenuBar mb_Menu = new MenuBar();
        Menu m_Options = new Menu("Options");
        MenuItem mI_Start = new MenuItem("Start");
        MenuItem mI_Exit = new MenuItem("Exit");

        mI_Start.setOnAction((event) -> {
            this.start = true;
            this.grid.setVisible(start);
        });
        mI_Exit.setOnAction((event) -> {
            System.exit(1);
        });
        m_Options.getItems().addAll(mI_Start, mI_Exit);
        mb_Menu.getMenus().addAll(m_Options);
        
        this.pane.getChildren().addAll(mb_Menu, grid);

        primaryStage.setScene(this.scene);
//        this.thread = new Thread(this);
        this.thread.start();
    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, Variables.WIDTH, Variables.HEIGHT);
    }

    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };

}
