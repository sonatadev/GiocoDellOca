public class PosizioneConEvento extends Posizione{
    private boolean annulla;
    private boolean fisso;
    private int valoreEvento;

    public PosizioneConEvento(int numeroPosizione, int valoreEvento, boolean annulla, boolean fisso) {
        super(numeroPosizione);
        this.valoreEvento = valoreEvento;
        if(annulla){
            this.annulla = annulla;
            this.fisso = false;
        }else if(fisso){
            this.annulla = false;
            this.fisso = fisso;
        }
    }

}
