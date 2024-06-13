public class PosizioneConEvento extends Posizione implements Evento{
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

    @Override
    public int getDistanzaEvento(int numeroPosizione) {
        if(this.annulla)
            return numeroPosizione;

        if(this.fisso)
            return this.valoreEvento;

        return numeroPosizione += this.valoreEvento;
    }
}
