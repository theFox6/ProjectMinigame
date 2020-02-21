/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectminigame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
