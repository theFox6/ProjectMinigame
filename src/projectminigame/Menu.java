package projectminigame;

import GameEngine.PrintingGame;
import Games.RPS;
import java.io.PrintStream;
import java.util.Scanner;

public class Menu {
    private final PrintStream out;
    private final Scanner in;
    PrintingGame[] games;
    
    public Menu(PrintStream out, Scanner in) {
        this.out = out;
        this.in = in;
        PrintingGame[] g = {new QuitOption(out,in), new RPS(out,in)};
        games = g;
    }
    
    public static boolean truthyAnswer(String ans) {
        switch (ans) {
            case "j":
            case "ja":
            case "y":
            case "yes":
                return true;
            case "n":
            case "nein":
            case "no":
            default:
                return false;
        }
    }
    
    public boolean beenden;
    private class QuitOption extends PrintingGame {
        public QuitOption(PrintStream out, Scanner in) {
            super("Minigame Sammlung schließen",out,in);
            replayable = false;
        }

        @Override
        public void run() {
            text.println("Wirklich beenden? (j/n)");
            String c = input.next();
            beenden = truthyAnswer(c);
        }
    }
    
    public int MenuFromStream() {
        out.println("Menü");
        out.println();
        int i = 0;
        for (PrintingGame g : games)
            out.println(i+++" - "+g.name);
        out.println("Bitte gib die Zahl des Spiels ein, welches du spielen möchtest.");
        int choice = in.nextInt();
        return choice;
    }
    
    public void runStream() {
        boolean playOn;
        do {
            int choice = MenuFromStream();
            PrintingGame game = games[choice];
            do {
                out.println(game.name);
                out.println();
                game.run();

                if (game.replayable) {
                    out.println();
                    out.println("Nocheinmal spielen? (j/n)");
                    String c = in.next();
                    playOn = truthyAnswer(c);
                } else {
                   playOn = false;
                }
            } while (playOn);
        } while (!beenden);
    }
}
