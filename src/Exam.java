import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;

import tests.ScanRow;


/**
 * Represents a single Exam in a course. This includes the answer key as well as the student tests themselves. Tests must be obtained, probably reading from a file, before creating this object.
 * @author Jalal
 *
 */
public class Exam {

	private Map<StudentTest, List<StudentTest>> exam;

	public Exam(StudentTest answerKey, List<StudentTest> tests) throws IllegalArgumentException {

		if(answerKey == null || tests == null || tests.size() == 0) {throw new IllegalArgumentException("Answer key or tests is null or empty");}


		exam = new HashMap<StudentTest, List<StudentTest>>();
		exam.put(answerKey, tests);
		
	}

	public Map<StudentTest, List<StudentTest>> getExam() {
		return this.exam;
	}

	public void grade(JTextArea screen) {
		StudentTest answer = exam.keySet().iterator().next();
		
		ArrayList<StudentTest> tests = getStudentTests(this.exam);
		ArrayList<ScanRow> answerRows = answer.getRows();
		
		for(StudentTest test : tests) {
			
			ArrayList<ScanRow> testRows = test.getRows();
			
			for(int i=0; i<answerRows.size(); i++) {
				if(answerRows.get(i).getRowAnswer() != test.getRows().get(i).getRowAnswer()) {
					screen.append("Error at row: " + i + " of test " + test.getName() + "\n");
				}
				
			}
			testRows.clear();
		}
		screen.append("Done grading.");
	}

	private ArrayList<StudentTest> getStudentTests(Map<StudentTest, List<StudentTest>> exam) {
		Iterator<List<StudentTest>> testLists = exam.values().iterator();
		Iterator<StudentTest> testIt = null;
		if(testLists.hasNext()) {
			testIt = testLists.next().iterator();
		}

		ArrayList<StudentTest> tests = new ArrayList<StudentTest>(); 
		while(testIt.hasNext()) {
			tests.add(testIt.next());
		}

		return tests;
	}

	public int size() {
		return this.exam.size();
	}
}
