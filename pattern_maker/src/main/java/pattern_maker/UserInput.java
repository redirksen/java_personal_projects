package pattern_maker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

// /Users/rebeccadirksen/Pictures/WhiteMage.jpg
// /Users/rebeccadirksen/Project_Resources/Perler_Color.csv

public class UserInput {

	public BufferedImage importImage() {
		Scanner input = new Scanner(System.in);
		BufferedImage img = null;
		System.out.println("Please enter the absolute path for the image you would like to use.");
		String fileAddress = input.nextLine();

		try {
			img = ImageIO.read(new File(fileAddress));

			System.out.println("Reading complete.");
		} catch (IOException e) {

			System.out.println(e);
		}
		input.close();
		return img;
	}

	public File getInputFileFromUser() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Please enter absolute path for the CSV of color values");
		String path = userInput.nextLine();

		File inputFile = new File(path);
		if (inputFile.exists() == false) { // checks for the existence of a file
			System.out.println(path + " does not exist");
			System.exit(1); // Ends the program
		} else if (inputFile.isFile() == false) {
			System.out.println(path + " is not a file");
			System.exit(1); // Ends the program
		}
		return inputFile;
	}

}
