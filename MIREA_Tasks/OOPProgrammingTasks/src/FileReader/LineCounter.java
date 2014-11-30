package FileReader;

public class LineCounter implements CommandInterpreter {
	private int lineCount = 0;
	@Override
	public void processCommand(String str) {
		lineCount++;
	}
	public int getCount() {
		return lineCount;
	}
}
