/*
 * Class: HomePage
 * Description: Main dashboard modified with vertical row list, right-aligned welcome, and introduction asset.
 */

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HomePage extends JFrame {

    public HomePage(String username) {

        setTitle("Home");
        // Matches the updated LearnScreen mobile viewport dimensions
        setSize(410, 730);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null); // Retaining null layout for explicit mobile absolute positioning
        panel.setBackground(Color.WHITE);

        // =====================================================
        // HEADER (ADJUSTED FOR MOBILE)
        // =====================================================
        JPanel top = new JPanel();
        top.setBackground(new Color(67, 130, 0));
        top.setBounds(0, 0, 410, 60); 
        top.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 16));

        JLabel homeTitle = new JLabel("Good Health & Well-Being");
        homeTitle.setForeground(Color.WHITE);
        homeTitle.setFont(new Font("Arial", Font.BOLD, 20));

        top.add(homeTitle);
        panel.add(top);

        // =====================================================
        // 1. WELCOME USER (MODIFIED: SHIFTED TO THE RIGHT)
        // =====================================================
        // Subtly shifted text layout slightly right with trailing icon indicator
        JLabel welcome = new JLabel("Welcome, " + username + "!", SwingConstants.LEFT);
        welcome.setFont(new Font("Arial", Font.BOLD, 20)); 
        welcome.setForeground(new Color(40, 40, 40));
        welcome.setBounds(20, 80, 355, 30); // Width leaves a right margin spacer
        panel.add(welcome);

        // =====================================================
        // 2. NEW: PICTURE / GRAPHIC BANNER ASSET
        // =====================================================
        java.net.URL imgURL = getClass().getResource("/PICTURE/health.jpg");
        ImageIcon bannerIcon = (imgURL != null) ? new ImageIcon(imgURL) : new ImageIcon();
        
        if (bannerIcon.getImage() != null) {
            Image scaledImg = bannerIcon.getImage().getScaledInstance(350, 140, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImg));
            imageLabel.setBounds(25, 130, 350, 140); // Centered horizontally: (410 - 350) / 2 = 30
            panel.add(imageLabel);
        } else {
            // Soft placeholder layout box fallback if picture asset link breaks
            JPanel placeholder = new JPanel();
            placeholder.setBounds(25, 130, 350, 140);
            placeholder.setBackground(new Color(242, 246, 244));
            placeholder.setBorder(new LineBorder(new Color(210, 220, 215), 1, true));
            JLabel phText = new JLabel("[ SDG 3 Graphic Asset Missing ]");
            phText.setForeground(Color.GRAY);
            placeholder.add(phText);
            panel.add(placeholder);
        }

        // =====================================================
        // 3. NEW: SHORT INTRODUCTORY TEXT BLOCK
        // =====================================================
        JLabel introLabel = new JLabel(
            "<html><center>Today is your day to start fresh, to eat right, to train hard, to live healthy, to be proud.</center></html>",
            SwingConstants.CENTER
        );
        introLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        introLabel.setForeground(new Color(110, 110, 110)); // Modern slate charcoal tone
        introLabel.setBounds(30, 285, 340, 40); 
        panel.add(introLabel);

        // =====================================================
        // MODIFIED: MODULE ROWS (CHANGED FROM GRID TO VERTICAL LIST)
        // Center position X = 25, Width = 350, Height = 60
        // =====================================================
        
        // BUTTON 1: LEARN
        JButton learnCard = createWideRowCard(
                "Learning Unit",
                350
        );
        learnCard.addActionListener(e -> {
            new Learn(username);
            dispose();
        });
        panel.add(learnCard);

        // BUTTON 2: QUIZ
        JButton quizCard = createWideRowCard(
                "Quiz",
                425
        );
        quizCard.addActionListener(e -> {
            new QuizScreen(username); 
            dispose(); 
        });
        panel.add(quizCard);

        // BUTTON 3: FLASH CARDS
        JButton wellnessCard = createWideRowCard(
                "Flash Cards",
                500
        );
        wellnessCard.addActionListener(e -> {
            new FlashCard(username);
            dispose();
        });
        panel.add(wellnessCard);

        add(panel);
        setVisible(true);
    }

    // =====================================================
    // MODIFIED DESIGN: CREATE WIDE HORIZONTAL ROW SELECTION 
    // =====================================================
    public JButton createWideRowCard(String title, int y) {

        // Uses a cleaner side-by-side or stacked HTML format tailored for horizontal bar strips
        JButton button = new JButton(
                "<html><div style='text-align: left; margin-left: 20px;'>" +
                "<font size='4' color='#d0e4ef'><b>" + title + "</b></font> &nbsp;&nbsp;&nbsp;&nbsp;" +
                "</div></html>"
        );

        // Transforms item into wide block row: Width 350px, Height 60px
        button.setBounds(25, y, 350, 60);
        button.setHorizontalAlignment(SwingConstants.LEFT); // Content aligns left gracefully
        button.setBackground(new Color(67, 130, 0)); // Consistent corporate green
        button.setForeground(Color.WHITE); 
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); 

        // Smooth modern crisp outline styling
        button.setBorder(new LineBorder(new Color(230, 230, 230), 1, true));

        return button;
    }
}