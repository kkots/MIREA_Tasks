package FileReader;

public class CountTotalLetters implements CommandInterpreter {
	private int letterCount = 0;
	public void processCommand(String str) {
		letterCount += str.length();
	}
	public int getCount() {
		return letterCount;
	}
}
