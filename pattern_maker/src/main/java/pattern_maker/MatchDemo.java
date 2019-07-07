package pattern_maker;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MatchDemo {

	public static void main(String[] args) {
		// pulls in list of possible colors from class that the list
		PossibleColors possibleColors = new PossibleColors();
		List<ColorDetail> choices = possibleColors.getColors();
		Set<ColorDetail> neededColors = new LinkedHashSet<ColorDetail>();

		// pulls in image

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("/Users/rebeccadirksen/Pictures/WhiteMage.jpg"));

			System.out.println("Reading complete.");
		} catch (IOException e) {

			System.out.println(e);
		}

		// runs through the image
		int w = img.getWidth();
		int h = img.getHeight();
		System.out.println("width, height: " + w + ", " + h);

		for (int i = 0; i < h; i += 2) { // NOTE: Modified to +=2 for mage input
			String line = new String();
			for (int j = 0; j < w; j += 2) { // NOTE: Modified to +=2 for mage input
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
			System.out.println(line);
		} // end loop over height
		for (ColorDetail color : neededColors) {
			System.out.println(color.getSymbol() + "   " + color.getName());
			System.out.println(" ");
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
