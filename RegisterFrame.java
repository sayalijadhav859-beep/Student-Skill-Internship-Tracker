import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterFrame extends JFrame {

    JTextField nameField;
    JTextField usernameField;
    JPasswordField passwordField;

    JButton registerButton;
    JButton backButton;

    // Colors
    Color dustyRose = new Color(183, 110, 121);
    Color lightRose = new Color(255, 240, 243);
    Color darkRose = new Color(110, 60, 70);

    public RegisterFrame() {

        // Window Settings
        setTitle("Student Registration");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(dustyRose);
        mainPanel.setLayout(new BorderLayout());

        // ---------------- TITLE ----------------

        JLabel titleLabel = new JLabel(
                "Create Student Account",
                SwingConstants.CENTER
        );

        titleLabel.setFont(new Font("Serif", Font.BOLD, 27));
        titleLabel.setForeground(Color.WHITE);

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(30, 10, 20, 10)
        );

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // ---------------- FORM PANEL ----------------

        JPanel formPanel = new JPanel();

        formPanel.setBackground(dustyRose);

        formPanel.setLayout(
                new GridLayout(6, 1, 8, 8)
        );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 65, 10, 65
                )
        );

        // Name Label
        JLabel nameLabel = new JLabel("Full Name");

        nameLabel.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        nameLabel.setForeground(Color.WHITE);

        // Name Field
        nameField = new JTextField();

        nameField.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        nameField.setBackground(lightRose);
        nameField.setForeground(darkRose);

        // Username Label
        JLabel usernameLabel = new JLabel("Username");

        usernameLabel.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        usernameLabel.setForeground(Color.WHITE);

        // Username Field
        usernameField = new JTextField();

        usernameField.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        usernameField.setBackground(lightRose);
        usernameField.setForeground(darkRose);

        // Password Label
        JLabel passwordLabel = new JLabel("Password");

        passwordLabel.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        passwordLabel.setForeground(Color.WHITE);

        // Password Field
        passwordField = new JPasswordField();

        passwordField.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        passwordField.setBackground(lightRose);
        passwordField.setForeground(darkRose);

        // Add components
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);

        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // ---------------- BUTTON PANEL ----------------

        JPanel buttonPanel = new JPanel();

        buttonPanel.setBackground(dustyRose);

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 0, 30, 0
                )
        );

        registerButton = new JButton("Register");
        backButton = new JButton("Back to Login");

        // Register Button
        registerButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        registerButton.setBackground(lightRose);
        registerButton.setForeground(darkRose);
        registerButton.setFocusPainted(false);

        registerButton.setPreferredSize(
                new Dimension(120, 40)
        );

        // Back Button
        backButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        backButton.setBackground(lightRose);
        backButton.setForeground(darkRose);
        backButton.setFocusPainted(false);

        backButton.setPreferredSize(
                new Dimension(140, 40)
        );

        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // ---------------- BUTTON ACTIONS ----------------

        registerButton.addActionListener(
                e -> register()
        );

        backButton.addActionListener(e -> {

            dispose();

            new LoginFrame().setVisible(true);
        });
    }

    // ---------------- REGISTER METHOD ----------------

    private void register() {

        String name = nameField.getText().trim();

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        // Check empty fields
        if (name.isEmpty()
                || username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields.",
                    "Registration Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        try {

            FileWriter writer =
                    new FileWriter(
                            "students.txt",
                            true
                    );

            writer.write(
                    name + "," +
                    username + "," +
                    password + "\n"
            );

            writer.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Registration Successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

            new LoginFrame().setVisible(true);

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error while saving student data.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ---------------- MAIN METHOD ----------------

    public static void main(String[] args) {

        new RegisterFrame().setVisible(true);
    }
}
