package arezzo.notes;

public class Silence extends SimpleNote{

    public NotationEnum.Silences silence;

    public Silence(NotationEnum.Silences sil){
        super(NotationEnum.GetNotationSil(sil));
        silence=sil;
    }
    public Silence(){
        super();
        silence=NotationEnum.Silences.PAUSE;
        majNotationEnum();
    }
    public void majNotationEnum(){
        notation = NotationEnum.GetNotationSil(silence);
    }
    @Override
    public void augmenterLeTon() {
        if (silence == NotationEnum.Silences.DEMI_SOUPIR){
            silence = NotationEnum.Silences.SOUPIR;
        }else if(silence == NotationEnum.Silences.SOUPIR) {
            silence = NotationEnum.Silences.DEMI_PAUSE;
        }else if(silence == NotationEnum.Silences.DEMI_PAUSE_POINTEE) {
            silence = NotationEnum.Silences.PAUSE;
        }
        majNotationEnum();
    }
    public String getOctave(){
        return silence.toString();
    }
    @Override
    public void diminuerLeTon() {
        if (silence == NotationEnum.Silences.PAUSE){
            silence = NotationEnum.Silences.DEMI_PAUSE_POINTEE;
        }else if(silence == NotationEnum.Silences.DEMI_PAUSE_POINTEE) {
            silence = NotationEnum.Silences.DEMI_PAUSE;
        }else if(silence == NotationEnum.Silences.DEMI_PAUSE) {
            silence = NotationEnum.Silences.SOUPIR;
        }else if(silence == NotationEnum.Silences.SOUPIR){
            silence = NotationEnum.Silences.DEMI_SOUPIR;
        }
        majNotationEnum();
    }
    public String getImage(){
        switch (silence){
            case PAUSE:
                return "/images-notes/images/pause.png";
            case DEMI_PAUSE:
                return "/images-notes/images/demiPause.png";
            case DEMI_SOUPIR:
                return "/images-notes/images/demiSoupir.png";
            case SOUPIR:
                return  "/images-notes/images/soupir.png";
        }
        return"/images-notes/images/soupir.png";
    }

    @Override
    public void transposer(int transpose) {
        for (int i = 0; i < transpose; i++) {
            this.augmenterLeTon();
        }
    }
}
