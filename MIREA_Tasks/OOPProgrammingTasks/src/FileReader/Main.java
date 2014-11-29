package FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class Main {
	public void readFile(String pathString, CommandInterpreter interpreter) {
		Path path = Paths.get(pathString);
		Charset charset = Charset.forName("UTF-8");
		try {
			List<String> list = Files.readAllLines(path, charset);
			for (int i = 0; i < list.size(); i ++) {
				interpreter.processCommand(list.get(i));
			}
			System.out.println(interpreter.output());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
