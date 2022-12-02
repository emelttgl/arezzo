package arezzo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import partition.Partition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class VuePartitionController extends Controller implements Initializable {
    @FXML
    protected ScrollPane MelodiePane;
    @FXML
    private Button LesNotes;
    @FXML
    protected Label Name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.LesNotes.setOnAction(event-> {
            this.listeNotes();
        });

    }
    private void listeNotes() {
        Stage stage = null;
        if (stage == null) {
            Parent parent = null;
            try {
                FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("arezzo/vues/ListeNotes.fxml"));
                parent = loader.load();
                ListeNotesController listeNotesController=loader.getController();
                this.melodie.addObserver(listeNotesController);
                listeNotesController.majListe(this.melodie);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Liste Des Notes : ");
            stage.setScene(new Scene(parent));
        }

        stage.show();
    }



    public void update(Observable observable, Object o) {
       Partition partition= this.melodie.getMelodie().getPartition();
        partition.setPreferedMaxWidth((int)this.MelodiePane.getWidth());
        if (partition != null){
            Image img=partition.getImage();
            if(img != null){
                this.MelodiePane.setContent(new ImageView(img));
            }
        }
        this.Name.setText(this.melodie.getMelodie().getString());
    }




}
