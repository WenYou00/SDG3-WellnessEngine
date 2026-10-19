/*
 * Class: GamificationEngine
 * Description: - Implements IGamification interface. 
 *              - Uses file storage for leaderboard, method overloading, badge logic.
*/

// Import AWT classes for GUI components and layouts
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

// Class declaration implementing IGamification interface
public class GamificationEngine implements IGamification {
    // Private final variable to store player's name (cannot be changed after construction)
    //private final String playerName;
    // Private variable to track number of stars earned
    private int starsEarned;
    // Private final list to store badges earned by player
    private final List<String> badges;
    // Static constant for leaderboard filename (shared across all instances)
    private static final String LEADERBOARD_FILE = "leaderboard.txt";

    // Constructor that takes player name as parameter
    public GamificationEngine(String playerName) {
        // Initialize player name field
        //this.playerName = playerName;
        // Initialize stars earned to zero
        this.starsEarned = 0;
        // Initialize empty ArrayList for badges
        this.badges = new ArrayList<>();
    }

    // Method to award stars based on score and total questions
    public void awardStars(int score, int totalQuestions) {
        // Calculate percentage score as double
        double percentage = ((double) score / totalQuestions) * 100;
        // Check if percentage is 90% or above
        if (percentage >= 90) {
            // Award 5 stars for excellent performance
            starsEarned = 5;
        // Check if percentage is 70% or above
        } else if (percentage >= 70) {
            // Award 4 stars for good performance
            starsEarned = 4;
        // Check if percentage is 40% or above
        } else if (percentage >= 40) {
            // Award 3 stars for average performance
            starsEarned = 3;
        // Check if percentage is 20% or above
        } else if (percentage >= 20) {
            // Award 2 stars for below average performance
            starsEarned = 2;
        // All other percentages below 20%
        } else {
            // Award 1 star for participation
            starsEarned = 1;
        }
    }

    // Override method from IGamification interface (method overloading)
    @Override
    public void awardStars(int score) {
        // Call the main awardStars method with default 20 total questions
        awardStars(score, 20);
    }

    // Override method to award badges based on score and total questions
    @Override
    public void awardBadges(int score, int totalQuestions) {
        // Clear any existing badges before awarding new ones
        badges.clear();
        // Calculate percentage score
        double percentage = ((double) score / totalQuestions) * 100;

        // Check if percentage is 90% or above
        if (percentage >= 90) {
            // Add Master Scholar badge to list
            badges.add("Master Scholar Badge");
        }
        // Check if percentage is 70% or above (independent of previous condition)
        else if (percentage >= 70) {
            // Add Knowledge Seeker badge to list
            badges.add("Knowledge Seeker Badge");
        }
        // Check if percentage is 40% or above
        else if (percentage >= 40) {
            // Add Green Learner badge to list
            badges.add("Green Learner Badge");
        }
        // Check if percentage is 20% or above
        else if (percentage >= 20) {
            // Add Perseverance badge to list
            badges.add("Perseverance Badge");
        }
        // Check if score is positive but percentage is below 20%
        else if (score > 0 && percentage < 20) {
            // Add Rainmaker badge for low but non-zero scores
            badges.add("Rainmaker Badge");
        }
    }

    // Override method to update leaderboard with player name and score
    @Override
    public void updateLeaderboard(String playerName, int finalScore) {
        // Create ArrayList to store leaderboard entries
        List<String> leaderboard = new ArrayList<>();
        // Create File object for leaderboard file
        File file = new File(LEADERBOARD_FILE);

        // Check if leaderboard file already exists
        if (file.exists()) {
            // Try-with-resources to automatically close BufferedReader
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                // Variable to store each line read from file
                String line;
                // Read file line by line until end of file
                while ((line = reader.readLine()) != null) {
                    // Add each line to leaderboard list
                    leaderboard.add(line);
                }
            // Catch and handle any IO exceptions
            } catch (IOException e) {
                // Print error message to console
                System.out.println("Error reading leaderboard: " + e.getMessage());
            }
        }

