package FileReader;

public class CountTotalLetters implements CommandInterpreter {
	private int letterCount;
	public CountTotalLetters() {
		letterCount = 0;
	}
	public void processCommand(String str) {
		letterCount += str.length();
	}
	public String output() {
		return String.valueOf(letterCount);
	}
}
