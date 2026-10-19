/*
 * Interface: IDataHandler
 * Description: Contract for saving/loading scores to file.
*/

import java.util.Map;

public interface IDataHandler {
    void saveScore(String username, int score);
    Map<String, Integer> loadScores();
    int getHighestScore(String username);
    void displayAllScores();
    boolean fileExists();
}