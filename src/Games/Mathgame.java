package Games;

import GameEngine.PrintingGame;
import java.io.PrintStream;
import java.util.Scanner;

public class Mathgame extends PrintingGame {
    public Mathgame(PrintStream out, Scanner in) {
        super("Kopfrechnen", out, in);
        
    }
    
    @Override
    public void run() {
        p("In Bearbeitung");
        p("Willkommen beim Kopfrechnen. Es werden einfache Aufgaben der Grundrechenarten gegeben, die du lösen musst.");
        p("");
        
        int eingabe;
        int anzahl = 0;
        
        for (int x = 0; x == 0;) {
            anzahl += 1;
            int zahl1 = (int) (Math.random() * 100) + 1;
            int zahl2 = (int) (Math.random() * 100) + 1;
            text.print(zahl1 + "+" + zahl2 + "=");
            eingabe = input.nextInt();
            if (eingabe == zahl1 + zahl2) {
                
            }
            else {
                p("Das ist falsch. Richtig wäre " + (zahl1+zahl2) + ". Du hast " + (anzahl-1) + " mal richtig gerechnet.");
                return;
            }
        }
        
        // Subtraktion, Addition, etc. hinzufügen
        // vielleicht Timer oder ähnliche Elemente, die es spannender machen

    }
    
} 