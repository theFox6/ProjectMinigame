package GameEngine;

import java.util.Random;

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
    
    public static final Random rng = new Random();
    
    /**
     * <p>the basic constructor of a game</p>
     * It can be called in another constructor with {@code super("epic game")}.
     * @param name the name of the game displayed in the menu
     */
    public Game(String name) {
    	//set the name
        this.name = name;
        //set the Random seed to the current nanosecond time
        rng.setSeed(System.nanoTime());
    }
    
    /**
     * returns a random number between 0 (inclusive) and the specified value (exclusive)
     * @param bound the first integer that is larger than the range
     * @return a random integer from the rng
     */
    protected int randomInt(int bound) {
    	return rng.nextInt(bound);
    }
}
