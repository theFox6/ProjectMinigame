package Games;

import GameEngine.PrintingGame;
import java.io.PrintStream;
import java.util.Scanner;

public class TTT extends PrintingGame {

    public TTT(PrintStream out, Scanner in) {
        super("Tic Tac Toe", out, in);

    }

    @Override
    public void run() {
        int a = 0;
        String XO = "";
        boolean win = false, player1 = true, s;
        String[][] Felder = {
            {" ", "|", " ", "|", " "},
            {"-", "+", "-", "+", "-"},
            {" ", "|", " ", "|", " "},
            {"-", "+", "-", "+", "-"},
            {" ", "|", " ", "|", " "},};
        //Ausgabe vom Array
        printBorder(Felder);
        while (!win) {
            s = true;
            if (player1 == true) {
                p("Player 1 ist an der Reihe");
                p("Wähle ein Feld aus (1-9)");
                XO = "X";
                a = input.nextInt();
            } else if (player1 == false) {
                p("Player 2 ist an der Reihe");
                p("Wähle ein Feld aus (1-9)");
                XO = "O";
                a = input.nextInt();
            }
            //Setzen von XO für pl1 u pl2
            if (a >= 1 && a <= 3) {
                a--;
                if (" ".equals(Felder[0][a * 2])) {
                    Felder[0][a * 2] = XO;
                    printBorder(Felder);
                } else {
                    p("Dieses Feld ist belegt");
                    s = false;
                }
            }
            else if (a >= 4 && a <= 6) {

                a = a - 4;
                if (" ".equals(Felder[2][a * 2])) {
                    Felder[2][a * 2] = XO;
                    printBorder(Felder);
                } else {
                    p("Dieses Feld ist belegt");
                    s = false;
                }

            }
            else if (a >= 7 && a <= 9) {
                a = a - 7;
                if (" ".equals(Felder[4][a * 2])) {
                    Felder[4][a * 2] = XO;
                    printBorder(Felder);
                } else {
                    p("Dieses Feld ist belegt");
                    s = false;
                }
                
            }
            else {
            p("Dieses Feld ist nicht existent");
            s = false;
            }

            //Tauschen der Spieler
            if (s == true) {
                player1 = !player1;
            }
            //Kontrolle Sieg
            for (int i = 0; i < 5; i = i + 2) {
                if (Felder[i][0] == Felder[i][2] && Felder[i][0] == Felder[i][4] && Felder[i][0] != " ") {
                    win = true;
                    p("Es gibt einen Sieger");
                }
            }
            for (int i = 0; i < 5; i = i + 2) {
                if (Felder[0][i] == Felder[2][i] && Felder[0][i] == Felder[4][i] && Felder[0][i] != " ") {
                    win = true;
                    p("Es gibt einen Sieger");
                }
            }
            if (Felder[0][0] == Felder[2][2] && Felder[0][0] == Felder[4][4] && Felder[0][0] != " ") {
                win = true;
                p("Es gibt einen Sieger");
            } else if (Felder[0][4] == Felder[2][2] && Felder[0][4] == Felder[4][0] && Felder[0][4] != " ") {
                win = true;
                p("Es gibt einen Sieger");
            }
//Wenn alles belegt
        }
    }

    private void printBorder(String[][] Felder) {
        for (int i = 0; i < 5; i++) {
            for (int z = 0; z < 5; z++) {
                text.print(Felder[i][z]);
            }
            p("");
        }
    }
}
