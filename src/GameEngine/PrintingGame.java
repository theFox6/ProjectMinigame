package GameEngine;

import java.io.PrintStream;

public abstract class PrintingGame implements Runnable {
    public final String name;
    protected PrintStream text;
    
    public PrintingGame(String name, PrintStream out) {
        this.name = name;
        text = out;
    }
    
    protected void p(String msg) {
        text.println(msg);
    }
}
