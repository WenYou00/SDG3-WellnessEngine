/*
 * Class: WelcomeScreen
 * Description: Entry screen for username input. Navigates to HomePage.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {

    public WelcomeScreen() {

        // =========================
        // FRAME SETTINGS (MOBILE)
        // =========================
        setTitle("SDG 3: Health and Well-Being");
        setSize(410, 730); // Standardized to match HomePage and LearnScreen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // MAIN PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // Retaining null layout for explicit absolute layout control
        mainPanel.setBackground(new Color(236, 243, 241));

        // =========================
        // LOGO (FIXED PATH & BOUNDS)
        // =========================
        // Dynamically loads from project resource directories instead of a hardcoded C:\ drive path
        java.net.URL logoURL = getClass().getResource("/PICTURE/LOGO.png");
        ImageIcon icon = (logoURL != null) ? new ImageIcon(logoURL) : new ImageIcon();

        Image img = icon.getImage().getScaledInstance(130, 120, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);

        JLabel imageLabel = new JLabel(resizedIcon);
        // Centered horizontally: (410 width - 130 logo width) / 2 = 140
        imageLabel.setBounds(140, 70, 130, 120);
        mainPanel.add(imageLabel);

        // =========================
        // TITLE
        // =========================
        JLabel mainTitle = new JLabel("SDG 3 Wellness Engine", SwingConstants.CENTER);
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26)); // Scaled slightly down for mobile headers
        mainTitle.setForeground(new Color(35, 35, 35));
        mainTitle.setBounds(20, 210, 370, 40);
        mainPanel.add(mainTitle);

        // =========================
        // NAME TEXTFIELD
        // =========================
        JTextField nameField = new JTextField();
        // Centered horizontally: (410 width - 240 field width) / 2 = 85
        nameField.setBounds(85, 290, 240, 35); // Added height for a more comfortable mobile typing surface
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setHorizontalAlignment(JTextField.CENTER);
        
        // Modernized text box presentation accent border
        nameField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 195, 190), 1), 
                " Enter your name ", 
                0, 0, 
                new Font("Arial", Font.PLAIN, 11), 
                Color.GRAY
        ));
        mainPanel.add(nameField);

        // =========================
        // ENTER BUTTON
        // =========================
        JButton enterButton = new JButton("Enter");
        // Centered horizontally: (410 width - 240 button width) / 2 = 85
        enterButton.setBounds(85, 345, 240, 45);
        enterButton.setBackground(new Color(67, 130, 0)); // Matched the corporate green branding
        enterButton.setForeground(Color.WHITE);
        enterButton.setFont(new Font("Arial", Font.BOLD, 18));
        enterButton.setFocusPainted(false);
        enterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(enterButton);

        // =========================
        // WELCOME TEXT BLOCK
        // =========================
        JLabel welcomeTitle = new JLabel("Welcome to Your Wellness Journey!", SwingConstants.CENTER);
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeTitle.setBounds(20, 470, 370, 30);
        mainPanel.add(welcomeTitle);

        JLabel desc1 = new JLabel("Explore lessons, build healthy habits,", SwingConstants.CENTER);
        desc1.setFont(new Font("Arial", Font.PLAIN, 14));
        desc1.setForeground(new Color(80, 90, 85));
        desc1.setBounds(20, 505, 370, 20);

        JLabel desc2 = new JLabel("and take care of your body and mind.", SwingConstants.CENTER);
        desc2.setFont(new Font("Arial", Font.PLAIN, 14));
        desc2.setForeground(new Color(80, 90, 85));
        desc2.setBounds(20, 525, 370, 20);

        mainPanel.add(desc1);
        mainPanel.add(desc2);

        // =========================
        // INTERACTIVE ACTION CONTROLS
        // =========================
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = nameField.getText().trim();

                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter your name.", 
                            "Missing Input", 
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    // OPEN HOMEPAGE
                    new HomePage(username);

                    // CLOSE CURRENT PAGE
                    dispose();
                }
            }
        });

        add(mainPanel);
        setVisible(true);
    }
}