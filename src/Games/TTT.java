package Games;

import printing.CharField;
import printing.PrintingGame;

public class TTT extends PrintingGame {

    public TTT() {
        super("Tic Tac Toe");
    }

    @Override
    public void run() {
        int field = 0, y, x, n;
        char XO = ' ';
        boolean win = false, player = true, switching = true, full = false;
        CharField f = new CharField();
        String winner = "";
        
        //intruduction
        p("Das Spiel ist wiefolgt aufgebaut:");
        //creates the Charfields
        //for numbers
        n = 1;
        for (y = 0; y <= 4; y = y + 2) {
            for (x = 0; x <= 4; x = x + 2) {
                final int RADIX = 10;
                char ch = Character.forDigit(n, RADIX);
                f.set(x, y, ch);
                n++;
            }
        }
        // for |
        for (y = 0; y <= 4; y = y + 2) {
            for (x = 1; x <= 4; x = x + 2) {
                f.set(x, y, '|');
            }
        }
        //for -
        for (y = 1; y <= 4; y = y + 2) {
            for (x = 0; x <= 4; x = x + 2) {
                f.set(x, y, '-');
            }
        }
        //for +
        for (y = 1; y <= 4; y = y + 2) {
            for (x = 1; x <= 4; x = x + 2) {
                f.set(x, y, '+');
            }
        }

        //print the field
        p(f.toString(0, 0, 5, 5));
        
        //reset the numbers
        for (y = 0; y <= 4; y = y + 2) {
            for (x = 0; x <= 4; x = x + 2) {         
                f.set(x, y,' ');
            }
        }
        

        //playable
        while (!win && !full) {
            switching = true;
            if (player == true) {
                p("Player 1 ist an der Reihe.");
                p("Wähle ein Feld aus (1-9).");
                XO = 'X';
                winner = "Spieler 1 ";
                field = input.nextInt();
            } else if (player == false) {
                p("Player 2 ist an der Reihe.");
                p("Wähle ein Feld aus (1-9).");
                XO = 'O';
                winner = "Spieler 2 ";
                field = input.nextInt();
            }

            //find the coordinates in the field
            y = 0;
            for (x = field; x > 3; x = x - 3) {
                y++;
            }
            x = x - 1;
            //set X/O if allowed
            if (field >= 1 && field <= 9) {
                if (' ' == f.get(x * 2, y * 2)) {
                    f.set(x * 2, y * 2, XO);
                    p(f.toString(0, 0, 5, 5));
                } else {
                    p("Dieses Feld ist belegt.");
                    switching = false;
                }
            } else {
                p("Dieses Feld existiert nicht.");
                switching = false;
            }

            //switch the players
            if (switching == true) {
                player = !player;
            }
            //check win
            for (x = 0; x < 5; x = x + 2) {
                if (f.get(x, 0) == f.get(x, 2) && f.get(x, 0) == f.get(x, 4) && f.get(x, 0) != ' ') {
                    win = true;
                    p(winner + "hat gewonnen.");
                }
            }
            for (y = 0; y < 5; y = y + 2) {
                if (f.get(0, y) == f.get(2, y) && f.get(0, y) == f.get(4, y) && f.get(0, y) != ' ') {
                    win = true;
                    p(winner + "hat gewonnen.");
                }
            }
            if (f.get(0, 0) == f.get(2, 2) && f.get(0, 0) == f.get(4, 4) && f.get(0, 0) != ' ') {
                win = true;
                p(winner + "hat gewonnen");
            } else if (f.get(0, 4) == f.get(2, 2) && f.get(0, 4) == f.get(4, 0) && f.get(0, 4) != ' ') {
                win = true;
                p(winner + "hat gewonnen");
            }
            //check if full
            if (!win) {
                full = true;
                for (x = 0; x <= 4; x = x + 2) {
                    for (y = 0; y <= 4; y = y + 2) {
                        if (f.get(x, y) == ' ') {
                            full = false;
                            break;
                        }
                    }
                }
                if (full) {
                    p("Es gibt keinen Sieger.");
                }
            }
        }
    }

}
