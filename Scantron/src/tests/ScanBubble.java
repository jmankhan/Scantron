package tests;

import java.awt.image.BufferedImage;

public class ScanBubble {

	private int x, y, width, height;
	private BufferedImage bubbleImage;

	/**
	 * Constructs a new Bubble object that will represent an individual bubble in the test form. 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param testImage
	 */
	public ScanBubble(int x, int y, int w, int h, BufferedImage testImage) {

		this.x			= x;
		this.y			= y;
		this.width		= w;
		this.height		= h;
		this.bubbleImage= splice(testImage);

	}

	/**
	 * Splices given image into just one bubble bounded rectangularly
	 * @param testImage Full test form image
	 * @return Individual bubble BufferedImage
	 */
	private BufferedImage splice(BufferedImage testImage) {
		return testImage.getSubimage(x, y, width, height);
	}


	/**
	 * Determines what the average color of this bubble is by searching through each pixel and averaging the pixel color
	 */
	public int getAverageColorDensity() {

		int area = this.width * this.height;
		int color = 0;

		for(int x=0; x<this.width; x++) {
			for(int y=0; y<this.height; y++) {
				color += -bubbleImage.getRGB(x, y);
			}
		}

		return color/area;
	}

	/**
	 * Gets the Image of just this bubble in the test form.
	 * @return BufferedImage
	 */
	public BufferedImage getBubbleImage() {
		return this.bubbleImage;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
