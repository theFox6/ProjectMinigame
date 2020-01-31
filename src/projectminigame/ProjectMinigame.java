/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectminigame;

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
        setVisible(true);
    }
    
    @Override
    public void run() {
        Menu.runStream(pta.output, pta.input);
        dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ProjectMinigame().run();
    }
    
}
