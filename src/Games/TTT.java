package Games;

import printing.CharField;
import printing.PrintingGame;

public class TTT extends PrintingGame {

    public TTT() {
        super("Tic Tac Toe");
    }

    @Override
    public void run() {
        int a = 0;
        char XO = ' ';
        boolean win = false, player1 = true, s, belegt=false;
        CharField f = new CharField();
        
        
         // für | 
        for (int w = 0; w <= 6 * 2 - 1; w = w + 2) {
            for (int i = 1; i <= 7 * 2 - 1; i = i + 2) {
                f.set(i, w, 'i');
            }
        }
        //für -
        for (int w = 1; w < 6 * 2 - 2; w = w + 2) {
            for (int i = 0; i <= 7 * 2; i = i + 2) {
                f.set(i, w, '-');
            }
        }
        //für +
        for (int w = 1; w < 6 * 2 - 2; w = w + 2) {
            for (int i = 1; i <= 7 * 2-1; i = i + 2) {
                f.set(i, w, '+');
            }
        }
         p(f.toString(0, 0, 5, 5));
         
     
        //Ausgabe vom Array
      
        while (!win && !belegt) {
            s = true;
            if (player1 == true) {
                p("Player 1 ist an der Reihe");
                p("Wähle ein Feld aus (1-9)");
                XO = 'X';
                a = input.nextInt();
            } else if (player1 == false) {
                p("Player 2 ist an der Reihe");
                p("Wähle ein Feld aus (1-9)");
                XO = 'O';
                a = input.nextInt();
            }

            //Setzen von XO für pl1 u pl2
            if (a < 0 && a > 9) {
                p("Dieses Feld ist nicht existent");
                s = false;
            }
            int n = 0, y;
            for (y = a; y > 3; y = y - 3) {
                n++;
            }
            y = y - 1;
            if (a >= 0 && a <= 9) {
                //
                if (' '==f.get(y*2,n*2)) {
                    f.set(y*2,n*2,XO);
                  p(f.toString(0, 0, 5, 5));
                } else {
                    p("Dieses Feld ist belegt");
                    s = false;
                }
            } else {
                p("Dieses Feld ist nicht existent");
                s = false;
            }

            //Tauschen der Spieler
            if (s == true) {
                player1 = !player1;
            }
            //Kontrolle Sieg
            for (int i = 0; i < 5; i = i + 2) {
                if (f.get(i,0) == f.get(i,2) && f.get(i,0) ==  f.get(i,4)  && f.get(i,0) != ' ') {
                    win = true;
                    p("Es gibt einen Sieger");
                }
            }
            for (int i = 0; i < 5; i = i + 2) {
                if (f.get(0,i) == f.get(2,i) && f.get(0,i) ==  f.get(4,i)  && f.get(0,i) != ' '){
                    win = true;
                    p("Es gibt einen Sieger");
                }
            }
            if (f.get(0,0) == f.get(2,2) && f.get(0,0) ==f.get(4,4) && f.get(0,0) != ' ') {
                win = true;
                p("Es gibt einen Sieger");
            } else if (f.get(0,4) == f.get(2,2) && f.get(0,4)== f.get(4,0) && f.get(0,4) != ' ') {
                win = true;
                p("Es gibt einen Sieger");
            }
//wenn voll
if (!win){
belegt=true;
  for (int i = 0; i < 5; i= i+2) {
         for (int z = 0; z < 5; z=z+2) {
               if(f.get(i,z)==' '){
               belegt = false;
               break;
               }
            }
        }
  if (belegt){
      p("Es gibt keinen Sieger");
  }
}


        }
    }

}
