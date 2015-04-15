package application.scanner;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import application.Main;

public class Scanner {

	private ScanImage answer_key;
	private ArrayList<ScanImage> tests;
	
	/**
	 * Creates a new Scanner object that will find the differences between the answer key and each test
	 * @param answer BufferedImage independent variable
	 * @param tests ArrayList<BufferedImage> dependent variables
	 */
	public Scanner(ScanImage answer, ArrayList<ScanImage> tests) {
		this.answer_key = answer;
		this.tests = tests;
		
		Identifier id = findIdentifier(answer_key.getImage());
		JFrame f = new JFrame();
		JLabel j = new JLabel();
		j.setIcon(new ImageIcon(answer_key.getImage()));
		f.add(j);
		f.setSize(1000,1000);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Searches for a set Identifier and returns its location if found
	 * @param image Image to search
	 * @return Identifier object or null if not found
	 */
	private Identifier findIdentifier(BufferedImage image) {
		int firstX, lastX, firstY, lastY;
		firstX = lastX = firstY = lastY = -1;
		
		for(int y=0; y<image.getHeight(); y++) {
			for(int x=0; x<image.getWidth(); x++) {
				
				if(image.getRGB(x, y) == Identifier.getRGB()) {
					if(firstX <= lastX)
						firstX = image.getRGB(x, y);
					else 
						lastX = image.getRGB(x, y);
					
					if(firstY <= lastY)
						firstY = image.getRGB(x, y);
					else
						lastY = image.getRGB(x, y);
					
					if(lastX - firstX == Identifier.getWidth() && lastY - firstY == Identifier.getHeight())
						return new Identifier(x,y);
				}
			}
		}
		
		return null;
	}
	
	public static void main(String args[]) throws IOException {
		ScanImage img1 = new ScanImage(ImageIO.read(Main.class.getResourceAsStream("/res/answer_key.png")));
		Scanner s = new Scanner( img1, null);
	}
}
