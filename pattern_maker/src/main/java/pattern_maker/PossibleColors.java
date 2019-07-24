package pattern_maker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PossibleColors {
	UserInput input = new UserInput();
	private List<ColorDetail> colors = new ArrayList<ColorDetail>();
	
	public PossibleColors() {
		File inputFile = input.getInputFileFromUser();
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
	


}
