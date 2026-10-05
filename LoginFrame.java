import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginFrame extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton registerButton;

    Color dustyRose = new Color(183, 110, 121);
    Color lightRose = new Color(255, 240, 243);
    Color darkRose = new Color(110, 60, 70);

    public LoginFrame() {

        setTitle("Student Skill & Internship Tracker");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(dustyRose);
        mainPanel.setLayout(new BorderLayout());

        // ---------- TITLE ----------

        JLabel titleLabel = new JLabel(
                "Student Skill & Internship Tracker",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Serif", Font.BOLD, 25)
        );

        titleLabel.setForeground(Color.WHITE);

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 10, 20, 10
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        // ---------- FORM ----------

        JPanel formPanel = new JPanel();

        formPanel.setBackground(dustyRose);

        formPanel.setLayout(
                new GridLayout(4, 1, 10, 10)
        );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 60, 10, 60
                )
        );

        JLabel usernameLabel =
                new JLabel("Username");

        usernameLabel.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        usernameLabel.setForeground(Color.WHITE);

        usernameField = new JTextField();

        usernameField.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        usernameField.setBackground(lightRose);
        usernameField.setForeground(darkRose);

        JLabel passwordLabel =
                new JLabel("Password");

        passwordLabel.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        passwordLabel.setForeground(Color.WHITE);

        passwordField = new JPasswordField();

        passwordField.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        passwordField.setBackground(lightRose);
        passwordField.setForeground(darkRose);

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // ---------- BUTTONS ----------

        JPanel buttonPanel = new JPanel();

        buttonPanel.setBackground(dustyRose);

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 0, 30, 0
                )
        );

        loginButton =
                new JButton("Login");

        registerButton =
                new JButton("Register");

        styleButton(loginButton);
        styleButton(registerButton);

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // ---------- BUTTON ACTIONS ----------

        loginButton.addActionListener(
                e -> login()
        );

        registerButton.addActionListener(e -> {

            dispose();

            new RegisterFrame().setVisible(true);
        });
    }

    // ---------- BUTTON STYLE ----------

    private void styleButton(JButton button) {

        button.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        button.setBackground(lightRose);
        button.setForeground(darkRose);

        button.setFocusPainted(false);

        button.setPreferredSize(
                new Dimension(120, 40)
        );
    }

    // ---------- LOGIN ----------

    private void login() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        boolean loginSuccessful = false;

        String studentName = "";

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("students.txt")
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] studentData =
                        line.split(",");

                if (studentData.length == 3) {

                    String savedName =
                            studentData[0];

                    String savedUsername =
                            studentData[1];

                    String savedPassword =
                            studentData[2];

                    if (username.equals(savedUsername)
                            && password.equals(savedPassword)) {

                        loginSuccessful = true;

                        studentName = savedName;

                        break;
                    }
                }
            }

            reader.close();

            if (loginSuccessful) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful!",
                        "Welcome",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();

                // Open Dashboard
                new DashboardFrame(
                        studentName
                ).setVisible(true);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "No registered student found.\nPlease register first.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ---------- MAIN ----------

    public static void main(String[] args) {

        new LoginFrame().setVisible(true);
    }
}