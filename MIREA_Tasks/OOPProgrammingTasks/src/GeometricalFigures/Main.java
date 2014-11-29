package GeometricalFigures;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 17.11.2014.
 */
public class Main {
	static List<Figure> figureArray = new ArrayList<Figure>();

	public static Figure hitTest(float x, float y) {
		for (Figure figure : figureArray) {
			if (figure.hitTest(x,y)) return figure;
		}
		return null;
	}
}