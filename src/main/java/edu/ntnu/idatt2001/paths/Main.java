package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.filehandling.StoryLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static Game game;

    static  Scanner scanner = new Scanner(System.in);

    static StoryLoader fileReader;

    static Logger logger = Logger.getLogger(Main.class.getName());


    public static void newGame() throws IOException {
        File file = new File("src/main/resources/storyExample.paths");
        fileReader = new StoryLoader(file);
        game = new Game(new Player("Zac", 100, 0, 0), fileReader.getStory(), new ArrayList<>());
    }

    public static void main(String[] args) throws IOException {
        newGame();
        logger.log(Level.INFO, "New game started");

        Passage currentPassage = game.begin();

        while (currentPassage != null) {
            printPassageInfo(currentPassage);
            printPassageChoiceInfo(currentPassage);
            int choice = scanner.nextInt();
            currentPassage = game.go(currentPassage.getLinks().get(choice - 1));
        }

        System.out.println("Game over");
    }

    public static void printPassageInfo(Passage passage){
        System.out.println(passage.getTitle());
        System.out.println(passage.getContent());

    }

    public static void printPassageChoiceInfo(Passage passage) {
        System.out.println("\nChoose one of the following options:");
        for (int i = 0; i < passage.getLinks().size(); i++) {
            System.out.println(i + 1 + ": " + passage.getLinks().get(i).getText());
        }
    }


}
