import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import jdk.jfr.Recording;
import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordingFile;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.*;

public class task03 {

    public static void main(String[] args) throws Exception {
        Logger LOGGER = Logger.getLogger(lesson.class.getName());
        FileHandler fileHandler = new FileHandler("Lesson3task03Log.txt", false);
        LOGGER.addHandler(fileHandler);

        Recording recording = new Recording();
        recording.start();
        clearScreen();

        ArrayList <Integer> arr = new ArrayList<>();
        Random rnd = new Random();
        Integer arrSize = rnd.nextInt(10, 50);
        
        Double averageValue = 0.0;
        for (int i = 0; i < arrSize; i++) {
            arr.add(rnd.nextInt(50));
            LOGGER.info("arr[" + i + "] = " + arr.get(i));
            averageValue += arr.get(i);
        }
        averageValue = averageValue / arrSize;
        System.out.println("Base arr:\t\t" + arr);     
        System.out.println("min of arr =\t\t" + Collections.min(arr));
        System.out.println("max of arr =\t\t" + Collections.max(arr));
        System.out.println("average of arr =\t" + averageValue);
   
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

    private static void min(ArrayList<Integer> arr) {
    }