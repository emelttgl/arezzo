package arezzo.notes;

import partition.Partition;
import java.io.Serializable;

public abstract class SimpleNote implements Serializable {
    protected String notation;
    public SimpleNote(String notation){
        this.notation=notation;
    }
    public SimpleNote(){
        notation="";
    }

    @Override
    public String toString() {
        return getNotation();
    }

    public void play(Partition partition){
        partition.play(notation);
    }
    public String getNotation(){
        return  notation;
    }
    public String getOctave(){
        return "";
    }
    public String getImage(){
        return "";
    }
    public abstract void augmenterLeTon();
    public abstract void diminuerLeTon();
    public abstract void transposer(int transpose);

}
