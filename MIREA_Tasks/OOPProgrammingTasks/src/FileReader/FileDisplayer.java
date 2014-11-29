package FileReader;

public class FileDisplayer implements CommandInterpreter{
	@Override
	public void processCommand(String str) {
		System.out.println(str);
	}
	@Override
	public String output() {
		return "";
	}
}
