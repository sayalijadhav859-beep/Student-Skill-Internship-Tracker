import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SkillsFrame extends JFrame {

    Color dustyRose = new Color(183, 110, 121);
    Color lightRose = new Color(255, 240, 243);
    Color darkRose = new Color(110, 60, 70);

    JTextField skillField;
    JComboBox<String> levelBox;

    DefaultListModel<String> skillModel;
    JList<String> skillList;

    String studentName;

    public SkillsFrame(String studentName) {

        this.studentName = studentName;

        setTitle("My Skills");
        setSize(600, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(dustyRose);
        mainPanel.setLayout(new BorderLayout());

        // ---------- TITLE ----------

        JLabel titleLabel = new JLabel(
                "My Skills",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Serif", Font.BOLD, 28)
        );

        titleLabel.setForeground(Color.WHITE);

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 10, 20, 10
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        // ---------- INPUT PANEL ----------

        JPanel inputPanel = new JPanel(
                new GridLayout(2, 2, 10, 10)
        );

        inputPanel.setBackground(dustyRose);

        inputPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 60, 10, 60
                )
        );

        JLabel skillLabel =
                createLabel("Skill Name");

        skillField =
                createTextField();

        JLabel levelLabel =
                createLabel("Skill Level");

        levelBox = new JComboBox<>(
                new String[]{
                        "Beginner",
                        "Intermediate",
                        "Advanced"
                }
        );

        styleComboBox(levelBox);

        inputPanel.add(skillLabel);
        inputPanel.add(skillField);

        inputPanel.add(levelLabel);
        inputPanel.add(levelBox);

        // ---------- ADD BUTTON ----------

        JButton addButton =
                new JButton("Add Skill");

        styleButton(addButton);

        JPanel addPanel = new JPanel();

        addPanel.setBackground(dustyRose);

        addPanel.add(addButton);

        // ---------- SKILL LIST ----------

        skillModel =
                new DefaultListModel<>();

        skillList =
                new JList<>(skillModel);

        skillList.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        skillList.setBackground(lightRose);
        skillList.setForeground(darkRose);

        skillList.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(skillList);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Your Skills"
                )
        );

        // ---------- DELETE ----------

        JButton deleteButton =
                new JButton("Delete Selected Skill");

        styleButton(deleteButton);

        JPanel deletePanel = new JPanel();

        deletePanel.setBackground(dustyRose);

        deletePanel.add(deleteButton);

        // ---------- LIST PANEL ----------

        JPanel listPanel =
                new JPanel(new BorderLayout());

        listPanel.setBackground(dustyRose);

        listPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 60, 10, 60
                )
        );

        listPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        listPanel.add(
                deletePanel,
                BorderLayout.SOUTH
        );

        // ---------- CENTER ----------

        JPanel centerPanel =
                new JPanel(new BorderLayout());

        centerPanel.setBackground(dustyRose);

        JPanel topPanel =
                new JPanel(new BorderLayout());

        topPanel.setBackground(dustyRose);

        topPanel.add(
                inputPanel,
                BorderLayout.CENTER
        );

        topPanel.add(
                addPanel,
                BorderLayout.SOUTH
        );

        centerPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                listPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // ---------- BOTTOM ----------

        JPanel bottomPanel = new JPanel();

        bottomPanel.setBackground(dustyRose);

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 0, 25, 0
                )
        );

        JButton backButton =
                new JButton("Back to Dashboard");

        styleButton(backButton);

        backButton.setPreferredSize(
                new Dimension(170, 40)
        );

        bottomPanel.add(backButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // ---------- LOAD SKILLS ----------

        loadSkills();

        // ---------- ADD SKILL ----------

        addButton.addActionListener(
                e -> addSkill()
        );

        // ---------- DELETE ----------

        deleteButton.addActionListener(
                e -> deleteSkill()
        );

        // ---------- BACK ----------

        backButton.addActionListener(e -> {

            dispose();

            new DashboardFrame(
                    studentName
            ).setVisible(true);
        });
    }

    // ==================================================
    // ADD SKILL
    // ==================================================

    private void addSkill() {

        String skill =
                skillField.getText().trim();

        String level =
                levelBox.getSelectedItem()
                        .toString();

        if (skill.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a skill.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Check duplicate skill

        for (int i = 0;
             i < skillModel.size();
             i++) {

            String existing =
                    skillModel.get(i);

            String existingSkill =
                    existing.split(" \\| ")[0];

            if (existingSkill.equalsIgnoreCase(
                    skill
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "This skill is already added.",
                        "Duplicate Skill",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }
        }

        String skillData =
                skill + " | " + level;

        skillModel.addElement(
                skillData
        );

        saveSkills();

        skillField.setText("");

        levelBox.setSelectedIndex(0);

        JOptionPane.showMessageDialog(
                this,
                "Skill added successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ==================================================
    // DELETE SKILL
    // ==================================================

    private void deleteSkill() {

        int selectedIndex =
                skillList.getSelectedIndex();

        if (selectedIndex == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a skill to delete.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String skill =
                skillModel.get(selectedIndex);

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete " + skill + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice ==
                JOptionPane.YES_OPTION) {

            skillModel.remove(
                    selectedIndex
            );

            saveSkills();

            JOptionPane.showMessageDialog(
                    this,
                    "Skill deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // ==================================================
    // SAVE SKILLS
    // ==================================================

    private void saveSkills() {

        try {

            FileWriter writer =
                    new FileWriter(
                            "skills_"
                            + studentName
                            + ".txt"
                    );

            for (int i = 0;
                 i < skillModel.size();
                 i++) {

                writer.write(
                        skillModel.get(i)
                        + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error saving skills."
            );
        }
    }

    // ==================================================
    // LOAD SKILLS
    // ==================================================

    private void loadSkills() {

        File file =
                new File(
                        "skills_"
                        + studentName
                        + ".txt"
                );

        if (!file.exists()) {
            return;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file)
                    );

            String line;

            while ((line = reader.readLine())
                    != null) {

                if (!line.trim().isEmpty()) {

                    skillModel.addElement(
                            line.trim()
                    );
                }
            }

            reader.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading skills."
            );
        }
    }

    // ==================================================
    // LABEL
    // ==================================================

    private JLabel createLabel(String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        label.setForeground(Color.WHITE);

        return label;
    }

    // ==================================================
    // TEXT FIELD
    // ==================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        field.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        field.setBackground(lightRose);
        field.setForeground(darkRose);

        return field;
    }

    // ==================================================
    // COMBO BOX
    // ==================================================

    private void styleComboBox(
            JComboBox<String> box
    ) {

        box.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        box.setBackground(lightRose);
        box.setForeground(darkRose);
    }

    // ==================================================
    // BUTTON
    // ==================================================

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

    // ==================================================
    // MAIN
    // ==================================================

    public static void main(String[] args) {

        new SkillsFrame(
                "Sayali Jadhav"
        ).setVisible(true);
    }
}