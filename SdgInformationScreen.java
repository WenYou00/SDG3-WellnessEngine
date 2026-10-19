/*
 * Class: SdgInformationScreen
 * Description: Displays detailed information for 14 SDG 3 targets.
*/

import javax.swing.*;
import java.awt.*;

public class SdgInformationScreen extends JFrame {

    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 18);
    private static final Font BODY_FONT = new Font("Arial", Font.PLAIN, 14);
    private static final Insets GAP = new Insets(0, 0, 25, 0);

    public SdgInformationScreen(String username) {
        // ==========================================
        // FRAME SETTINGS
        // ==========================================
        setTitle("SDG 3 Targets & Information");
        setSize(410, 730); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // This is our internal container panel
        JPanel panel = new JPanel(new GridBagLayout()) {
            // FORCE CHANGE: Hard-override the tracking width to force a layout gap on the right
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(365, super.getPreferredSize().height);
            }
        };
        panel.setBackground(Color.WHITE);
        
        // Internal padding inside the custom bounded space
        panel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // =====================================================
        // HEADER TITLE
        // =====================================================
        JLabel header = new JLabel("SDG 3: Health & Well-Being", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22)); 
        header.setForeground(new Color(67, 130, 0));
        panel.add(header, gbc);
        gbc.gridy++;

        // =====================================================
        // CONTENT SECTIONS (Overview + Targets)
        // =====================================================
        gbc.insets = GAP;
        panel.add(createSection("Overview", "/PICTURE/overview.png",
                "ENSURE HEALTHY LIVES AND PROMOTE WELL-BEING FOR ALL AT ALL AGES.\n\n" +
                "SDG 3 aims to improve global health by reducing mortality, fighting diseases, " +
                "ensuring universal healthcare, and strengthening health systems worldwide."), gbc);
        gbc.gridy++;

        panel.add(createSection("Target 3.1: Reduce Maternal Mortality", "/PICTURE/maternal.png",
                "Reduce the global maternal mortality ratio to less than 70 per 100,000 live births by 2030."), gbc);
        gbc.gridy++;

        panel.add(createSection("Target 3.2: End Under-5 Deaths", "/PICTURE/under5.png",
                "End preventable deaths of newborns and children under 5 years.\n\n" +
                "Neonatal mortality \u2264 12 per 1,000 live births\n" +
                "Under-5 mortality \u2264 25 per 1,000 live births"), gbc);
        gbc.gridy++;

        panel.add(createSection("Target 3.3: Communicable Diseases", "/PICTURE/diseases.png",
                "End epidemics of AIDS, tuberculosis, malaria, and other communicable diseases.\n\n" +
                "Also combat hepatitis and water-borne diseases."), gbc);
        gbc.gridy++;

        panel.add(createSection("Target 3.4: NCDs & Mental Health", "/PICTURE/mental_health.png",
                "Reduce premature mortality from non-communicable diseases by one-third.\n\n" +
                "Includes heart disease, cancer, diabetes, respiratory diseases.\n\n" +
                "Promote mental health and well-being."), gbc);
        gbc.gridy++;

        panel.add(createSection("Target 3.5: Substance Abuse", "/PICTURE/substance_abuse.png",
                "Strengthen prevention and treatment of substance abuse.\n\n" +
                "Includes narcotic drugs and harmful alcohol use."), gbc);
        gbc.gridy++;

        panel.add(createSection("Target 3.6: Road Safety", "/PICTURE/road_safety.png",
                "Halve global deaths and injuries from road traffic accidents."), gbc);
        gbc.gridy++;

        panel.add(createSection("Target 3.7: Reproductive Health", "/PICTURE/reproductive_health.png",
                "Ensure universal access to sexual and reproductive health-care services, family planning, and education."), gbc);
        gbc.gridy++;

        panel.add(createSection("Target 3.8: Universal Health Coverage", "/PICTURE/uhc.png",
                "Achieve universal health coverage including financial protection and access to essential medicines and vaccines."), gbc);
        gbc.gridy++;

        panel.add(createSection("Target 3.9: Pollution Reduction", "/PICTURE/pollution.png",
                "Reduce deaths from air, water, soil pollution and hazardous chemicals."), gbc);
        gbc.gridy++;

        panel.add(createSection("Target 3.a: Tobacco Control", "/PICTURE/tobacco.png",
                "Strengthen WHO Framework Convention on Tobacco Control."), gbc);
        gbc.gridy++;

        panel.add(createSection("Target 3.b: Medicines & Vaccines", "/PICTURE/vaccine.png",
                "Support research and development of vaccines and medicines.\n\n" +
                "Ensure access to affordable essential medicines in developing countries."), gbc);
        gbc.gridy++;

        panel.add(createSection("Target 3.c: Health Workforce", "/PICTURE/health_workers.png",
                "Increase training, recruitment, and retention of healthcare workers in developing countries."), gbc);
        gbc.gridy++;

        panel.add(createSection("Target 3.d: Health Emergency Preparedness", "/PICTURE/emergency.png",
                "Strengthen early warning systems, risk reduction, and management of global health emergencies."), gbc);

        // =====================================================
        // BACK TO MENU HUB BUTTON
        // =====================================================
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 30, 0);

        JButton backToHubButton = new JButton("← Back to Hub Options");
        backToHubButton.setFont(new Font("Arial", Font.BOLD, 14));
        backToHubButton.setBackground(new Color(67, 130, 0));
        backToHubButton.setForeground(Color.WHITE);
        backToHubButton.setFocusPainted(false);
        backToHubButton.setPreferredSize(new Dimension(220, 45)); 
        backToHubButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        backToHubButton.addActionListener(e -> {
            dispose(); 
            new Learn(username); 
        });
        panel.add(backToHubButton, gbc);

        // Create scroll pane container
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(18);

        // FORCE CHANGE: Make sure the scroll pane viewport wraps around the explicit content pane bounds
        JPanel containerWrapper = new JPanel(new BorderLayout());
        containerWrapper.setBackground(Color.WHITE);
        containerWrapper.add(panel, BorderLayout.NORTH);
        
        scroll.setViewportView(containerWrapper);

        add(scroll);

        SwingUtilities.invokeLater(() -> {
            scroll.getVerticalScrollBar().setValue(0);
        });

        setVisible(true);
    }

    // =====================================================
    // BOX LAYOUT SECTION CARD GENERATOR
    // =====================================================
    public JPanel createSection(String title, String imagePath, String content) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBackground(new Color(245, 245, 245));
        box.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(5, 12, 12, 12)
        ));

        JTextArea t = new JTextArea(title);
        t.setFont(TITLE_FONT);
        t.setLineWrap(true);
        t.setWrapStyleWord(true);
        t.setEditable(false);
        t.setOpaque(false);
        t.setAlignmentX(Component.LEFT_ALIGNMENT);
        t.setMaximumSize(new Dimension(310, 60));

        JLabel img = new JLabel();
        img.setAlignmentX(Component.LEFT_ALIGNMENT);

        ImageIcon icon = loadImage(imagePath);
        if (icon != null) {
            // FORCE CHANGE: Scaled to 310px width to fit inside our fixed width column
            Image scaled = icon.getImage().getScaledInstance(320, 220, Image.SCALE_SMOOTH);
            img.setIcon(new ImageIcon(scaled));
        } else {
            img.setText("Image not found");
            img.setForeground(Color.GRAY);
        }

        JTextArea text = new JTextArea(content);
        text.setFont(BODY_FONT);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        text.setFocusable(false);
        text.setOpaque(false);
        text.setForeground(new Color(70, 70, 70));
        text.setAlignmentX(Component.LEFT_ALIGNMENT);

        box.add(t);
        box.add(Box.createRigidArea(new Dimension(0, 10)));
        box.add(img);
        box.add(Box.createRigidArea(new Dimension(0, 10)));
        box.add(text);

        return box;
    }

    private ImageIcon loadImage(String path) {
        java.net.URL url = getClass().getResource(path);
        if (url != null) {
            return new ImageIcon(url);
        }
        System.out.println("Missing image: " + path);
        return null;
    }
}