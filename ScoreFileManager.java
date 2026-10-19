/*
 * Class: ScoreFileManager
 * Description: - Manages quiz scores storage to scores.txt file.
 *              - Implements IDataHandler interface for score operations.
*/

import java.io.*;
import java.util.*;

public class ScoreFileManager implements IDataHandler {
    
    // File name for storing scores
    private static final String SCORE_FILE = "scores.txt";
    
    // Saves a user's score. Keeps only the highest score per user.
    @Override
    public void saveScore(String username, int score) {
        // Load existing scores
        Map<String, Integer> allScores = loadScores();
        
        // Keep highest score per user only
        if (!allScores.containsKey(username) || allScores.get(username) < score) {
            allScores.put(username, score);
        }
        
        // Write back to file
        writeScoresToFile(allScores);
    }
    
    // Overloading example: save score with date.
    public void saveScore(String username, int score, String date) {
        Map<String, Integer> allScores = loadScores();
        if (!allScores.containsKey(username) || allScores.get(username) < score) {
            allScores.put(username, score);
        }
        
        // Write scores with date to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            for (Map.Entry<String, Integer> entry : allScores.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + ":" + date);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving scores: " + e.getMessage());
        }
    }
    
    // Writes all scores to file (private helper method).
    private void writeScoresToFile(Map<String, Integer> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving scores: " + e.getMessage());
        }
    }
    
    // Loads all scores from file. Returns a Map with username as key.
    @Override
    public Map<String, Integer> loadScores() {
        Map<String, Integer> scores = new HashMap<>();
        
        // Read file line by line
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2) {
                    String name = parts[0];
                    try {
                        int score = Integer.parseInt(parts[1]);

                        // Keep highest score per user
                        if (!scores.containsKey(name) || scores.get(name) < score) {
                            scores.put(name, score);
                        }
                    } catch (NumberFormatException e) {
                        // Skip invalid line
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet
        } catch (IOException e) {
            System.out.println("Error loading scores: " + e.getMessage());
        }
        
        return scores;
    }
    
    // Gets the highest score for a specific user
    @Override
    public int getHighestScore(String username) {
        Map<String, Integer> scores = loadScores();
        return scores.getOrDefault(username, 0);
    }
    
    // Displays all scores in descending order (leaderboard)
    @Override
    public void displayAllScores() {
        Map<String, Integer> scores = loadScores();
        if (scores.isEmpty()) {
            System.out.println("No scores yet.");
            return;
        }
        
        // Sort by score (highest first)
        List<Map.Entry<String, Integer>> list = new ArrayList<>(scores.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        // Print leaderboard
        System.out.println("===== HIGH SCORES =====");
        int rank = 1;
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(rank + ". " + entry.getKey() + ": " + entry.getValue() + "/20");
            rank++;
            if (rank > 10) break;  // Show only top 10
        }
    }
    
    // Checks if the score file exists.
    @Override
    public boolean fileExists() {
        File file = new File(SCORE_FILE);
        return file.exists();
    }
}