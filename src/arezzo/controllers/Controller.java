package arezzo.controllers;

import arezzo.notes.PlayMelodie;

import java.util.Observer;

public abstract class Controller implements Observer {

    protected PlayMelodie melodie;
    public Controller(){
    }

    public void setMelodie(PlayMelodie mel){
        melodie=mel;
    }


}
