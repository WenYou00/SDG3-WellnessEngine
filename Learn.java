/*
 * Class: Learn
 * Interface: ILearningContent
 * Description: Learning module hub with SDG 3 targets and interactive modules.
*/

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

interface ILearningContent {
    String getTitle();
    String getBodyText();
    String getImageUrl();
}

class HealthTopicPage implements ILearningContent {
    private final String title;
    private final String bodyText;
    private final String imageUrl;

    public HealthTopicPage(String title, String bodyText, String imageUrl) {
        this.title = title;
        this.bodyText = bodyText;
        this.imageUrl = imageUrl;
    }

    @Override public String getTitle() { return title; }
    @Override public String getBodyText() { return bodyText; }
    @Override public String getImageUrl() { return imageUrl; }
}

class LearningContentProvider {
    private final List<ILearningContent> healthModulePages = new ArrayList<>();

    public LearningContentProvider() {
        initializeSDG3SevenPillars();
    }

    private void initializeSDG3SevenPillars() {
        // Module 1
        healthModulePages.add(new HealthTopicPage(
            "Global Strategies for Maternal and Neonatal Survival",
            "A fundamental benchmark of international development is safeguarding maternal and infant lives during childbirth and early growth cycles, aligning with the core definition of health as a complete state of physical well-being. The overarching global target is to drastically reduce childbirth mortality worldwide. To achieve this, international frameworks set a definitive goal to reduce the global maternal mortality ratio to less than 70 maternal deaths per 100,000 live births. Simultaneously, the framework targets ending preventable deaths of newborns and children under 5 years of age, with all nations aiming to reduce neonatal mortality to at least as low as 12 deaths per 1,000 live births and under-5 mortality to at least as low as 25 deaths per 1,000 live births. Meeting these thresholds requires broad access to routine prenatal screening, ensuring that 100% of births are managed by skilled health personnel, implementing universal postpartum hemorrhage interventions, and executing early neonatal nutritional programmes to completely eradicate infant wasting and stunted growth.",
            "/PICTURE/maternal.png"
        ));

        // Module 2
        healthModulePages.add(new HealthTopicPage(
            "Epidemiology and Eradicating Communicable Disease Outbreaks",
            "Public safety and collective health security depend on breaking chains of viral, bacterial, and parasitic transmission across global populations through continuous research and clinical learning. The cornerstone of global disease prevention is ending systemic epidemics, specifically targeting HIV, Malaria, and Tuberculosis. Eradicating these pathogens demands aggressive medical, logistical, and environmental protocols. For instance, combating HIV relies on the worldwide scaling of antiretroviral therapy (ART) to achieve the 95-95-95 target (95% diagnosed, 95% on treatment, 95% virally suppressed). Mitigating Malaria requires extensive vector management through insecticide-treated nets and indoor residual spraying, while controlling Tuberculosis involves deploying rapid molecular diagnostics and standardizing short-course directly observed treatment. These initiatives must be amplified by strengthening global immunization networks to guarantee equitable vaccine distribution, safeguarding communities from entirely preventable pandemics.",
            "/PICTURE/diseases.png"
        ));

        // Module 3
        healthModulePages.add(new HealthTopicPage(
            "The Physiology of Physical Health and Chronic Illness Prevention",
            "Human metabolic health relies entirely on the precise intake of macronutrients and micronutrients alongside strict fluid and movement balances. To promote healthy lifestyles and actively prevent non-communicable diseases (NCDs), individuals must maintain basic physical wellness habits: regular movement, a nutrient-dense diet, and sufficient sleep. NCDs—principally cardiovascular disease, cancer, chronic respiratory diseases, and diabetes—account for the majority of global mortality and are combated through proactive lifestyle interventions. Regular physical movement sustains vascular elasticity, a nutrient-dense diet fuels cellular metabolism and structural repair, and sufficient sleep allows the glymphatic system to clear metabolic waste. Prevention requires large-scale behavioral shifts alongside legislative taxation on harmful tobacco use or excessive refined sugars to reduce systemic cellular inflammation across populations.",
            "/PICTURE/overview.png"
        ));

        // Module 4
        healthModulePages.add(new HealthTopicPage(
            "Advancing Psychological Healthcare and Substance Abuse Treatment",
            "Modern healthcare architecture places equal priority on physical preservation, psychological stability, and cognitive stress management. To achieve a baseline of total health, frameworks require that nations actively strengthen substance abuse treatments and mental healthcare infrastructures. This involves integrating mental healthcare into primary health settings, scaling up evidence-based pharmacological and cognitive behavioral therapies, and strengthening treatments for harmful substance use, including narcotic drug abuse and harmful alcohol consumption. Managing mental well-being requires active cognitive pacing, curiosity, and continuous learning to preserve neuroplasticity. Cultivating deep social support structures and dismantling institutional stigma around psychological therapy are vital to reducing global anxiety and clinical depression rates.",
            "/PICTURE/mental_health.png"
        ));

        // Module 5
        healthModulePages.add(new HealthTopicPage(
            "Environmental Health, Civil Infrastructure, and Road Safety",
            "External surroundings, clean working ecosystems, and structural safety systems are directly responsible for thousands of preventable injuries and illnesses daily. Global civil targets require that nations halve the number of global road crash injuries and deaths while drastically reducing pollution-related illnesses. Minimizing traffic trauma requires strict urban planning, implementing intelligent traffic infrastructure, enforcing rigorous vehicle safety standards, and improving emergency response times. To combat pollution-related illnesses, environmental legislation must restrict industrial particulate matter emissions (PM2.5 and PM10), safeguard municipal drinking water treatment facilities, ensure chemical-free agricultural runoff, and strictly eliminate hazardous toxins from urban air supply lines. Fostering these safe, clean spaces directly preserves systemic physiological health across global populations.",
            "/PICTURE/road_safety.png"
        ));

        // Module 6
        healthModulePages.add(new HealthTopicPage(
            "Achieving Universal Healthcare Coverage and Financial Protection",
            "The ultimate structural pillar of a stable healthcare system is providing affordable, high-quality medical services to all citizens, regardless of socioeconomic standing. International targets state that societies must ensure everyone can afford access to essential medicines and health services. This aligns directly with achieving Universal Health Coverage (UHC), ensuring all individuals receive vital health services without suffering catastrophic financial hardship or poverty. Achieving true equity means securing a reliable, transparent supply chain of affordable, certified essential medicines, diagnostic tools, and vaccines. Furthermore, it demands expanding medical coverage models, strengthening hospital workforces in rural sectors, and eliminating direct out-of-pocket health costs that drive vulnerable communities into financial instability.",
            "/PICTURE/uhc.png"
        ));

        // Module 7
        healthModulePages.add(new HealthTopicPage(
            "Promoting Health Literacy, Community Connection, and Meaning",
            "Sustainable health outcomes require highly literate populations that understand systemic health issues and feel a deep sense of social belonging. Health education programs are required to build community trust in science, improve maternal nutrition literacy, and dismantle cultural stigmas surrounding early clinical screening. By promoting comprehensive knowledge of physical, mental, and social health dynamics, education empowers individuals to make informed wellness choices. Furthermore, this literacy acts as an entry point for finding lifelong meaning, personal values, and purpose. When communities understand health issues, they are equipped to foster supportive social relationships, advocate for stronger public sanitary policies, and build robust safety nets that enhance collective human resilience.",
            "/PICTURE/vaccine.png"
        ));

        // Module 8
        healthModulePages.add(new HealthTopicPage(
        "Target 3.7: Universal Access to Reproductive Healthcare",
        "Universal access to sexual and reproductive health services is a fundamental component of achieving SDG 3. This target recognizes that reproductive health is not merely about childbirth but encompasses comprehensive family planning, informed decision-making, and access to quality healthcare throughout a person's reproductive life. Global frameworks aim to ensure that every individual has the right to make informed choices about their reproductive health without discrimination or coercion. Achieving this target requires strengthening health systems to provide family planning services, prenatal and postnatal care, safe delivery services, and prevention and treatment of sexually transmitted infections. Special attention must be given to adolescent girls who face unique reproductive health challenges, including higher risks of maternal mortality and complications from early pregnancy. Comprehensive sexuality education in schools and communities helps young people understand their bodies, make informed decisions, and seek healthcare when needed. Community-based interventions and mobile health clinics have proven effective in reaching underserved populations in remote areas. Addressing cultural barriers and eliminating stigma surrounding reproductive health services are essential steps toward achieving universal access by 2030.",
        "/PICTURE/reproductive_health.png"
    ));

        // Module 9
         healthModulePages.add(new HealthTopicPage(
        "Target 3.a: Global Tobacco Control Framework",
        "The WHO Framework Convention on Tobacco Control represents the first global public health treaty negotiated under the auspices of the World Health Organization. This landmark agreement provides evidence-based measures to reduce tobacco consumption and protect populations from the devastating health, economic, and social consequences of tobacco use. Key provisions of the convention include large graphic warning labels on tobacco packages, comprehensive bans on tobacco advertising and sponsorship, protection from second-hand smoke in public spaces, increased tobacco taxes to reduce affordability, and measures to combat illicit trade in tobacco products. Despite significant progress, tobacco remains the leading cause of preventable death worldwide, killing more than 8 million people annually. Implementation varies significantly between countries, with low and middle-income nations facing challenges in enforcement and monitoring. The economic burden of tobacco-related diseases strains healthcare systems and reduces workforce productivity. Strengthening implementation requires political commitment, international cooperation, civil society engagement, and sustained funding for tobacco control programs. Success stories from countries like Australia, which implemented plain packaging laws, and Turkey, which achieved near-universal smoke-free public places, demonstrate that comprehensive tobacco control saves lives.",
        "/PICTURE/tobacco.png"
    ));

        // Module 10
         healthModulePages.add(new HealthTopicPage(
        "Target 3.c: Building a Sustainable Health Workforce",
        "The global health workforce faces unprecedented challenges including shortages, maldistribution, inadequate training, poor working conditions, and international migration of skilled professionals. Target 3.c addresses these challenges by calling for substantial increases in health financing and the recruitment, development, training, and retention of health workers, particularly in developing countries where needs are greatest. The World Health Organization estimates a global shortage of 18 million health workers, with Africa bearing 25 percent of the global disease burden but employing only 3 percent of the world's health workforce. This imbalance perpetuates health inequities and undermines progress toward universal health coverage. Solutions require comprehensive workforce planning that accounts for demographic changes, disease patterns, and technological advances. Investing in training institutions, faculty development, and educational infrastructure is essential for producing competent health professionals. Improving working conditions, offering competitive compensation, providing career development opportunities, and ensuring workplace safety help retain staff in underserved areas. International cooperation is needed to manage cross-border migration ethically through bilateral agreements and code of practice implementation. Technology-enabled solutions, such as telemedicine and mobile health applications, can extend the reach of limited health workers while task-sharing and community health worker programs optimize the use of available human resources.",
        "/PICTURE/health_workers.png"
    ));
    }

