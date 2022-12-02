package arezzo.controllers;

import arezzo.notes.PlayMelodie;
import arezzo.notes.SimpleNote;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Iterator;
import java.util.Observable;
import java.util.ResourceBundle;

public class ListeNotesController extends Controller implements Initializable {
    @FXML
    protected ListView LesNotes;
    protected ContextMenu cont;
    protected PlayMelodie melodie;

    @Override
    public void update(Observable observable, Object o) {
        if (this.melodie != null) {
            this.majListe(this.melodie);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.cont=new ContextMenu();
        MenuItem effacer=new MenuItem("Effacer");
        MenuItem augmenter= new MenuItem("Augmenter d'un demi ton");
        MenuItem diminuer= new MenuItem("Diminuer d'un demi ton");
        effacer.setOnAction(actionEvent -> this.effacer());
        augmenter.setOnAction(actionEvent -> this.augmenter());
        diminuer.setOnAction(actionEvent -> this.diminuer());
        this.cont.getItems().addAll(effacer,augmenter,diminuer);
        this.LesNotes.setContextMenu(this.cont);
    }
    public void effacer(){
        Iterator iterator=this.LesNotes.getSelectionModel().getSelectedItems().iterator();
        while(iterator.hasNext()) {
            Object obj=iterator.next();
            Pane pane = (Pane) obj;
            if (pane != null){
            SimpleNote note =(SimpleNote) pane.getUserData();
            if (note!=null){
               this.melodie.supprimer(note);
            }
            }
        }
    }
    public void augmenter(){
        Iterator iterator=this.LesNotes.getSelectionModel().getSelectedItems().iterator();
        while(iterator.hasNext()) {
            Object obj=iterator.next();
            Pane pane = (Pane) obj;
            if (pane != null){
                SimpleNote note =(SimpleNote) pane.getUserData();
                if (note!=null){
                    this.melodie.augmenterleton(note);
                }
            }
        }
    }
    public void diminuer(){
        Iterator iterator=this.LesNotes.getSelectionModel().getSelectedItems().iterator();
        while(iterator.hasNext()) {
            Object obj=iterator.next();
            Pane pane = (Pane) obj;
            if (pane != null){
                SimpleNote note =(SimpleNote) pane.getUserData();
                if (note!=null){
                    this.melodie.diminuerleton(note);
                }
            }
        }
    }
    public void majListe(PlayMelodie mel){
        this.melodie=mel;
        LesNotes.getItems().clear();

        for (SimpleNote note :melodie.getMelodie()) {
            HBox hbox= new HBox();
            hbox.setSpacing(30);
            ImageView image= new ImageView(new Image(getClass().getResourceAsStream(note.getImage())));
            image.setFitHeight(30);
            image.setFitWidth(30);
            hbox.getChildren().addAll(new Text(note.getOctave().replaceAll("_"," ")),image,new Text(note.toString()));
            hbox.setUserData(note);
            LesNotes.getItems().add(hbox);
        }

    }
}
