package projectminigame;

import GameEngine.Game;
import GameEngine.GameManager;
import printing.PrintingGame;
import textAreaIO.PrintingTextArea;
import Games.Dicegame;
import Games.Mathgame;
import Games.RPS;
import Games.TTT;
import java.io.PrintStream;
import java.util.Scanner;
import painting.PaintingGame;

/**
 * the screen showing all available games and asking which to play.
 */
public class Menu {
    /**
     * the stream to print the menu's contents to
     */
    private final PrintStream out;
    
    /**
     * the scanner to read the user inputs from
     */
    private final Scanner in;
	/**
	 * all available text based games
	 */
    PrintingGame[] printingGames;
    PaintingGame[] paintingGames;
    private GameManager gameManager;
    
    /**
     * prepare the Menu
     * @param out the stream to print the menu's contents to 
     * @param in the scanner to read the user inputs from
     */
    public Menu(PrintingTextArea pta) {
    	//store the output and input
        out = pta.output;
        in = pta.input;
        //add all games to an array
        PrintingGame[] tg = {
            new QuitOption(),
            new RPS(),
            new TTT(),
            new Mathgame(),
            new Dicegame()
        };
        //set the game array
		printingGames = tg;
        PaintingGame[] gg = {
        };
        paintingGames = gg;
        gameManager = new GameManager(pta);
    }
    
    /**
     * Whether an answer can be interpreted as yes
     * @param ans the String the user typed
     * @return whether it is a yes
     */
    public static boolean truthyAnswer(String ans) {
        switch (ans.toLowerCase()) {
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
    
    /**
     * whether to quit the menu
     */
    public boolean beenden;
    
    /**
     * the menu option that closes the menu
     */
    private class QuitOption extends PrintingGame {
        private QuitOption() {
        	//set up the text
            super("Minigame Sammlung schließen");
            //don't ask if the user wants to play it again
            replayable = false;
        }

        /**
         * close the menu
         */
        @Override
        public void run() {
        	//the most annoying question
            text.println("Wirklich beenden? (j/n)");
            //get the response
            String c = input.next();
            //close if we got a yes
            beenden = truthyAnswer(c);
        }
    }
    
    /**
     * display the choice of games and ask the user what to play
     * @return whatever the user chose
     */
    public int menuFromStream() {
    	//header
        out.println("Menü");
        out.println();
        //display all the games
        int i = 0;
        for (PrintingGame g : printingGames)
            out.println(i+++" - "+g.name);
        for (PaintingGame g : paintingGames)
            out.println(i+++" - "+g.name);
        //ask the user
        out.println("Bitte gib die Zahl des Spiels ein, welches du spielen möchtest.");
        //get his choice
        int choice = in.nextInt();
        //return it
        return choice;
    }

    /**
     * run the Menu and the user selected games
     * until the user wishes to close it 
     */
    public void runStream() {
    	// whether to play the game again
        boolean playOn;
        //start displaying the menu
        do {
        	//actually display it
            int choice = menuFromStream();
			Game game;
            //check if the choice was ok
            if (choice<printingGames.length)
                game = printingGames[choice];
            else {
                choice -= printingGames.length;
                game = paintingGames[choice];
            }
            //start playing
            do {
                try {
                    gameManager.prepareGame(game);
                } catch (GameManager.AlreadyRunningException ex) {
                    out.println("fehler ein Spiel läuft noch");
                }
            	//the game's header
                out.println(game.name);
                out.println();
                //run the game
                try {
                    gameManager.start();
                } catch (GameManager.AlreadyRunningException ex) {
                    out.println("fehler ein Spiel läuft bereits");
                }
                
                if (game.replayable) {
                	//ask the user whether to play again
                    out.println();
                    out.println("Nocheinmal spielen? (j/n)");
                    //get the answer
                    String c = in.next();
                    //play on if it was a yes
                    playOn = truthyAnswer(c);
                } else {
                	// stop if the game doesn't want to be played again
                	playOn = false;
                }
            } while (playOn);
        } while (!beenden);
    }

	public void terminate() {
		gameManager.terminate();
	}
}
