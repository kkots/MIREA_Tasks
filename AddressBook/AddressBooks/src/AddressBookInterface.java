import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class AddressBookInterface extends JComponent implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inputText;
	private JTextArea outputText;
	public int outputRows;
	public int outputColumns;
	public int outputLength = 0;
	private String[] output;
	private ActionListener listener;
	private ListDisplayListener listDisplayListener;
	private boolean isDisplayingLongOutput = false;
	
	public AddressBookInterface(int w, int h) {
		outputRows=w;
		outputColumns=h;
		inputText = new JTextField(w);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		output = new String[outputRows];
		add(inputText);
		outputText = new JTextArea(h, w);
		displayText();
		add(outputText);
		inputText.addActionListener(this);
	}
	public void setActionListener(ActionListener listener) {
		this.listener=listener;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (inputText.getText().equals("exit")) {
			isDisplayingLongOutput = false;
			if (listener != null) listener.actionPerformed(new ActionEvent(this, 0, "exit"));
		} else if (isDisplayingLongOutput) {
			processDisplay();
		} else if (listener!=null) listener.actionPerformed(new ActionEvent(this, 0, inputText.getText()));
		inputText.setText("");
		displayText();
	}
	
	public void displayLongOutput(ListDisplayListener listDisplayListener) {
		this.listDisplayListener = listDisplayListener;
		isDisplayingLongOutput = true;
		processDisplay();
	}
	
	private void processDisplay() {
		String[] ar = listDisplayListener.getNext(outputRows-1);
		if (output[outputLength - 1].equals("press enter to continue or type exit")) outputLength--;
		int i;
		for (i = 0; i < ar.length && ar[i] != null; i++) {
			addOutput(ar[i]);
		}
		if (i == 0) {
			isDisplayingLongOutput = false;
			listener.actionPerformed(new ActionEvent(this, 0, "finish"));
		} else {
			addOutput("press enter to continue or type exit");
		}
	}

	public void shiftOutput(int steps) {
		for (int i = 0; i < outputLength - steps; i++) {
			output[i] = output[i + steps];
		}
		outputLength -= steps;
	}

	public void addOutput(String... strings) {
		int startIndex = 0;
		if (strings.length > outputRows - outputLength) {
			if (strings.length > outputRows) {
				outputLength = 0;
				startIndex = strings.length - outputRows;
			} else {
				shiftOutput(strings.length - (outputRows - outputLength));
			}
		}
		for (; startIndex < strings.length; startIndex++) {
			output[outputLength] = strings[startIndex];
			outputLength++;
		}
	}

	public void appendOutput(String txt) {
		if (outputLength == 0)
			addOutput(txt);
		else
			output[outputLength - 1] += txt;
	}

	public void displayText() {
		String txt = "";
		for (int i = 0; i < outputLength; i++) {
			txt += output[i] + "\n";
		}
		outputText.setText(txt);
	}
}
