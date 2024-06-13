import java.util.*;

public class Gioco {
    private TavoloDaGioco tavoloDaGioco;
    private List<Giocatore> listaGiocatori;
    private int turnoGiocatore;
    private int contaTurno;

    public Gioco(TavoloDaGioco tavolo, int numeroGiocatori) {
        this.tavoloDaGioco = tavolo;
        this.listaGiocatori = registrazioneGiocatori(numeroGiocatori, tavolo.getTavolo().get(0));
        this.turnoGiocatore = 0;
        this.contaTurno = 0;
    }

    public static void main(String[] args){
        TavoloDaGioco tavolo = new TavoloDaGioco();

        System.out.println("Quante persone giocheranno?");
        int numeroGiocatori = 2;

        Gioco gioco = new Gioco(tavolo, numeroGiocatori);

        gioco.giocoPartita();
    }

    public void giocoPartita(){
        System.out.println("Turno 0.");
        while(true){
            lanciaDado();
            System.out.println();
        }
    }

    private List<Giocatore> registrazioneGiocatori(int numeroGiocatori, Posizione posizioneIniziale){
        List<Giocatore> giocatori = new ArrayList<Giocatore>();
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < numeroGiocatori; i++){
            System.out.println("Ciao giocatore numero " + (this.turnoGiocatore + 1) + ", inserisci prima il tuo username e poi la tua nazionalità.");
            Giocatore giocatore = new Giocatore(sc.nextLine(), sc.nextLine(), posizioneIniziale);
            giocatori.add(giocatore);
            this.turnoGiocatore++;
        }
        this.turnoGiocatore = 0;
        return giocatori;
    }

    public void lanciaDado(){
        Random generaLancio = new Random();
        int valoreDado = generaLancio.nextInt(6) + 1;

        System.out.println(this.listaGiocatori.get(this.turnoGiocatore).getUsername() + " ha lanciato il dado ed è uscito " + valoreDado);

        setNuovaPosizione(valoreDado, this.tavoloDaGioco.getEventi());

        if(this.turnoGiocatore < this.listaGiocatori.size()-1){
            this.turnoGiocatore++;
        }
        else{
            this.turnoGiocatore = 0;

            this.contaTurno++;
            System.out.println("Turno " + this.contaTurno + ".");
        }
    }

    private void setNuovaPosizione(int valoreDado, Map<Integer, TavoloDaGioco.Evento> eventi){
        int numeroPosizioneAttuale = listaGiocatori.get(this.turnoGiocatore).getPosizione().getNumeroPosizione();
        int nuovaPosizione = numeroPosizioneAttuale + valoreDado;

        if(nuovaPosizione == 30)
            finePartita();

        if(eventi.containsKey(nuovaPosizione)){
            int posizioneDopoEvento = this.tavoloDaGioco.getPosizioneConEvento(nuovaPosizione, valoreDado);

            if(posizioneDopoEvento > 30){
                System.out.println("Dopo l'imprevisto " + this.listaGiocatori.get(this.turnoGiocatore).getUsername() + " è finito sulla casella numero " + posizioneDopoEvento + ", di conseguenza tornerà indietro dell'eccesso di caselle rispetto alla casella 30.");
                posizioneDopoEvento = 30 - (posizioneDopoEvento - 30);
                System.out.println("Dopo lo spostamento causato dall'eccesso " + this.listaGiocatori.get(this.turnoGiocatore).getUsername() + " è finito sulla casella numero " + posizioneDopoEvento);
                this.listaGiocatori.get(this.turnoGiocatore).setPosizione(this.tavoloDaGioco.getTavolo().get(nuovaPosizione));
                System.out.println();
            }
            else{
                System.out.println("Dopo l'imprevisto " + this.listaGiocatori.get(this.turnoGiocatore).getUsername() + " è finito sulla casella numero " + posizioneDopoEvento);
                this.listaGiocatori.get(this.turnoGiocatore).setPosizione(this.tavoloDaGioco.getTavolo().get(posizioneDopoEvento));
                System.out.println();
            }
        }
        else{
            if(nuovaPosizione > 30){
                System.out.println(this.listaGiocatori.get(this.turnoGiocatore).getUsername() + " è finito sulla casella numero " + nuovaPosizione + ", di conseguenza tornerà indietro dell'eccesso di caselle rispetto alla casella 30.");
                nuovaPosizione = 30 - (nuovaPosizione - 30);
                System.out.println("Dopo lo spostamento causato dall'eccesso " + this.listaGiocatori.get(this.turnoGiocatore).getUsername() + " è finito sulla casella numero " + nuovaPosizione);
                this.listaGiocatori.get(this.turnoGiocatore).setPosizione(this.tavoloDaGioco.getTavolo().get(nuovaPosizione));
                System.out.println();
            }
            else{
                System.out.println(this.listaGiocatori.get(this.turnoGiocatore).getUsername() + " è finito sulla casella numero " + nuovaPosizione);
                this.listaGiocatori.get(this.turnoGiocatore).setPosizione(this.tavoloDaGioco.getTavolo().get(nuovaPosizione));
                System.out.println();
            }
        }
    }

    public void finePartita(){
        System.out.println(this.listaGiocatori.get(this.turnoGiocatore).getUsername() + " ha raggiungo la trentesima posizione e ha vinto!");
        System.exit(0);
    }

}
