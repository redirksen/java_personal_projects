package pattern_maker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class UserInput {


//	"s"
	
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
}
