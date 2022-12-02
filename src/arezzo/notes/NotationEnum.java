package arezzo.notes;

public class NotationEnum {
    public enum Silences{
        DEMI_SOUPIR, SOUPIR, DEMI_PAUSE, DEMI_PAUSE_POINTEE, PAUSE
    }
    public enum Durees{
        CROCHE, NOIRE, BLANCHE, RONDE, NONE
    }
    public enum Notes{
        DO, RE, MI, FA, SOL, LA, SI, DO_NOIR,RE_NOIR, FA_NOIR, SOL_NOIR, LA_NOIR,
    }
    public enum Alterations{
        DIESE, BEMOL
    }
    public enum HauteurNotes{
        GRAVE, AIGU, MEDIUM, BARRE
    }
    public enum Instrument{
        PIANO, GUITARE, SAXOPHONE, TROMPETTE
    }
    public static String GetNotationEnumInstru(Instrument instrument){
        if(instrument==Instrument.PIANO)return "Piano";
        else if(instrument == Instrument.GUITARE) return "Guitare";
        else if(instrument == Instrument.SAXOPHONE) return "Saxophone";
        else if(instrument == Instrument.TROMPETTE) return "Trompette";
        return "Piano";
    }
    public static String GetNotationEnumHt(Notes notes, HauteurNotes hauteurNotes){
        String notesNotation = "|";
        if (hauteurNotes == HauteurNotes.BARRE) return notesNotation;
        switch (notes){
            case DO:
                notesNotation = "c";
                break;
            case RE:
                notesNotation = "d";
                break;
            case MI:
                notesNotation = "e";
                break;
            case FA:
                notesNotation = "f";
                break;
            case SOL:
                notesNotation = "g";
                break;
            case LA:
                notesNotation = "a";
                break;
            case SI:
                notesNotation = "b";
                break;
            case DO_NOIR:
                notesNotation = "^c";
                break;
            case RE_NOIR:
                notesNotation = "^d";
                break;
            case FA_NOIR:
                notesNotation = "^e";
                break;
            case SOL_NOIR:
                notesNotation = "^f";
                break;
            case LA_NOIR:
                notesNotation = "^g";
                break;
        }
        if(hauteurNotes==HauteurNotes.AIGU) {
            return notesNotation;
        }else if(hauteurNotes==HauteurNotes.GRAVE){
            notesNotation = notesNotation.toUpperCase()+",";
        }else if(hauteurNotes== HauteurNotes.MEDIUM){
            notesNotation = notesNotation.toUpperCase();
        }
        return notesNotation;
    }
    public static String GetNotationEnum(Notes notes, HauteurNotes hauteurNotes, Durees durees){
        String note = GetNotationEnumHt(notes, hauteurNotes);
        note+= GetNotationEnumDure(durees);
        return note;
    }
    public static String GetNotationEnumDure(Durees durees){
        String dureeNotation= "";
        if(durees== Durees.NONE) {
            return dureeNotation;
        } else if (durees==Durees.CROCHE) {
            dureeNotation = "/";
        } else if (durees==Durees.BLANCHE) {
            dureeNotation = "2";
        } else if (durees==Durees.NOIRE) {
            dureeNotation = "";
        } else if (durees==Durees.RONDE) {
            dureeNotation = "4";
        }
        return dureeNotation;

    }
    public static String GetNotationSil(Silences silence){
        String silencesNotation = "z1/2";
        if (silence == Silences.DEMI_SOUPIR)
            return silencesNotation;
        if (silence== Silences.SOUPIR){
            silencesNotation = "z1";
        }else if(silence == Silences.DEMI_PAUSE){
            silencesNotation = "z2";
        }else if(silence == Silences.DEMI_PAUSE_POINTEE){
            silencesNotation = "z3";
        }else if(silence == Silences.PAUSE){
            silencesNotation = "z4";
        }
        return silencesNotation;
    }
    public static String GetNotationEnumAlt(Alterations alt) {
        if (alt == Alterations.DIESE) {
            return "^";
        } else {
            return "_";
        }
    }
    public static Notes notes[] = {Notes.DO, Notes.DO_NOIR, Notes.RE, Notes.RE_NOIR, Notes.MI, Notes.FA, Notes.FA_NOIR, Notes.SOL, Notes.SOL_NOIR, Notes.SI};


}
