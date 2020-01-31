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
        int zufall = (int) Math.round(Math.random() * 2) + 1;
        // Auswertung des Ergebnisses
        switch (zufall) {
            case 1:
                p("Der Com wählt Schere");
                switch (opt) {
                    case 1:
                        p("Es ist unentschieden");
                        break;
                    case 2:
                        p("Sie Haben gewonnen");
                        break;
                    case 3:
                        p("Sie haben verloren");
                        break;
                    default:
                        p("Sie haben eine falsche Option gewählt.");
                        break;
                }
            break;
            case 2:
                p("Der Com wählt Stein");
                switch (opt) {
                    case 1:
                        p("Sie haben Verloren");
                        break;
                    case 2:
                        p("Es ist unentschieden");
                        break;
                    case 3:
                        p("Sie haben Gewonnen");
                        break;
                    default:
                        p("Sie haben eine falsche Option gewählt.");
                        break;
                }
            break;
            case 3:
                p("Der Com wählt Papier");
                switch (opt) {
                    case 1:
                        p("Sie haben Gewonnen");
                        break;
                    case 2:
                        p("Sie haben Gewonnen");
                        break;
                    case 3:
                        p("Es ist unentschieden");
                        break;
                    default:
                        p("Sie haben eine falsche Option gewählt.");
                        break;
                }
            break;
            default:
                p("Die Entwickler haben Mist gebaut.");
            break;
        }
    }
}
