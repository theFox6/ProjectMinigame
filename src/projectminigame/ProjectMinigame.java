/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectminigame;

import GameEngine.PrintingGame;
import Games.RPS;

/**
 *
 * @author JFuchs
 */
public class ProjectMinigame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PrintingGame game = new RPS(System.out);
        
        System.out.println(game.name);
        System.out.println("---------------------------------");
        game.run();
    }
    
}
