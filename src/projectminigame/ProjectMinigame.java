package projectminigame;

import painting.GamePanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import GameEngine.GameManager;
import gameEngine.PanelManager;
import textAreaIO.PrintingTextArea;

/**
 *
 * @author JFuchs
 */
public class ProjectMinigame extends JFrame implements Runnable, PanelManager {
	private static final long serialVersionUID = -4818574648486440494L;
	private final GameManager gm = new GameManager(this);
	private final PrintingTextArea pta = new PrintingTextArea();
	private final GamePanel gp = new GamePanel(gm);
	private Menu menu = null;
	private final JScrollPane scrollPane;
    
    /**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
	    new ProjectMinigame().run();
	}

	public ProjectMinigame() {
        super("ProjectMinigame");
        scrollPane = new JScrollPane(pta);
        add(scrollPane);
        scrollPane.setLocation(0,0);
        scrollPane.setSize(GamePanel.WIDTH-5, GamePanel.HEIGHT-30);
        add(gp);
        gp.setLocation(0,0);
        showTextPanel();
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
        setLocation(scsize.width / 2 - GamePanel.CENTER.x, scsize.height / 2 - GamePanel.CENTER.y);
        setVisible(true);
    }
    
    @Override
    public void run() {
        menu = new Menu(gm, this);
        menu .runStream();
        dispose();
    }

    @Override
	public void showGraphicsPanel() {
		scrollPane.setVisible(false);
		pta.setEnabled(false);
		//scrollPane.setOpaque(true);
		gp.setVisible(true);
		gp.setEnabled(true);
		//gp.setOpaque(false);
		gp.grabFocus();
	}

	@Override
	public void showTextPanel() {
		gp.setVisible(false);
		gp.setEnabled(false);
		//gp.setOpaque(true);
		scrollPane.setVisible(true);
		pta.setEnabled(true);
		//scrollPane.setOpaque(false);
		pta.grabFocus();
	}

	@Override
	public GamePanel getGraphicsPanel() {
		return gp;
	}

	@Override
	public PrintStream getTextOut() {
		return pta.output;
	}

	@Override
	public Scanner getTextIn() {
		return pta.input;
	}
    
}
