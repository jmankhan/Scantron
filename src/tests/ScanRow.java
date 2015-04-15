package tests;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ScanRow {

	/**
	 * Dimensions and location of the row
	 */
	private int x, y, width, height;
	
	/**
	 * Image of the row
	 */
	private BufferedImage rowImage;
	
	/**
	 * List of each bubble in this row
	 */
	private ArrayList<ScanBubble> bubbles;

	/**
	 * Dimensions of each bubble. Defaulted to rowWidth/6 and rowHeight
	 */
	private int bubbleWidth, bubbleHeight;
	
	private int rowNum;
	private final float diffFactor = 50.0f;
	
	/**
	 * Creates a new object representing each row in the test form. The image passed is the full image of the test.
	 * It will be spliced based on the parameters passed.
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param testImage full test form image
	 */
	public ScanRow(int x, int y, int w, int h, BufferedImage testImage) {
		this.x			= x;
		this.y			= y;
		this.width		= w;
		this.height		= h;
		this.rowImage	= splice(testImage);
		
		this.bubbleWidth  = (int) Math.floor(w/6);
		this.bubbleHeight = h;
		this.bubbles	  = new ArrayList<ScanBubble>();
		this.rowNum		  = -1;
		
		generateBubbles(testImage);
	}
	
	/**
	 * Splices the given image with the previously specified dimensions
	 * @param testImage
	 * @return Spliced BufferedImage of just the row
	 */
	private BufferedImage splice(BufferedImage testImage) {
		return testImage.getSubimage(x, y, width, height);
	}
	
	/**
	 * Creates a list of the bubbles in this row from the specified dimensions
	 * @param Full test form image
	 */
	private void generateBubbles(BufferedImage testImage) {
		for(int x = this.x; x < (this.width+this.x)-1; x += bubbleWidth) {
			bubbles.add(new ScanBubble(x, this.y, this.bubbleWidth, this.bubbleHeight, testImage));
		}
	}
	
	/**
	 * Gets this row's image from the full test form image
	 * @return BufferedImage
	 */
	public BufferedImage getImage() {
		return this.rowImage;
	}
	
	/**
	 * Gets each bubble associated with this row
	 * @return
	 */
	public ArrayList<ScanBubble> getBubbles() {
		return this.bubbles;
	}
	
	/**
	 * Gets the numerical index of the bubble that has the highest color density in this row
	 * @return index starting at 0 of the highest color density bubble
	 */
	public int getRowAnswer() {
		float lowest = bubbles.get(0).getLuminance();
		int answer = 0;
		
		for(int i=0; i < bubbles.size(); i++) {
			if(bubbles.get(i).getLuminance() < lowest && Math.abs(bubbles.get(i).getLuminance()-lowest) >= diffFactor) {
				answer = i;
				lowest = bubbles.get(i).getLuminance();
			}
		}

		return answer;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setRowNum(int n) {
		this.rowNum=n;
	}
	
	public int getRowNum() {
		return this.rowNum;
	}
	
}
