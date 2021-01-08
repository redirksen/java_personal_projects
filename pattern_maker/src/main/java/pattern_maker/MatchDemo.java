package pattern_maker;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MatchDemo {

	public static void main(String[] args) {
		// pulls in list of possible colors from class that the list
		PossibleColors possibleColors = new PossibleColors();
		List<ColorDetail> choices = possibleColors.getColors();
		Set<ColorDetail> neededColors = new LinkedHashSet<ColorDetail>();
		UserInput input = new UserInput();
		// gets location for new file
		String newFileLocation = input.newFileLocation();

		// gets name for new file
		String newFileName = input.newFileName();

		// Get file resizing
		int sizing = input.pixelSizing();

		// pulls in image

		BufferedImage img = input.importImage();

		//create new file to hold pattern
		FilesOut.createPatternFile(newFileLocation, newFileName);
		
		// runs through the image
		int w = img.getWidth();
		int h = img.getHeight();
		System.out.println("width, height: " + w + ", " + h);

		for (int i = 0; i < h; i += sizing) {
			String line = new String();
			for (int j = 0; j < w; j += sizing) {
//			        System.out.println("x,y: " + j + ", " + i);
				Color test = new Color(img.getRGB(j, i));
//			        System.out.println("Real Pixel RGB: " + test);

				// does color comparison
				ColorDetail closestToZero = new ColorDetail(0, 0, 0, "black", "-");
				// max possible double. first item will always be smaller
				double smallestValue = Double.POSITIVE_INFINITY;

				for (ColorDetail color : choices) {
					double distance = RGBDistance(color, test);
//						System.out.println(color.getName() + "  " + distance);
					if (smallestValue > distance) {
						closestToZero = color;
						smallestValue = distance;
					}

				} // end loop over color choices
				line = line + closestToZero.getSymbol();
				neededColors.add(closestToZero);

			} // end loop over width
			FilesOut.writeLines(newFileLocation, newFileName, line);
		} // end loop over height
		FilesOut.createColorListFile(newFileLocation, newFileName);
		try {
			FileWriter myWriter = new FileWriter(newFileLocation + newFileName +"NeededColors.txt");
			for (ColorDetail color : neededColors) {
				myWriter.write(color.getSymbol() + "   " + color.getName() + "\n");
				myWriter.write(" \n");
			}
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static double RGBDistance(Color testA, Color testB) {
		double redCalc = Math.pow(testA.getRed() - testB.getRed(), 2);
		double greenCalc = Math.pow(testA.getGreen() - testB.getGreen(), 2);
		double blueCalc = Math.pow(testA.getBlue() - testB.getBlue(), 2);
		double distance = Math.sqrt(redCalc + greenCalc + blueCalc);
		return distance;

	}

}
