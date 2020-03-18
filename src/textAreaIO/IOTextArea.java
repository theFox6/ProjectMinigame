package textAreaIO;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import javax.swing.JTextArea;

/**
 * a JTextArea that text can be written to and that can read user inputs
 */
public class IOTextArea extends JTextArea {
	private static final long serialVersionUID = -3174114718655799243L;
	
	/**
	 * a queue transferring the user inputs to the listeners
	 */
	private TransferQueue<String> inputQueue = new LinkedTransferQueue<>();
	
	/**
	 * where the user input starts
	 */
	private int inputStart = 0;
	
	/**
	 * sets up a new TextArea and the PrintStream
	 */
	public IOTextArea() {
		//set up the text area
		super();
		//disable editing
		setEditable(false);
		//capture important key presses
		setUpKeyListener();
		//make sure the user doesn't change the editing position
		addCaretListener((e) -> {
			//no need to check if there is no caret
			if (!isEditable())
				return;
			//check where the editing position is at
			int cpos = getCaretPosition();
			//if its before the user input put it back
			if (cpos < inputStart)
				setCaretPosition(inputStart);
		});
		//set the font large and usable for coordinate systems
        setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
	}

	/**
	 * capture important key presses
	 */
	private void setUpKeyListener() {
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (!isEditable())
					return;
				int kc = e.getKeyCode();
				if (kc == KeyEvent.VK_PAGE_UP || kc == KeyEvent.VK_PAGE_DOWN || kc == KeyEvent.VK_UP || kc == KeyEvent.VK_DOWN) {
					//make sure the line isn't changed
				 	e.consume();
				} else if (kc == KeyEvent.VK_LEFT) {
					//don't go outside the editing bounds
					if (getCaretPosition() == inputStart)
						e.consume();
				} else if (kc == KeyEvent.VK_BACK_SPACE) {
					//don't delete outside the editing bounds
					if (getCaretPosition() <= inputStart)
						e.consume();
				} else if (kc == KeyEvent.VK_ENTER) {
					//make sure the caret is at the end
					setCaretPosition(getText().length());
				}
			}
			
			public void keyTyped(KeyEvent e) {
				if (!isEditable())
					return;
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					//get the text
					String all = getText();
					//try to give the input to a listener
					inputQueue.tryTransfer(all.substring(inputStart,all.length()));
					if (inputQueue.hasWaitingConsumer())
						//if there are more waiting for input reset the start position
						inputStart = getCaretPosition();
					else
						//if there are none waiting disallow inputs
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
	 * <p>enable the user input and wait until the user presses enter</p>
	 * @return the text the user entered
	 * @throws InterruptedException
	 * 	if the thread is interrupted while waiting for user input
	 */
	public String readln() throws InterruptedException {
		//enable the input
		setEditable(true);
		//make sure it is actually editable
		updateUI();
		//check where the input starts
		inputStart  = getText().length();
		//put the caret
		setCaretPosition(inputStart);
		//wait for the input
		return inputQueue.take();
	}
	
	/**
	 * remove all text from the text area
	 */
	public void clear() {
		//replace it with no text
		setText("");
	}
}
