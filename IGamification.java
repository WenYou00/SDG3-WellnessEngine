/**
 * Interface: IGamification
 * Description: Contract for awarding stars, badges, and updating leaderboard.
 */

public interface IGamification {
    void awardStars(int score);
    void awardBadges(int score, int totalQuestions);
    void updateLeaderboard(String playerName, int finalScore);
}