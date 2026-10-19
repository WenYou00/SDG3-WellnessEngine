/*
 * Interface: Evaluable
 * Description: Contract for quiz questions. Defines question prompt, options, and answer check.
*/

public interface Evaluable {
    String getQuestionPrompt();
    String[] getOptions();
    boolean checkAnswer(String userAnswer);
}