        // Add new player entry to leaderboard list
        leaderboard.add(playerName + ":" + finalScore);

        // Sort leaderboard in descending order by score
        leaderboard.sort((a, b) -> {
            try {
                String[] partA = a.split(":");
                String[] partB = b.split(":");
                if (partA.length < 2 || partB.length < 2) return 0;
                // Parse score from first entry (after colon)
                int scoreA = Integer.parseInt(partA[1]);
                // Parse score from second entry
                int scoreB = Integer.parseInt(partB[1]);
                 // Compare scores for descending order (higher scores first)
                return Integer.compare(scoreB, scoreA);
            } catch (Exception e) {
                return 0;
            }
        });

        // Try-with-resources to automatically close BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Initialize counter for entries written
            int count = 0;
            // Loop through each entry in sorted leaderboard
            for (String entry : leaderboard) {
                // Write entry to file
                writer.write(entry);
                // Write new line after each entry
                writer.newLine();
                // Increment counter
                count++;
                // Stop after writing top 10 entries
                if (count >= 10) break;
            }
        // Catch and handle any IO exceptions
        } catch (IOException e) {
            // Print error message to console
            System.out.println("Error writing leaderboard: " + e.getMessage());
        }
    }

    // Method to display leaderboard in a GUI dialog
    public void displayLeaderboardGUI(JFrame parent) {
        // Create File object for leaderboard file
        File file = new File(LEADERBOARD_FILE);
        // Create StringBuilder for building display text
        StringBuilder sb = new StringBuilder();
        // Add top border line
        sb.append("═══════════════════════════════\n");
        // Add leaderboard title with trophy emojis
        sb.append("        🏆 LEADERBOARD 🏆        \n");
        // Add bottom border line
        sb.append("═══════════════════════════════\n\n");
        
        // Check if leaderboard file does not exist
        if (!file.exists()) {
            // Message for empty leaderboard
            sb.append("     No scores yet.\n");
            // Encouragement to be first player
            sb.append("  Be the first to play!\n");
        } else {
            // Try-with-resources to read leaderboard file
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                // Variable to store each line
                String line;
                // Initialize rank counter starting at 1
                int rank = 1;
                // Read file line by line
                while ((line = reader.readLine()) != null) {
                    // Split line by colon separator
                    String[] parts = line.split(":");
                    // Check if line has both name and score
                    if (parts.length == 2) {
                        // Format and append rank, name, and points
                        sb.append(String.format("  %d. %-15s %3s points\n", rank, parts[0], parts[1]));
                        // Increment rank counter
                        rank++;
                    }
                }
            // Catch and handle IO exceptions
            } catch (IOException e) {
                // Add error message to display
                sb.append("Error loading leaderboard.\n");
            }
        }
        
        // Add bottom border line
        sb.append("\n═══════════════════════════════\n");
        
        // Create JTextArea to display leaderboard text
        JTextArea textArea = new JTextArea(sb.toString());
        // Make text area non-editable
        textArea.setEditable(false);
        // Set monospaced font for aligned display
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        // Set light gray background color
        textArea.setBackground(new Color(245, 245, 245));
        // Create scroll pane for text area
        JScrollPane scrollPane = new JScrollPane(textArea);
        // Set preferred size for scroll pane
        scrollPane.setPreferredSize(new Dimension(400, 300));
        // Add green border around scroll pane
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(67, 130, 0), 2));
        
        // Show message dialog with leaderboard
        JOptionPane.showMessageDialog(parent, scrollPane, " Leaderboard", 
                                       JOptionPane.INFORMATION_MESSAGE);
    }

    // Getter method for stars earned
    public int getStarsEarned() {
        // Return current stars earned value
        return starsEarned;
    }

    // Getter method for badges list
    public List<String> getBadges() {
        // Return list of earned badges
        return badges;
    }

    // Method to award badges with default total questions (method overloading)
    public void awardBadges(int score) {
        // Call main awardBadges method with default 20 questions
        awardBadges(score, 20);
    }
}