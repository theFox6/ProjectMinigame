package GameEngine;

import java.io.PrintStream;
import java.util.Scanner;

public abstract class PrintingGame implements Runnable {

    /**
     * whether the menu will ask if you want to play again
     */
    public boolean replayable = true;

    /**
     * the games name that is shown in the menu
     */
    public final String name;

    /**
     * the output stream
     */
    protected PrintStream text;

    /**
     * the input scanner
     */
    protected Scanner input;
    
    /**
     * create a new game
     * @param name the displayed name of the game
     */
    public PrintingGame(String name) {
        this.name = name;
    }
    
    /**
     * create a new game
     * @param name the displayed name of the game
     * @param out the output stream
     * @param in the input scanner
     * @deprecated
     */
    @Deprecated
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public PrintingGame(String name, PrintStream out, Scanner in) {
        this.name = name;
        setStreams(out, in);
    }
    
    /**
     * set the out and in of the game
     * this is called by the engine to setup games
     * @param out the output stream
     * @param in the input scanner
     */
    public void setStreams(PrintStream out, Scanner in) {
        text = out;
        input = in;
    }
    
    /**
     * an abbreviation for text.println
     * @param msg the text to print
     */
    protected void p(String msg) {
        text.println(msg);
    }
}
