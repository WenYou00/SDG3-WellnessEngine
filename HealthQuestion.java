/*
 * Class: HealthQuestion
 * Description: Implements Evaluable. Stores question text, options, and correct answer.
*/

public class HealthQuestion implements Evaluable {
    private String questionPrompt;
    private String[] options;
    private String correctAnswer;

    public HealthQuestion(String prompt, String[] opts, String answer) {
        this.questionPrompt = prompt;
        this.options = opts;
        this.correctAnswer = answer;
    }

    @Override 
    public String getQuestionPrompt() { 
        return questionPrompt; 
    }

    @Override 
    public String[] getOptions() { 
        return options; 
    }
    
    @Override 
    public boolean checkAnswer(String userAnswer) {
        return correctAnswer.trim().equalsIgnoreCase(userAnswer.trim());
    }
}

