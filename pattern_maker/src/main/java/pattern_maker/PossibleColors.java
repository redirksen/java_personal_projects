package pattern_maker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PossibleColors {
	private List<ColorDetail> colors = new ArrayList<ColorDetail>();
	
	public PossibleColors() {
		File inputFile = getInputFileFromUser();
		try {
			Scanner fileScanner = new Scanner(inputFile);
			while(fileScanner.hasNextLine()) { 
				String line = fileScanner.nextLine();
				String[] splitLine = line.split(",");
				String name = splitLine[0];
				String symbol = splitLine[1];
				int red = Integer.parseInt(splitLine[2]);
				int green = Integer.parseInt(splitLine[3]);
				int blue = Integer.parseInt(splitLine[4]);
				ColorDetail temp = new ColorDetail(red, green, blue, name, symbol);
				colors.add(temp);

			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File was not found");
		}

	}
	
	public List<ColorDetail> getColors() {
		return colors;
	}
	
	@SuppressWarnings("resource")
	private static File getInputFileFromUser() {
		Scanner userInput = new Scanner(System.in);
		//System.out.print("Please enter path to input file >>> ");
		String path = "/Users/rebeccadirksen/Project_Resources/Perler_Color.csv";

		File inputFile = new File(path);
		if(inputFile.exists() == false) { // checks for the existence of a file
			System.out.println(path+" does not exist");
			System.exit(1); // Ends the program
		} else if(inputFile.isFile() == false) {
			System.out.println(path+" is not a file");
			System.exit(1); // Ends the program
		}
		return inputFile;
	}

}
