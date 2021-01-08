package pattern_maker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

// /Users/becca/Coding_Projects/perler/Pictures/WhiteMage.jpg
// /Users/becca/Coding_Projects/perler/Perler_Color.csv
// /Users/becca/Coding_Projects/perler/Created_Patterns/

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

	public String newFileName(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the name for the new file");
		String s = scan.next();
		return s;
	}

	public String newFileLocation(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the abosolute path for the new files");
		String s = scan.next();
		return s;
	}

	public Integer pixelSizing(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter 1 or the how many pixels need to skipped to keep a 1:1");
		int i = scan.nextInt();
		return i;
	}
}
