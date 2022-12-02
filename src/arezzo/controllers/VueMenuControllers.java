package arezzo.controllers;

import arezzo.notes.Melodie;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;

public class VueMenuControllers extends Controller implements Initializable {
    @FXML
    protected Menu Melodie, Outils;
    @FXML
    protected MenuItem New, Open, Save, Exit, Renommer, Transposer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Exit.setOnAction(actionEvent -> Exit());
        Renommer.setOnAction(actionEvent -> RenommerNom());
        Transposer.setOnAction(actionEvent -> Transposer());
        Open.setOnAction(actionEvent -> OuvrirFichier());
        Save.setOnAction(actionEvent -> EnregistrerSous());
        New.setOnAction(actionEvent -> New());

    }
    public void RenommerNom(){
        TextInputDialog txt = new TextInputDialog("Nom de la Partition");
        txt.setTitle("Renommer Partition");
        txt.setContentText("Entrer le nom de la Partition : ");
        Optional<String> opt =txt.showAndWait();
        if(opt.isPresent()){
            melodie.setName(opt.get());
        }
    }
    public void New(){
        melodie.reinit();
    }
    public void Transposer(){
        TextInputDialog txt = new TextInputDialog("Entrer un nombre");
        txt.setTitle("Transposer");
        txt.setContentText("Entrer la valeur de transposition: ");
        Optional<String> opt=txt.showAndWait();
        if (opt.isPresent()){
            int transp=1;
            transp= Integer.parseInt((String)opt.get());
            melodie.transposer(transp);
        }
    }
    public void OuvrirFichier(){
        FileChooser fichier = new FileChooser();
        fichier.setTitle("Selectionner un fichier");
        fichier.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("arezzo files (AREZZO)", "*.arezzo");
        fichier.getExtensionFilters().add(extFilter);
        File file=fichier.showOpenDialog(new Stage());
        if(file !=null){
            try {
                FileInputStream fileInputStream=new FileInputStream(file.getPath());
                ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
                Melodie mel = (arezzo.notes.Melodie) objectInputStream.readObject();
                melodie.setMelodie(mel.getNotes());
                melodie.setName(mel.getString());
                objectInputStream.close();
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void Exit(){
        Platform.exit();
        System.exit(0);
    }
    public void EnregistrerSous(){
        FileChooser fichier = new FileChooser();
        fichier.setTitle("Enregistrer un fichier");
        fichier.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("arezzo files (AREZZO)", "*.arezzo");
        fichier.getExtensionFilters().add(extFilter);
        File file= fichier.showOpenDialog(new Stage());
        if (file !=null){
            try{
                FileOutputStream fileOutputStream =new FileOutputStream(file.getPath());
                ObjectOutputStream objectOutputStream =new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(melodie.getMelodie());
                objectOutputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
