package textAreaIO;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import javax.swing.JTextArea;

/**
 * a JTextArea that receives text from a PrintStream
 */
public class IOTextArea extends JTextArea {
	private static final long serialVersionUID = -3174114718655799243L;
	private TransferQueue<String> inputQueue = new LinkedTransferQueue<>();
	private int inputStart = 0;
	
	/**
	 * sets up a new TextArea and the PrintStream
	 */
	public IOTextArea() {
		super();
		setEditable(false);
		setUpKeyListener();
		addCaretListener((e) -> {
			if (!isEditable())
				return;
			int cpos = getCaretPosition();
			if (cpos < inputStart)
				setCaretPosition(inputStart);
		});
	}

	private void setUpKeyListener() {
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (!isEditable())
					return;
				int kc = e.getKeyCode();
				if (kc == KeyEvent.VK_PAGE_UP || kc == KeyEvent.VK_PAGE_DOWN || kc == KeyEvent.VK_UP || kc == KeyEvent.VK_DOWN) {
				 	e.consume();
				} else if (kc == KeyEvent.VK_LEFT) {
					if (getCaretPosition() == inputStart)
						e.consume();
				} else if (kc == KeyEvent.VK_BACK_SPACE) {
					if (getCaretPosition() <= inputStart)
						e.consume();
				} else if (kc == KeyEvent.VK_ENTER) {
					setCaretPosition(getText().length());
				}
			}
			
			public void keyTyped(KeyEvent e) {
				if (!isEditable())
					return;
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					String all = getText();
					inputQueue.tryTransfer(all.substring(inputStart,all.length()));
					if (inputQueue.hasWaitingConsumer())
						inputStart = getCaretPosition();
					else
						setEditable(false);
				}
			}
		});
	}

	/**
	 * write a character
	 * @param b the character to append
	 */
	public void write(int b) {
		append(new String(Character.toChars(b)));
	}
	
	/**
	 * write a string
	 * @param s the string to append
	 */
	public void write(String s) {
		append(s);
	}
	
	/**
	 * enable the user input and wait until the user presses enter
	 * @return the text the user entered
	 * @throws InterruptedException
	 * 	if the thread is interrupted while waiting for user input
	 */
	public String readln() throws InterruptedException {
		setEditable(true);
		updateUI();
		inputStart  = getText().length();
		setCaretPosition(inputStart);
		return inputQueue.take();
	}
	
	public void clear() {
		setText("");
	}
}
