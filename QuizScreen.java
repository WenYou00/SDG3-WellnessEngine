/*
 * Class: QuizScreen
 * Description: Main quiz interface with 20 questions, timer, scoring, and gamification.
*/


import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class QuizScreen extends JFrame {// This class represents the quiz interface where users will answer questions related to SDG 3,
                                        //  and it manages the quiz flow, user interactions, and result display based on the data provided in the quizBank array

    // Sallen: Gamification and Timer fields
    private GamificationEngine gamification;
    private GlobalTimerManager timer;
    private JLabel timerLabel;
    private Timer displayTimer;
                                   
    private String username;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private HealthQuestion[] quizBank;

    // GUI Components
    private JLabel progressLabel;
    private JTextArea questionArea;
    private JRadioButton[] optionButtons;
    private ButtonGroup buttonGroup;
    private JButton actionButton;
    private JButton endQuizButton; // Button to exit the quiz immediately
    private JPanel quizContentPanel;
    private JPanel topPanel;

    public QuizScreen(String username) { // Constructor receives username for personalized experience and potential future data tracking
        this.username = username;// Store username for later use in results display or potential future features like user profiles or progress tracking

        // Sallen: Initialize gamification
        this.gamification = new GamificationEngine(username);



        // 1. Initialize your data bank structure matching image_b82163.png
        loadDataBanks();

        // =========================
        // FRAME SETTINGS (MOBILE)
        // =========================
        setTitle("SDG 3 Quiz");
        setSize(410, 730);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Changed from EXIT_ON_CLOSE to DISPOSE_ON_CLOSE to allow returning to HomePage without closing the entire app
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        GridBagConstraints gbc = new GridBagConstraints();// Used for flexible component placement and spacing control
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header Title
        JLabel header = new JLabel("Let's Test Your Knowledge!", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setForeground(new Color(67, 130, 0));
        gbc.insets = new Insets(0, 0, 15, 0);
        panel.add(header, gbc);
        gbc.gridy++;// Move to the next row for the progress tracker and quiz content below

        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        // Progress Tracker
        progressLabel = new JLabel("Question 1 of " + quizBank.length, SwingConstants.LEFT);// Initializes with the first question's progress status
        progressLabel.setFont(new Font("Arial", Font.BOLD, 12));
        progressLabel.setForeground(Color.GRAY);

        // Sallen: Timer Label
        timerLabel = new JLabel("Time: --", SwingConstants.RIGHT);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 12));
        timerLabel.setForeground(Color.RED);
        
        topPanel.add(progressLabel, BorderLayout.WEST);
        topPanel.add(timerLabel, BorderLayout.EAST);
        
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(topPanel, gbc);
        gbc.gridy++;

        // Quiz Content Card Box
        quizContentPanel = new JPanel(); // This panel will hold the question text and the answer options, styled as a card with padding and borders to visually separate it from the background
        quizContentPanel.setLayout(new BoxLayout(quizContentPanel, BoxLayout.Y_AXIS));
        quizContentPanel.setBackground(new Color(245, 245, 245));
        quizContentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 225, 225), 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Wrapped Question Display area
        questionArea = new JTextArea();
        questionArea.setFont(new Font("Arial", Font.BOLD, 16));
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setEditable(false);
        questionArea.setFocusable(false);
        questionArea.setOpaque(false);
        questionArea.setForeground(new Color(35, 35, 35));
        questionArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        quizContentPanel.add(questionArea);
        quizContentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Radio Options setup
        optionButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            optionButtons[i].setOpaque(false);
            optionButtons[i].setFocusPainted(false);
            optionButtons[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            buttonGroup.add(optionButtons[i]);
            quizContentPanel.add(optionButtons[i]);
            quizContentPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        }

        gbc.insets = new Insets(0, 0, 25, 0);// Spacing after the quiz content card before the action button
        panel.add(quizContentPanel, gbc);
        gbc.gridy++;// Move to the next row for the action button below

        // Submit Action button
        actionButton = new JButton("Submit Answer");
        actionButton.setFont(new Font("Arial", Font.BOLD, 16));
        actionButton.setBackground(new Color(67, 130, 0));
        actionButton.setForeground(Color.WHITE);
        actionButton.setFocusPainted(false);
        actionButton.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        actionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        actionButton.addActionListener(e -> handleActionClick());// Centralized method to handle both answer submission and final quiz completion logic based on the button's current text state (Submit Answer vs Back to Home)

        gbc.insets = new Insets(0, 0, 0, 0);// No extra spacing needed after the button as it's the last component in the layout
        panel.add(actionButton, gbc);

        add(panel);

