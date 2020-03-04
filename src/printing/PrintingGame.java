package printing;

import GameEngine.Game;
import java.io.PrintStream;
import java.util.Scanner;

public abstract class PrintingGame extends Game {
    protected PrintStream text;
    protected Scanner input;
    
    public PrintingGame(String name, PrintStream out, Scanner in) {
        super(name);
        text = out;
        input = in;
    }
    
    protected void p(String msg) {
        text.println(msg);
    }
}
