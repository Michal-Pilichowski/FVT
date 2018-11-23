package pilichm.gui.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pilichm.utils.Context;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane borderPane = new BorderPane();
        primaryStage.setTitle("Finveintra");
        borderPane.setTop(FXMLLoader.load(getClass().getResource("/pilichm/gui/layout/menu_bar.fxml")));
        Scene scene = new Scene(borderPane, 700, 500);

        Context context = Context.getContext();
        context.setBorderPane(borderPane);

        primaryStage.getIcons().add(new Image("file:src/ic_main.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        MenuBar menuBar = (MenuBar) borderPane.lookup("#main_menu_bar");
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
