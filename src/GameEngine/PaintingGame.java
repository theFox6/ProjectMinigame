package GameEngine;

import java.awt.event.KeyAdapter;

public abstract class PaintingGame extends KeyAdapter implements Runnable {
    public boolean replayable = true;
    public final String name;
    
    public PaintingGame(String name) {
        this.name = name;
    }
}
