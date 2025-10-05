import java.util.*;

/**
 * Manages the leaderboard and player rankings (Lite)
 */
public class Leaderboard {
	private List<Player> players;
	private Map<String, Player> playerMap;
	
	public Leaderboard() {
		this.players = new ArrayList<>();
		this.playerMap = new HashMap<>();
	}
	
	public void updatePlayer(Player player) {
        if (!playerMap.containsKey(player.getName())) {
            players.add(player);
        }
        // Ensure map points to the current instance; do not add attempts here
        playerMap.put(player.getName(), player);
	}
	
	public List<Player> getTopPlayers(int count) {
		List<Player> sortedPlayers = new ArrayList<>(players);
		sortedPlayers.sort((p1, p2) -> Integer.compare(p2.getTotalScore(), p1.getTotalScore()));
		int playersToReturn = Math.min(count, sortedPlayers.size());
		return sortedPlayers.subList(0, playersToReturn);
	}
	
	public Player getPlayer(String name) {
		return playerMap.get(name);
	}
	
	public void displayLeaderboard() {
		System.out.println("\n" + "=".repeat(80));
		System.out.println("LEADERBOARD");
		System.out.println("=".repeat(80));
		List<Player> topPlayers = getTopPlayers(10);
		if (topPlayers.isEmpty()) {
			System.out.println("No players yet. Be the first to play!");
			return;
		}
		System.out.printf("%-4s %-20s %-10s %-8s %-12s %-10s%n", 
					 "Rank", "Player", "Score", "Games", "Accuracy", "Questions");
		System.out.println("-".repeat(80));
		for (int i = 0; i < topPlayers.size(); i++) {
			Player player = topPlayers.get(i);
			String medal = i == 0 ? "[1st]" : i == 1 ? "[2nd]" : i == 2 ? "[3rd]" : "[" + (i + 1) + "]";
			System.out.printf("%-6s %-20s %-10d %-8d %-11.1f%% %-10d%n",
						 medal, 
						 player.getName(),
						 player.getTotalScore(),
						 player.getGamesPlayed(),
						 player.getAccuracy(),
						 player.getTotalQuestions());
		}
		System.out.println("=".repeat(80));
	}
}


