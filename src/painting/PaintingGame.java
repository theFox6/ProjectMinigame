package painting;

import GameEngine.Game;
import java.awt.event.KeyListener;

public abstract class PaintingGame extends Game implements KeyListener, Runnable, Paintable {
    public boolean replayable = true;
    public volatile boolean running = true;

    public PaintingGame(String name) {
        super(name);
    }
}
