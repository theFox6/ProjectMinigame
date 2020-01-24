package Games;

import GameEngine.PrintingGame;
import java.io.PrintStream;

public class RPS extends PrintingGame {
    public RPS(PrintStream out) {
        super("Schere Stein Papier", out);
    }
    
    @Override
    public void run() {
        p("In Bearbeitung");
        // Benutzereingabe R/P/S
        // zufällige Auswahl des Com Spielers
        // Auswertung des Ergebnisses
    }
}
