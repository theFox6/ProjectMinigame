package GameEngine;

import java.io.PrintStream;
import java.util.Scanner;

public abstract class PrintingGame implements Runnable {
    public boolean replayable = true;
    public final String name;
    protected PrintStream text;
    protected Scanner input;
    
    public PrintingGame(String name) {
        this.name = name;
    }
    
    @Deprecated
    public PrintingGame(String name, PrintStream out, Scanner in) {
        this.name = name;
        setStreams(out, in);
    }
    
    public void setStreams(PrintStream out, Scanner in) {
        text = out;
        input = in;
    }
    
    protected void p(String msg) {
        text.println(msg);
    }
}