// =====================================================
// END QUIZ IMMEDIATELY BUTTON
// =====================================================
endQuizButton = new JButton("End Quiz");
endQuizButton.setFont(new Font("Arial", Font.BOLD, 14));
endQuizButton.setBackground(new Color(180, 40, 40)); // A soft red color for an exit action
endQuizButton.setForeground(Color.WHITE);
endQuizButton.setFocusPainted(false);
endQuizButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
endQuizButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

// What happens when clicked:
endQuizButton.addActionListener(e -> {
    // 1. Confirm if the user really wants to quit
    int confirm = JOptionPane.showConfirmDialog(
        this, 
        "Are you sure you want to end the quiz? Your current progress will be lost.", 
        "End Quiz?", 
        JOptionPane.YES_NO_OPTION, 
        JOptionPane.QUESTION_MESSAGE
    );
    
    // 2. If they click YES, take them back to the HomePage/LearnScreen and close this window
    if (confirm == JOptionPane.YES_OPTION) {
        new HomePage(username); // Opens the home page
        dispose();              // Closes and clears the quiz from memory
    }
});

// Add it to your GridBagLayout panel
gbc.gridy++;
gbc.insets = new Insets(10, 0, 0, 0); // Spacing above the exit button
panel.add(endQuizButton, gbc);

        // Sallen: Start the quiz timer
        startQuizTimer();

        // Load the very first question from the object bank
        displayQuestion();

        setVisible(true);
    }

    // =====================================================
    // TIMER METHODS
    // =====================================================
    
    private void startQuizTimer() {
        timer = new GlobalTimerManager(300, () -> {
            SwingUtilities.invokeLater(() -> {
                if (timer.isTimeExpired()) {
                    JOptionPane.showMessageDialog(this, "Time's Up! Submitting your quiz...");
                    autoSubmitQuiz();
                }
            });
        });
        timer.startTimer();
        
        displayTimer = new Timer(1000, e -> updateTimerDisplay());
        displayTimer.start();
    }
    
    private void updateTimerDisplay() {
        if (timer != null && !timer.isTimeExpired()) {
            int remaining = timer.getTimeRemaining();
            int minutes = remaining / 60;
            int seconds = remaining % 60;
            timerLabel.setText(String.format("Time: %02d:%02d", minutes, seconds));
        } else if (timer != null && timer.isTimeExpired()) {
            timerLabel.setText("Time: Expired!");
            if (displayTimer != null) {
                displayTimer.stop();
            }
        }
    }
    
    private void autoSubmitQuiz() {
        if (displayTimer != null) {
            displayTimer.stop();
        }
        showResults();
    }

    // =====================================================
    // DATA POPULATION METHOD(FROM image_b82163.png)
    // =====================================================
    private void loadDataBanks() { // This method initializes the quizBank array with HealthQuestion objects based on the provided question and answer data, 
                                 // structured to match the image reference for consistency in content delivery
        // Change the array size parameter if you expand your bank beyond 17 questions later!
        quizBank = new HealthQuestion[20];// Total of 20 questions (13 MCQ + 7 True/False) as per the provided data set, indexed from 0 to 19

        // 13 MCQ Questions
        quizBank[0] = new HealthQuestion("Which diseases are specifically targeted under communicable disease prevention?", new String[]{"A) Cancer and Diabetes", "B) Asthma and Obesity", "C) HIV, Malaria and Tuberculosis", "D) Heart Disease and Stroke"}, "C");
        quizBank[1] = new HealthQuestion("What is one benefit of routine health screenings?", new String[]{"A) Increase disease spread", "B) Detect health issues early", "C) Replace traditional medicine", "D) Eliminate all diseases"}, "B");
        quizBank[2] = new HealthQuestion("Target moderate exercise minutes per week?", new String[]{"A) 30 min", "B) 60 min", "C) 150 min", "D) 500 min"}, "C");
        quizBank[3] = new HealthQuestion("Vitamin synthesized via natural solar rays?", new String[]{"A) Vitamin A", "B) Vitamin B12", "C) Vitamin C", "D) Vitamin D"}, "D");
        quizBank[4] = new HealthQuestion("Normal resting heart rate range for adults?", new String[]{"A) 30-50 bpm", "B) 60-100 bpm", "C) 120-150 bpm", "D) 160-200 bpm"}, "B");
        quizBank[5] = new HealthQuestion("Optimal foundation for structural dietary plates?", new String[]{"A) Sugar", "B) Fats", "C) Whole Grains/Veg", "D) Dairy elements"}, "C");
        quizBank[6] = new HealthQuestion("Effective path to de-escalate acute mental anxiety?", new String[]{"A) Suppression", "B) Deep Breathing", "C) Caffeine loading", "D) Isolation"}, "B");
        quizBank[7] = new HealthQuestion("Which is a non-communicable chronic disease?", new String[]{"A) Influenza", "B) Type 2 Diabetes", "C) Malaria", "D) Rabies"}, "B");
        quizBank[8] = new HealthQuestion("What is the central priority code of SDG 3?", new String[]{"A) Factory builds", "B) Green energy", "C) Health and well-being", "D) Space Exploration"}, "C");
        quizBank[9] = new HealthQuestion("Max daily sodium limits advised for typical adults?", new String[]{"A) 500 mg", "B) 2,300 mg", "C) 5,000 mg", "D) 10,000 mg"}, "B");
        quizBank[10] = new HealthQuestion("Which of these is a key mental health disorder?", new String[]{"A) Hypertension", "B) Depression", "C) Asthma", "D) Arthritis"}, "B");
        quizBank[11] = new HealthQuestion("Which is a common symptom of vitamin D deficiency?", new String[]{"A) Fatigue", "B) Improved vision", "C) Increased energy", "D) Enhanced immunity"}, "A");
        quizBank[12] = new HealthQuestion("Which lifestyle factor most contributes to cardiovascular disease?", new String[]{"A) Regular exercise", "B) Balanced diet", "C) Smoking", "D) Adequate sleep"}, "C");
        
        // 7 True/False Questions (Optimized to pass only 2 options instead of 4!)
        quizBank[13] = new HealthQuestion("Antibiotics successfully eradicate viral standard influenza.", new String[]{"A) True", "B) False"}, "B");
        quizBank[14] = new HealthQuestion("Maternal and child survival is a key indicator of a country's health system performance.", new String[]{"A) True", "B) False"}, "A");
        quizBank[15] = new HealthQuestion("Dynamic stretching preserves localized musculoskeletal health.", new String[]{"A) True", "B) False"}, "A");
        quizBank[16] = new HealthQuestion("Chronic sleep deprivation degrades baseline leukocyte tracking.", new String[]{"A) True", "B) False"}, "A");
        quizBank[17] = new HealthQuestion("Electronic vaporizers present zero tracking damage markers to lungs.", new String[]{"A) True", "B) False"}, "B");
        quizBank[18] = new HealthQuestion("Hypertension displays clear early distinct physical visual markers.", new String[]{"A) True", "B) False"}, "B");
        quizBank[19] = new HealthQuestion("Poor sanitation and unsafe water can contribute to disease outbreaks.", new String[]{"A) True", "B) False"}, "A");
    }

    // =====================================================
    // CORE DYNAMIC INTERACTIVE LOGIC
    // =====================================================
    private void displayQuestion() { // This method updates the quiz content panel with the current question and its corresponding answer options, handling both MCQ and True/False formats dynamically based on the data in the quizBank array
        buttonGroup.clearSelection(); // Clears any previous selections to ensure a fresh state for the new question
        
        // Target current active data object
        HealthQuestion currentQuestion = quizBank[currentQuestionIndex];// Pulls the current question object from the quizBank array based on the currentQuestionIndex tracker,
                                                                        //  allowing for dynamic content updates as the user progresses through the quiz


        // Update Tracker Progress Headers
        progressLabel.setText("Question " + (currentQuestionIndex + 1) + " of " + quizBank.length);// Updates the progress label to reflect the current question number out of the total,
                                                                                                    //  providing real-time feedback on quiz progression for the user
        
        
        
        questionArea.setText(currentQuestion.getQuestionPrompt());// Sets the question text area with the prompt from the current HealthQuestion object, allowing for dynamic question content display based on the quizBank data structure


        // Pull option array values safely from within your object instance
        String[] currentOptions = currentQuestion.getOptions();// Retrieves the array of answer options for the current question, which can vary in length (2 for True/False, 4 for MCQ),
                                                                //  allowing for flexible handling of different question formats within the same display logic

        int totalAvailableOptions = currentOptions.length;// Determines how many options are available for the current question, which is crucial for correctly displaying only 
                                                         // the relevant number of option buttons (e.g., showing only 2 buttons for True/False questions and hiding the unused buttons to maintain a clean UI)

        for (int i = 0; i < 4; i++) { // Loop through all 4 pre-allocated option buttons, but only set text and visibility for the number of options that are actually available for the current question
            if (i < totalAvailableOptions) { // If the current index is within the range of available options for this question, set the button text and make it visible
                optionButtons[i].setText(currentOptions[i]);// Sets the text of the option button to the corresponding option from the current question's options array, ensuring that the displayed options match the content of the current question
                optionButtons[i].setVisible(true);// Makes the button visible if it's within the range of available options, allowing for dynamic adjustment of the UI based on whether it's a True/False question (2 options) or an MCQ (4 options)
            } else {// If the current index exceeds the number of available options for this question, hide the button to maintain a clean and relevant UI, especially important for True/False questions where only 2 options are needed
                // Instantly hides button indices 2 and 3 if it's a 2-option True/False question!

                optionButtons[i].setVisible(false);
            }
        }
        actionButton.setText("Submit Answer");

      // Smoothly redraw card boundaries
        quizContentPanel.revalidate();// Revalidates the quiz content panel to ensure that any changes to the components (like visibility toggling of option buttons) are
                                    //  properly accounted for in the layout, allowing for dynamic adjustments to the UI as the user progresses through different question formats


        quizContentPanel.repaint();// Repaints the quiz content panel to visually update the display after any changes to the components, ensuring that the user sees the most current state of the quiz content as they interact with it
    
    }

    private void handleActionClick() { // This method handles the logic for when the user clicks the action button, which serves dual purposes: submitting an answer during the quiz and
                                      //  allowing the user to return to the home page after completing the quiz, based on the button's current text state

        if (actionButton.getText().equals("Back to Home")) {
            if (displayTimer != null) displayTimer.stop();
            if (timer != null) timer.stopTimer();
             //new HomePage(username);
            dispose();
            
            return;
        }

        HealthQuestion currentQuestion = quizBank[currentQuestionIndex];// Retrieves the current question object from the quizBank array based on the currentQuestionIndex tracker,
                                                                        //  allowing access to the question's options and answer-checking logic for validating the user's selected answer when they click the submit button

        String[] currentOptions = currentQuestion.getOptions();
        int totalAvailableOptions = currentOptions.length;

        int selectedIndex = -1;// This variable will hold the index of the selected option,
                                //  initialized to -1 to indicate that no selection has been made yet, which is important for validating that the user has actually chosen an answer before allowing them to submit

        for (int i = 0; i < totalAvailableOptions; i++) {// Loop through only the available options for the current question to check which one is selected,
                                                          //  ensuring that we are validating against the correct set of options based on whether it's a True/False or MCQ format


            if (optionButtons[i].isSelected()) { // Checks if the current option button in the loop is selected by the user, which is crucial for determining which answer they have chosen to submit for validation against the correct answer stored in the HealthQuestion object
                selectedIndex = i;
                break;
            }
        }

        if (selectedIndex == -1) {  // If no option is selected (selectedIndex remains -1), show a warning message prompting the user to make a selection before they can submit their answer, which is important for ensuring that the quiz interaction is complete and that the user is actively engaging with the content rather than accidentally submitting without choosing an answer
            JOptionPane.showMessageDialog(this, "Please select an answer choice before moving forward!", "Selection Needed", JOptionPane.WARNING_MESSAGE);
            return;
        }

         // Mapping index profiles back to Character representations ('A', 'B', 'C', 'D')
        char selectedCharAnswer = (char) ('A' + selectedIndex);
        String userAnsStr = String.valueOf(selectedCharAnswer);

        if (currentQuestion.checkAnswer(userAnsStr)) {
            score++;
        }

         // Layout state iterator
        currentQuestionIndex++;
        if (currentQuestionIndex < quizBank.length) {
            displayQuestion();
        } else {
            if (displayTimer != null) displayTimer.stop();
            if (timer != null) timer.stopTimer();
            showResults();
        }
    }

