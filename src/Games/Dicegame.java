package Games;

import GameEngine.PrintingGame;

public class Dicegame extends PrintingGame {
    public Dicegame() {
        super("Würfeln");
    }
    
    //Würfelsymbole
    private static final char[]dice = {'\u2680','\u2681','\u2682','\u2683','\u2684','\u2685'};
    
    @Override
    public void run() {
        
        //Begrüßung
        p("Willkommen beim Würfeln! Drücke Enter um fortzufahren.");
        
        //Zufallszahlen bestimmen
        int player = (int) (Math.round(Math.random()*5)+1);
        int com = (int) (Math.round(Math.random()*5)+1);
        
        //Warten auf Enter (sind 2 um einen Bug zu umgehen)
        input.nextLine();
        input.nextLine();
        
        //Ausgabe
        p("Du hast " + dice[player-1] + " gewürfelt!");
        p("Der Rechner hat " + dice[com-1] + " gewürfelt!");
        
        //Prüfung wer gewonnen hat
        if (player == com) {
            p("Gleichstand!");
        }
        else if (player < com) {
            p("Du hast verloren!");
        }
        else {
            p("Du hast gewonnen!");
        }
    }
}
