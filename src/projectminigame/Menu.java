package projectminigame;

import Games.battleships.Battleships;
import GameEngine.Game;
import GameEngine.GameManager;
import printing.PrintingGame;
import Games.Dicegame;
import Games.Mathgame;
import Games.RPS;
import Games.Slotgame;
import Games.TTT;
import gameEngine.PanelManager;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.PrintStream;
import java.util.Scanner;

import painting.GamePanel;
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
	private PanelManager panelManager;
    
    /**
     * prepare the Menu
     */
    public Menu(GameManager gm, PanelManager pm) {
    	//store the output and input
        out = pm.getTextOut();
        in = pm.getTextIn();
        //add all games to an array
        PrintingGame[] tg = {
            new RPS(),
            new TTT(),
            new Mathgame(),
            new Battleships(),
            new Slotgame(),
            new Dicegame()
        };
        //set the game array
		printingGames = tg;
        PaintingGame[] gg = {
        		new QuitOption()
        };
        paintingGames = gg;
        gameManager = gm;
        panelManager = pm;
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
    private class QuitOption extends PaintingGame {
        private int selection;
		private boolean first;

		public QuitOption() {
        	//set up the text
            super("Minigame Sammlung schließen");
            //don't ask if the user wants to play it again
            replayable = false;
        }
		
		public void prepare() {
			super.prepare();
			selection = 1;
			first = true;
		}

        /**
         * close the menu
         */
        @Override
        public void run() {}

		@Override
		public void keyReleased(KeyEvent e) {
			if (first) {
				first = false;
				return;
			}
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_LEFT) {
				selection = 1;
			} else if (key == KeyEvent.VK_RIGHT) {
				selection = 2;
			} else if (key == KeyEvent.VK_ENTER) {
				beenden = (selection == 1);
				running = false;
			}
		}

		@Override
		public void paint(Graphics2D g) {
			g.setBackground(Color.WHITE);
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(5));
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
			g.clearRect(0,0,GamePanel.WIDTH,GamePanel.HEIGHT);
			
			final int cx = GamePanel.CENTER.x;
			final int cy = GamePanel.CENTER.y;
			g.drawString("Wirklich beenden?",cx - 200, cy - 100);
			g.drawString("Ja",cx - 180 ,cy);
			g.drawString("Nein",cx + 10,cy);
			g.drawLine(cx - 400 + selection * 200, cy + 10,cx - 300 + selection * 200, cy + 10);
		}
    }
    
    /**
     * display the choice of games and ask the user what to play
     * @return whatever the user chose
     */
    public int menuFromStream() {
    	panelManager.showTextPanel();
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
                    out.println("Fehler: ein Spiel läuft noch");
                    System.err.println("Fehler: ein Spiel läuft noch");
                }
            	//the game's header
                out.println(game.name);
                out.println();
                //run the game
                try {
                    gameManager.start();
                } catch (GameManager.AlreadyRunningException ex) {
                    out.println("Fehler: ein Spiel läuft bereits");
                    System.err.println("Fehler: ein Spiel läuft bereits");
                }
                panelManager.showTextPanel();
                
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
