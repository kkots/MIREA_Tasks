import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddressBookManager implements ActionListener {
	private AddressBook book;
	private String status = null;
	private AddressBookInterface component;
	Map<String, StateProgram> stateMap=new HashMap<String, StateProgram>();
	
	public AddressBookManager(AddressBookInterface component){
		this.component=component;
		book=new AddressBook();
		for (int i = 0; i < 30; i++) {
			book.addAddress("name" + i, "telephone" + i, "email" + i);
		}

		stateMap.put("add", new StateAdd());
		stateMap.put("search", new StateSearch());
		stateMap.put("showall", new StateShowAll());
		stateMap.put("delete", new StateDelete());
		stateMap.put("edit", new StateEdit());
		stateMap.put("save", new StateSave());
		stateMap.put("load", new StateLoad());
		stateMap.put("clearall", new StateClearAll());
		
		component.setActionListener(this);
		component.addOutput("type help for a list of commands", "you initially get a book with 30 filled records");
		component.displayText();
	}

	public void actionPerformed(ActionEvent e) {
		String txt=e.getActionCommand();
		if (txt.equals("finish")) {
			StateProgram program = stateMap.get(status);
			if (program != null) program.forceFinish();
			status = null;
		} else if (txt.equals("exit")) {
			StateProgram program = stateMap.get(status);
			if (program != null) program.forceFinish();
			status = null;
			component.addOutput("-exit");
		} else if (status == null) {
			if (txt.equals("help")) {
				component.addOutput("-help", "add (adds new record)",
						"search (looks for a name)",
						"showall (displays all records)",
						"delete (deletes a record)",
						"edit (edits a record)",
						"save (save address book to a file)",
						"load (loads address book from a file)",
						"clearall (removes all records)",
						"exit (exits any command at any time)");
			} else {
				StateProgram program=stateMap.get(txt);
				if(program != null) {
					component.addOutput("-"+txt);
					if (!program.run(null)) status = txt;
				} else
					component.addOutput("unknown command. Type help for a list");
			}
		} else {
			StateProgram program = stateMap.get(status);
			boolean isFinished = program.run(txt);
			if (isFinished) {
				status = null;
			}
		}
	}
	
	private interface StateProgram {
		public boolean run(String input);//returns true if state is exiting
		public void forceFinish();
	}
	
	private class StateAdd implements StateProgram {
		private String state;
		private String nameEntered, telephoneEntered;
		public boolean run(String input){
			if (state == null) {
				component.addOutput("enter name");
				state = "enter name";
			} else if (state.equals("enter name")) {
				nameEntered = input;
				component.appendOutput(": \""+input+"\"");
				component.addOutput("enter telephone");
				state = "enter telephone";
			} else if (state.equals("enter telephone")) {
				telephoneEntered = input;
				component.appendOutput(": \""+input+"\"");
				component.addOutput("enter email");
				state = "enter email";
			} else if (state.equals("enter email")) {
				book.addAddress(nameEntered, telephoneEntered, input);
				component.addOutput("record created. Index=" + (book.getCount()-1));
				state = null;
				return true;
			}
			return false;
		}
		public void forceFinish() {
			state = null;
			status = null;
		}
	}
	
	
	private class StateSearch implements StateProgram {
		private String state;
		private String nameEntered;
		private SearchDisplayer searchDisplayer;
		public StateSearch() {
			searchDisplayer = new SearchDisplayer();
		}
		public boolean run(String input) {
			if (state == null) {
				component.addOutput("enter a name or a part of it");
				state = "enter name";
			} else if (state.equals("enter name")) {
				nameEntered=input.toLowerCase();
				state = "displaying";
				searchDisplayer.restart();
				component.displayLongOutput(searchDisplayer);
			} else {
				state = null;
				return true;
			}
			return false;
		}
		public void forceFinish() {
			state = null;
			status = null;
		}
		private class SearchDisplayer implements ListDisplayListener {
			private int index;
			private int totalCount;
			public void restart() {
				index = 0;
				totalCount = 0;
			}
			public String[] getNext(int maximum) {
				int i;
				String[] ar = new String[maximum];
				int count = 0;
				for (i = 0; i + index < book.addresses.size(); i++) {
					Address address = book.addresses.get(i + index);
					if (address.name.toLowerCase().contains(nameEntered)) {
						ar[count] = address.name + ". Index=" + (i + index);
						count++;
						if (count >= maximum) {
							index += i;
							return ar;
						}
					}
				}
				totalCount += count;
				index += i;
				if(totalCount == 0){
					forceFinish();
					component.addOutput("found nothing");
				}
				return ar;
			}
		}
	}
	
	private class StateShowAll implements StateProgram {
		private boolean isDisplaying = false;
		private Displayer displayer;
		public StateShowAll() {
			displayer = new Displayer();
		}
		public boolean run(String input) {
			if (!isDisplaying) {
				displayer.index = 0;
				isDisplaying = true;
				component.displayLongOutput(displayer);
			} else {
				isDisplaying = false;
				return true;
			}
			return false;
		}
		private class Displayer implements ListDisplayListener {
			public int index;
			public String[] getNext(int maximum) {
				int count;
				String[] ar = new String[maximum];
				for (count = 0; index + count < book.addresses.size(); count++) {
					int i = index + count;
					Address address = book.addresses.get(i);
					ar[count*3] = address.name + ". Index=" + (i);
					ar[count*3 + 1] = address.telephone;
					ar[count*3 + 2] = address.email;
					if (count + 3 > maximum) {
						index += i;
						return ar;
					}
				}
				index += count;
				return ar;
			}
		}
		public void forceFinish() {
			isDisplaying = false;
			status = null;
		}
	}
	
	private class StateDelete implements StateProgram {
		private boolean isEnteringIndex = false;
		public boolean run(String input) {
			if (!isEnteringIndex) {
				component.addOutput("enter index of address to delete");
				isEnteringIndex = true;
			} else {
				int result = parseInt(input);
				if (result != -1) {
					component.addOutput("deleted record #" + result);
					book.delete(result);
					isEnteringIndex = false;
					return true;
				}
			}
			return false;
		}
		public void forceFinish() {
			isEnteringIndex = false;
			status = null;
		}
	}
	
	private class StateEdit implements StateProgram {
		private String state;
		private int indexEntered;
		private String nameEntered, telephoneEntered;
		public boolean run(String input){
			if (state == null) {
				component.addOutput("enter index of address to edit");
				state = "enter index";
			} else if (state.equals("enter index")) {
				int result = parseInt(input);
				if (result != -1) {
					indexEntered = result;
					state = "enter name";
					component.appendOutput(": "+indexEntered);
					component.addOutput("enter name");
				}
			} else if (state.equals("enter name")) {
				nameEntered = input;
				component.appendOutput(": \""+input+"\"");
				component.addOutput("enter telephone");
				state = "enter telephone";
			} else if (state.equals("enter telephone")) {
				telephoneEntered = input;
				component.appendOutput(": \""+input+"\"");
				component.addOutput("enter email");
				state = "enter email";
			} else if (state.equals("enter email")) {
				Address addr = book.getByIndex(indexEntered);
				addr.name = nameEntered;
				addr.telephone = telephoneEntered;
				addr.email = input;
				component.addOutput("record edited. Index=" + indexEntered);
				state = null;
				return true;
			}
			return false;
		}
		public void forceFinish() {
			state = null;
			status = null;
		}
	}

	private class StateSave implements StateProgram {
		private boolean isEnteringPath;
		public boolean run(String input){
			if (!isEnteringPath) {
				component.addOutput("enter path to FILE. Example","C:/folder1/folder2/outputFile.txt");
				isEnteringPath = true;
			} else {
				Path path = Paths.get(input);
				List<String> list = Arrays.asList(book.printAll());
				Charset charset = Charset.forName("UTF-8");
				try {
					Files.write(path, list, charset);
					component.addOutput("File saved successfully");
					isEnteringPath = false;
					status = null;
				} catch (IOException e) {
					component.addOutput("Error: "+e.getMessage());
				}
			}
			return false;
		}
		public void forceFinish() {
			isEnteringPath = false;
			status = null;
		}
	}
	
	private class StateLoad implements StateProgram {
		private boolean isEnteringPath;
		public boolean run(String input){
			if (!isEnteringPath) {
				component.addOutput("Warning, all unsaved data will be lost.", "enter path to FILE. Example","C:/folder1/folder2/outputFile.txt");
				isEnteringPath = true;
			} else {
				Path path = Paths.get(input);
				Charset charset = Charset.forName("UTF-8");
				try {
					List<String> list = Files.readAllLines(path, charset);
					book = new AddressBook();
					for (int i = 0; i < list.size(); i += 3) {
						book.addAddress(list.get(i), list.get(i+1), list.get(i+2));
					}
					component.addOutput("File loaded successfully");
					isEnteringPath = false;
					status = null;
				} catch (IOException e) {
					component.addOutput("Error: "+e.getMessage());
				}
			}
			return false;
		}
		public void forceFinish() {
			isEnteringPath = false;
			status = null;
		}
	}
	
	private class StateClearAll implements StateProgram {
		public boolean run(String input) {
			if (book.getCount() != 0) {
				component.addOutput("all cleared");
				book = new AddressBook();
			}
			return true;
		}
		public void forceFinish() {			
		}
	}
	
	private int parseInt(String input) {
		try {
			int integer = Integer.parseInt(input);
			if (integer < 0)
				component.addOutput("Error: provided integer is negative");
			else if (integer > book.addresses.size())
				component.addOutput("Error: index too great");
			else
				return integer;
		} catch (NumberFormatException e2) {
			component.addOutput("Error: input is not an integer. Try again");
		}
		return -1;
	}
}
