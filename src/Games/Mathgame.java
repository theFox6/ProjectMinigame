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
        int schwierigkeit;
        int stellen = 0;
        int operation;
        
        p("Wie schwer soll das Rechnen sein? 1: Leicht - 2: Normal - 3: Schwer");
        schwierigkeit = input.nextInt();
        
        switch (schwierigkeit) {
            case 1:
                stellen = 10;
                break;
            case 2:
                stellen = 100;
                break;
            case 3:
                stellen = 1000;
                break;
            default:
                break;
        }
        
        for (int x = 0; x == 0;) {
            anzahl += 1;
            int zahl1 = (int) (Math.random() * stellen) + 1;
            int zahl2;
            
            operation = (int) (Math.random() * 3) + 1;
            switch (operation) {
                case 1:
                    zahl2 = (int) (Math.random() * stellen) + 1;
                    text.print(zahl1 + "+" + zahl2 + "=");
                    eingabe = input.nextInt();
                    if (eingabe == zahl1 + zahl2) {
                        break;
                    }
                    else {
                        p("Das ist falsch. Richtig wäre " + (zahl1+zahl2) + ". Du hast " + (anzahl-1) + " mal richtig gerechnet.");
                        return;
                    } 
                case 2:
                    zahl2 = (int) (Math.random() * stellen) + 1;
                    if (zahl2 > zahl1) {
                        int h = zahl2;
                        zahl2 = zahl1;
                        zahl1 = h;
                    }
                    text.print(zahl1 + "-" + zahl2 + "=");
                    eingabe = input.nextInt();
                    if (eingabe == zahl1 - zahl2) {
                        break;
                    }
                    else {
                        p("Das ist falsch. Richtig wäre " + (zahl1-zahl2) + ". Du hast " + (anzahl-1) + " mal richtig gerechnet.");
                        return;
                    } 
                case 3:
                    zahl2 = (int) (Math.random() * 10) + 1;
                    text.print(zahl1 + "*" + zahl2 + "=");
                    eingabe = input.nextInt();
                    if (eingabe == zahl1 * zahl2) {
                        break;
                    }
                    else {
                        p("Das ist falsch. Richtig wäre " + (zahl1*zahl2) + ". Du hast " + (anzahl-1) + " mal richtig gerechnet.");
                        return;
                    } 
            }

        }
        
        // Division hinzufügen
        // vielleicht Timer oder ähnliche Elemente, die es spannender machen

    }
    
} 