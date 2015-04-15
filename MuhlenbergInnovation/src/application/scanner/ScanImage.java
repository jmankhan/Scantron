package application.scanner;

import java.awt.image.BufferedImage;

public class ScanImage {

	/**
	 * Margins ordered left, top, right, bottom
	 */
	private int[] margins;
	private BufferedImage img;
	
	public ScanImage(BufferedImage img) {
		this.img = img;
	}
	
	/**
	 * Sets the value of the margins for this image
	 * @param index which margin to set
	 * @param value value of margin
	 */
	public void setMargins(int index, int value) {
		margins[index] = value;
	}
	
	public BufferedImage getImage() {
		return this.img;
	}
	
}
