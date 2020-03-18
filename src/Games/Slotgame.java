package Games;

import printing.PrintingGame;

public class Slotgame extends PrintingGame {
    public Slotgame() {
        super("Einarmiger Bandit");
    }

    @Override
    public void run() {

        //Begrüßung
        p("Willkommen beim einarmigen Banditen!");
        p("");
        
        //3 zufällige Zahlen zwischen 1 und 7 werden bestimmt
        int slot1 = (int) ((Math.random()*7) + 1);
        int slot2 = (int) ((Math.random()*7) + 1);
        int slot3 = (int) ((Math.random()*7) + 1);
        
        //Ergebnis wird ausgegeben
        p("Dein Ergebnis: " + slot1 + " " + slot2 + " " + slot3);
        
        //Prüft ob alle 3 Zahlen gleich sind
        if (slot1 == slot2 && slot2 == slot3 && slot1 == slot3){
            p("Du hast gewonnen!");
        }
        else {
            p("Du hast leider nicht gewonnen.");
        }
        
    }     
}
