package GUI;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        StartScreen newGame = new StartScreen();
        newGame.run();


    }

}

