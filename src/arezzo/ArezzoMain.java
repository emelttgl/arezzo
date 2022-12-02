package arezzo;

import arezzo.controllers.*;
import arezzo.notes.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ArezzoMain extends Application {

    @Override
    public void start(Stage stage){
        Pane root = new VBox();
        FXMLLoader fxmlLoader = new FXMLLoader();
        MenuBar menu =null;
        try {
            menu = (MenuBar) fxmlLoader.load(this.getClass().getResource("vues/VueMenu.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        };
        VueMenuControllers vueMenuControllers =(VueMenuControllers)fxmlLoader.getController();
        fxmlLoader =new FXMLLoader();
        Pane partition = null;
        try{
            partition=(Pane)fxmlLoader.load(this.getClass().getResource("vues/VuePartition.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        };
        VuePartitionController vuePartitionController=(VuePartitionController) fxmlLoader.getController();
        fxmlLoader =new FXMLLoader();
        Pane piano = null;
        try{
        piano=(Pane)fxmlLoader.load(this.getClass().getResource("vues/VuePiano.fxml").openStream());
        } catch (IOException e) {
        e.printStackTrace();
        };
        VuePianoController vuePianoController =(VuePianoController)fxmlLoader.getController();
        root.getChildren().addAll(menu, partition, piano);
        PlayMelodie mel=new PlayMelodie(vueMenuControllers,vuePianoController,vuePartitionController);
        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Arezzo");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
