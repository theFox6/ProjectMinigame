package Games;

import printing.PrintingGame;

public class RPS extends PrintingGame {

    public RPS() {
        super("Schere Stein Papier");
    }

    @Override
    public void run() {
        
        /**
         *deklaration and initaization
         */
        int opt = 0, com;

        /**
         * userinput R/P/S
         */
        do {
            p("Für Schere: 1");
            p("Für Stein: 2");
            p("Für Papier: 3");
            p("Wählen Sie eine der Optionen.");
            opt = input.nextInt();
            if (opt < 1 || opt > 3){
            p("Diese Optionen existiert nicht.");
            }
        } while (opt < 1 || opt > 3);

        /**
         * create a random number for the Com
         */
        com = randomInt(3) + 1;

        /**
         * compare the numbers
         */
        switch (com) {
            case 1:
                p("Der Computer wählt Schere.");
                switch (opt) {
                    case 1:
                        p("Es ist unentschieden.");
                        break;
                    case 2:
                        p("Sie haben gewonnen.");
                        break;
                    case 3:
                        p("Sie haben verloren.");
                        break;
                }
                break;
            case 2:
                p("Der Computer wählt Stein.");
                switch (opt) {
                    case 1:
                        p("Sie haben verloren.");
                        break;
                    case 2:
                        p("Es ist unentschieden.");
                        break;
                    case 3:
                        p("Sie haben gewonnen.");
                        break;
                }
                break;
            case 3:
                p("Der Computer wählt Papier.");
                switch (opt) {
                    case 1:
                        p("Sie haben gewonnen.");
                        break;
                    case 2:
                        p("Sie haben verloren.");
                        break;
                    case 3:
                        p("Es ist unentschieden.");
                        break;
                }
                break;
            default:
                p("Die Entwickler haben Mist gebaut.");
                break;
        }
    }
}
