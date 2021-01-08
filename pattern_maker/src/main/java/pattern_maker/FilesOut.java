package pattern_maker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilesOut {
    public static void createColorListFile(String newFileLocation, String newFileName){
        try {
            File myObj = new File(newFileLocation + newFileName +"NeededColors.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

    }

    public static void createPatternFile(String newFileLocation, String newFileName){
        try {
            File myObj = new File(newFileLocation + newFileName +"Pattern.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

    }

    public static void writeLines(String newFileLocation, String newFileName, String pattern){
        try {
            FileWriter myWriter = new FileWriter(newFileLocation + newFileName +"Pattern.txt", true);
            myWriter.write(pattern + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
