package projectminigame;

import GameEngine.PrintingGame;
import Games.Dicegame;
import Games.Mathgame;
import Games.RPS;
import Games.TTT;
import java.io.PrintStream;
import java.util.Scanner;

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
    PrintingGame[] games;
    
    /**
     * prepare the Menu
     * @param out the stream to print the menu's contents to 
     * @param in the scanner to read the user inputs from
     */
    public Menu(PrintStream out, Scanner in) {
    	//store the output and input
        this.out = out;
        this.in = in;
        //add all games to an array
        PrintingGame[] g = {
            new QuitOption(),
            new RPS(),
            new TTT(),
            new Mathgame(),
            new Dicegame()
        };
        //set the game array
        games = g;
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
        for (PrintingGame g : games)
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
            //check if the choice was ok
            if (choice<0 || choice>games.length) {
                out.println("Bitte gib eine Zahl zwischen 0 und " + games.length + " ein.");
                continue;
            }
            //select the game
            PrintingGame game = games[choice];
            //setup the game
            game.setStreams(out,in);
            //start playing
            do {
            	//the game's header
                out.println(game.name);
                out.println();
                //run the game
                game.run();

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
}
