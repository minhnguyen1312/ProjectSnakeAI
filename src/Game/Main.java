package Game;

import DefaultBotFrameWork.SnakesUIMain;
import GUI.StartScreen;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        new StartScreen();

    }

    public static void runBotvsBot() {
        try {
            //String[] args = "";
            SnakesUIMain.main(null);
        } catch (InterruptedException | IOException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            System.err.print("Error occurred!");
        }
    }

}

