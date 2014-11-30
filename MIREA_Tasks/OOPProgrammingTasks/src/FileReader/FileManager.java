package FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class FileManager {
	public static void readFile(String pathString, CommandInterpreter interpreter) throws IOException {
		Path path = Paths.get(pathString);
		Charset charset = Charset.forName("UTF-8");
		List<String> list = Files.readAllLines(path, charset);
		for (int i = 0; i < list.size(); i ++) {
			interpreter.processCommand(list.get(i));
		}
	}
}
