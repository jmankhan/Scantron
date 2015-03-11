package application.scanner;

import com.sun.prism.paint.Color;

public class Identifier {

	private static final int WIDTH = 20, HEIGHT = 18;
	private int x, y;

	/**
	 * Creates a new Identifier object that will serve as a reference point to begin scanning
	 * @param x
	 * @param y
	 */
	public Identifier(int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	
	public static Identifier getDefaultIdentifier(int x, int y) {
		return new Identifier(x, y);
	}
	
	public static int getWidth() {
		return WIDTH;
	}
	
	public static int getHeight() {
		return HEIGHT;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public static int getRGB() {
		return 0;
	}
}