// =====================================================
// SHOW RESULTS WITH GAMIFICATION
// =====================================================
// Method to display quiz results with gamification elements
private void showResults() {
    // Check if display timer is currently running
    if (displayTimer != null) {
        // Stop the display timer to prevent further updates
        displayTimer.stop();
        // Set display timer reference to null
        displayTimer = null;
    }
    // Check if quiz timer is still running
    if (timer != null) {
        // Stop the quiz timer
        timer.stopTimer();
    }
    
    // Update timer label to show quiz has expired/completed
    timerLabel.setText("Time: Expired!");
    int total = quizBank.length;
    int score = this.score;  
    // Calculate percentage score
    int percentage = (score * 100) / total;

    String message;
    if (percentage >= 80) {
        message = "Outstanding!";
    } else if (percentage >= 60) {
        message = "That's good!";
    } else if (percentage >= 40) {
        message = "Good try!";
    } else if (percentage >= 20) {
        message = "You can do better!";
    } else {
        message = "Don't give up!";
    }

    // Award stars based on score and total questions
    gamification.awardStars(score, quizBank.length);
    // Award badges based on performance
    gamification.awardBadges(score, quizBank.length);
    // Update leaderboard with current user's score
    gamification.updateLeaderboard(username, score);

    // Save score to file using ScoreFileManager
    ScoreFileManager scoreManager = new ScoreFileManager();
    scoreManager.saveScore(username, score);

    // Remove all components from quiz content panel
    quizContentPanel.removeAll();
    // Set layout to vertical box layout for stacking components
    quizContentPanel.setLayout(new BoxLayout(quizContentPanel, BoxLayout.Y_AXIS));
    
    // Create title label for quiz completion
    JLabel completedLabel = new JLabel("Quiz Completed!", SwingConstants.CENTER);
    // Set font style, weight and size for completion label
    completedLabel.setFont(new Font("Arial", Font.BOLD, 24));
    // Set dark green color for the text
    completedLabel.setForeground(new Color(67, 130, 0));
    // Center align the label horizontally
    completedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Create congratulations message with username
    JLabel congratsLabel = new JLabel("Excellent effort, " + username + "!", SwingConstants.CENTER);
    // Set font style and size for congratulations
    congratsLabel.setFont(new Font("Arial", Font.BOLD, 18));
    // Set dark gray color for text
    congratsLabel.setForeground(new Color(50, 50, 50));
    // Center align the label
    congratsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Create label to display final score
    JLabel scoreLabel = new JLabel("Your Final Score: " + score + " / " + quizBank.length, SwingConstants.CENTER);
    // Set font style and size for score label
    scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
    // Set green color for score text
    scoreLabel.setForeground(new Color(67, 130, 0));
    // Center align the label
    scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Create label to display percentage
    JLabel percentLabel = new JLabel("(" + percentage + "%)", SwingConstants.CENTER);
    // Set plain font style for percentage
    percentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    // Set gray color for percentage text
    percentLabel.setForeground(Color.GRAY);
    // Center align the label
    percentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Create label to display
    JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
    messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
    messageLabel.setForeground(new Color(67, 130, 0));
    messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Get number of stars earned from gamification system
    int stars = gamification.getStarsEarned();
    // Convert star count to visual star symbols
    String starSymbol = getStarSymbol(stars);
    // Create label with star symbols and count
   JLabel starsLabel = new JLabel(
    "<html><center>" + starSymbol + "<br><br>" + stars + " Stars Earned</center></html>", 
    SwingConstants.CENTER
);
    // Force Java to use a standard font that is guaranteed to have basic shapes
    starsLabel.setFont(new Font("SansSerif", Font.BOLD, 18)); 
    starsLabel.setForeground(new Color(255, 190, 0)); // Nice bright gold color
    // Center align the label
    starsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Get list of badges earned from gamification system
    List<String> earnedBadges = gamification.getBadges();
    // Create string builder for HTML formatted badge display
    StringBuilder badgesText = new StringBuilder("<html><div style='text-align: center;'>");
    // Add bold header for badges section
    badgesText.append("<b>Badges Earned:</b><br>");
    // Check if any badges were earned
    if (earnedBadges.isEmpty()) {
        // Display message if no badges earned
        badgesText.append("No badges yet. Keep learning!<br>");
    } else {
        // Loop through each earned badge
        for (String badge : earnedBadges) {
            // Add badge emoji and name to display
            badgesText.append("🏅 ").append(badge.trim()).append("<br>");
        }
    }
    // Close HTML div tag
    badgesText.append("</div></html>");
    // Create label with HTML formatted badge text
    JLabel badgesLabel = new JLabel(badgesText.toString(), SwingConstants.CENTER);
    // Set font style and size for badges label
    badgesLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    // Center align the label
    badgesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Create button to view leaderboard
    JButton leaderboardBtn = new JButton(" View Leaderboard");
    // Set font style for button text
    leaderboardBtn.setFont(new Font("Arial", Font.BOLD, 14));
    // Set gray background color for button
    leaderboardBtn.setBackground(new Color(100, 100, 100));
    // Set white text color
    leaderboardBtn.setForeground(Color.WHITE);
    // Remove focus border when button is clicked
    leaderboardBtn.setFocusPainted(false);
    // Center align the button
    leaderboardBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    // Set maximum button size
    leaderboardBtn.setMaximumSize(new Dimension(250, 40));
    // Change cursor to hand when hovering over button
    leaderboardBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    // Add action listener to show leaderboard GUI
    leaderboardBtn.addActionListener(e -> gamification.displayLeaderboardGUI(QuizScreen.this));
    
    // Add all components to panel with spacing
    quizContentPanel.add(Box.createVerticalGlue()); // Push content to center vertically
    quizContentPanel.add(completedLabel); // Add completion title
    quizContentPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Add 15px spacing
    quizContentPanel.add(congratsLabel); // Add congratulations message
    quizContentPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Add 25px spacing
    quizContentPanel.add(scoreLabel); // Add score display
    quizContentPanel.add(percentLabel); // Add percentage display
    quizContentPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Add 15px spacing
    quizContentPanel.add(messageLabel); // Add message display    
    quizContentPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Add 15px spacing
    quizContentPanel.add(starsLabel); // Add stars earned display
    quizContentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add 20px spacing
    quizContentPanel.add(badgesLabel); // Add badges earned display
    quizContentPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Add 25px spacing
    quizContentPanel.add(leaderboardBtn); // Add leaderboard button
    quizContentPanel.add(Box.createVerticalGlue()); // Push content from bottom
    
    // Hide all radio button options
    for (JRadioButton rb : optionButtons) {
        rb.setVisible(false); // Make each radio button invisible
    }
    
    // Hide the question area panel
    questionArea.setVisible(false);
    
    // Change action button text to home option
    actionButton.setText(" Back to Home");
    // Center align the action button
    actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Clear any existing action listeners from button
    ActionListener[] listeners = actionButton.getActionListeners();
    // Loop through all existing listeners
    for (ActionListener al : listeners) {
        actionButton.removeActionListener(al); // Remove each listener
    }
    
    // Add new action listener for returning home
    actionButton.addActionListener(e -> {
        // Create new home page with username
        new HomePage(username);
        // Close current quiz screen window
        dispose();
    });
    
    // Refresh the quiz content panel layout
    quizContentPanel.revalidate();
    // Repaint the quiz content panel to show changes
    quizContentPanel.repaint();
    endQuizButton.setVisible(false); // Hide the exit button since the quiz is finished!
}

// Helper method to convert star count to visual star symbols
private String getStarSymbol(int stars) {
    // Switch statement based on number of stars earned using safe unicode escapes
    switch(stars) {
        case 5: return "\u2605\u2605\u2605\u2605\u2605"; 
        case 4: return "\u2605\u2605\u2605\u2605"; 
        case 3: return "\u2605\u2605\u2605"; 
        case 2: return "\u2605\u2605"; 
        case 1: return "\u2605"; 
        default: return "\u2606\u2606\u2606\u2606\u2606"; 
    }
}
}