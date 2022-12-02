package arezzo.notes;


public class Note extends SimpleNote {

    protected NotationEnum.Notes notes;
    protected NotationEnum.Durees duree;
    protected NotationEnum.HauteurNotes hauteurNotes;


    public Note() {
        super();
        this.notes = NotationEnum.Notes.DO;
        this.hauteurNotes = NotationEnum.HauteurNotes.MEDIUM;
        this.duree = NotationEnum.Durees.NONE;
        majNote();
    }

    public Note(NotationEnum.Notes note, NotationEnum.HauteurNotes hauteurNotes, NotationEnum.Durees durees) {
        super(NotationEnum.GetNotationEnum(note, hauteurNotes, durees));
        this.notes = note;
        this.duree = durees;
        this.hauteurNotes = hauteurNotes;
        majNote();
    }

    public NotationEnum.Durees getDuree() {
        return duree;
    }

    public void majNote() {
        this.notation = NotationEnum.GetNotationEnum(notes, hauteurNotes, duree);
    }

    @Override
    public void augmenterLeTon() {
        int i=0;
        for (; i < NotationEnum.notes.length; i++) {
            if (notes == NotationEnum.notes[i]) {
                break;
            }
        }
            int j = (i + 1) % NotationEnum.notes.length;
            notes = NotationEnum.notes[j];
            majNote();
        }

    public String getImage(){
        if(hauteurNotes== NotationEnum.HauteurNotes.MEDIUM){
            return "/images-notes/images/noire.png";
        }
        return"/images-notes/images/croche.png";
    }
    @Override
    public void diminuerLeTon() {
        int i=0;
        for (; i < NotationEnum.notes.length; i++) {
            if (notes == NotationEnum.notes[i]) {
                break;
            }
        }
        int j = (i - 1) % NotationEnum.notes.length;
        notes = NotationEnum.notes[j];
        majNote();
    }
    public NotationEnum.Durees getDurees(){
        return duree;
    }
    public NotationEnum.Notes getNotes(){
        return notes;
    }
    @Override
    public void transposer(int transpose) {
        for (int i = 0; i < transpose; i++) {
            this.augmenterLeTon();
        }
    }
}
