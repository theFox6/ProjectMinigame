package GameEngine;

import painting.GamePanel;
import printing.PrintingGame;
import painting.PaintingGame;
import painting.Paintable;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import textAreaIO.PrintingTextArea;

public class GameManager implements KeyListener,Paintable,Runnable {
    
    private GamePanel gp;
    public PaintingGame currentG;
    public PrintingTextArea pa;
    public PrintingGame currentT;
    
    // game thread
    private Thread gameThread;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;
    
    private void checkRunning() throws AlreadyRunningException {
        if (currentG != null || currentT != null)
            throw new AlreadyRunningException();
    }
    
    public void prepare(PaintingGame game) throws AlreadyRunningException {
        checkRunning();
        currentG = game;
    }
    
    public void prepare(PrintingGame game) throws AlreadyRunningException {
        checkRunning();
        currentT = game;
    }
    
    public void prepareGame(Game game) throws AlreadyRunningException {
        if (game instanceof PaintingGame)
            prepare((PaintingGame) game);
        else if (game instanceof PrintingGame)
            prepare((PrintingGame) game);
    }
    
    public void start() throws AlreadyRunningException {
        if (gameThread != null)
            if (gameThread.isAlive())
                throw new AlreadyRunningException();
        if (currentG != null) {
            gameThread = new Thread(this);
            gameThread.start();
        } else if (currentT != null) {
            gameThread = new Thread(currentT,"game thread");
            gameThread.run();
            currentT = null;
        } else
            throw new NullPointerException("no game prepared");
    }
    
    public void start(PaintingGame game) throws AlreadyRunningException {
        checkRunning();
        currentG = game;
        gameThread = new Thread(this,"game thread");
        gameThread.start();
    }
    
    public void start(PrintingGame game) throws AlreadyRunningException {
        checkRunning();
        currentT = game;
        gameThread = new Thread(currentT,"game thread");
        gameThread.run();
    }
    
    @Override
    public void run() {
        long start;
        long elapsed;
        long wait;

        // game loop
        while (currentG.running) {
                start = System.nanoTime();

                update();
                paint(gp.getBufferGraphics());
                gp.drawToScreen();

                elapsed = System.nanoTime() - start;

                wait = targetTime - elapsed / 1000000;
                if (wait < 0)
                        wait = 5;

                try {
                        Thread.sleep(wait);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
        }
        currentG = null;
    }

    public void update() {
        if (currentG != null)
            currentG.run();
    }

    @Override
    public void paint(Graphics2D g) {
        if (currentG != null)
            currentG.paint(g);
        g.dispose();
    }

    @Override
    public void keyPressed(KeyEvent key) {
        if (currentG != null)
            currentG.keyPressed(key);
    }

    @Override
    public void keyTyped(KeyEvent key) {
        if (currentG != null)
         currentG.keyTyped(key);
    }

    @Override
    public void keyReleased(KeyEvent key) {
        if (currentG != null)
            currentG.keyReleased(key);
    }

    public static class AlreadyRunningException extends Exception {
		private static final long serialVersionUID = -3213271580040806881L;
    }

	public void terminate() {
		gameThread.interrupt();
	}
}
