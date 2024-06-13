import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TavoloDaGioco {
    private List<Posizione> tavolo;
    private Map<Integer, Evento> eventi = setHashMap();

    public Map<Integer, Evento> setHashMap(){
        Map<Integer, Evento> temp = new HashMap<Integer, Evento>();

        temp.put(4, new Evento(2, false, false));
        temp.put(9, new Evento(-3, false, false));
        temp.put(13, new Evento(0, true, false));
        temp.put(14, new Evento(0, false, true));
        temp.put(16, new Evento(8, false, true));
        temp.put(18, new Evento(2, false, false));
        temp.put(21, new Evento(15, false, true));
        temp.put(24, new Evento(0, true, false));
        temp.put(27, new Evento(0, false, true));
        temp.put(29, new Evento(3, false, false));

        return temp;
    }

    public TavoloDaGioco() {
        this.tavolo = generaPosizioniGioco();
    }

    protected class Evento{
        private int valoreEvento;
        private boolean annulla;
        private boolean fisso;


        public Evento( int valoreEvento, boolean annulla, boolean fisso) {
            this.annulla = annulla;
            this.fisso = fisso;
            this.valoreEvento = valoreEvento;
        }
    }

    public Map<Integer, Evento> getEventi() {
        return eventi;
    }

    private List<Posizione> generaPosizioniGioco(){
        List<Posizione> posizioni = new ArrayList<Posizione>();


        for(int i = 0; i < 31; i++){
            if(eventi.containsKey(i)){
                PosizioneConEvento posEv = new PosizioneConEvento(i, eventi.get(i).valoreEvento, eventi.get(i).annulla, eventi.get(i).fisso);
                posizioni.add(posEv);
            }
            else{
                Posizione pos = new Posizione(i);
                posizioni.add(pos);
            }
        }
        return posizioni;
    }

    public List<Posizione> getTavolo() {
        return tavolo;
    }

    public int getPosizioneConEvento(int numeroPosizione, int valoreDado){
        System.out.println("La casella " + numeroPosizione + " ha un imprevisto: ");

        int valoreEvento = eventi.get(numeroPosizione).valoreEvento;
        boolean annulla = eventi.get(numeroPosizione).annulla;
        boolean fisso = eventi.get(numeroPosizione).fisso;

        if(annulla){
            System.out.println("Il tiro è stato annullato, rimanere fermi.");
            return numeroPosizione - valoreDado;
        }

        if(fisso){
            System.out.println("Andare alla casella " + valoreEvento);
            return valoreEvento;
        }

        System.out.println(valoreEvento > 0 ? "Andare avanti di " + valoreEvento + " caselle" : "Andare indietro di " + Math.abs(valoreEvento) + " caselle");
        return numeroPosizione += valoreEvento;
    }
}
