import javax.swing.*;
import java.awt.*;
import java.io.*;

public class DashboardFrame extends JFrame {

    Color dustyRose = new Color(183, 110, 121);
    Color lightRose = new Color(255, 240, 243);
    Color darkRose = new Color(110, 60, 70);

    String studentName;

    public DashboardFrame(String studentName) {

        this.studentName = studentName;

        setTitle("Student Skill & Internship Tracker");
        setSize(750, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(
                new BorderLayout()
        );

        mainPanel.setBackground(dustyRose);

        // ================= HEADER =================

        JPanel headerPanel = new JPanel();

        headerPanel.setBackground(dustyRose);
        headerPanel.setLayout(
                new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel = new JLabel(
                "Student Skill & Internship Tracker"
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        titleLabel.setFont(
                new Font("Serif", Font.BOLD, 27)
        );

        titleLabel.setForeground(Color.WHITE);

        JLabel welcomeLabel = new JLabel(
                "Welcome, " + studentName + "!"
        );

        welcomeLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        welcomeLabel.setFont(
                new Font("Arial", Font.BOLD, 19)
        );

        welcomeLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel(
                "Manage your skills and internship journey"
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        subtitleLabel.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        subtitleLabel.setForeground(lightRose);

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 10, 15, 10
                )
        );

        headerPanel.add(titleLabel);

        headerPanel.add(
                Box.createVerticalStrut(8)
        );

        headerPanel.add(welcomeLabel);

        headerPanel.add(
                Box.createVerticalStrut(5)
        );

        headerPanel.add(subtitleLabel);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // ================= CENTER =================

        JPanel centerPanel = new JPanel();

        centerPanel.setBackground(dustyRose);

        centerPanel.setLayout(
                new BoxLayout(
                        centerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 60, 5, 60
                )
        );

        // ================= STATISTICS =================

        int skillCount = getSkillCount();
        int internshipCount = getInternshipCount();
        int completedCount = getCompletedCount();

        JPanel statsPanel =
                new JPanel(
                        new GridLayout(
                                1, 3, 15, 0
                        )
                );

        statsPanel.setBackground(dustyRose);

        statsPanel.add(
                createCard(
                        "Skills",
                        String.valueOf(skillCount)
                )
        );

        statsPanel.add(
                createCard(
                        "Internships",
                        String.valueOf(internshipCount)
                )
        );

        statsPanel.add(
                createCard(
                        "Completed",
                        String.valueOf(completedCount)
                )
        );

        centerPanel.add(statsPanel);

        centerPanel.add(
                Box.createVerticalStrut(15)
        );

        // ================= SUMMARY =================

        JPanel summaryPanel =
                new JPanel();

        summaryPanel.setBackground(lightRose);

        summaryPanel.setLayout(
                new GridLayout(4, 2, 8, 5)
        );

        summaryPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.WHITE,
                                2
                        ),
                        BorderFactory.createEmptyBorder(
                                10, 20, 10, 20
                        )
                )
        );

        JLabel summaryTitle =
                new JLabel("Internship Summary");

        summaryTitle.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        summaryTitle.setForeground(darkRose);

        summaryPanel.add(summaryTitle);
        summaryPanel.add(new JLabel(""));

        int applied = getStatusCount("Applied");
        int selected = getStatusCount("Selected");
        int completed = getStatusCount("Completed");
        int rejected = getStatusCount("Rejected");

        addSummaryRow(
                summaryPanel,
                "Applied",
                applied
        );

        addSummaryRow(
                summaryPanel,
                "Selected",
                selected
        );

        addSummaryRow(
                summaryPanel,
                "Completed",
                completed
        );

        addSummaryRow(
                summaryPanel,
                "Rejected",
                rejected
        );

        centerPanel.add(summaryPanel);

        centerPanel.add(
                Box.createVerticalStrut(15)
        );

        // ================= BUTTONS =================

        JButton profileButton =
                createMenuButton(
                        "Student Profile",
                        "Manage your personal information"
                );

        JButton skillsButton =
                createMenuButton(
                        "My Skills",
                        "Add and manage your skills"
                );

        JButton internshipButton =
                createMenuButton(
                        "Internships",
                        "Track your internship applications"
                );

        JButton aboutButton =
                createMenuButton(
                        "About Project",
                        "Learn about this application"
                );

        centerPanel.add(profileButton);

        centerPanel.add(
                Box.createVerticalStrut(8)
        );

        centerPanel.add(skillsButton);

        centerPanel.add(
                Box.createVerticalStrut(8)
        );

        centerPanel.add(internshipButton);

        centerPanel.add(
                Box.createVerticalStrut(8)
        );

