
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import jdk.jfr.Recording;
import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordingFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.*;

public class task02 {

    public static void main(String[] args) throws Exception {
        Logger LOGGER = Logger.getLogger(lesson.class.getName());
        FileHandler fileHandler = new FileHandler("Lesson3task02Log.txt", false);
        LOGGER.addHandler(fileHandler);

        Recording recording = new Recording();
        recording.start();
        clearScreen();

        ArrayList arr = new ArrayList<>();
        Random rnd = new Random();

        for (int i = 0; i < 10; i++) {
            arr.add(rnd.nextInt(10));
            LOGGER.info("arr[" + i + "] = " + arr.get(i));
        }
        System.out.println("Base arr: \t" + arr);
                for (int i = arr.size()-1; i > 0; --i) {
                    if ((Integer) arr.get(i) % 2 == 0) {
                        LOGGER.info("arr[" + i + "] = " + arr.get(i) + "removed");
                        arr.remove(i);

                    }
                }
        System.out.println("arr without odds: \t" + arr);

   
        recording.stop();

        try {
            Files.createDirectories(Paths.get("profile"));
            recording.dump(Paths.get("profile", "myrecording.jfr"));
        } catch (IOException e) {
            try {
                RecordingFile recordingFile = new RecordingFile(Paths.get("profile", "myrecording.jfr"));
                while (recordingFile.hasMoreEvents()) {
                    RecordedEvent event = recordingFile.readEvent();
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
