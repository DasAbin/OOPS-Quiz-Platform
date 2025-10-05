import java.util.ArrayList;
import java.util.List;

/**
 * Represents a quiz question with multiple choice answers
 */
public class Question {
	private String questionText;
	private List<String> options;
	private int correctAnswer;
	private String category;
	private String hint;
	private int difficulty;
	
	public Question(String questionText, List<String> options, int correctAnswer, 
				  String category, String hint, int difficulty) {
		this.questionText = questionText;
		this.options = new ArrayList<>(options);
		this.correctAnswer = correctAnswer;
		this.category = category;
		this.hint = hint;
		this.difficulty = difficulty;
	}
	
	public String getQuestionText() { return questionText; }
	public List<String> getOptions() { return new ArrayList<>(options); }
	public int getCorrectAnswer() { return correctAnswer; }
	public String getCategory() { return category; }
	public String getHint() { return hint; }
	public int getDifficulty() { return difficulty; }
	
	public boolean isCorrect(int answer) {
		return answer == correctAnswer;
	}
	
	public int getPoints() {
		return difficulty * 10;
	}
	
	@Override
	public String toString() {
		return String.format("[%s] %s (Difficulty: %d)", category, questionText, difficulty);
	}
}


