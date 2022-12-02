package arezzo.controllers;

import arezzo.notes.NotationEnum;
import arezzo.notes.Note;
import arezzo.notes.PlayMelodie;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class VuePianoController extends Controller implements Initializable {
    @FXML
    protected Button DoBlanche, ReBlanche, MiBlanche, FaBlanche, SolBlanche, LaBlanche, SiBlanche, DoNoir, ReNoir, FaNoir, SolNoir, LaNoir, Play, Silence;
    @FXML
    protected RadioButton Aigue, Medium, Grave, Ronde, Blanche, Noire, Croche, Piano, Saxo, Guitare, Trompette;
    @FXML
    protected ToggleGroup Type, Instru, Durees;
    @FXML
    protected Slider Volume, Tempo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DoBlanche.setOnAction(actionEvent -> this.noteAction(NotationEnum.Notes.DO));
        ReBlanche.setOnAction(actionEvent -> this.noteAction(NotationEnum.Notes.RE));
        MiBlanche.setOnAction(actionEvent -> this.noteAction(NotationEnum.Notes.MI));
        FaBlanche.setOnAction(actionEvent -> this.noteAction(NotationEnum.Notes.FA));
        SolBlanche.setOnAction(actionEvent -> this.noteAction(NotationEnum.Notes.SOL));
        LaBlanche.setOnAction(actionEvent -> this.noteAction(NotationEnum.Notes.LA));
        SiBlanche.setOnAction(actionEvent -> this.noteAction(NotationEnum.Notes.SI));
        DoNoir.setOnAction(actionEvent -> this.noteAction(NotationEnum.Notes.DO_NOIR));
        ReNoir.setOnAction(actionEvent -> this.noteAction(NotationEnum.Notes.RE_NOIR));
        FaNoir.setOnAction(actionEvent -> this.noteAction(NotationEnum.Notes.FA_NOIR));
        SolNoir.setOnAction(actionEvent -> this.noteAction(NotationEnum.Notes.SOL_NOIR));
        LaNoir.setOnAction(actionEvent -> this.noteAction(NotationEnum.Notes.LA_NOIR));
        Silence.setOnAction(actionEvent -> this.silenceAction());
        Aigue.setUserData(NotationEnum.HauteurNotes.AIGU);
        Medium.setUserData(NotationEnum.HauteurNotes.MEDIUM);
        Grave.setUserData(NotationEnum.HauteurNotes.GRAVE);
        Ronde.setUserData(NotationEnum.Durees.RONDE);
        Blanche.setUserData(NotationEnum.Durees.BLANCHE);
        Noire.setUserData(NotationEnum.Durees.NOIRE);
        Croche.setUserData(NotationEnum.Durees.CROCHE);
        Piano.setUserData(NotationEnum.Instrument.PIANO);
        Guitare.setUserData(NotationEnum.Instrument.GUITARE);
        Saxo.setUserData(NotationEnum.Instrument.SAXOPHONE);
        Trompette.setUserData(NotationEnum.Instrument.TROMPETTE);
        Type.selectedToggleProperty().addListener(actionEvent -> changeType());
        Instru.selectedToggleProperty().addListener(actionEvent -> changeInstru());
        Durees.selectedToggleProperty().addListener(actionEvent-> changeDurees());
        Volume.valueProperty().addListener(listener -> changeVolume());
        Tempo.valueProperty().addListener(listener-> changeTempo());
        Play.setOnAction(actionEvent -> PlayM());
    }
    public void changeType(){
        this.melodie.setHauteurNotes((NotationEnum.HauteurNotes)Type.getSelectedToggle().getUserData());
    }
    public void changeInstru(){
        this.melodie.setInstrument((NotationEnum.Instrument)Instru.getSelectedToggle().getUserData());
    }
    public void changeDurees(){
        NotationEnum.Durees durees=(NotationEnum.Durees)Durees.getSelectedToggle().getUserData();
        this.melodie.setDurees(durees);
        switch (durees){
            case NOIRE:
                this.melodie.setSilence(NotationEnum.Silences.SOUPIR);
                break;
            case RONDE:
                this.melodie.setSilence(NotationEnum.Silences.PAUSE);
                break;
            case CROCHE:
                this.melodie.setSilence(NotationEnum.Silences.DEMI_SOUPIR);
                break;
            case BLANCHE:
                this.melodie.setSilence(NotationEnum.Silences.DEMI_PAUSE);
                break;
        }
    }
    public void noteAction(NotationEnum.Notes notes){
        this.melodie.setNotes(notes);
    }
    public void silenceAction(){
        this.melodie.ajtSilence();
    }
    public void changeVolume(){
        this.melodie.setVolume(Volume.getValue());
    }
    public void changeTempo(){
        this.melodie.setTempo(Tempo.getValue());
    }
    public void PlayM(){
        this.melodie.Play();
    }


    @Override
    public void update(Observable observable, Object o) {

    }
}
