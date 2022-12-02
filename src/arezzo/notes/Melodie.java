package arezzo.notes;

import partition.Partition;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Melodie implements Iterable<SimpleNote>, Serializable {
   protected  String string;
   protected transient Partition partition;
   protected ArrayList<SimpleNote>notes;

   public Melodie(String string){
       try {
           Synthesizer synthesizer = MidiSystem.getSynthesizer();
           partition = new Partition(synthesizer);
           notes = new ArrayList<>();
           this.string = string;
           partition.setMelodie(getMelodieNote());
       } catch (MidiUnavailableException e) {
           e.printStackTrace();
       }
   }
    public Melodie()
    {
        try {
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            partition = new Partition(synthesizer);
            notes = new ArrayList<>();
            this.string = "No Title";
            partition.setMelodie(getMelodieNote());
        } catch (MidiUnavailableException m) {
            m.printStackTrace();
        }
    }
    public String getMelodieNote()
    {
        return buildMelodie();
    }
    public String buildMelodie() {
        String notation = "";
        for (SimpleNote nt : notes) {
            notation = notation + nt+ " ";
        }
        return notation;
    }
   public void ajout(SimpleNote simpleNote){
       notes.add(simpleNote);
       partition.setMelodie((getMelodieNote()));
       simpleNote.play(partition);
   }
   public void play(){
       partition.setMelodie(buildMelodie());
       partition.play();
   }
   public void ajtSilence(Silence silence){
       notes.add(silence);
       partition.setMelodie(getMelodieNote());
   }
   public String getString(){
       return string;
   }
    public void suprrNote(SimpleNote note){
       int index= notes.indexOf(note);
        if (index != notes.size() - 1 && note instanceof Note){
            Note n = (Note) note;
            switch (n.getDuree()){
                case NONE:
                    notes.add(index, new Silence(NotationEnum.Silences.DEMI_SOUPIR));
                    break;
                case NOIRE:
                    notes.add(index, new Silence(NotationEnum.Silences.SOUPIR));
                    break;
                case RONDE:
                    notes.add(index, new Silence(NotationEnum.Silences.PAUSE));
                    break;
                case CROCHE:
                    notes.add(index, new Silence(NotationEnum.Silences.DEMI_SOUPIR));
                    break;
                case BLANCHE:
                    notes.add(index, new Silence(NotationEnum.Silences.DEMI_PAUSE));
                    break;
            }
        }
        notes.remove(note);
        partition.setMelodie(getMelodieNotation());
    }
    public String getMelodieNotation(){
       return buildMelodie();
    }
    public void augmenterTon(SimpleNote note){
       if (note instanceof Note){
           Note n= (Note) note;
           n.augmenterLeTon();
       } else if (note instanceof  Silence) {
           Silence s=(Silence) note;
           s.augmenterLeTon();
       }
        partition.setMelodie(getMelodieNotation());
    }
    public void diminuerTon(SimpleNote note){
        if (note instanceof Note){
            Note n= (Note) note;
            n.diminuerLeTon();
        } else if (note instanceof  Silence) {
            Silence s=(Silence) note;
            s.diminuerLeTon();
        }
        partition.setMelodie(getMelodieNotation());
    }
    public void setString(String str) {
        string = str;
        partition.setTitre(str);
    }
    public void setTempo(double tempo){
       partition.setTempo((int)tempo);
    }
    public Partition getPartition(){
       return partition;
    }
    public void setVolume(double volume){
       partition.setVolume(volume);
    }
    public void setNotes(ArrayList<SimpleNote>notes){
       this.notes=notes;
       partition.setMelodie(getMelodieNotation());
    }
    public void transposer(int transposer){
        for (SimpleNote note:notes) {
            note.transposer(transposer);
        }
        partition.setMelodie(getMelodieNotation());
    }
    public void setInstrument(String instrument){
       partition.setInstrument(instrument);
    }
    public ArrayList<SimpleNote>getNotes(){
       return notes;
    }
    @Override
    public Iterator<SimpleNote> iterator() {
        return notes.iterator();
    }
}
