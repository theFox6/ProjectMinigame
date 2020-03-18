package Games;

import printing.PrintingGame;

public class RPS extends PrintingGame {
    public RPS() {
        super("Schere Stein Papier");
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
        int zufall = randomInt(3) + 1;
        // Auswertung des Ergebnisses
        switch (zufall) {
            case 1:
                p("Der Com wählt Schere");
                switch (opt) {
                    case 1:
                        p("Es ist unentschieden");
                        break;
                    case 2:
                        p("Sie haben gewonnen");
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
                        p("Sie haben verloren");
                        break;
                    case 2:
                        p("Es ist unentschieden");
                        break;
                    case 3:
                        p("Sie haben gewonnen");
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
                        p("Sie haben gewonnen");
                        break;
                    case 2:
                        p("Sie haben verloren");
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
