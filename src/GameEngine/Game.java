package GameEngine;

public abstract class Game implements Runnable {
    public boolean replayable = true;
    public final String name;
    
    public Game(String name) {
        this.name = name;
    }
}
