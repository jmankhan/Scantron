import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
				BufferedImage testImg = ImageIO.read(f);
				if(testImg != null)
					tests.add(new StudentTest(testImg, f.getName().substring(0, f.getName().length()-4)));
			}

			return new Exam(answer, tests);
			
		} catch (IOException e1) {e1.printStackTrace();}
		
		return null;
	}
	
	/**
	 * Creates the GUI on a new JPanel
	 */
	private JPanel setupPromptGUI() {
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		JTextField answerField  = new JTextField(10);
		JTextField testField	= new JTextField(10);
		
		JButton answerButton 	= new JButton("Answer file..");
		JButton testButton 		= new JButton("Test directory..");
		JButton grade 			= new JButton("Grade");
		JTextArea gradeArea	= new JTextArea();

//		JComponent[] array = {answerField, testField, answerButton, testButton, grade, gradeArea};

		answerButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				
				int value = chooser.showOpenDialog(panel);
				if(value == JFileChooser.APPROVE_OPTION) {
					answerPath = chooser.getSelectedFile().getPath();
					answerField.setText(answerPath);
				}
			}
			
		});
		
		testButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				
				int value = chooser.showOpenDialog(panel);
				if(value == JFileChooser.APPROVE_OPTION) {
					testDirectory = chooser.getSelectedFile().getPath();
					testField.setText(testDirectory);
				}
			}
			
		});
		
		grade.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				findExam().grade(gradeArea);
			}
		});
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.WEST;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(5, 5, 0, 0);
		
		panel.add(answerField, gc);
		
		gc.gridx = 1;
		panel.add(answerButton, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		panel.add(testField, gc);
		
		gc.gridx = 1;
		panel.add(testButton, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		panel.add(grade, gc);
		
		gc.gridy = 3;
		panel.add(gradeArea, gc);
		
		return panel;
	}
	
	public static void main(String args[]) {
		JFrame f = new JFrame();
		f.add(new Main(f));
		
		f.setVisible(true);
		f.setSize(500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);

	}

}
