package application.scanner;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Row {

	private int x, y, width, height, pos;
	private BufferedImage fullImage;
	private ArrayList<Bubble> bubbles;
	
	private int marginL, marginT;
	
	/**
	 * Creates a Row object from the given image and positions
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param original
	 */
	public Row(int x, int y, int w, int h, BufferedImage original) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		
		this.fullImage = original;
		this.bubbles = new ArrayList<Bubble>();
		
		int numBubbles = 6;
		int bx = x;
		int by = y;
		int bw = w/numBubbles;
		int bh = h;
		for(; bx<w; bx+=bw) {
			bubbles.add(new Bubble(bx, by, bw, bh, getRowImage()));
		}
	}
	
	public BufferedImage getRowImage() {
		return this.fullImage.getSubimage(x, y, width, height);
	}
	
	public ArrayList<Bubble> getBubbles() {
		return this.bubbles;
	}
	
	public void setMargins(int mL, int mT) {
		this.marginL = mL;
		this.marginT = mT;
	}
}