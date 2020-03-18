package projectminigame;

import painting.GamePanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import textAreaIO.PrintingTextArea;

/**
 * the runner of the whole application
 * and also the main window
 */
public class ProjectMinigame extends JFrame implements Runnable {
	private static final long serialVersionUID = -4818574648486440494L;
	/**
	 * the text output
	 */
	private final PrintingTextArea pta = new PrintingTextArea();
	private Menu menu = null;
    
    /**
     * set up the main frame
     */
    public ProjectMinigame() {
        super("ProjectMinigame");
    	//add the scrollable text area
        add(new JScrollPane(pta));
        pack();
        //setResizable(false);
        //setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	if (menu != null)
            		menu.terminate();
                System.exit(0);
            }
        });
        try {
            setIconImage(ImageIO.read(ProjectMinigame.class.getResource("Jcontroller.png")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Dimension scsize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(GamePanel.WIDTH,GamePanel.HEIGHT);
        setLocation(scsize.width / 2 - GamePanel.WIDTH / 2, scsize.height / 2 - GamePanel.HEIGHT / 2);
        setVisible(true);
    }
    
    /**
     * run the menu then close
     */
    @Override
    public void run() {
    	//setup the menu and run it
        menu = new Menu(pta);
        menu.runStream();
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
