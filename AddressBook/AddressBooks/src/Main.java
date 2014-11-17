import java.awt.Container;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class Main extends JFrame {
	// https://docs.oracle.com/javase/8/docs/api/allclasses-noframe.html
	private static final long serialVersionUID = 1L;
	public static Main instance;// this is a singleton class
	private AddressBookInterface component;
	private AddressBookManager manager;

	public static void main(String[] args) {
		instance = new Main();
	}

	public Main() {
		setTitle("Address Book");
		setVisible(true);
		addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}
		});
		Container pane = getContentPane();
		component = new AddressBookInterface(20, 20);
		pane.add(component);
		manager = new AddressBookManager(component);
		pack();
	}
	public static void trace(Object...args) {
		String str = "";
		for (int i=0; i < args.length; i++) {
			str += String.valueOf(args[i])+", ";
		}
		str = str.substring(0, str.length() - 2);
		System.out.println(str);
	}
}
