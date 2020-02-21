package Games;

import GameEngine.CharField;
import GameEngine.PrintingGame;
import java.io.PrintStream;
import java.util.Scanner;

public class Battleships extends PrintingGame {
    public Battleships(PrintStream out, Scanner in) {
        super("Schiffe versenken", out, in);
    }
    private static final char[]letters = {'A','B','C','D','E','F','G','H','I'};
    
    @Override
    public void run() {
    
        p("In Bearbeitung");
        p("Willkommen bei Schiffe versenken!");
        
        CharField f = new CharField();
        
        for (int num = 1; num < 10; num++) {
            f.set(num*2,0,Integer.toString(num).charAt(0));
        }
        
        for (int lett = 1; lett < 10; lett++) {
            f.set(0,lett,letters[lett-1]);
        }     

        for (short yp = 1; yp < 10; yp++) {
            for (short xp = 1; xp < 10; xp++) {
                f.set(xp*2,yp,'~');
            }
        }
        
        p(f.toString(0, 0, 22, 11));
        
    } 
}
