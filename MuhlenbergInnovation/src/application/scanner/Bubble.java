package application.scanner;

import java.awt.image.BufferedImage;

public class Bubble {

	private int x, y, width, height;
	private BufferedImage rowImage;
	
	/**
	 * Creates a new Bubble object with the given positions inside the Row image
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param rowImage
	 */
	public Bubble(int x, int y, int width, int height, BufferedImage rowImage) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rowImage = rowImage;
	}
	
	/**
	 * Calculates the average color of the bubble image
	 * @return int average color 
	 */
	public int getAverageColorDensity() {
		BufferedImage bubbleImage = rowImage.getSubimage(x, y, width, height);
		int area = width*height;
		int color = 0;
		
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				color += bubbleImage.getRGB(x, y);
			}
		}
		
		return color/area;
	}

	/**
	 * Gets the bubble image
	 * @return BufferedImage
	 */
	public BufferedImage getBubbleImage() {
		return this.rowImage.getSubimage(x, y, width, height);
	}
}
