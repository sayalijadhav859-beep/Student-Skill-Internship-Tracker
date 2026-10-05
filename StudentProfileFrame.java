import javax.swing.*;
import java.awt.*;

public class StudentProfileFrame extends JFrame {

    Color dustyRose = new Color(183, 110, 121);
    Color lightRose = new Color(255, 240, 243);
    Color darkRose = new Color(110, 60, 70);

    JTextField nameField;
    JTextField emailField;
    JTextField phoneField;
    JTextField courseField;
    JTextField branchField;

    JComboBox<String> yearBox;

    String studentName;

    public StudentProfileFrame(String studentName) {

        this.studentName = studentName;

        setTitle("Student Profile");
        setSize(550, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(dustyRose);
        mainPanel.setLayout(new BorderLayout());

        // ---------- TITLE ----------

        JLabel titleLabel = new JLabel(
                "Student Profile",
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

        // ---------- FORM PANEL ----------

        JPanel formPanel = new JPanel();

        formPanel.setBackground(dustyRose);

        formPanel.setLayout(
                new GridLayout(6, 2, 12, 12)
        );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 60, 20, 60
                )
        );

        // Full Name
        JLabel nameLabel = createLabel("Full Name");

        nameField = createTextField();
        nameField.setText(studentName);

        // Email
        JLabel emailLabel = createLabel("Email");

        emailField = createTextField();

        // Phone
        JLabel phoneLabel = createLabel("Phone");

        phoneField = createTextField();

        // Course
        JLabel courseLabel = createLabel("Course");

        courseField = createTextField();

        // Branch
        JLabel branchLabel = createLabel("Branch");

        branchField = createTextField();

        // Year
        JLabel yearLabel = createLabel("Year");

        yearBox = new JComboBox<>(
                new String[]{
                        "First Year",
                        "Second Year",
                        "Third Year",
                        "Final Year"
                }
        );

        yearBox.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        yearBox.setBackground(lightRose);
        yearBox.setForeground(darkRose);

        // Add components
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        formPanel.add(emailLabel);
        formPanel.add(emailField);

        formPanel.add(phoneLabel);
        formPanel.add(phoneField);

        formPanel.add(courseLabel);
        formPanel.add(courseField);

        formPanel.add(branchLabel);
        formPanel.add(branchField);

        formPanel.add(yearLabel);
        formPanel.add(yearBox);

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // ---------- BUTTON PANEL ----------

        JPanel buttonPanel = new JPanel();

        buttonPanel.setBackground(dustyRose);

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 0, 30, 0
                )
        );

        JButton saveButton =
                new JButton("Save Profile");

        JButton backButton =
                new JButton("Back to Dashboard");

        styleButton(saveButton);
        styleButton(backButton);

        saveButton.setPreferredSize(
                new Dimension(130, 40)
        );

        backButton.setPreferredSize(
                new Dimension(160, 40)
        );

        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // ---------- SAVE ----------

        saveButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Profile saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        // ---------- BACK ----------

        backButton.addActionListener(e -> {

            dispose();

            new DashboardFrame(
                    studentName
            ).setVisible(true);
        });
    }

    // ---------- LABEL METHOD ----------

    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        label.setForeground(Color.WHITE);

        return label;
    }

    // ---------- TEXT FIELD METHOD ----------

    private JTextField createTextField() {

        JTextField field = new JTextField();

        field.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        field.setBackground(lightRose);
        field.setForeground(darkRose);

        return field;
    }

    // ---------- BUTTON STYLE ----------

    private void styleButton(JButton button) {

        button.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        button.setBackground(lightRose);
        button.setForeground(darkRose);

        button.setFocusPainted(false);
    }

    // ---------- MAIN ----------

    public static void main(String[] args) {

        new StudentProfileFrame(
                "Sayali Jadhav"
        ).setVisible(true);
    }
}