package gameEngine;

import java.io.PrintStream;
import java.util.Scanner;

import painting.GamePanel;

public interface PanelManager {
	void showGraphicsPanel();
	void showTextPanel();
	GamePanel getGraphicsPanel();
	PrintStream getTextOut();
	Scanner getTextIn();
}
