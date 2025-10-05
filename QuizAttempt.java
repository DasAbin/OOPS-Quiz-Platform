import java.time.LocalDateTime;

/**
 * Represents a single quiz attempt by a player
 */
public class QuizAttempt {
	private LocalDateTime timestamp;
	private String category;
	private int score;
	private int correctAnswers;
	private int totalQuestions;
	private int timeTaken;
	
	public QuizAttempt(String category, int score, int correctAnswers, 
					 int totalQuestions, int timeTaken) {
		this.timestamp = LocalDateTime.now();
		this.category = category;
		this.score = score;
		this.correctAnswers = correctAnswers;
		this.totalQuestions = totalQuestions;
		this.timeTaken = timeTaken;
	}
	
	public LocalDateTime getTimestamp() { return timestamp; }
	public String getCategory() { return category; }
	public int getScore() { return score; }
	public int getCorrectAnswers() { return correctAnswers; }
	public int getTotalQuestions() { return totalQuestions; }
	public int getTimeTaken() { return timeTaken; }
	
	public double getAccuracy() {
		return totalQuestions > 0 ? (double) correctAnswers / totalQuestions * 100 : 0;
	}
	
	@Override
	public String toString() {
		return String.format("[%s] %s - Score: %d (%d/%d) - Time: %ds", 
					   timestamp.toLocalDate(), category, score, 
					   correctAnswers, totalQuestions, timeTaken);
	}
}


