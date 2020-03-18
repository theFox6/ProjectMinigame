package painting;

import GameEngine.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class PaintingGame extends Game implements KeyListener, Runnable, Paintable {
    public volatile boolean running = true;

    public PaintingGame(String name) {
        super(name);
    }
    
    public void prepare() {
    	running = true;
	}
    
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
	public void keyTyped(KeyEvent arg0) {}
    @Override
    public void keyReleased(KeyEvent e) {}

}
