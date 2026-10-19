/*
 * Class: FlashCard
 * Description: Flash card system for reviewing health knowledge before quiz.
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FlashCard extends JFrame {
    
    // Current card index (starts from 0)
    private int currentCardIndex = 0;
    
    // List to store all flash cards
    private List<Card> cards;
    
    // UI Components
    private JTextArea questionArea;           // Displays the question
    private JTextArea answerArea;             // Displays the answer
    private JButton showButton;               // Button to show/hide answer
    private JButton nextButton;               // Button to go to next card
    private JLabel progressLabel;             // Shows progress 
    private boolean isAnswerVisible = false;  // Tracks if answer is showing
    private JPanel cardPanel;                 // Panel that holds question/answer
    private JScrollPane questionScroll;       // Scroll pane for question
    private JScrollPane answerScroll;         // Scroll pane for answer
    private String username;                  // Current user's name
    

    //Inner class representing a single flash card
    class Card {
        String question;  // Question text
        String answer;    // Answer text
        
        Card(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }
    }
    
    // Constructor - sets up the Flash Card window
    public FlashCard(String username) {
        this.username = username;
        loadCards();  // Load all card data
        
        // Frame settings
        setTitle("Flash Cards");
        setSize(410, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // ========== HEADER ==========
        JPanel header = new JPanel();
        header.setBackground(new Color(67, 130, 0));
        header.setPreferredSize(new Dimension(410, 60));
        JLabel titleLabel = new JLabel("📇 Flash Cards", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        header.add(titleLabel);
        mainPanel.add(header, BorderLayout.NORTH);
        
        // ========== CARD PANEL (flips between question and answer) ==========
        cardPanel = new JPanel(new CardLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Question Panel (front of card)
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionArea = new JTextArea();
        questionArea.setFont(new Font("Arial", Font.BOLD, 18));
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setEditable(false);
        questionArea.setBackground(Color.WHITE);
        questionArea.setForeground(new Color(50, 50, 50));
        questionArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(67, 130, 0), 2),
            BorderFactory.createEmptyBorder(30, 20, 30, 20)
        ));
        questionScroll = new JScrollPane(questionArea);
        questionScroll.setBorder(null);
        questionScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        questionPanel.add(questionScroll, BorderLayout.CENTER);
        
        // Answer Panel (back of card)
        JPanel answerPanel = new JPanel(new BorderLayout());
        answerArea = new JTextArea();
        answerArea.setFont(new Font("Arial", Font.PLAIN, 16));
        answerArea.setLineWrap(true);
        answerArea.setWrapStyleWord(true);
        answerArea.setEditable(false);
        answerArea.setBackground(new Color(245, 245, 245));
        answerArea.setForeground(new Color(67, 130, 0));
        answerArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(67, 130, 0), 2),
            BorderFactory.createEmptyBorder(30, 20, 30, 20)
        ));
        answerScroll = new JScrollPane(answerArea);
        answerScroll.setBorder(null);
        answerScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        answerPanel.add(answerScroll, BorderLayout.CENTER);
        
        // Add both panels to cardPanel
        cardPanel.add(questionPanel, "QUESTION");
        cardPanel.add(answerPanel, "ANSWER");
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        
        // ========== BUTTON PANEL ==========
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(Color.WHITE);
        
        // Show/Hide Answer button
        showButton = new JButton("Show Answer");
        showButton.setFont(new Font("Arial", Font.BOLD, 14));
        showButton.setBackground(new Color(100, 150, 250));
        showButton.setForeground(Color.WHITE);
        showButton.setFocusPainted(false);
        showButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showButton.addActionListener(e -> toggleAnswer());
        
        // Next Card button
        nextButton = new JButton("Next Card →");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBackground(new Color(67, 130, 0));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nextButton.addActionListener(e -> nextCard());
        
        buttonPanel.add(showButton);
        buttonPanel.add(nextButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // ========== BOTTOM BAR ==========
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 15, 15));
        
        // Progress label
        progressLabel = new JLabel("Card 1 of " + cards.size(), SwingConstants.CENTER);
        progressLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        progressLabel.setForeground(Color.GRAY);
        
        // Back button to return home
        JButton backButton = new JButton("← Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 12));
        backButton.setBackground(Color.LIGHT_GRAY);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> {
            new HomePage(username);
            dispose();
        });
        
        bottomPanel.add(backButton, BorderLayout.WEST);
        bottomPanel.add(progressLabel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.NORTH);
        
        add(mainPanel);
        displayCurrentCard();  // Show first card
        setVisible(true);
    }
    
    // Loads all flash card data (10 cards related to Quiz content)
    private void loadCards() {
        cards = new ArrayList<>();
        
        // Card 1
        cards.add(new Card(
            "Which diseases are specifically targeted under communicable disease prevention?",
            "HIV, Malaria and Tuberculosis are the main communicable diseases targeted.\n\n" +
            "• HIV: Antiretroviral therapy helps manage the disease\n" +
            "• Malaria: Prevented through insecticide-treated nets\n" +
            "• Tuberculosis: Treated with antibiotics"
        ));
        
        // Card 2
        cards.add(new Card(
            "What is one benefit of routine health screenings?",
            "Routine health screenings help detect health issues early.\n\n" +
            "Benefits include:\n" +
            "• Early treatment when diseases are easier to cure\n" +
            "• Prevention of serious complications\n" +
            "• Better health outcomes"
        ));
        
        // Card 3
        cards.add(new Card(
            "How many minutes of moderate exercise are recommended per week?",
            "150 minutes of moderate exercise per week is recommended.\n\n" +
            "This can be broken down into:\n" +
            "• 30 minutes per day, 5 days a week\n" +
            "• Examples: brisk walking, cycling, swimming"
        ));
        
        // Card 4
        cards.add(new Card(
            "Which vitamin is synthesized from sunlight?",
            "Vitamin D is produced by your skin when exposed to sunlight.\n\n" +
            "Why it matters:\n" +
            "• Helps absorb calcium for strong bones\n" +
            "• Supports immune system function\n" +
            "• May improve mood"
        ));
        
        // Card 5
        cards.add(new Card(
            "What is the normal resting heart rate for adults?",
            "60-100 beats per minute is the normal resting heart rate.\n\n" +
            "Note:\n" +
            "• Athletes may have lower rates (40-60 bpm)\n" +
            "• Higher rates may indicate stress or illness"
        ));
        
        // Card 6
        cards.add(new Card(
            "Which is a non-communicable chronic disease?",
            "Type 2 Diabetes is a non-communicable chronic disease.\n\n" +
            "Others include:\n" +
            "• Heart disease\n" +
            "• Cancer\n" +
            "• Chronic respiratory diseases\n\n" +
            "These cannot be spread between people."
        ));
        
        // Card 7
        cards.add(new Card(
            "What is the central priority of SDG 3?",
            "Health and well-being for all at all ages.\n\n" +
            "This includes:\n" +
            "• Reducing maternal and child mortality\n" +
            "• Fighting communicable diseases\n" +
            "• Ensuring universal health coverage"
        ));
        
        // Card 8
        cards.add(new Card(
            "What is a key mental health disorder?",
            "Depression is a common mental health disorder.\n\n" +
            "Symptoms may include:\n" +
            "• Persistent sadness\n" +
            "• Loss of interest in activities\n" +
            "• Changes in sleep and appetite\n\n" +
            "Treatment: therapy, medication, lifestyle changes"
        ));
        
        // Card 9
        cards.add(new Card(
            "True or False: Antibiotics can cure viral influenza.",
            "False. Antibiotics only work against bacteria, not viruses.\n\n" +
            "Why:\n" +
            "• Influenza is caused by a virus\n" +
            "• Taking antibiotics for viruses can cause resistance\n" +
            "• Rest, fluids, and antivirals are used for flu"
        ));
        
        // Card 10
        cards.add(new Card(
            "True or False: Poor sanitation can contribute to disease outbreaks.",
            "True. Poor sanitation and unsafe water can spread diseases.\n\n" +
            "Examples:\n" +
            "• Cholera spreads through contaminated water\n" +
            "• Typhoid fever from poor hygiene\n" +
            "• Proper sanitation prevents many diseases"
        ));
    }
    
    // Displays the current card (shows question, hides answer)
    private void displayCurrentCard() {
        Card current = cards.get(currentCardIndex);
        
        // Set text content
        questionArea.setText(current.question);
        questionArea.setCaretPosition(0);
        
        answerArea.setText(current.answer);
        answerArea.setCaretPosition(0);
        
        // Show question panel (front of card)
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, "QUESTION");
        isAnswerVisible = false;
        showButton.setText("Show Answer");
        progressLabel.setText("Card " + (currentCardIndex + 1) + " of " + cards.size());
        
        // Reset scroll bars to top
        questionScroll.getVerticalScrollBar().setValue(0);
        answerScroll.getVerticalScrollBar().setValue(0);
        
        revalidate();
        repaint();
    }
    
    // Toggles between showing question and answer (flips the card)
    private void toggleAnswer() {
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        
        if (!isAnswerVisible) {
            // Show answer (back of card)
            cl.show(cardPanel, "ANSWER");
            isAnswerVisible = true;
            showButton.setText("Hide Answer");
            answerScroll.getVerticalScrollBar().setValue(0);
        } else {
            // Show question (front of card)
            cl.show(cardPanel, "QUESTION");
            isAnswerVisible = false;
            showButton.setText("Show Answer");
            questionScroll.getVerticalScrollBar().setValue(0);
        }
        
        revalidate();
        repaint();
    }
    
    // Moves to the next card. 
    private void nextCard() {
        currentCardIndex++;
        
        // Check if this was the last card
        if (currentCardIndex >= cards.size()) {
            // Show completion message
            JOptionPane.showMessageDialog(this, 
                "🎉 Congratulations! 🎉\n\nYou've reviewed all " + cards.size() + " cards!\n\n" +
                "Now try the Quiz to test your knowledge!\n\n" +
                "Returning to HomePage...",
                "Great Job!", JOptionPane.INFORMATION_MESSAGE);
            
            // Return to HomePage
            new HomePage(username);
            dispose();
            return;
        }
        
        displayCurrentCard();
    }
}