    public ILearningContent getPage(int index) {
        if (index >= 0 && index < healthModulePages.size()) {
            return healthModulePages.get(index);
        }
        return null;
    }

    public int getTotalPages() {
        return healthModulePages.size();
    }
}

public class Learn extends JFrame {
    private final String currentUsername;

    public Learn(String username) {
        this.currentUsername = username;

        // ==========================================
        // MAIN HUB DASHBOARD FRAME SIZE (410 x 730)
        // ==========================================
        setTitle("SDG 3 Hub");
        setSize(410, 730); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 25, 40, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 60, 0); 

        // HEADER TITLE
        JLabel titleLabel = new JLabel("SDG 3 Hub", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(67, 130, 0));
        panel.add(titleLabel, gbc);

        // Core UI Dashboard Style Variables (Resized for 410px constraint bounds)
        Dimension btnSize = new Dimension(340, 55);
        Font btnFont = new Font("Arial", Font.BOLD, 16);
        Color themeColor = new Color(67, 130, 0);

        // BUTTON 1: INFO TARGETS
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 25, 0);
        
        JButton infoButton = new JButton("Info (View Targets)");
        infoButton.setFont(btnFont);
        infoButton.setBackground(themeColor);
        infoButton.setForeground(Color.WHITE);
        infoButton.setPreferredSize(btnSize);
        infoButton.setFocusPainted(false);
        infoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        infoButton.addActionListener(e -> {
            dispose(); 
            new SdgInformationScreen(username); 
        });
        panel.add(infoButton, gbc);

        // BUTTON 2: MODULE PRESENTATION LABELS
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 60, 0);
        
        JButton learnButton = new JButton("Learn");
        learnButton.setFont(btnFont);
        learnButton.setBackground(themeColor);
        learnButton.setForeground(Color.WHITE);
        learnButton.setPreferredSize(btnSize);
        learnButton.setFocusPainted(false);
        learnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        learnButton.addActionListener(e -> {
            dispose(); 
            new InteractiveLearnWindow(); 
        });
        panel.add(learnButton, gbc);

        // RETURN BACK DESIGN TERMINAL
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        
        JButton backHome = new JButton("← Back to Homepage");
        backHome.setFont(new Font("Arial", Font.BOLD, 14));
        backHome.setBackground(Color.LIGHT_GRAY);
        backHome.setFocusPainted(false);
        backHome.setPreferredSize(new Dimension(200, 40));
        backHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backHome.addActionListener(e -> {
            dispose();
            new HomePage(username);
        });
        panel.add(backHome, gbc);

        add(panel);
        setVisible(true);
    }

    // ==========================================
    // INTERACTIVE SLIDESHOW FRAME SIZE (410 x 730)
    // ==========================================
    private class InteractiveLearnWindow extends JFrame {
        private final LearningContentProvider contentProvider;
        private int currentPage = 0;

        private final JLabel pageNumLabel;
        private final JLabel topicTitleLabel;
        private final JTextArea bodyTextArea;
        private final JLabel imageLabel;
        private final JButton prevButton;
        private final JButton nextButton;

        public InteractiveLearnWindow() {
            contentProvider = new LearningContentProvider();

            setTitle("SDG 3 Learning Modules");
            setSize(410, 730); 
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(false);

            // Container Wrapper Panel
            JPanel mainWrapper = new JPanel(new BorderLayout());
            mainWrapper.setBackground(Color.WHITE);

            // HOMEPAGE-STYLE GREEN BANNER TOP
            JPanel greenHeaderBanner = new JPanel(new BorderLayout());
            greenHeaderBanner.setBackground(new Color(67, 130, 0));
            greenHeaderBanner.setPreferredSize(new Dimension(410, 60));
            
            JLabel bannerText = new JLabel("Learning Modules", SwingConstants.CENTER);
            bannerText.setFont(new Font("Arial", Font.BOLD, 20));
            bannerText.setForeground(Color.WHITE);
            greenHeaderBanner.add(bannerText, BorderLayout.CENTER);
            mainWrapper.add(greenHeaderBanner, BorderLayout.NORTH);

            // INSIDE CONTENT BODY PANEL (GridBagLayout)
            JPanel contentPanel = new JPanel(new GridBagLayout());
            contentPanel.setBackground(Color.WHITE);
            contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;

            // PAGE NUMBER DISPLAY LABEL
            pageNumLabel = new JLabel("", SwingConstants.LEFT);
            pageNumLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            pageNumLabel.setForeground(Color.GRAY);
            gbc.insets = new Insets(0, 0, 5, 0);
            contentPanel.add(pageNumLabel, gbc);

            // MODULE HEADER TITLE
            gbc.gridy++;
            gbc.insets = new Insets(0, 0, 15, 0);
            topicTitleLabel = new JLabel("", SwingConstants.CENTER);
            topicTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
            topicTitleLabel.setForeground(new Color(50, 50, 50));
            contentPanel.add(topicTitleLabel, gbc);

            // IMAGE CONTAINER PLACEHOLDER (Scaled for mobile bounds viewport layout)
            gbc.gridy++;
            gbc.insets = new Insets(0, 0, 15, 0);
            imageLabel = new JLabel("", SwingConstants.CENTER);
            imageLabel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
            imageLabel.setBackground(new Color(248, 248, 248));
            imageLabel.setOpaque(true);
            imageLabel.setPreferredSize(new Dimension(340, 150)); // Changed down from 410x160 to prevent clipping
            contentPanel.add(imageLabel, gbc);

            // DYNAMIC BODY TEXT DISPLAY SCROLLER
            gbc.gridy++;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(0, 0, 15, 0);

            bodyTextArea = new JTextArea();
            bodyTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
            bodyTextArea.setLineWrap(true);
            bodyTextArea.setWrapStyleWord(true);
            bodyTextArea.setEditable(false);
            bodyTextArea.setBackground(Color.WHITE);
            bodyTextArea.setForeground(new Color(60, 60, 60));
            
            JScrollPane textScrollPane = new JScrollPane(bodyTextArea);
            textScrollPane.setBorder(BorderFactory.createEmptyBorder());
            contentPanel.add(textScrollPane, gbc);

            mainWrapper.add(contentPanel, BorderLayout.CENTER);

            // BOTTOM TASK CONTROLS PANEL
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
            bottomPanel.setBackground(Color.WHITE);
            bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 20, 25));

            // Row 1: Left/Right Arrow Navigation Button blocks
            JPanel arrowPanel = new JPanel(new GridLayout(1, 2, 15, 0));
            arrowPanel.setBackground(Color.WHITE);

            prevButton = new JButton("<< Previous");
            prevButton.setFont(new Font("Arial", Font.BOLD, 13));
            prevButton.setBackground(Color.LIGHT_GRAY);
            prevButton.setFocusPainted(false);
            prevButton.setPreferredSize(new Dimension(160, 40));
            prevButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            prevButton.addActionListener(e -> {
                if (currentPage > 0) {
                    currentPage--;
                    updateView();
                }
            });

            nextButton = new JButton("Next >>");
            nextButton.setFont(new Font("Arial", Font.BOLD, 13));
            nextButton.setBackground(new Color(67, 130, 0));
            nextButton.setForeground(Color.WHITE);
            nextButton.setFocusPainted(false);
            nextButton.setPreferredSize(new Dimension(160, 40));
            nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            nextButton.addActionListener(e -> {
                if (currentPage < contentProvider.getTotalPages() - 1) {
                    currentPage++;
                    updateView();
                }
            });

            arrowPanel.add(prevButton);
            arrowPanel.add(nextButton);
            bottomPanel.add(arrowPanel);
            
            // Layout Break Spacing
            bottomPanel.add(Box.createRigidArea(new Dimension(0, 12)));

            // Row 2: Back to Selection Dashboard Button
            JButton backToHubButton = new JButton("← Back to Hub Options");
            backToHubButton.setFont(new Font("Arial", Font.BOLD, 14));
            backToHubButton.setBackground(new Color(67, 130, 0));
            backToHubButton.setForeground(Color.WHITE);
            backToHubButton.setFocusPainted(false);
            backToHubButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            backToHubButton.setMaximumSize(new Dimension(340, 45));
            backToHubButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            backToHubButton.addActionListener(e -> {
                dispose();
                new Learn(currentUsername);
            });
            bottomPanel.add(backToHubButton);

            mainWrapper.add(bottomPanel, BorderLayout.SOUTH);
            add(mainWrapper);

            updateView();
            setVisible(true);
        }

        private void updateView() {
            ILearningContent currentPageData = contentProvider.getPage(currentPage);
            if (currentPageData != null) {
                pageNumLabel.setText("Module Page: " + (currentPage + 1) + " / " + contentProvider.getTotalPages());
                topicTitleLabel.setText("<html><center>" + currentPageData.getTitle() + "</center></html>");
                bodyTextArea.setText(currentPageData.getBodyText());
                bodyTextArea.setCaretPosition(0); 

                java.net.URL url = getClass().getResource(currentPageData.getImageUrl());
                if (url != null) {
                    ImageIcon icon = new ImageIcon(url);
                    // CHANGED: Match smooth instance calculations down from 410x160 to new 340x150 scale rules
                    Image scaled = icon.getImage().getScaledInstance(340, 180, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(scaled));
                    imageLabel.setText("");
                } else {
                    imageLabel.setIcon(null);
                    imageLabel.setText("Image Asset Folder: " + currentPageData.getImageUrl());
                    imageLabel.setForeground(Color.GRAY);
                }

                prevButton.setEnabled(currentPage > 0);
                nextButton.setEnabled(currentPage < contentProvider.getTotalPages() - 1);
            }
        }
    }
}