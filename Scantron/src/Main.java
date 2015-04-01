import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Main extends JPanel {

	private static final long serialVersionUID = 1L;
	private String testDirectory = "";
	private String answerPath = "";
	
	private JPanel drawPanel;
	
	public Main(JFrame parent) {

		//add the prompt panel
		add(setupPromptGUI());
		drawPanel = new JPanel();
		add(drawPanel);
	}
	
	/**
	 * Reads answer key and tests from file
	 * @return Exam object if all files found. Null if not found
	 */
	private Exam findExam() {
		try {
			
			BufferedImage answerImg = ImageIO.read(new File(answerPath));
			StudentTest answer = new StudentTest(answerImg);
			
			ArrayList<StudentTest> tests = new ArrayList<StudentTest>();
			
			File testDir = new File(testDirectory);
			for(File f:testDir.listFiles()) {
				tests.add(new StudentTest(ImageIO.read(f), f.getName()));
			}
			
			return new Exam(answer, tests);
			
		} catch (IOException e1) {e1.printStackTrace();}
		
		return null;
	}
	
	/**
	 * Creates the GUI on a new JPanel
	 */
	private JPanel setupPromptGUI() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		
		JTextField answerField  = new JTextField();
		JTextField testField	= new JTextField();
		
		JButton answerButton 	= new JButton("Answer file..");
		JButton testButton 		= new JButton("Test directory..");
		JButton grade 			= new JButton("Grade");
		
		answerButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("C:\\Users\\Jalal\\workspace\\Scantron\\res"));
				
				int value = chooser.showOpenDialog(panel);
				if(value == JFileChooser.APPROVE_OPTION) {
					answerPath = chooser.getSelectedFile().getPath();
					answerField.setText(answerPath);
					
					System.out.println("answer file at " + answerPath);
				}
			}
			
		});
		
		testButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("C:\\Users\\Jalal\\workspace\\Scantron\\res"));
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				
				int value = chooser.showOpenDialog(panel);
				if(value == JFileChooser.APPROVE_OPTION) {
					testDirectory = chooser.getSelectedFile().getPath();
					testField.setText(testDirectory);
					System.out.println("test directory at " + testDirectory);
				}
			}
			
		});
		
		grade.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				findExam().grade();
			}
		});
		
		panel.add(answerField);
		panel.add(answerButton);
		
		panel.add(testField);
		panel.add(testButton);
		panel.add(grade);
		
		return panel;
	}
	
	public static void main(String args[]) {
		JFrame f = new JFrame();
		f.setVisible(true);
		f.setSize(500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);

		f.add(new Main(f));
	}

}
