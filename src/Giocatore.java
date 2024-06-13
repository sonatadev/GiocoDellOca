import java.util.Map;
import java.util.Random;

public class Giocatore {
    private final String username;
    private final String nazionalità;
    private Posizione posizione;

    public Giocatore(String username, String nazionalità, Posizione posizioneIniziale) {
        this.username = username;
        this.nazionalità = nazionalità;
        this.posizione = posizioneIniziale;
    }

    public String getUsername() {
        return username;
    }

    public String getNazionalità() {
        return nazionalità;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }
}
