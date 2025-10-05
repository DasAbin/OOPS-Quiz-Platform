import java.util.*;

/**
 * Manages quiz questions by category
 */
public class QuestionManager {
	private Map<String, List<Question>> questionsByCategory;
	private List<Question> allQuestions;
	
	public QuestionManager() {
		this.questionsByCategory = new HashMap<>();
		this.allQuestions = new ArrayList<>();
		initializeSampleQuestions();
	}
	
	public void addQuestion(Question question) {
		String category = question.getCategory();
		
		if (!questionsByCategory.containsKey(category)) {
			questionsByCategory.put(category, new ArrayList<>());
		}
		
		questionsByCategory.get(category).add(question);
		allQuestions.add(question);
	}
	
	public List<Question> getRandomQuestions(String category, int count) {
		List<Question> categoryQuestions = questionsByCategory.get(category);
		return pickRandom(categoryQuestions, count);
	}
	
	public List<Question> getRandomQuestions(int count) {
		return pickRandom(allQuestions, count);
	}
	
	public Set<String> getCategories() {
		return new HashSet<>(questionsByCategory.keySet());
	}
	
	public int getQuestionCount(String category) {
		List<Question> questions = questionsByCategory.get(category);
		return questions != null ? questions.size() : 0;
	}
	
	public int getTotalQuestionCount() {
		return allQuestions.size();
	}
	
	private <T> List<T> pickRandom(List<T> source, int count) {
		if (source == null || source.isEmpty()) {
			return new ArrayList<>();
		}
		List<T> shuffled = new ArrayList<>(source);
		Collections.shuffle(shuffled);
		int itemsToReturn = Math.min(count, shuffled.size());
		return shuffled.subList(0, itemsToReturn);
	}

	private void initializeSampleQuestions() {
		addQuestion(new Question(
			"What is the chemical symbol for gold?",
			Arrays.asList("Au", "Ag", "Go", "Gd"),
			0, "Science", "It starts with 'A' and is related to Aurora", 1
		));
		
		addQuestion(new Question(
			"Which planet is known as the Red Planet?",
			Arrays.asList("Venus", "Mars", "Jupiter", "Saturn"),
			1, "Science", "Think about the Roman god of war", 1
		));
		
		addQuestion(new Question(
			"What is the speed of light in vacuum?",
			Arrays.asList("299,792,458 m/s", "300,000,000 m/s", "150,000,000 m/s", "450,000,000 m/s"),
			0, "Science", "It's approximately 3 x 10^8 m/s", 2
		));
		
		addQuestion(new Question(
			"In which year did World War II end?",
			Arrays.asList("1944", "1945", "1946", "1947"),
			1, "History", "It ended in the same year the atomic bombs were dropped", 1
		));
		
		addQuestion(new Question(
			"Who was the first President of the United States?",
			Arrays.asList("Thomas Jefferson", "John Adams", "George Washington", "Benjamin Franklin"),
			2, "History", "He's on the dollar bill and the quarter", 1
		));
		
		addQuestion(new Question(
			"What is the capital of Australia?",
			Arrays.asList("Sydney", "Melbourne", "Canberra", "Perth"),
			2, "Geography", "It's not the largest city in Australia", 1
		));
		
		addQuestion(new Question(
			"Which ocean is the largest?",
			Arrays.asList("Atlantic", "Pacific", "Indian", "Arctic"),
			1, "Geography", "It covers more than one-third of Earth's surface", 1
		));
		
		addQuestion(new Question(
			"How many players are on a basketball team on the court?",
			Arrays.asList("4", "5", "6", "7"),
			1, "Sports", "Think about the starting lineup", 1
		));
		
		addQuestion(new Question(
			"In which sport would you perform a slam dunk?",
			Arrays.asList("Volleyball", "Basketball", "Tennis", "Soccer"),
			1, "Sports", "It involves putting the ball through a hoop", 1
		));
		
		addQuestion(new Question(
			"What does CPU stand for?",
			Arrays.asList("Central Processing Unit", "Computer Processing Unit", 
					 "Central Program Unit", "Computer Program Unit"),
			0, "Technology", "It's the brain of the computer", 2
		));
		
		addQuestion(new Question(
			"Which programming language was created by James Gosling?",
			Arrays.asList("C++", "Python", "Java", "JavaScript"),
			2, "Technology", "Think of coffee - this language is named after it", 2
		));
	}
}


