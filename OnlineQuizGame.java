import java.util.*;


public class OnlineQuizGame {
    private Scanner scanner;
    private QuestionManager questionManager;
    private QuizEngine quizEngine;
    private Leaderboard leaderboard;
    private Player currentPlayer;
    
    public OnlineQuizGame() {
        this.scanner = new Scanner(System.in);
        this.questionManager = new QuestionManager();
        this.quizEngine = new QuizEngine(questionManager);
        this.leaderboard = new Leaderboard();
        this.currentPlayer = null;
    }
    
    public static void main(String[] args) {
        OnlineQuizGame quiz = new OnlineQuizGame();
        quiz.run();
    }
    
    public void run() {
        displayWelcome();
        
        while (true) {
            if (currentPlayer == null) {
                handlePlayerLogin();
            }
            
            displayMainMenu();
            int choice = getMenuChoice(0, 4);
            
            switch (choice) {
                case 1: startNewQuiz(); break;
                case 2: viewLeaderboard(); break;
                case 3: addNewQuestion(); break;
                case 4: switchPlayer(); break;
                case 0: 
                    System.out.println("\nThank you for playing! Goodbye!");
                    System.exit(0);
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    private void displayWelcome() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("WELCOME TO THE ONLINE QUIZ GAME SYSTEM!");
        System.out.println("=".repeat(80));
        System.out.println("Test your knowledge across various categories!");
        System.out.println("Features: Timer - Randomized - Leaderboard - Lifelines - Analytics");
        System.out.println("=".repeat(80));
    }
    
    private void handlePlayerLogin() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PLAYER LOGIN");
        System.out.println("=".repeat(50));
        
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine().trim();
        
        if (playerName.isEmpty()) {
            playerName = "Anonymous Player";
        }
        
        currentPlayer = leaderboard.getPlayer(playerName);
        if (currentPlayer == null) {
            currentPlayer = new Player(playerName);
            System.out.println("[OK] Welcome, " + playerName + "! You're a new player.");
        } else {
            System.out.println("[OK] Welcome back, " + playerName + "!");
            System.out.println("Your stats: Score: " + currentPlayer.getTotalScore() + 
                             ", Games: " + currentPlayer.getGamesPlayed() + 
                             ", Accuracy: " + String.format("%.1f", currentPlayer.getAccuracy()) + "%");
        }
    }
    
    private void displayMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("MAIN MENU - Welcome, " + currentPlayer.getName() + "!");
        System.out.println("=".repeat(60));
        System.out.println("1. Start New Quiz");
        System.out.println("2. View Leaderboard");
        System.out.println("3. Add New Question");
        System.out.println("4. Switch Player");
        System.out.println("0. Exit");
        System.out.println("- Tips: During quiz, type h=Hint, s=Skip");
        System.out.println("=".repeat(60));
    }
    
    private void startNewQuiz() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("START NEW QUIZ");
        System.out.println("=".repeat(50));
        
        String category = selectCategory();
        int questionCount = category.equals("All Categories")
            ? questionManager.getTotalQuestionCount()
            : questionManager.getQuestionCount(category);
        
        System.out.println("\nControls: h = Hint, s = Skip");
        
        QuizAttempt attempt = quizEngine.startQuiz(currentPlayer, category, questionCount);
        
        if (attempt != null) {
            currentPlayer.addQuizAttempt(attempt);
            leaderboard.updatePlayer(currentPlayer);
            
            System.out.println("\n[SUCCESS] Quiz completed!");
            System.out.println("Final Score: " + attempt.getScore());
            System.out.println("Accuracy: " + String.format("%.1f", attempt.getAccuracy()) + "%");
        }
    }
    
    private String selectCategory() {
        Set<String> categories = questionManager.getCategories();
        List<String> categoryList = new ArrayList<>(categories);
        categoryList.add("All Categories");
        
        System.out.println("\nAvailable Categories:");
        for (int i = 0; i < categoryList.size(); i++) {
            String category = categoryList.get(i);
            int count = category.equals("All Categories") ? 
                       questionManager.getTotalQuestionCount() : 
                       questionManager.getQuestionCount(category);
            System.out.println((i + 1) + ". " + category + " (" + count + " questions)");
        }
        
        System.out.print("Select category (1-" + categoryList.size() + "): ");
        int choice = getMenuChoice(1, categoryList.size());
        return categoryList.get(choice - 1);
    }
    
    private void viewLeaderboard() {
        leaderboard.displayLeaderboard();
    }
    
    private void addNewQuestion() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ADD NEW QUESTION");
        System.out.println("=".repeat(50));
        
        System.out.print("Enter question text: ");
        String questionText = scanner.nextLine().trim();
        
        if (questionText.isEmpty()) {
            System.out.println("[ERROR] Question text cannot be empty!");
            return;
        }
        
        System.out.println("\nEnter options (4 options required):");
        List<String> options = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            System.out.print("Option " + (i + 1) + ": ");
            options.add(scanner.nextLine().trim());
        }
        
        System.out.print("Enter correct answer (1-4): ");
        int correctAnswer = getMenuChoice(1, 4) - 1;
        
        System.out.print("Enter category: ");
        String category = scanner.nextLine().trim();
        
        System.out.print("Enter hint: ");
        String hint = scanner.nextLine().trim();
        
        System.out.print("Enter difficulty (1=Easy, 2=Medium, 3=Hard): ");
        int difficulty = getMenuChoice(1, 3);
        
        Question newQuestion = new Question(questionText, options, correctAnswer, category, hint, difficulty);
        questionManager.addQuestion(newQuestion);
        
        System.out.println("[OK] Question added successfully!");
    }
    
    
    
    private void switchPlayer() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SWITCH PLAYER");
        System.out.println("=".repeat(50));
        
        currentPlayer = null;
        System.out.println("[OK] Player logged out. Please login again.");
    }
    
    private int getMenuChoice(int min, int max) {
        while (true) {
            try {
                System.out.print("Your choice: ");
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    System.out.println("[ERROR] Please enter a valid choice!");
                    continue;
                }
                
                int choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("[ERROR] Please enter a number between " + min + " and " + max + "!");
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Please enter a valid number!");
            }
        }
    }
}
