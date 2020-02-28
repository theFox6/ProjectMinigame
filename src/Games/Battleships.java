package Games;

import GameEngine.CharField;
import GameEngine.PrintingGame;
import GameEngine.Short2DPoint;
import java.io.PrintStream;
import java.util.Scanner;

public class Battleships extends PrintingGame {
    public Battleships() {
        super("Schiffe versenken");
    }
    private static final char[]letters = {'A','B','C','D','E','F','G','H','I'};
    
    public void displayField(CharField f) {
        Short2DPoint origin = new Short2DPoint(0,0);
        Short2DPoint size = new Short2DPoint(11,11);
        Short2DPoint scale = new Short2DPoint(2,1);
        p(f.toString(origin, size, scale));
    }
    
    @Override
    public void run() {
    
        p("In Bearbeitung");
        p("Willkommen bei Schiffe versenken!");
        
        CharField f = new CharField();
        
        for (int num = 1; num < 10; num++) {
            f.set(num,0,Integer.toString(num).charAt(0));
        }
        
        for (int lett = 1; lett < 10; lett++) {
            f.set(0,lett,letters[lett-1]);
        }     

        for (short yp = 1; yp < 10; yp++) {
            for (short xp = 1; xp < 10; xp++) {
                f.set(xp,yp,'~');
            }
        }
        
        displayField(f);
        CharField s = new CharField();
        
        f.toString(0, 0, 22, 11);
        
    } 
}
