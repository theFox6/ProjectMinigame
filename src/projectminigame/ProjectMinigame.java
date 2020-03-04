/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author JFuchs
 */
public class ProjectMinigame extends JFrame implements Runnable {
    
    private final PrintingTextArea pta = new PrintingTextArea();
    
    public ProjectMinigame() {
        super("ProjectMinigame");
        setContentPane(new JScrollPane(pta));
        pack();
        //setResizable(false);
        //setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
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
    
    @Override
    public void run() {
        new Menu(pta.output, pta.input).runStream();
        dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ProjectMinigame().run();
    }
    
}
