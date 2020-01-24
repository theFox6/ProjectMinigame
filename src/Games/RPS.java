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
        // Benutzereingabe R/P/S
        p("Für Schere: 1");
        p("Für Stein: 2");
        p("Für Papier: 3");
        p("Wählen Sie eine der Folgenenden Optionen");
        int opt = input.nextInt();
        
        // zufällige Auswahl des Com Spielers
        int zufall = (int) Math.round(Math.random() * 3) + 1;
        // Auswertung des Ergebnisses
        if (zufall == 1) {
            p("Der Com wählt Schere");
            if (opt == 1) {
                p("Es ist unentschieden");
            } else if (opt == 2) {
                p("Sie Haben gewonnen");
            } else if (opt == 3) {
                p("Sie haben verloren");
            }
        } else if (zufall == 2) {
            p("Der Com wählt Stein");
            if (opt == 1) {
                p("Sie haben Verloren");
            } else if (opt == 2) {
                p("Es ist unentschieden");
            } else if (opt == 3) {
                p("Sie haben Gewonnen");
            }
        } else if (zufall == 3) {
            p("Der Com wählt Papier");
            if (opt == 1) {
                p("Sie haben Gewonnen");
            } else if (opt == 2) {
                p("Sie haben Gewonnen");
            } else if (opt == 3) {
                p("Es ist unentschieden");
            }
        }
           else {
        p("Sie habe eine Falsche Option verwendet");
        }
    }
}
