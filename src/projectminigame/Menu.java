package projectminigame;

import GameEngine.Game;
import GameEngine.GameManager;
import printing.PrintingGame;
import Games.Mathgame;
import Games.RPS;
import Games.TTT;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import painting.PaintingGame;

public class Menu {
    private final PrintStream out;
    private final Scanner in;
    PrintingGame[] printingGames;
    PaintingGame[] paintingGames;
    private GameManager gameManager;
    
    public Menu(PrintStream out, Scanner in) {
        this.out = out;
        this.in = in;
        PrintingGame[] tg = {
            new QuitOption(out,in),
            new RPS(out,in),
            new TTT(out,in),
            new Mathgame(out,in)
        };
        printingGames = tg;
        PaintingGame[] gg = {
        };
        paintingGames = gg;
        gameManager = new GameManager();
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
        for (PrintingGame g : printingGames)
            out.println(i+++" - "+g.name);
        for (PaintingGame g : paintingGames)
            out.println(i+++" - "+g.name);
        out.println("Bitte gib die Zahl des Spiels ein, welches du spielen möchtest.");
        int choice = in.nextInt();
        return choice;
    }

    public void runStream() {
        boolean playOn;
        do {
            int choice = MenuFromStream();
            Game game;
            if (choice<printingGames.length)
                game = printingGames[choice];
            else {
                choice -= printingGames.length;
                game = paintingGames[choice];
            }
            do {
                try {
                    gameManager.prepareGame(game);
                } catch (GameManager.AlreadyRunningException ex) {
                    out.println("fehler ein Spiel läuft noch");
                }
                out.println(game.name);
                out.println();
                try {
                    gameManager.start();
                } catch (GameManager.AlreadyRunningException ex) {
                    out.println("fehler ein Spiel läuft bereits");
                }
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
