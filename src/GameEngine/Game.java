package GameEngine;

/**
 * what all Games have in common
 */
public abstract class Game implements Runnable {
    /**
     * Whether to ask if the user wants to play again.
     */
    public boolean replayable = true;
    
    /**
     * the name of the game displayed in the menu
     */
    public final String name;
    
    /**
     * <p>the basic constructor of a game</p>
     * It can be called in another constructor with {@code super("epic game")}.
     * @param name the name of the game displayed in the menu
     */
    public Game(String name) {
        this.name = name;
    }
}
