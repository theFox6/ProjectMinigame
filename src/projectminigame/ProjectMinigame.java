package projectminigame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import textAreaIO.PrintingTextArea;

/**
 * the runner of the whole application
 * and also the main window
 */
public class ProjectMinigame extends JFrame implements Runnable {
    /**
     * the text output
     */
    private final PrintingTextArea pta = new PrintingTextArea();
    
    /**
     * set up the main frame
     */
    public ProjectMinigame() {
    	//let's add the scrollable text area
        add(new JScrollPane(pta));
        pack();
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }
    
    /**
     * run the menu then close
     */
    @Override
    public void run() {
    	//setup the menu and run it
        new Menu(pta.output, pta.input).runStream();
        // close the window
        dispose();
    }

    /**
     * run the application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	//run the main frame
        new ProjectMinigame().run();
    }
    
}
