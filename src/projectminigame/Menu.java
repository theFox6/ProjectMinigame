package projectminigame;

import GameEngine.Game;
import GameEngine.GameManager;
import printing.PrintingGame;
import Games.Mathgame;
import Games.RPS;
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

public class Menu {
    private final PrintStream out;
    private final Scanner in;
    PrintingGame[] printingGames;
    PaintingGame[] paintingGames;
    private GameManager gameManager;
	private PanelManager panelManager;
    
    public Menu(GameManager gm, PanelManager pm) {
        this.out = pm.getTextOut();
        this.in = pm.getTextIn();
        PrintingGame[] tg = {
            new RPS(out,in),
            new TTT(out,in),
            new Mathgame(out,in)
        };
        printingGames = tg;
        PaintingGame[] gg = {
        		new QuitOption()
        };
        paintingGames = gg;
        gameManager = gm;
        panelManager = pm;
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
    private class QuitOption extends PaintingGame {
        private int selection;
		private boolean first;

		public QuitOption() {
            super("Minigame Sammlung schließen");
            replayable = false;
        }
		
		public void prepare() {
			super.prepare();
			selection = 1;
			first = true;
		}

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
    
    public int MenuFromStream() {
    	panelManager.showTextPanel();
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
                    out.println("Fehler: ein Spiel läuft noch");
                    System.err.println("Fehler: ein Spiel läuft noch");
                }
                out.println(game.name);
                out.println();
                try {
                    gameManager.start();
                } catch (GameManager.AlreadyRunningException ex) {
                    out.println("Fehler: ein Spiel läuft bereits");
                    System.err.println("Fehler: ein Spiel läuft bereits");
                }
                panelManager.showTextPanel();
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

	public void terminate() {
		gameManager.terminate();
	}
}
