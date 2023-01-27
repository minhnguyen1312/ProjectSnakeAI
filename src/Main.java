import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static final String CSV_PATH = "score.csv";
    public static boolean append = true;
    public static ArrayList<String> aList = new ArrayList<String>();

    public static void main(String[] args) throws Exception {
        readAllLinesFromFile(CSV_PATH);
        System.out.println("Unsorted:\n");
        for(String aSnake: aList){
            System.out.println(aSnake +"\n");
        }

        ArrayList<SnakeScore> snakes = convertListToSnake(aList);
        System.out.println("SORTED:\n");
        for(SnakeScore snake : snakes){
            System.out.println(snake.toString());
        }
    }

    private static ArrayList<SnakeScore> convertListToSnake(ArrayList<String> aList) {
        ArrayList<SnakeScore> snake = new ArrayList<>();
        aList.remove(0);
        for(String snakePart : aList) {
            String[] parts = snakePart.split(",");
            String player = parts[0];
            int score = Integer.valueOf(parts[1]);
            int difficulty = Integer.valueOf(parts[2]);
            String date = parts[3];
            snake.add(new SnakeScore(player,score,difficulty,date));
        }

        Collections.sort(snake, new Comparator<SnakeScore>() {
            @Override
            public int compare(SnakeScore o1, SnakeScore o2) {
                return o1.compareTo(o2);
            }
        });
        return snake;
    }


    public static ArrayList<String> readAllLinesFromFile(String path) throws IOException {

        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while( (line = bufferedReader.readLine()) != null){
            aList.add(line);
        }
        bufferedReader.close();

        return aList;

    }

}