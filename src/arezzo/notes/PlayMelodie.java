package arezzo.notes;

import arezzo.controllers.Controller;

import java.util.ArrayList;
import java.util.Observable;

public class PlayMelodie extends Observable {
    protected double volume;
    protected double tempo;
    protected Melodie melodie;
    protected NotationEnum.Silences silence;
    protected NotationEnum.Notes note;
    protected NotationEnum.Durees durees;
    protected NotationEnum.HauteurNotes hauteurNotes;
    protected NotationEnum.Instrument instrument;
    protected NotationEnum.Alterations alterations;
    protected ArrayList<Controller> controllers;

    public PlayMelodie(Controller... obs) {
        hauteurNotes=NotationEnum.HauteurNotes.MEDIUM;
        instrument= NotationEnum.Instrument.PIANO;
        silence= NotationEnum.Silences.PAUSE;
        durees= NotationEnum.Durees.NONE;
        alterations= NotationEnum.Alterations.DIESE;
        controllers = new ArrayList<>();
        for (arezzo.controllers.Controller observer : obs) {
            controllers.add(observer);
            addObserver(observer);
        }
        reinit();
    }
    public void Play(){
        melodie.play();
    }
    public Melodie getMelodie(){
        return melodie;
    }
    public void setNotes(NotationEnum.Notes not){
            this.note=not;
            melodie.ajout(new Note(note, hauteurNotes,durees));
            setChanged();
            notifyObservers();
    }
    public void ajtSilence(){
        melodie.ajtSilence(new Silence(silence));
        setChanged();
        notifyObservers();
    }
    public void augmenterleton(SimpleNote note){
        melodie.augmenterTon(note);
        setChanged();
        notifyObservers();
    }
    public void diminuerleton(SimpleNote note){
        melodie.diminuerTon(note);
        setChanged();
        notifyObservers();
    }
    public NotationEnum.Notes getNote(){
        return note;
    }

    public void setHauteurNotes(NotationEnum.HauteurNotes hauteurNotes) {
        this.hauteurNotes=hauteurNotes;
    }
    public void setInstrument(NotationEnum.Instrument instrument){
        this.instrument=instrument;
        melodie.setInstrument(NotationEnum.GetNotationEnumInstru(instrument));
    }
    public void setDurees(NotationEnum.Durees durees){
        this.durees=durees;
    }
    public void setSilence(NotationEnum.Silences silence){
        this.silence=silence;
    }
    public void setVolume(double volume){
        this.volume=volume;
        melodie.setVolume(volume);
    }
    public void reinit(){
        melodie=new Melodie();
        for (Controller control : controllers) {
            control.setMelodie(this);
        }
        setChanged();
        notifyObservers();
    }
    public void supprimer(SimpleNote note){
        melodie.suprrNote(note);
        setChanged();
        notifyObservers();
    }
    public void setTempo(double tempo){
        this.tempo=tempo;
        melodie.setTempo(tempo);
    }
    public void transposer(int transp){
        melodie.transposer(transp);
        setChanged();
        notifyObservers();
    }
    public void setMelodie(ArrayList<SimpleNote>notes){
        melodie.setNotes(notes);
        setChanged();
        notifyObservers();
    }
    public void setName(String title){
        melodie.setString(title);
        setChanged();
        notifyObservers();
    }
}

