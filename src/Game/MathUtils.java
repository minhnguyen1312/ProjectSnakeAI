package Game;

public class MathUtils {
    public static Double distance (Coordinate c1, Coordinate c2) {
        return Math.sqrt(Math.pow((c1.x - c2.x), 2) + Math.pow((c1.y - c2.y), 2));
    }

    public static int min (double[] distances) {
        if (distances[0] < distances[1]) {
            if (distances[0] < distances[2]) {
                return 0;
            }
            return 2;
        } else {
            if (distances[1] < distances[2]) {
                return 1;
            }
            return 2;
        }
    }
}
