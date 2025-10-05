🎮 Online Quiz Game System

A console-based quiz game in Java with multiple categories, scoring system, leaderboards, and interactive gameplay features.

## ✨ Features

- **Multiple Categories**: Science, History, Geography, Sports, Technology
- **Smart Scoring**: Points based on difficulty (10/20/30 pts) with 25% penalty for wrong answers
- **Player Profiles**: Track scores, accuracy, and game history
- **Leaderboard**: Top 10 players ranked by total score
- **Interactive Lifelines**: Hints and skip options during quiz
- **Custom Questions**: Add your own questions to the game

## 🚀 Quick Start

```bash
# Clone the repository
git clone https://github.com/yourusername/online-quiz-game.git

# Compile
javac *.java

# Run
java OnlineQuizGame
```

## 🎯 How to Play

1. Enter your name to login
2. Select "Start New Quiz" from the main menu
3. Choose a category or "All Categories"
4. Answer questions (1-4) or use:
   - `h` for hint
   - `s` to skip question
5. View your final score and leaderboard ranking

## 📁 Project Structure

- `OnlineQuizGame.java` - Main application controller
- `QuizEngine.java` - Quiz execution logic
- `QuestionManager.java` - Question storage and retrieval
- `Player.java` - Player profiles and statistics
- `Leaderboard.java` - Ranking system
- `Question.java` & `QuizAttempt.java` - Data models

## 🔧 Requirements

- Java JDK 8 or higher

## 🤝 Contributing

Contributions welcome! Fork the repo and submit a pull request.

---

**Happy Quizzing! 🏆**
