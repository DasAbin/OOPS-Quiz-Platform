import java.util.List;

/**
 * Handles the quiz execution (Lite)
 */
public class QuizEngine {
	private java.util.Scanner scanner;
	private QuestionManager questionManager;
	private boolean lastWasSkip;
	
	public QuizEngine(QuestionManager questionManager) {
		this.scanner = new java.util.Scanner(System.in);
		this.questionManager = questionManager;
	}
	
	public QuizAttempt startQuiz(Player player, String category, int questionCount) {
		System.out.println("\n" + "=".repeat(60));
		System.out.println("QUIZ STARTING");
		System.out.println("=".repeat(60));
		System.out.println("Player: " + player.getName());
		System.out.println("Category: " + category);
		System.out.println("Questions: " + questionCount);
		System.out.println("=".repeat(60));
		
		List<Question> questions = category.equals("All Categories")
			? questionManager.getRandomQuestions(questionCount)
			: questionManager.getRandomQuestions(category, questionCount);
		
		if (questions.isEmpty()) {
			System.out.println("[ERROR] No questions available for this category!");
			return null;
		}
		
		int score = 0;
		int correctAnswers = 0;
		int totalTimeTaken = 0;
		
		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			lastWasSkip = false; // reset per question
			System.out.println("\n" + "-".repeat(60));
			System.out.println("Question " + (i + 1) + " of " + questions.size());
			System.out.println("Category: " + question.getCategory());
			System.out.println("Difficulty: " + getDifficultyText(question.getDifficulty()));
			System.out.println("Points: " + question.getPoints());
			System.out.println("-".repeat(60));
			
			boolean result = askQuestion(question);
            if (result) {
                score += question.getPoints();
                correctAnswers++;
                System.out.println("[CORRECT] Correct! +" + question.getPoints() + " points");
			} else {
				if (lastWasSkip) {
					System.out.println("[SKIP] No penalty applied.");
				} else {
					int penalty = Math.max(1, question.getPoints() / 4); // 25% penalty
					score -= penalty;
					System.out.println("[INCORRECT] Incorrect (" + (-penalty) + ") The correct answer was: " +
								  question.getOptions().get(question.getCorrectAnswer()));
				}
            }
		}
		
		QuizAttempt attempt = new QuizAttempt(category, score, correctAnswers, 
										  questions.size(), totalTimeTaken);
		
		System.out.println("\n" + "=".repeat(60));
		System.out.println("QUIZ COMPLETED!");
		System.out.println("=".repeat(60));
		System.out.println("Final Score: " + score);
		System.out.println("Correct Answers: " + correctAnswers + "/" + questions.size());
		System.out.println("Accuracy: " + String.format("%.1f", attempt.getAccuracy()) + "%");
		System.out.println("Time Taken: " + totalTimeTaken + " seconds");
		System.out.println("=".repeat(60));
		
		return attempt;
	}
	
    private boolean askQuestion(Question question) {
        System.out.println("\n" + question.getQuestionText());
        System.out.println();
        List<String> options = question.getOptions();
        printOptions(options);
        System.out.print("Enter your answer (1-" + options.size() + ") or h=Hint, s=Skip: ");
        String input = scanner.nextLine().trim();

        // Handle lifelines minimally
        if (input.equalsIgnoreCase("h")) {
            System.out.println("\n[HINT] " + question.getHint());
            System.out.print("Enter your answer (1-" + options.size() + "): ");
            input = scanner.nextLine().trim();
        } else if (input.equalsIgnoreCase("s")) {
            System.out.println("[SKIP] Question skipped.");
            lastWasSkip = true; // mark skip so no negative marks
            return false;
        }

        return parseAndValidateAnswer(input, options, question);
    }
	
	private void printOptions(List<String> displayOptions) {
		for (int i = 0; i < displayOptions.size(); i++) {
			if (displayOptions.get(i) != null) {
				System.out.println((i + 1) + ". " + displayOptions.get(i));
			}
		}
		System.out.println();
	}
	
	private boolean parseAndValidateAnswer(String input, List<String> options, Question question) {
		try {
			int answer = Integer.parseInt(input) - 1;
			if (answer >= 0 && answer < options.size()) {
				return question.isCorrect(answer);
			} else {
				System.out.println("[ERROR] Invalid option!");
				return false;
			}
		} catch (NumberFormatException e) {
			System.out.println("[ERROR] Invalid input!");
			return false;
		}
	}
	
	private String getDifficultyText(int difficulty) {
		String[] labels = {"Unknown", "Easy", "Medium", "Hard"};
		return (difficulty >= 1 && difficulty <= 3) ? labels[difficulty] : labels[0];
	}
}


