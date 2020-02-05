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
        
        //Begrüßung
        p("Willkommen beim Kopfrechnen. Es werden einfache Aufgaben der Grundrechenarten gegeben, die du lösen musst.");
        p("");
        
        //definiert alle Werte, die wir brauchen.
        int eingabe;
        int anzahl = 0;
        int schwierigkeit;
        int stellen = 0;
        int operation;
        
        //Aufforderung die Schwierigkeit auszuwählen
        p("Wie schwer soll das Rechnen sein? 1: Leicht - 2: Normal - 3: Schwer");
        schwierigkeit = input.nextInt();
        
        //Schwierigkeit ändert nur die Anzahl der Stellen
        switch (schwierigkeit) {
            case 1:
                stellen = 10; //1 bis 10
                break;
            case 2:
                stellen = 100; //1 bis 100
                break;
            case 3:
                stellen = 1000; //1 bis 1000
                break;
            default: //wenn jemand meint lustig zu sein
                p("Wenn du nicht einmal bis 3 zählen kannst ist dieses Spiel wahrscheinlich nichts für dich.");
                return;
        }
        
        //Schleife sorgt dafür, dass nicht nach dem ersten Mal Schluss ist
        for (int x = 0; x == 0;) {
            anzahl += 1; //zählt wie lange die Schleife durchläuft
            int zahl1 = (int) (Math.random() * stellen) + 1;
            int zahl2; //Zahl 2 ist abhängig von der Rechenoperation
            
            operation = (int) (Math.random() * 3) + 1; //zufällige Zahl von 1 bis 3 bestimmt Operation
            switch (operation) {
                case 1: //Addition
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
                case 2: //Subtraktion
                    zahl2 = (int) (Math.random() * stellen) + 1;
                    //die kleinere Zahl wird immer von der größeren abgezogen um negative Zahlen zu vermeiden
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
                case 3: //Multiplikation
                    zahl2 = (int) (Math.random() * 10) + 1; //Zahl 2 liegt immer zwischen 1 und 10, weil es sonst zu schwer wäre
                    text.print(zahl1 + "*" + zahl2 + "=");
                    eingabe = input.nextInt();
                    if (eingabe == zahl1 * zahl2) {
                        break;
                    }
                    else {
                        p("Das ist falsch. Richtig wäre " + (zahl1*zahl2) + ". Du hast " + (anzahl-1) + " mal richtig gerechnet.");
                        return;
                    } 
                default:
                    p("Wenn du das lesen kannst sag uns bitte Bescheid. Das soll nämlich nicht so sein");
                    return;
            }

        }
        
        // Division hinzufügen
        // vielleicht Timer oder ähnliche Elemente, die es spannender machen

    }
    
} 