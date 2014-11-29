package FileReader;

public class LineCounter implements CommandInterpreter {
	private int count;
	public LineCounter() {
		count = 0;
	}
	@Override
	public void processCommand(String str) {
		count++;
	}
	public String output() {
		return String.valueOf(count);
	}
}
