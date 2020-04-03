package Games;

import printing.CharField;
import printing.PrintingGame;

public class TTT extends PrintingGame {

    public TTT() {
        super("Tic Tac Toe");
    }

    @Override
    public void run() {
 
        /**
         * deklaration and initialization
        */
        int field = 0, y, x, n,RADIX, turns = 0;
        char XO = ' ',ch;
        boolean win = false, player = true, switching = true, full = false;
        CharField f = new CharField();
        String winner = "";
        
        /**
         * creating the first Tic Tac Toe field so the player can see how the game works
         * including the numbers one to nine 
        */
        
        p("Das Spiel ist wie folgt aufgebaut:");
        
        /**
         * set the Charfield
         * set |,+,- and numbers
         */
        
        // for numbers
        n = 1;
        for (y = 0; y <= 4; y = y + 2) {
            for (x = 0; x <= 4; x = x + 2) {
                RADIX = 10;
                ch = Character.forDigit(n, RADIX);
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
        
        // for -
        for (y = 1; y <= 4; y = y + 2) {
            for (x = 0; x <= 4; x = x + 2) {
                f.set(x, y, '-');
            }
        }
        
        // for +
        for (y = 1; y <= 4; y = y + 2) {
            for (x = 1; x <= 4; x = x + 2) {
                f.set(x, y, '+');
            }
        }

        /**
         * show the Charfield
         */
        p(f.toString(0, 0, 5, 5));
        
         /**
         * clean the field 
         * reset the numbers to ' ' 
         */
        for (y = 0; y <= 4; y = y + 2) {
            for (x = 0; x <= 4; x = x + 2) {         
                f.set(x, y,' ');
            }
        }
        
        /**
         * the real game 
         * loop as long as no win and not full
         */
        while (!win && !full) {
            turns++;
            
         /**
         * loop until switching stays true
         */
            do {
                 switching = true;
                 
             /**
             * output for the user
             * set the right arguments
             */   
            if (player == true) {
                p("Player 1 ist an der Reihe.");
                p("Sie haben X.");
                p("Wähle ein Feld aus (1-9).");
                XO = 'X';
                winner = "Spieler 1 ";
                field = input.nextInt();
            } else if (player == false) {
                p("Player 2 ist an der Reihe.");
                p("Sie haben O.");
                p("Wähle ein Feld aus (1-9).");
                XO = 'O';
                winner = "Spieler 2 ";
                field = input.nextInt();
            }
            
            /**
            * find the coordinates in the field
            */
            y = 0;
            for (x = field; x > 3; x = x - 3) {
                y++;
            }
            x = x - 1;
            
            /*
            * set X/O if allowed
            */
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
            } while (switching == false);
            
            /**
             * switch the players
             */    
            player = !player;
            
           /**
            * check if there are three equal symols ihe same row
            * only after 4 turns
            */
           if (turns >=5){
           // vertikal
            for (x = 0; x <= 4; x = x + 2) {
                if (f.get(x, 0) == f.get(x, 2) && f.get(x, 0) == f.get(x, 4) && f.get(x, 0) != ' ') {
                    win = true;
                    p(winner + "hat gewonnen.");
                }
            }
            
            // horizontal
            for (y = 0; y <= 4; y = y + 2) {
                if (f.get(0, y) == f.get(2, y) && f.get(0, y) == f.get(4, y) && f.get(0, y) != ' ') {
                    win = true;
                    p(winner + "hat gewonnen.");
                }
            }
            
            // diagonal
            if (f.get(0, 0) == f.get(2, 2) && f.get(0, 0) == f.get(4, 4) && f.get(0, 0) != ' ') {
                win = true;
                p(winner + "hat gewonnen.");
            } else if (f.get(0, 4) == f.get(2, 2) && f.get(0, 4) == f.get(4, 0) && f.get(0, 4) != ' ') {
                win = true;
                p(winner + "hat gewonnen.");
            }
           }
            /**
             * check if there is another possibility to set
             */
            if (!win && turns == 9) {
                full = true;   
                p("Es gibt keinen Sieger.");
            }
        }
    }

}
