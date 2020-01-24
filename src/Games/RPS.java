package Games;

import GameEngine.PrintingGame;
import java.io.PrintStream;
import java.util.Scanner;

public class RPS extends PrintingGame {
    public RPS(PrintStream out, Scanner in) {
        super("Schere Stein Papier", out, in);
    }
    
    @Override
    public void run() {
        p("In Bearbeitung");
        // Benutzereingabe R/P/S
        // zufällige Auswahl des Com Spielers
        // Auswertung des Ergebnisses
    }
}
