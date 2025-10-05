import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the quiz system
 */
public class Player {
	private String name;
	private int totalScore;
	private int gamesPlayed;
	private int correctAnswers;
	private int totalQuestions;
	private List<QuizAttempt> quizHistory;
	
	public Player(String name) {
		this.name = name;
		this.totalScore = 0;
		this.gamesPlayed = 0;
		this.correctAnswers = 0;
		this.totalQuestions = 0;
		this.quizHistory = new ArrayList<>();
	}
	
	public String getName() { return name; }
	public int getTotalScore() { return totalScore; }
	public int getGamesPlayed() { return gamesPlayed; }
	public int getCorrectAnswers() { return correctAnswers; }
	public int getTotalQuestions() { return totalQuestions; }
	public List<QuizAttempt> getQuizHistory() { return new ArrayList<>(quizHistory); }
	
	public double getAccuracy() {
		return totalQuestions > 0 ? (double) correctAnswers / totalQuestions * 100 : 0;
	}
	
	public void addQuizAttempt(QuizAttempt attempt) {
		quizHistory.add(attempt);
		totalScore += attempt.getScore();
		gamesPlayed++;
		correctAnswers += attempt.getCorrectAnswers();
		totalQuestions += attempt.getTotalQuestions();
	}
	
	@Override
	public String toString() {
		return String.format("%s - Score: %d, Games: %d, Accuracy: %.1f%%", 
					   name, totalScore, gamesPlayed, getAccuracy());
	}
}


