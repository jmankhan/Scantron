import java.awt.image.BufferedImage;
import java.util.ArrayList;

import tests.ScanRow;


/**
 * Creates an object that represents an individual test as part of the Exam. This uses a BufferedImage that must be read from a file before creating this object. The image will be spliced in this class.
 * @author Jalal
 *
 */
public class StudentTest {

	/**
	 * The Image of the test from file
	 */
	private BufferedImage testImage;

	/**
	 * The coordinates and dimensions of each row. Can be changed, but defaulted to 0,0,20,150
	 */
	private int rowWidth = 121, rowHeight = 20;

	/**
	 * Each individual answer row put into a list
	 */
	private ArrayList<ScanRow> rows;

	/**
	 * Margins of the paper. Ordered top, right, down, left. Defaulted to 57,60,110,57
	 */
	private int marginT = 41, marginR = 62, marginD = 110, marginL = 57;

	/**
	 * Keeps track of errors relative to answer key
	 */
	private int error;
	
	private String name;
	
	/**
	 * Constructs a new StudentTest object using the Image read from file. It will be spliced and can be compared later
	 * @param testImage
	 */
	public StudentTest(BufferedImage testImage) throws IllegalArgumentException {
		this(testImage, "");
	}

	public StudentTest(BufferedImage testImage, String testName) throws IllegalArgumentException {
		if(testImage == null) {throw new IllegalArgumentException("Image cannot be null");}
		
		this.testImage  = testImage;
		this.rows		= new ArrayList<ScanRow>();
		generateRows(testImage.getWidth(), testImage.getHeight());

		error = 0;
		
		this.name = testName;
	}
	/**
	 * Splices the given TestImage into individual rows based on specified margins and dimensions.
	 * Adds each row to ArrayList<ScanRow> rows
	 * @param width width of the full TestImage
	 * @param height height of the full TestImage
	 */
	private void generateRows(int width, int height) {

		int count = 1;
		
		for(int x=marginL; x<width-marginR+1; x+=(rowWidth+marginR)) {
			for(int y=marginT; y<height-marginD; y+=rowHeight) {
				
				ScanRow r = new ScanRow(x,y,rowWidth,rowHeight, testImage);
				r.setRowNum(count++);
				rows.add(r);
			}
		}
	}

	/**
	 * The BufferedImage data of the test this object represents
	 * @return BufferedImage
	 */
	public BufferedImage getImage() {
		return this.testImage;
	}
	
	/**
	 * Gets the List of each ScanRow, representing the individual answer rows in the StudentTest.
	 * @return ArrayList<ScanRow> of each row
	 */
	public ArrayList<ScanRow> getRows() {
		return this.rows;
	}
	
	/**
	 * Increments error by delta
	 * @param delta amount to add to error
	 */
	public void addError(int delta) {
		error += delta;
	}
	
	
	/**
	 * Gets the amount of error accumulated for this test compared with the answer key
	 * @return int
	 */
	public int getError() {
		return this.error;
	}
	
	public String getName() {
		return this.name;
	}
}
