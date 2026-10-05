import javax.swing.*;
import java.awt.*;
import java.io.*;

public class InternshipFrame extends JFrame {

    Color dustyRose = new Color(183, 110, 121);
    Color lightRose = new Color(255, 240, 243);
    Color darkRose = new Color(110, 60, 70);

    JTextField companyField;
    JTextField roleField;
    JTextField durationField;

    JComboBox<String> statusBox;
    JComboBox<String> filterBox;

    DefaultListModel<String> internshipModel;
    JList<String> internshipList;

    String studentName;

    public InternshipFrame(String studentName) {

        this.studentName = studentName;

        setTitle("Internship Tracker");
        setSize(700, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(dustyRose);
        mainPanel.setLayout(new BorderLayout());

        // ---------- TITLE ----------

        JLabel titleLabel = new JLabel(
                "Internship Tracker",
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

        // ---------- FORM ----------

        JPanel formPanel = new JPanel(
                new GridLayout(4, 2, 10, 10)
        );

        formPanel.setBackground(dustyRose);

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 60, 10, 60
                )
        );

        JLabel companyLabel =
                createLabel("Company Name");

        companyField = createTextField();

        JLabel roleLabel =
                createLabel("Internship Role");

        roleField = createTextField();

        JLabel durationLabel =
                createLabel("Duration");

        durationField = createTextField();

        JLabel statusLabel =
                createLabel("Status");

        statusBox = new JComboBox<>(
                new String[]{
                        "Applied",
                        "Selected",
                        "Completed",
                        "Rejected"
                }
        );

        styleComboBox(statusBox);

        formPanel.add(companyLabel);
        formPanel.add(companyField);

        formPanel.add(roleLabel);
        formPanel.add(roleField);

        formPanel.add(durationLabel);
        formPanel.add(durationField);

        formPanel.add(statusLabel);
        formPanel.add(statusBox);

        // ---------- ADD BUTTON ----------

        JButton addButton =
                new JButton("Add Internship");

        styleButton(addButton);

        JPanel addPanel = new JPanel();

        addPanel.setBackground(dustyRose);

        addPanel.add(addButton);

        // ---------- FILTER ----------

        JPanel filterPanel = new JPanel();

        filterPanel.setBackground(dustyRose);

        filterPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 60, 10, 60
                )
        );

        JLabel filterLabel =
                createLabel("Filter by Status:");

        filterBox = new JComboBox<>(
                new String[]{
                        "All",
                        "Applied",
                        "Selected",
                        "Completed",
                        "Rejected"
                }
        );

        styleComboBox(filterBox);

        filterPanel.add(filterLabel);
        filterPanel.add(filterBox);

        // ---------- LIST ----------

        internshipModel =
                new DefaultListModel<>();

        internshipList =
                new JList<>(internshipModel);

        internshipList.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        internshipList.setBackground(lightRose);
        internshipList.setForeground(darkRose);

        internshipList.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(internshipList);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Your Internships"
                )
        );

        // ---------- DELETE ----------

        JButton deleteButton =
                new JButton("Delete Selected");

        styleButton(deleteButton);

        JPanel deletePanel = new JPanel();

        deletePanel.setBackground(dustyRose);

        deletePanel.add(deleteButton);

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

        JPanel topSection =
                new JPanel(new BorderLayout());

        topSection.setBackground(dustyRose);

        topSection.add(
                formPanel,
                BorderLayout.CENTER
        );

        topSection.add(
                addPanel,
                BorderLayout.SOUTH
        );

        centerPanel.add(
                topSection,
                BorderLayout.NORTH
        );

        centerPanel.add(
                filterPanel,
                BorderLayout.CENTER
        );

        // ---------- CONTENT ----------

        JPanel contentPanel =
                new JPanel(new BorderLayout());

        contentPanel.setBackground(dustyRose);

        contentPanel.add(
                topSection,
                BorderLayout.NORTH
        );

        JPanel middlePanel =
                new JPanel(new BorderLayout());

        middlePanel.setBackground(dustyRose);

        middlePanel.add(
                filterPanel,
                BorderLayout.NORTH
        );

        middlePanel.add(
                listPanel,
                BorderLayout.CENTER
        );

        contentPanel.add(
                middlePanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                contentPanel,
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
                new Dimension(160, 40)
        );

        bottomPanel.add(backButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // ---------- LOAD ----------

        loadInternships();

        // ---------- ADD ----------

        addButton.addActionListener(
                e -> addInternship()
        );

        // ---------- DELETE ----------

        deleteButton.addActionListener(
                e -> deleteInternship()
        );

        // ---------- FILTER ----------

        filterBox.addActionListener(
                e -> filterInternships()
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
    // ADD INTERNSHIP
    // ==================================================

    private void addInternship() {

        String company =
                companyField.getText().trim();

        String role =
                roleField.getText().trim();

        String duration =
                durationField.getText().trim();

        String status =
                statusBox.getSelectedItem()
                        .toString();

        if (company.isEmpty()
                || role.isEmpty()
                || duration.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all internship details.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String internship =
                company
                + " | "
                + role
                + " | "
                + duration
                + " | "
                + status;

        internshipModel.addElement(
                internship
        );

        saveInternships();

        companyField.setText("");
        roleField.setText("");
        durationField.setText("");

        filterBox.setSelectedItem("All");

        JOptionPane.showMessageDialog(
                this,
                "Internship added successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ==================================================
    // DELETE
    // ==================================================

    private void deleteInternship() {

        int selectedIndex =
                internshipList.getSelectedIndex();

        if (selectedIndex == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an internship.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String selected =
                internshipList.getSelectedValue();

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete selected internship?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice ==
                JOptionPane.YES_OPTION) {

            removeInternshipFromFile(
                    selected
            );

            refreshList();

            JOptionPane.showMessageDialog(
                    this,
                    "Internship deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // ==================================================
    // FILTER
    // ==================================================

    private void filterInternships() {

        String selectedStatus =
                filterBox.getSelectedItem()
                        .toString();

        DefaultListModel<String> filteredModel =
                new DefaultListModel<>();

        File file = new File(
                "internships_"
                + studentName
                + ".txt"
        );

        if (!file.exists()) {
            internshipList.setModel(
                    filteredModel
            );
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

                if (line.trim().isEmpty()) {
                    continue;
                }

                if (selectedStatus.equals("All")
                        || line.endsWith(
                                "| " + selectedStatus
                        )) {

                    filteredModel.addElement(
                            line
                    );
                }
            }

            reader.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error filtering internships."
            );
        }

        internshipList.setModel(
                filteredModel
        );
    }

    // ==================================================
    // REFRESH LIST
    // ==================================================

    private void refreshList() {

        internshipModel.clear();

        loadInternships();

        filterInternships();
    }

    // ==================================================
    // REMOVE FROM FILE
    // ==================================================

    private void removeInternshipFromFile(
            String internshipToRemove
    ) {

        File file = new File(
                "internships_"
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

            StringBuilder data =
                    new StringBuilder();

            String line;

            boolean removed = false;

            while ((line = reader.readLine())
                    != null) {

                if (!removed
                        && line.equals(
                                internshipToRemove
                        )) {

                    removed = true;
                    continue;
                }

                data.append(line)
                        .append("\n");
            }

            reader.close();

            FileWriter writer =
                    new FileWriter(file);

            writer.write(
                    data.toString()
            );

            writer.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error deleting internship."
            );
        }
    }

    // ==================================================
    // SAVE
    // ==================================================

    private void saveInternships() {

        try {

            FileWriter writer =
                    new FileWriter(
                            "internships_"
                            + studentName
                            + ".txt"
                    );

            for (int i = 0;
                 i < internshipModel.size();
                 i++) {

                writer.write(
                        internshipModel.get(i)
                        + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error saving internship data."
            );
        }
    }

    // ==================================================
    // LOAD
    // ==================================================

    private void loadInternships() {

        File file = new File(
                "internships_"
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

                    internshipModel.addElement(
                            line.trim()
                    );
                }
            }

            reader.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading internship data."
            );
        }
    }

    // ==================================================
    // LABEL
    // ==================================================

    private JLabel createLabel(
            String text
    ) {

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

        new InternshipFrame(
                "Sayali Jadhav"
        ).setVisible(true);
    }
}