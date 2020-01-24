package projectminigame;

import GameEngine.PrintingGame;
import Games.RPS;
import java.io.PrintStream;
import java.util.Scanner;

public class Menu {
    public static int MenuFromStream(PrintingGame[] games, PrintStream out, Scanner in) {
        out.println("Menü");
        out.println();
        int i = 0;
        for (PrintingGame g : games)
            out.println(i+++" - "+g.name);
        out.println("Bitte gib die Zahl des Spiels ein, welches du spielen möchtest.");
        int choice = in.nextInt();
        return choice;
    }
    
    public static void runStream(PrintStream out, Scanner in) {
        PrintingGame[] games = {new RPS(out,in)};
        boolean playOn = true;
        int choice = MenuFromStream(games, out, in);
        PrintingGame game = games[choice];
        
        do {
            out.println(game.name);
            out.println();
            game.run();
            
            out.println();
            out.println("nocheinmal spielen? (j/n)");
            String c = in.next();
            switch (c) {
                case "j":
                case "y":
                    playOn = true;
                break;
                case "n":
                default:
                    playOn = false;
                break;
            }
        } while (playOn);
    }
}
