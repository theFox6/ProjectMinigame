package textAreaIO;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.CharBuffer;
import java.util.Scanner;

/**
 * an IOTextArea that receives text from a PrintStream
 * and has a Scanner to read user inputs
 */
public class PrintingTextArea extends IOTextArea implements Closeable {
	private static final long serialVersionUID = 7000005244229705652L;
	
	/**
	 * data printed with this stream is displayed in the text area
	 */
	public final PrintStream output = new PrintStream(new TextAreaStream());
	
	/**
	 * a scanner to receive user inputs from the text area
	 */
	public final Scanner input = new Scanner(new Readable() {
		/**
		 * read some text
		 */
		public int read(CharBuffer cb) throws IOException {
			String text;
			try {
				text = readln();
			} catch (InterruptedException e) {
				throw new IOException("reading interrupted",e);
			}
			//put the text in the char buffer
			cb.put(text);
			//return how many characters were read
			return text.length();
		}
	});


	/**
	 * stop accepting outputs and input requests
	 */
	public void close() throws IOException {
		input.close();
		output.close();
	}

	/**
	 * an output stream that writes to the IOTextArea
	 */
	private class TextAreaStream extends OutputStream {
		private volatile boolean closed;
	
		/**
		 * @throws IOException if the stream is closed
		 */
		private void ensureOpen() throws IOException {
	        if (closed) {
	            throw new IOException("Stream closed");
	        }
	    }
	
		@Override
		public void write(int b) throws IOException {
			ensureOpen();
			PrintingTextArea.this.write(b);
		}
		
		@Override
		public void write(byte[] b, int off, int len) throws IOException {
			ensureOpen();
			PrintingTextArea.this.write(new String(b,off,len));
		}
		
		@Override
		public void write(byte[] b) throws IOException {
			ensureOpen();
			PrintingTextArea.this.write(new String(b));
		}
		
		/**
		 * stop accepting outputs
		 */
		@Override
		public void close() {
			closed = true;
		}
	}
}