        centerPanel.add(aboutButton);

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // ================= BOTTOM =================

        JPanel bottomPanel = new JPanel();

        bottomPanel.setBackground(dustyRose);

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 0, 20, 0
                )
        );

        JButton logoutButton =
                new JButton("Logout");

        styleButton(logoutButton);

        logoutButton.setPreferredSize(
                new Dimension(120, 38)
        );

        bottomPanel.add(logoutButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // ================= ACTIONS =================

        profileButton.addActionListener(e -> {

            dispose();

            new StudentProfileFrame(
                    studentName
            ).setVisible(true);
        });

        skillsButton.addActionListener(e -> {

            dispose();

            new SkillsFrame(
                    studentName
            ).setVisible(true);
        });

        internshipButton.addActionListener(e -> {

            dispose();

            new InternshipFrame(
                    studentName
            ).setVisible(true);
        });

        aboutButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Student Skill & Internship Tracker\n\n"
                    + "This Java-based application helps "
                    + "students manage their profile, "
                    + "skills and internship records.\n\n"
                    + "Features:\n"
                    + "• Student Registration & Login\n"
                    + "• Profile Management\n"
                    + "• Skill Management\n"
                    + "• Skill Level\n"
                    + "• Internship Tracking\n"
                    + "• Internship Status Filter\n"
                    + "• Internship Summary\n\n"
                    + "Developed using Java Swing.",
                    "About Project",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        logoutButton.addActionListener(e -> {

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to logout?",
                            "Logout",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                dispose();

                new LoginFrame().setVisible(true);
            }
        });
    }

    // ================= CARD =================

    private JPanel createCard(
            String title,
            String number
    ) {

        JPanel card = new JPanel();

        card.setBackground(lightRose);

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.WHITE,
                                2
                        ),
                        BorderFactory.createEmptyBorder(
                                8, 10, 8, 10
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        titleLabel.setForeground(darkRose);

        JLabel numberLabel =
                new JLabel(number);

        numberLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        numberLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        numberLabel.setForeground(darkRose);

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(3)
        );

        card.add(numberLabel);

        return card;
    }

    // ================= SUMMARY ROW =================

    private void addSummaryRow(
            JPanel panel,
            String title,
            int count
    ) {

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        titleLabel.setForeground(darkRose);

        JLabel countLabel =
                new JLabel(String.valueOf(count));

        countLabel.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        countLabel.setForeground(darkRose);

        panel.add(titleLabel);
        panel.add(countLabel);
    }

    // ================= MENU BUTTON =================

    private JButton createMenuButton(
            String title,
            String description
    ) {

        JButton button =
                new JButton(
                        "<html><center>"
                        + "<b>" + title + "</b><br>"
                        + "<font size='3'>"
                        + description
                        + "</font>"
                        + "</center></html>"
                );

        button.setBackground(lightRose);
        button.setForeground(darkRose);

        button.setFocusPainted(false);

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        return button;
    }

    // ================= BUTTON STYLE =================

    private void styleButton(
            JButton button
    ) {

        button.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        button.setBackground(lightRose);
        button.setForeground(darkRose);

        button.setFocusPainted(false);
    }

    // ================= SKILL COUNT =================

    private int getSkillCount() {

        File file = new File(
                "skills_" + studentName + ".txt"
        );

        return countLines(file);
    }

    // ================= INTERNSHIP COUNT =================

    private int getInternshipCount() {

        File file = new File(
                "internships_"
                + studentName
                + ".txt"
        );

        return countLines(file);
    }

    // ================= COMPLETED COUNT =================

    private int getCompletedCount() {

        return getStatusCount("Completed");
    }

    // ================= STATUS COUNT =================

    private int getStatusCount(
            String requiredStatus
    ) {

        File file = new File(
                "internships_"
                + studentName
                + ".txt"
        );

        if (!file.exists()) {
            return 0;
        }

        int count = 0;

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file)
                    );

            String line;

            while ((line = reader.readLine())
                    != null) {

                if (line.endsWith(
                        "| " + requiredStatus
                )) {

                    count++;
                }
            }

            reader.close();

        } catch (IOException e) {

            return 0;
        }

        return count;
    }

    // ================= COUNT LINES =================

    private int countLines(
            File file
    ) {

        if (!file.exists()) {
            return 0;
        }

        int count = 0;

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file)
                    );

            while (reader.readLine() != null) {
                count++;
            }

            reader.close();

        } catch (IOException e) {

            return 0;
        }

        return count;
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        new DashboardFrame(
                "Sayali Jadhav"
        ).setVisible(true);
    }
}