package painting;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import gameEngine.GameManager;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = -7607435223209665995L;

	// dimensions
	public static final int WIDTH = 860;
	public static final int HEIGHT = 480;
	public static final Point CENTER = new Point(WIDTH / 2, HEIGHT / 2);

	// image
	private BufferedImage image;

	public GamePanel(GameManager gsm) {
		super();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        addKeyListener(gsm);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}
        
    public Graphics2D getBufferGraphics() {
        return image.createGraphics();
    }

	public void drawToScreen() {
		if (!isVisible())
			return;
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}
}
