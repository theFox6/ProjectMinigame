package GameEngine;

import java.io.PrintStream;
import java.util.Scanner;

public abstract class PrintingGame implements Runnable {
    public final String name;
    protected PrintStream text;
    protected Scanner input;
    
    public PrintingGame(String name, PrintStream out, Scanner in) {
        this.name = name;
        text = out;
        input = in;
    }
    
    protected void p(String msg) {
        text.println(msg);
    }
}
