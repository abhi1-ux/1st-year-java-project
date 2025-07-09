import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Graphics;
import java.io.BufferedWriter;
import javax.swing.JCheckBox;
import java.awt.Dimension;

import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Font;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GymGUI extends JFrame {
    private static ArrayList<GymMember> list = new ArrayList<>();
    private JFrame fr1, fr2, fr3, fr4, fr5, fr6, fr7, fr8, fr9;

    public GymGUI() {
        fr1 = new JFrame("GymFreak");
        fr1.setSize(1400, 900);
        fr1.setLayout(null);
        fr1.setResizable(false);
        fr1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr1.getContentPane().setBackground(new Color(255, 255, 255));

        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1400, 80);
        headerPanel.setBackground(new Color(0, 128, 128));
        headerPanel.setLayout(null);
        fr1.add(headerPanel);

        JLabel h1 = new JLabel("GymFreak");
        h1.setBounds(580, 15, 240, 50);
        h1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        h1.setForeground(new Color(0, 204, 204));
        headerPanel.add(h1);

        JLabel h2 = new JLabel("Membership Management");
        h2.setBounds(500, 100, 400, 30);
        h2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        h2.setForeground(new Color(0, 204, 204));
        fr1.add(h2);

        // Buttons
        JButton addMember = createStyledButton("Add Member");
        addMember.setBounds(250, 200, 300, 60);
        addMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr1.dispose();
                addMember();
            }
        });
        fr1.add(addMember);

        JButton activation = createStyledButton("Activation & Deactivation");
        activation.setBounds(850, 200, 300, 60);
        activation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr1.dispose();
                activation();
            }
        });
        fr1.add(activation);

        JButton revert = createStyledButton("Revert Member");
        revert.setBounds(250, 300, 300, 60);
        revert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr1.dispose();
                addRevertmember();
            }
        });
        fr1.add(revert);

        JButton show = createStyledButton("Display Members");
        show.setBounds(850, 300, 300, 60);
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr1.dispose();
                display();
            }
        });
        fr1.add(show);

        JButton upgradePlan = createStyledButton("Upgrade Plan");
        upgradePlan.setBounds(250, 400, 300, 60);
        upgradePlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr1.dispose();
                upgradePlan();
            }
        });
        fr1.add(upgradePlan);

        JButton payment = createStyledButton("Payment");
        payment.setBounds(850, 400, 300, 60);
        payment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr1.dispose();
                processPayment();
            }
        });
        fr1.add(payment);

        JButton readFile = createStyledButton("Read from File");
        readFile.setBounds(250, 500, 300, 60);
        readFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFromFile();
            }
        });
        fr1.add(readFile);

        JButton saveFile = createStyledButton("Save to File");
        saveFile.setBounds(850, 500, 300, 60);
        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });
        fr1.add(saveFile);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(50, 100, 1300, 750);
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        mainPanel.setLayout(null);
        fr1.add(mainPanel);

        fr1.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isRollover()) {
                    g.setColor(new Color(180, 255, 255));
                } else {
                    g.setColor(new Color(204, 255, 255));
                }
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
        button.setForeground(new Color(0, 51, 102));
        button.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        button.setContentAreaFilled(false);
        return button;
    }

    private void readFromFile() {
        File file = new File("MemberDetails.txt");
        JFrame displayFrame = new JFrame("Member Details");
        displayFrame.setSize(1400, 900);
        displayFrame.setLayout(null);
        displayFrame.setResizable(false);
        displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayFrame.getContentPane().setBackground(new Color(255, 255, 255));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1400, 80);
        headerPanel.setBackground(new Color(0, 128, 128));
        headerPanel.setLayout(null);
        displayFrame.add(headerPanel);

        JLabel h1 = new JLabel("GymFreak");
        h1.setBounds(580, 15, 240, 50);
        h1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        h1.setForeground(new Color(0, 204, 204));
        headerPanel.add(h1);

        JLabel h2 = new JLabel("Member Details");
        h2.setBounds(550, 100, 300, 30);
        h2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        h2.setForeground(new Color(0, 204, 204));
        displayFrame.add(h2);

        // Display Area
        JTextArea displayArea = new JTextArea();
        displayArea.setFont(new Font("Courier New", Font.PLAIN, 16));
        displayArea.setEditable(false);
        displayArea.setBorder(new LineBorder(new Color(0, 204, 204), 1));

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            if (sb.length() == 0) {
                sb.append("No member details found in the file.\n");
            }
            displayArea.setText(sb.toString());
        } catch (IOException ex) {
            displayArea.setText("Error reading file: " + ex.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(50, 150, 1300, 650);
        scrollPane.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        displayFrame.add(scrollPane);

        // Back Button
        JButton back = createStyledButton("Back");
        back.setBounds(600, 820, 200, 40);
        back.setFont(new Font("Segoe UI", Font.BOLD, 16));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayFrame.dispose();
            }
        });
        displayFrame.add(back);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(50, 100, 1300, 750);
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        mainPanel.setLayout(null);
        displayFrame.add(mainPanel);

        displayFrame.setVisible(true);
    }

    private void saveToFile() {
        File file = new File("MemberDetails.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(String.format("%-5s %-15s %-15s %-15s %-25s %-20s %-10s %-10s %-10s %15s %-10s %-15s %-15s %-15s\n",
                "ID", "Name", "Location", "Phone", "Email", "Membership Start", "Plan", "Price", "Attendance", "Loyalty Points",
                "Active Status", "Full Payment", "Discount Amount", "Net Amount Paid"));
            writer.write("-----------------------------------------------------------------------------------------------------------------------------\n");

            for (GymMember member : list) {
                String plan = "";
                String price = "";
                String attendance = String.valueOf(member.getLoyaltyPoints());
                String discountAmount = "0";
                String netAmountPaid = "0";

                if (member instanceof RegularMember) {
                    RegularMember rm = (RegularMember) member;
                    plan = rm.getPlan();
                    price = String.format("%.2f", rm.getPrice());
                    discountAmount = "N/A";
                    netAmountPaid = rm.getIsFullPayment() ? String.format("%.2f", rm.getPrice()) : "0.00";
                    if (!rm.getRemovalReason().isEmpty()) {
                        writer.write("Removal Reason: " + rm.getRemovalReason() + "\n");
                    }
                } else if (member instanceof PremiumMember) {
                    PremiumMember pm = (PremiumMember) member;
                    plan = "Premium";
                    price = String.format("%.2f", pm.getPremiumCharge());
                    discountAmount = String.format("%.2f", pm.getDiscountAmount());
                    netAmountPaid = String.format("%.2f", pm.getPaidAmount());
                    if (!pm.getRemovalReason().isEmpty()) {
                        writer.write("Removal Reason: " + pm.getRemovalReason() + "\n");
                    }
                }

                writer.write(String.format("%-5d %-15s %-15s %-15s %-25s %-20s %-10s %-10s %-10s %15d %-10s %-15s %-15s %-15s\n",
                    member.getId(),
                    truncate(member.getName(), 15),
                    truncate(member.getLocation(), 15),
                    truncate(member.getPhone(), 15),
                    truncate(member.getEmail(), 25),
                    truncate(member.getMembershipStart(), 20),
                    truncate(plan, 10),
                    truncate(price, 10),
                    truncate(attendance, 10),
                    member.getLoyaltyPoints(),
                    member.getActiveStatus() ? "Active" : "Inactive",
                    member.getIsFullPayment() ? "Yes" : "No",
                    truncate(discountAmount, 15),
                    truncate(netAmountPaid, 15)));
            }
            JOptionPane.showMessageDialog(fr1, "Members saved successfully to " + file.getName(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(fr1, "Error saving file: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String truncate(String str, int length) {
        if (str == null) return "";
        return str.length() > length ? str.substring(0, length) : str;
    }

    public void addMember() {
        fr2 = new JFrame("Add Member");
        fr2.setSize(1400, 900);
        fr2.setLayout(null);
        fr2.setResizable(false);
        fr2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr2.getContentPane().setBackground(new Color(255, 255, 255));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1400, 80);
        headerPanel.setBackground(new Color(0, 128, 128));
        headerPanel.setLayout(null);
        fr2.add(headerPanel);

        JLabel h1 = new JLabel("GymFreak");
        h1.setBounds(580, 15, 240, 50);
        h1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        h1.setForeground(new Color(0, 204, 204));
        headerPanel.add(h1);

        JLabel h2 = new JLabel("Choose Membership Type");
        h2.setBounds(550, 100, 300, 30);
        h2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        h2.setForeground(new Color(0, 204, 204));
        fr2.add(h2);

        // Buttons
        JButton addRegularmember = createStyledButton("Add Regular Member");
        addRegularmember.setBounds(200, 200, 300, 50);
        addRegularmember.setFont(new Font("Segoe UI", Font.BOLD, 16));
        addRegularmember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr2.dispose();
                addRegularMember();
            }
        });
        fr2.add(addRegularmember);

        JButton addPremiummember = createStyledButton("Add Premium Member");
        addPremiummember.setBounds(550, 200, 300, 50);
        addPremiummember.setFont(new Font("Segoe UI", Font.BOLD, 16));
        addPremiummember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr2.dispose();
                addPremiumMember();
            }
        });
        fr2.add(addPremiummember);

        JButton back = createStyledButton("Home");
        back.setBounds(900, 200, 300, 50);
        back.setFont(new Font("Segoe UI", Font.BOLD, 16));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr2.dispose();
                new GymGUI();
            }
        });
        fr2.add(back);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(50, 100, 1300, 750);
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        mainPanel.setLayout(null);
        fr2.add(mainPanel);

        fr2.setVisible(true);
    }

    public void addRegularMember() {
        fr3 = new JFrame("Add Regular Member");
        fr3.setSize(1400, 900);
        fr3.setLayout(null);
        fr3.setResizable(false);
        fr3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr3.getContentPane().setBackground(new Color(255, 255, 255));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1400, 80);
        headerPanel.setBackground(new Color(0, 128, 128));
        headerPanel.setLayout(null);
        fr3.add(headerPanel);

        JLabel h1 = new JLabel("GymFreak");
        h1.setBounds(580, 15, 240, 50);
        h1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        h1.setForeground(new Color(0, 204, 204));
        headerPanel.add(h1);

        JLabel h2 = new JLabel("Add Regular Member");
        h2.setBounds(550, 100, 300, 30);
        h2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        h2.setForeground(new Color(0, 204, 204));
        fr3.add(h2);

        // Plan Prices
        JLabel l1 = new JLabel("Regular Plans: Basic (6500/-), Standard (12500/-), Deluxe (18500/-)");
        l1.setBounds(200, 140, 800, 30);
        l1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        l1.setForeground(new Color(0, 51, 102));
        fr3.add(l1);

        JLabel l2 = new JLabel("Premium Plan: 50000/-");
        l2.setBounds(900, 140, 300, 30);
        l2.setFont(new Font("Segoe UI", Font.BOLD, 20));
        l2.setForeground(new Color(0, 51, 102));
        fr3.add(l2);

        // Input Fields - Row 1
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(100, 200, 100, 30);
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr3.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(200, 200, 200, 30);
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        idField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr3.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(450, 200, 100, 30);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr3.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(550, 200, 200, 30);
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        nameField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr3.add(nameField);

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(800, 200, 150, 30);
        locationLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr3.add(locationLabel);

        JTextField locationField = new JTextField();
        locationField.setBounds(950, 200, 200, 30);
        locationField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        locationField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr3.add(locationField);

        // Input Fields - Row 2
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(100, 270, 100, 30);
        phoneLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr3.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(200, 270, 200, 30);
        phoneField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        phoneField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr3.add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(450, 270, 100, 30);
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr3.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(550, 270, 200, 30);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        emailField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr3.add(emailField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(800, 270, 150, 30);
        genderLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr3.add(genderLabel);

        JRadioButton male = new JRadioButton("Male");
        male.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        male.setBackground(new Color(255, 255, 255));
        male.setBounds(950, 270, 80, 30);
        fr3.add(male);

        JRadioButton female = new JRadioButton("Female");
        female.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        female.setBackground(new Color(255, 255, 255));
        female.setBounds(950, 300, 100, 30);
        fr3.add(female);

        JRadioButton other = new JRadioButton("Other");
        other.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        other.setBackground(new Color(255, 255, 255));
        other.setBounds(950, 330, 90, 30);
        fr3.add(other);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        // Input Fields - Row 3
        JLabel dobLabel = new JLabel("DOB:");
        dobLabel.setBounds(100, 380, 100, 30);
        dobLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr3.add(dobLabel);

        JComboBox<String> dobDay = new JComboBox<>(generateNumbers(1, 31));
        dobDay.setBounds(200, 380, 60, 30);
        dobDay.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fr3.add(dobDay);

        JComboBox<String> dobMonth = new JComboBox<>(getMonths());
        dobMonth.setBounds(280, 380, 100, 30);
        dobMonth.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fr3.add(dobMonth);

        JComboBox<String> dobYear = new JComboBox<>(generateYears(1950, 2025));
        dobYear.setBounds(400, 380, 100, 30);
        dobYear.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fr3.add(dobYear);

        JLabel mspLabel = new JLabel("Membership Start:");
        mspLabel.setBounds(550, 380, 200, 30);
        mspLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr3.add(mspLabel);

        JComboBox<String> mspDay = new JComboBox<>(generateNumbers(1, 31));
        mspDay.setBounds(750, 380, 60, 30);
        mspDay.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fr3.add(mspDay);

        JComboBox<String> mspMonth = new JComboBox<>(getMonths());
        mspMonth.setBounds(830, 380, 100, 30);
        mspMonth.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fr3.add(mspMonth);

        JComboBox<String> mspYear = new JComboBox<>(generateYears(2023, 2025));
        mspYear.setBounds(950, 380, 100, 30);
        mspYear.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fr3.add(mspYear);

        // Input Fields - Row 4
        JLabel referralLabel = new JLabel("Referral:");
        referralLabel.setBounds(100, 450, 100, 30);
        referralLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr3.add(referralLabel);

        JTextField referralField = new JTextField();
        referralField.setBounds(200, 450, 200, 30);
        referralField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        referralField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr3.add(referralField);

        JLabel removalReasonLabel = new JLabel("Removal Reason:");
        removalReasonLabel.setBounds(450, 450, 200, 30);
        removalReasonLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JTextArea removalReasonArea = new JTextArea();
        removalReasonArea.setBounds(650, 450, 200, 60);
        removalReasonArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        removalReasonArea.setBorder(new LineBorder(new Color(0, 204, 204), 1));

        // Input Fields - Row 5
        JLabel planLabel = new JLabel("Plan:");
        planLabel.setBounds(100, 520, 100, 30);
        planLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr3.add(planLabel);

        JComboBox<String> planField = new JComboBox<>(new String[]{"basic", "standard", "deluxe"});
        planField.setBounds(200, 520, 200, 30);
        planField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fr3.add(planField);
//dhbsjk1234
        JCheckBox fullPaymentCheck = new JCheckBox("Paid in Full");
        fullPaymentCheck.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fullPaymentCheck.setBackground(new Color(255, 255, 255));
        fullPaymentCheck.setBounds(450, 520, 200, 30);

        // Buttons
        JButton clearButton = createStyledButton("Clear");
        clearButton.setBounds(355, 650, 200, 40);
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idField.setText("");
                nameField.setText("");
                locationField.setText("");
                phoneField.setText("");
                emailField.setText("");
                referralField.setText("");
                removalReasonArea.setText("");
                dobDay.setSelectedIndex(0);
                dobMonth.setSelectedIndex(0);
                dobYear.setSelectedIndex(0);
                mspDay.setSelectedIndex(0);
                mspMonth.setSelectedIndex(0);
                mspYear.setSelectedIndex(0);
                planField.setSelectedIndex(0);
                fullPaymentCheck.setSelected(false);
                genderGroup.clearSelection();
            }
        });
        fr3.add(clearButton);

        JButton add = createStyledButton("Add Regular Member");
        add.setBounds(575, 650, 200, 40);
        add.setFont(new Font("Segoe UI", Font.BOLD, 16));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = idField.getText().trim();
                    String name = nameField.getText().trim();
                    String location = locationField.getText().trim();
                    String phone = phoneField.getText().trim();
                    String email = emailField.getText().trim();
                    String referral = referralField.getText().trim();
                    String removalReason = removalReasonArea.getText().trim();
                    String plan = (String) planField.getSelectedItem();
                    boolean isFullPayment = fullPaymentCheck.isSelected();

                    if (idText.isEmpty()) {
                        throw new IllegalArgumentException("ID cannot be empty.");
                    }
                    int id;
                    try {
                        id = Integer.parseInt(idText);
                        if (id <= 0) {
                            throw new IllegalArgumentException("ID must be a positive number.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("ID must be a numeric value.");
                    }

                    for (GymMember member : list) {
                        if (member.getId() == id) {
                            throw new IllegalArgumentException("Member ID already exists.");
                        }//1234
                    }

                    if (name.isEmpty() || !name.matches("[a-zA-Z\\s]+")) {
                        throw new IllegalArgumentException("Name must contain letters only.");
                    } //this is my comment
                    if (phone.isEmpty() || !phone.matches("\\d{10}")) {
                        throw new IllegalArgumentException("Phone must be a 10-digit number.");
                    }
                    if (location.isEmpty()) {
                        throw new IllegalArgumentException("Location cannot be empty.");
                    }
                    if (email.isEmpty() || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                        throw new IllegalArgumentException("Invalid email format.");
                    }
                    if (referral.isEmpty()) {
                        throw new IllegalArgumentException("Referral source cannot be empty.");
                    }
                    if (plan == null) {
                        throw new IllegalArgumentException("Please select a plan.");
                    }

                    String gender = male.isSelected() ? "Male" : female.isSelected() ? "Female" : other.isSelected() ? "Other" : "";
                    if (gender.isEmpty()) {
                        throw new IllegalArgumentException("Please select a gender.");
                    }

                    if (dobDay.getSelectedItem() == null || dobMonth.getSelectedItem() == null || dobYear.getSelectedItem() == null) {
                        throw new IllegalArgumentException("Please select a valid date of birth.");
                    }
                    String dob = dobDay.getSelectedItem() + " " + dobMonth.getSelectedItem() + " " + dobYear.getSelectedItem();

                    if (mspDay.getSelectedItem() == null || mspMonth.getSelectedItem() == null || mspYear.getSelectedItem() == null) {
                        throw new IllegalArgumentException("Please select a valid membership start date.");
                    }
                    String msp = mspDay.getSelectedItem() + " " + mspMonth.getSelectedItem() + " " + mspYear.getSelectedItem();

                    double price;
                    switch (plan.toLowerCase()) {
                        case "basic":
                            price = 6500;
                            break;
                        case "standard":
                            price = 12500;
                            break;
                        case "deluxe":
                            price = 18500;
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid plan selected.");
                    }

                    RegularMember rm = new RegularMember(id, name, location, phone, email, gender, dob, msp, referral, plan, price, isFullPayment);
                    rm.makePayment(isFullPayment ? price : 0);
                    list.add(rm);

                    JOptionPane.showMessageDialog(fr3, "Regular Member added successfully! Price: " + price);
                    clearButton.doClick();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(fr3, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(fr3, "Unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fr3.add(add);

        JButton back = createStyledButton("Home");
        back.setBounds(795, 650, 200, 40);
        back.setFont(new Font("Segoe UI", Font.BOLD, 16));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr3.dispose();
                new GymGUI();
            }
        });
        fr3.add(back);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(50, 100, 1300, 750);
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        mainPanel.setLayout(null);
        fr3.add(mainPanel);

        fr3.setVisible(true);
    }

    public void addPremiumMember() {
        fr4 = new JFrame("Add Premium Member");
        fr4.setSize(1400, 900);
        fr4.setLayout(null);
        fr4.setResizable(false);
        fr4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr4.getContentPane().setBackground(new Color(255, 255, 255));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1400, 80);
        headerPanel.setBackground(new Color(0, 128, 128));
        headerPanel.setLayout(null);
        fr4.add(headerPanel);

        JLabel h1 = new JLabel("GymFreak");
        h1.setBounds(580, 15, 240, 50);
        h1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        h1.setForeground(new Color(0, 204, 204));
        headerPanel.add(h1);

        JLabel h2 = new JLabel("Add Premium Member");
        h2.setBounds(550, 100, 300, 30);
        h2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        h2.setForeground(new Color(0, 204, 204));
        fr4.add(h2);

        // Plan Prices
        JLabel l1 = new JLabel("Regular Plans: Basic (6500/-), Standard (12500/-), Deluxe (18500/-)");
        l1.setBounds(200, 140, 800, 30);
        l1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        l1.setForeground(new Color(0, 51, 102));
        fr4.add(l1);

        JLabel l2 = new JLabel("Premium Plan: 50000/-");
        l2.setBounds(900, 140, 300, 30);
        l2.setFont(new Font("Segoe UI", Font.BOLD, 20));
        l2.setForeground(new Color(0, 51, 102));
        fr4.add(l2);

        // Input Fields - Row 1
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(100, 200, 100, 30);
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr4.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(200, 200, 200, 30);
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        idField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr4.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(450, 200, 100, 30);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr4.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(550, 200, 200, 30);
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        nameField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr4.add(nameField);

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(800, 200, 150, 30);
        locationLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr4.add(locationLabel);

        JTextField locationField = new JTextField();
        locationField.setBounds(950, 200, 200, 30);
        locationField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        locationField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr4.add(locationField);

        // Input Fields - Row 2
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(100, 270, 100, 30);
        phoneLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr4.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(200, 270, 200, 30);
        phoneField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        phoneField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr4.add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(450, 270, 100, 30);
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr4.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(550, 270, 200, 30);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        emailField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr4.add(emailField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(800, 270, 150, 30);
        genderLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr4.add(genderLabel);

        JRadioButton male = new JRadioButton("Male");
        male.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        male.setBackground(new Color(255, 255, 255));
        male.setBounds(950, 270, 80, 30);
        fr4.add(male);

        JRadioButton female = new JRadioButton("Female");
        female.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        female.setBackground(new Color(255, 255, 255));
        female.setBounds(950, 300, 100, 30);
        fr4.add(female);

        JRadioButton other = new JRadioButton("Other");
        other.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        other.setBackground(new Color(255, 255, 255));
        other.setBounds(950, 330, 90, 30);
        fr4.add(other);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        // Input Fields - Row 3
        JLabel dobLabel = new JLabel("DOB:");
        dobLabel.setBounds(100, 380, 100, 30);
        dobLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr4.add(dobLabel);

        JComboBox<String> dobDay = new JComboBox<>(generateNumbers(1, 31));
        dobDay.setBounds(200, 380, 60, 30);
        dobDay.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fr4.add(dobDay);

        JComboBox<String> dobMonth = new JComboBox<>(getMonths());
        dobMonth.setBounds(280, 380, 100, 30);
        dobMonth.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fr4.add(dobMonth);

        JComboBox<String> dobYear = new JComboBox<>(generateYears(1950, 2025));
        dobYear.setBounds(400, 380, 100, 30);
        dobYear.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fr4.add(dobYear);

        JLabel mspLabel = new JLabel("Membership Start:");
        mspLabel.setBounds(550, 380, 200, 30);
        mspLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr4.add(mspLabel);

        JComboBox<String> mspDay = new JComboBox<>(generateNumbers(1, 31));
        mspDay.setBounds(750, 380, 60, 30);
        mspDay.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fr4.add(mspDay);

        JComboBox<String> mspMonth = new JComboBox<>(getMonths());
        mspMonth.setBounds(830, 380, 100, 30);
        mspMonth.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fr4.add(mspMonth);

        JComboBox<String> mspYear = new JComboBox<>(generateYears(2023, 2025));
        mspYear.setBounds(950, 380, 100, 30);
        mspYear.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fr4.add(mspYear);

        // Input Fields - Row 4
        JLabel trainerLabel = new JLabel("Personal Trainer:");
        trainerLabel.setBounds(100, 450, 200, 30);
        trainerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr4.add(trainerLabel);

        JTextField trainerField = new JTextField();
        trainerField.setBounds(300, 450, 200, 30);
        trainerField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        trainerField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr4.add(trainerField);

        JLabel discountLabel = new JLabel("Discount:");
        discountLabel.setBounds(550, 450, 150, 30);
        discountLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JTextField discountField = new JTextField("0");
        discountField.setBounds(700, 450, 200, 30);
        discountField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        discountField.setBorder(new LineBorder(new Color(0, 204, 204), 1));

        // Paid in Full Checkbox
        JCheckBox paidInFullCheck = new JCheckBox("Paid in Full");
        paidInFullCheck.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        paidInFullCheck.setBackground(new Color(255, 255, 255));
        paidInFullCheck.setBounds(950, 450, 150, 30);
        fr4.add(paidInFullCheck);

        // Buttons
        JButton clearButton = createStyledButton("Clear");
        clearButton.setBounds(355, 650, 200, 40);
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idField.setText("");
                nameField.setText("");
                locationField.setText("");
                phoneField.setText("");
                emailField.setText("");
                trainerField.setText("");
                discountField.setText("0");
                paidInFullCheck.setSelected(false);
                dobDay.setSelectedIndex(0);
                dobMonth.setSelectedIndex(0);
                dobYear.setSelectedIndex(0);
                mspDay.setSelectedIndex(0);
                mspMonth.setSelectedIndex(0);
                mspYear.setSelectedIndex(0);
                genderGroup.clearSelection();
            }
        });
        fr4.add(clearButton);

        JButton add = createStyledButton("Add Premium Member");
        add.setBounds(575, 650, 200, 40);
        add.setFont(new Font("Segoe UI", Font.BOLD, 16));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = idField.getText().trim();
                    String name = nameField.getText().trim();
                    String location = locationField.getText().trim();
                    String phone = phoneField.getText().trim();
                    String email = emailField.getText().trim();
                    String trainer = trainerField.getText().trim();
                    String discountText = discountField.getText().trim();
                    boolean isFullPayment = paidInFullCheck.isSelected();

                    if (idText.isEmpty()) {
                        throw new IllegalArgumentException("ID cannot be empty.");
                    }
                    int id;
                    try {
                        id = Integer.parseInt(idText);
                        if (id <= 0) {
                            throw new IllegalArgumentException("ID must be a positive number.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("ID must be a numeric value.");
                    }

                    for (GymMember member : list) {
                        if (member.getId() == id) {
                            throw new IllegalArgumentException("Member ID already exists.");
                        }
                    }

                    if (name.isEmpty() || !name.matches("[a-zA-Z\\s]+")) {
                        throw new IllegalArgumentException("Name must contain letters only.");
                    }
                    if (phone.isEmpty() || !phone.matches("\\d{10}")) {
                        throw new IllegalArgumentException("Phone must be a 10-digit number.");
                    }
                    if (location.isEmpty()) {
                        throw new IllegalArgumentException("Location cannot be empty.");
                    }
                    if (email.isEmpty() || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                        throw new IllegalArgumentException("Invalid email format.");
                    }
                    if (trainer.isEmpty()) {
                        throw new IllegalArgumentException("Personal trainer name cannot be empty.");
                    }
                    double discountAmount;
                    try {
                        discountAmount = discountText.isEmpty() ? 0 : Double.parseDouble(discountText);
                        if (discountAmount < 0 || discountAmount > 50000) {
                            throw new IllegalArgumentException("Discount must be between 0 and 50000.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Discount must be a valid number.");
                    }

                    String gender = male.isSelected() ? "Male" : female.isSelected() ? "Female" : other.isSelected() ? "Other" : "";
                    if (gender.isEmpty()) {
                        throw new IllegalArgumentException("Please select a gender.");
                    }

                    if (dobDay.getSelectedItem() == null || dobMonth.getSelectedItem() == null || dobYear.getSelectedItem() == null) {
                        throw new IllegalArgumentException("Please select a valid date of birth.");
                    }
                    String dob = dobDay.getSelectedItem() + " " + dobMonth.getSelectedItem() + " " + dobYear.getSelectedItem();

                    if (mspDay.getSelectedItem() == null || mspMonth.getSelectedItem() == null || mspYear.getSelectedItem() == null) {
                        throw new IllegalArgumentException("Please select a valid membership start date.");
                    }
                    String msp = mspDay.getSelectedItem() + " " + mspMonth.getSelectedItem() + " " + mspYear.getSelectedItem();

                    PremiumMember pm = new PremiumMember(id, name, location, phone, email, gender, dob, msp, trainer, discountAmount, isFullPayment);
                    list.add(pm);

                    double amountDue = 50000 - discountAmount;
                    JOptionPane.showMessageDialog(fr4, "Premium Member added successfully! Amount Due: " + amountDue + 
                        (isFullPayment ? " (Paid in Full)" : ""));
                    clearButton.doClick();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(fr4, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(fr4, "Unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fr4.add(add);

        JButton back = createStyledButton("Home");
        back.setBounds(795, 650, 200, 40);
        back.setFont(new Font("Segoe UI", Font.BOLD, 16));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr4.dispose();
                new GymGUI();
            }
        });
        fr4.add(back);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(50, 100, 1300, 750);
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        mainPanel.setLayout(null);
        fr4.add(mainPanel);

        fr4.setVisible(true);
    }

    public void activation() {
        fr5 = new JFrame("Activation & Deactivation");
        fr5.setSize(1400, 900);
        fr5.setLayout(null);
        fr5.setResizable(false);
        fr5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr5.getContentPane().setBackground(new Color(255, 255, 255));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1400, 80);
        headerPanel.setBackground(new Color(0, 128, 128));
        headerPanel.setLayout(null);
        fr5.add(headerPanel);

        JLabel h1 = new JLabel("GymFreak");
        h1.setBounds(580, 15, 240, 50);
        h1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        h1.setForeground(new Color(0, 204, 204));
        headerPanel.add(h1);

        JLabel h2 = new JLabel("Activation & Deactivation");
        h2.setBounds(550, 100, 300, 30);
        h2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        h2.setForeground(new Color(0, 204, 204));
        fr5.add(h2);

        // Input Fields
        JLabel idLabel = new JLabel("Enter Member ID:");
        idLabel.setBounds(50, 190, 150, 30);
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr5.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(200, 190, 200, 30);
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        idField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr5.add(idField);

        // Buttons
        JButton activateButton = createStyledButton("Activate Membership");
        activateButton.setBounds(200, 240, 200, 40);
        activateButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        activateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = idField.getText().trim();
                    if (idText.isEmpty()) {
                        throw new IllegalArgumentException("ID cannot be empty.");
                    }
                    int id;
                    try {
                        id = Integer.parseInt(idText);
                        if (id <= 0) {
                            throw new IllegalArgumentException("ID must be a positive number.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("ID must be a numeric value.");
                    }

                    GymMember targetMember = null;
                    for (GymMember member : list) {
                        if (member.getId() == id) {
                            targetMember = member;
                            break;
                        }
                    }
                    if (targetMember == null) {
                        throw new IllegalArgumentException("Member ID not found.");
                    }

                    if (targetMember.getActiveStatus()) {
                        throw new IllegalStateException("Membership is already active.");
                    }

                    targetMember.activateMembership();
                    JOptionPane.showMessageDialog(fr5, "Membership activated successfully!");
                    idField.setText("");
                } catch (IllegalArgumentException | IllegalStateException ex) {
                    JOptionPane.showMessageDialog(fr5, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(fr5, "Unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fr5.add(activateButton);

        JButton deactivateButton = createStyledButton("Deactivate Membership");
        deactivateButton.setBounds(450, 240, 200, 40);
        deactivateButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        deactivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = idField.getText().trim();
                    if (idText.isEmpty()) {
                        throw new IllegalArgumentException("ID cannot be empty.");
                    }
                    int id;
                    try {
                        id = Integer.parseInt(idText);
                        if (id <= 0) {
                            throw new IllegalArgumentException("ID must be a positive number.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("ID must be a numeric value.");
                    }

                    GymMember targetMember = null;
                    for (GymMember member : list) {
                        if (member.getId() == id) {
                            targetMember = member;
                            break;
                        }
                    }
                    if (targetMember == null) {
                        throw new IllegalArgumentException("Member ID not found.");
                    }

                    if (!targetMember.getActiveStatus()) {
                        throw new IllegalStateException("Membership is already deactivated.");
                    }

                    targetMember.deactivateMembership();
                    JOptionPane.showMessageDialog(fr5, "Membership deactivated successfully!");
                    idField.setText("");
                } catch (IllegalArgumentException | IllegalStateException ex) {
                    JOptionPane.showMessageDialog(fr5, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(fr5, "Unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fr5.add(deactivateButton);

        JButton markAttendanceButton = createStyledButton("Mark Attendance");
        markAttendanceButton.setBounds(700, 240, 200, 40);
        markAttendanceButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        markAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = idField.getText().trim();
                    if (idText.isEmpty()) {
                        throw new IllegalArgumentException("ID cannot be empty.");
                    }
                    int id;
                    try {
                        id = Integer.parseInt(idText);
                        if (id <= 0) {
                            throw new IllegalArgumentException("ID must be a positive number.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("ID must be a numeric value.");
                    }

                    GymMember targetMember = null;
                    for (GymMember member : list) {
                        if (member.getId() == id) {
                            targetMember = member;
                            break;
                        }
                    }
                    if (targetMember == null) {
                        throw new IllegalArgumentException("Member ID not found.");
                    }

                    targetMember.markAttendance();
                    String memberType = (targetMember instanceof RegularMember) ? "Regular" : "Premium";
                    JOptionPane.showMessageDialog(fr5, "Attendance marked for " + memberType + " Member! Loyalty Points: " + targetMember.getLoyaltyPoints());
                    idField.setText("");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(fr5, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(fr5, "Cannot mark attendance: Membership is not activated.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(fr5, "Unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fr5.add(markAttendanceButton);

        JButton back = createStyledButton("Home");
        back.setBounds(950, 240, 200, 40);
        back.setFont(new Font("Segoe UI", Font.BOLD, 16));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr5.dispose();
                new GymGUI();
            }
        });
        fr5.add(back);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(50, 100, 1300, 750);
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        mainPanel.setLayout(null);
        fr5.add(mainPanel);

        fr5.setVisible(true);
    }

    public void addRevertmember() {
        fr6 = new JFrame("Revert Member");
        fr6.setSize(1400, 900);
        fr6.setLayout(null);
        fr6.setResizable(false);
        fr6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr6.getContentPane().setBackground(new Color(255, 255, 255));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1400, 80);
        headerPanel.setBackground(new Color(0, 128, 128));
        headerPanel.setLayout(null);
        fr6.add(headerPanel);

        JLabel h1 = new JLabel("GymFreak");
        h1.setBounds(580, 15, 240, 50);
        h1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        h1.setForeground(new Color(0, 204, 204));
        headerPanel.add(h1);

        JLabel h2 = new JLabel("Revert Member");
        h2.setBounds(550, 100, 300, 30);
        h2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        h2.setForeground(new Color(0, 204, 204));
        fr6.add(h2);

        // Input Fields
        JLabel idLabel = new JLabel("Enter Member ID:");
        idLabel.setBounds(50, 190, 150, 30);
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr6.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(200, 190, 300, 30);
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        idField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr6.add(idField);

        JLabel reasonLabel = new JLabel("Removal Reason:");
        reasonLabel.setBounds(50, 240, 250, 30);
        reasonLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr6.add(reasonLabel);

        JTextArea reasonArea = new JTextArea();
        reasonArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        reasonArea.setLineWrap(true);
        reasonArea.setWrapStyleWord(true);
        reasonArea.setBorder(new LineBorder(new Color(0, 204, 204), 1));

        JScrollPane reasonScroll = new JScrollPane(reasonArea);
        reasonScroll.setBounds(300, 240, 300, 60);
        reasonScroll.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr6.add(reasonScroll);

        JLabel reasonNote = new JLabel("Required for all members");
        reasonNote.setBounds(300, 300, 300, 20);
        reasonNote.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        reasonNote.setForeground(new Color(0, 51, 102));
        fr6.add(reasonNote);

        // Buttons
        JButton revertButton = createStyledButton("Revert Member");
        revertButton.setBounds(200, 330, 200, 40);
        revertButton.setFont(new Font("Arial", Font.BOLD, 16));
        revertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = idField.getText().trim();
                    String removalReason = reasonArea.getText().trim();

                    if (idText.isEmpty()) {
                        throw new IllegalArgumentException("ID cannot be empty.");
                    }
                    int id;
                    try {
                        id = Integer.parseInt(idText);
                        if (id <= 0) {
                            throw new IllegalArgumentException("ID must be a positive number.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("ID must be a numeric value.");
                    }

                    if (removalReason.isEmpty()) {
                        throw new IllegalArgumentException("Removal reason is required for all members.");
                    }

                    GymMember targetMember = null;
                    for (GymMember member : list) {
                        if (member.getId() == id) {
                            targetMember = member;
                            break;
                        }
                    }
                    if (targetMember == null) {
                        throw new IllegalArgumentException("Member ID not found.");
                    }

                    if (targetMember instanceof RegularMember) {
                        ((RegularMember) targetMember).revertRegularMember(removalReason);
                        JOptionPane.showMessageDialog(fr6, "Regular Member reverted successfully! Plan set to basic, reason: " + removalReason, 
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else if (targetMember instanceof PremiumMember) {
                        ((PremiumMember) targetMember).revertPremiumMember(removalReason);
                        JOptionPane.showMessageDialog(fr6, "Premium Member reverted successfully! Trainer and payments cleared, reason: " + removalReason, 
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    }

                    idField.setText("");
                    reasonArea.setText("");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(fr6, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(fr6, "Unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fr6.add(revertButton);

        JButton back = createStyledButton("Home");
        back.setBounds(450, 330, 200, 40);
        back.setFont(new Font("Segoe UI", Font.BOLD, 16));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr6.dispose();
                new GymGUI();
            }
        });
        fr6.add(back);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(50, 100, 1300, 750);
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        mainPanel.setLayout(null);
        fr6.add(mainPanel);

        fr6.setVisible(true);
    }

    public void display() {
        fr9 = new JFrame("Display Members");
        fr9.setSize(1400, 900);
        fr9.setLayout(null);
        fr9.setResizable(false);
        fr9.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr9.getContentPane().setBackground(new Color(255, 255, 255));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1400, 80);
        headerPanel.setBackground(new Color(0, 128, 128));
        headerPanel.setLayout(null);
        fr9.add(headerPanel);

        JLabel h1 = new JLabel("GymFreak");
        h1.setBounds(580, 15, 240, 50);
        h1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        h1.setForeground(new Color(0, 204, 204));
        headerPanel.add(h1);

        JLabel h2 = new JLabel("Display Members");
        h2.setBounds(550, 100, 300, 30);
        h2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        h2.setForeground(new Color(0, 204, 204));
        fr9.add(h2);

        // Display Area
        JTextArea displayArea = new JTextArea();
        displayArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        displayArea.setEditable(false);
        displayArea.setBorder(new LineBorder(new Color(0, 204, 204), 1));

        try {
            StringBuilder sb = new StringBuilder();
            if (list == null || list.isEmpty()) {
                sb.append("No members to display.\n");
            } else {
                for (GymMember member : list) {
                    if (member == null) continue;
                    sb.append("ID: ").append(member.getId()).append("\n");
                    sb.append("Name: ").append(member.getName() != null ? member.getName() : "N/A").append("\n");
                    sb.append("Location: ").append(member.getLocation() != null ? member.getLocation() : "N/A").append("\n");
                    sb.append("Phone: ").append(member.getPhone() != null ? member.getPhone() : "N/A").append("\n");
                    sb.append("Email: ").append(member.getEmail() != null ? member.getEmail() : "N/A").append("\n");
                    sb.append("Gender: ").append(member.getGender() != null ? member.getGender() : "N/A").append("\n");
                    sb.append("DOB: ").append(member.getDob() != null ? member.getDob() : "N/A").append("\n");
                    sb.append("Membership Start: ").append(member.getMembershipStart() != null ? member.getMembershipStart() : "N/A").append("\n");
                    sb.append("Active Status: ").append(member.getActiveStatus() ? "Active" : "Inactive").append("\n");
                    sb.append("Loyalty Points: ").append(member.getLoyaltyPoints()).append("\n");
                    if (member instanceof RegularMember) {
                        RegularMember rm = (RegularMember) member;
                        sb.append("Type: Regular\n");
                        sb.append("Plan: ").append(rm.getPlan() != null ? rm.getPlan() : "N/A").append("\n");
                        sb.append("Price: ").append(rm.getPrice()).append("\n");
                        sb.append("Referral: ").append(rm.getReferral() != null ? rm.getReferral() : "N/A").append("\n");
                        sb.append("Full Payment: ").append(rm.getIsFullPayment() ? "Yes" : "No").append("\n");
                        sb.append("Eligible for Upgrade: ").append(rm.getIsEligibleForUpgrade() ? "Yes" : "No").append("\n");
                        if (!rm.getRemovalReason().isEmpty()) {
                            sb.append("Removal Reason: ").append(rm.getRemovalReason()).append("\n");
                        }
                    } else if (member instanceof PremiumMember) {
                        PremiumMember pm = (PremiumMember) member;
                        sb.append("Type: Premium\n");
                        sb.append("Personal Trainer: ").append(pm.getPersonalTrainer() != null ? pm.getPersonalTrainer() : "N/A").append("\n");
                        sb.append("Discount Amount: ").append(pm.getDiscountAmount()).append("\n");
                        sb.append("Paid Amount: ").append(pm.getPaidAmount()).append("\n");
                        sb.append("Full Payment: ").append(pm.getIsFullPayment() ? "Yes" : "No").append("\n");
                        if (!pm.getRemovalReason().isEmpty()) {
                            sb.append("Removal Reason: ").append(pm.getRemovalReason()).append("\n");
                        }
                    }
                    sb.append("----------------------------------------\n");
                }
            }
            displayArea.setText(sb.toString());
        } catch (Exception ex) {
            displayArea.setText("Error displaying members: " + ex.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(50, 190, 1250, 600);
        scrollPane.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr9.add(scrollPane);

        // Button
        JButton back = createStyledButton("Home");
        back.setBounds(600, 800, 200, 40);
        back.setFont(new Font("Segoe UI", Font.BOLD, 16));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr9.dispose();
                new GymGUI();
            }
        });
        fr9.add(back);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(50, 100, 1300, 750);
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        mainPanel.setLayout(null);
        fr9.add(mainPanel);

        fr9.setVisible(true);
    }

    public void upgradePlan() {
        fr7 = new JFrame("Upgrade Plan");
        fr7.setSize(1400, 900);
        fr7.setLayout(null);
        fr7.setResizable(false);
        fr7.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr7.getContentPane().setBackground(new Color(255, 255, 255));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1400, 80);
        headerPanel.setBackground(new Color(0, 128, 128));
        headerPanel.setLayout(null);
        fr7.add(headerPanel);

        JLabel h1 = new JLabel("GymFreak");
        h1.setBounds(580, 15, 240, 50);
        h1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        h1.setForeground(new Color(0, 204, 204));
        headerPanel.add(h1);

        JLabel h2 = new JLabel("Upgrade Plan");
        h2.setBounds(550, 100, 300, 30);
        h2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        h2.setForeground(new Color(0, 204, 204));
        fr7.add(h2);

        // Input Fields
        JLabel idLabel = new JLabel("Enter Member ID:");
        idLabel.setBounds(50, 190, 150, 30);
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr7.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(200, 190, 200, 30);
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        idField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        fr7.add(idField);

        JLabel planLabel = new JLabel("New Plan:");
        planLabel.setBounds(450, 190, 150, 30);
        planLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fr7.add(planLabel);

        JComboBox<String> planField = new JComboBox<>(new String[]{"basic", "standard", "deluxe"});
        planField.setBounds(600, 190, 200, 30);
        planField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fr7.add(planField);

        // Buttons
        JButton upgradeButton = createStyledButton("Upgrade Plan");
        upgradeButton.setBounds(200, 240, 200, 40);
        upgradeButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        upgradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = idField.getText().trim();
                    if (idText.isEmpty()) {
                        throw new IllegalArgumentException("ID cannot be empty.");
                    }
                    int id;
                    try {
                        id = Integer.parseInt(idText);
                        if (id <= 0) {
                            throw new IllegalArgumentException("ID must be a positive number.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("ID must be a numeric value.");
                    }

                    String newPlan = (String) planField.getSelectedItem();
                    if (newPlan == null) {
                        throw new IllegalArgumentException("Please select a new plan.");
                    }

                    GymMember targetMember = null;
                    for (GymMember member : list) {
                        if (member.getId() == id) {
                            targetMember = member;
                            break;
                        }
                    }
                    if (targetMember == null) {
                        throw new IllegalArgumentException("Member ID not found.");
                    }

                    if (!(targetMember instanceof RegularMember)) {
                        throw new IllegalArgumentException("Only Regular Members can upgrade plans.");
                    }

                    RegularMember rm = (RegularMember) targetMember;
                    rm.upgradePlan(newPlan);
                    double newPrice = rm.getPrice();
                    JOptionPane.showMessageDialog(fr7, "Plan upgraded to " + newPlan + " successfully! New Price: " + newPrice);
                    idField.setText("");
                    planField.setSelectedIndex(0);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(fr7, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(fr7, "Unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fr7.add(upgradeButton);

        JButton back = createStyledButton("Home");
        back.setBounds(450, 240, 200, 40);
        back.setFont(new Font("Segoe UI", Font.BOLD, 16));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr7.dispose();
                new GymGUI();
            }
        });
        fr7.add(back);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(50, 100, 1300, 750);
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        mainPanel.setLayout(null);
        fr7.add(mainPanel);

        fr7.setVisible(true);
    }

        public void processPayment() {
        fr8 = new JFrame("Process Payment");
        fr8.setSize(1400, 900);
        fr8.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr8.setResizable(false);
        fr8.setLayout(new BorderLayout());
        fr8.getContentPane().setBackground(new Color(255, 255, 255));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(1400, 80));
        headerPanel.setBackground(new Color(0, 128, 128));
        headerPanel.setLayout(null);

        JLabel h1 = new JLabel("GymFreak");
        h1.setBounds(580, 15, 240, 50);
        h1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        h1.setForeground(new Color(0, 204, 204));
        headerPanel.add(h1);

        fr8.add(headerPanel, BorderLayout.NORTH);

        // Main Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setLayout(null);

        JLabel h2 = new JLabel("Process Payment");
        h2.setBounds(550, 20, 300, 30);
        h2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        h2.setForeground(new Color(0, 204, 204));
        contentPanel.add(h2);

        // Input Fields
        JLabel idLabel = new JLabel("Enter Member ID:");
        idLabel.setBounds(50, 100, 150, 30);
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        contentPanel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(200, 100, 200, 30);
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        idField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        contentPanel.add(idField);

        JLabel amountLabel = new JLabel("Payment Amount:");
        amountLabel.setBounds(450, 100, 150, 30);
        amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        contentPanel.add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(600, 100, 200, 30);
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        amountField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        contentPanel.add(amountField);

        JLabel discountLabel = new JLabel("Discount Amount:");
        discountLabel.setBounds(850, 100, 150, 30);
        discountLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        contentPanel.add(discountLabel);

        JTextField discountField = new JTextField("0.00");
        discountField.setBounds(1000, 100, 200, 30);
        discountField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        discountField.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        discountField.setEditable(false);
        contentPanel.add(discountField);

        // Buttons
        JButton calcDiscountButton = createStyledButton("Calculate Discount");
        calcDiscountButton.setBounds(200, 150, 200, 40);
        calcDiscountButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        calcDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = idField.getText().trim();
                    if (idText.isEmpty()) {
                        throw new IllegalArgumentException("ID cannot be empty.");
                    }
                    int id;
                    try {
                        id = Integer.parseInt(idText);
                        if (id <= 0) {
                            throw new IllegalArgumentException("ID must be a positive number.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("ID must be a numeric value.");
                    }

                    GymMember targetMember = null;
                    for (GymMember member : list) {
                        if (member.getId() == id) {
                            targetMember = member;
                            break;
                        }
                    }
                    if (targetMember == null) {
                        throw new IllegalArgumentException("Member ID not found.");
                    }

                    if (!(targetMember instanceof PremiumMember)) {
                        throw new IllegalArgumentException("Discounts are only applicable for Premium Members.");
                    }

                    PremiumMember pm = (PremiumMember) targetMember;
                    int loyaltyPoints = pm.getLoyaltyPoints();
                    double discountPercentage = Math.min(loyaltyPoints / 10.0, 50.0); // 1% per 10 points, max 50%
                    double baseAmount = pm.getPremiumCharge(); // 50000
                    double discountAmount = (discountPercentage / 100.0) * baseAmount;
                    discountField.setText(String.format("%.2f", discountAmount));
                    JOptionPane.showMessageDialog(fr8, "Discount calculated: " + String.format("%.2f", discountAmount) + 
                        " based on " + loyaltyPoints + " loyalty points.", "Discount", JOptionPane.INFORMATION_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(fr8, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(fr8, "Unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPanel.add(calcDiscountButton);

        JButton payButton = createStyledButton("Process Payment");
        payButton.setBounds(450, 150, 200, 40);
        payButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = idField.getText().trim();
                    String amountText = amountField.getText().trim();
                    String discountText = discountField.getText().trim();

                    if (idText.isEmpty()) {
                        throw new IllegalArgumentException("ID cannot be empty.");
                    }
                    int id;
                    try {
                        id = Integer.parseInt(idText);
                        if (id <= 0) {
                            throw new IllegalArgumentException("ID must be a positive number.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("ID must be a numeric value.");
                    }

                    if (amountText.isEmpty()) {
                        throw new IllegalArgumentException("Payment amount cannot be empty.");
                    }
                    double amount;
                    try {
                        amount = Double.parseDouble(amountText);
                        if (amount <= 0) {
                            throw new IllegalArgumentException("Payment amount must be positive.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Payment amount must be a valid number.");
                    }

                    double discountAmount;
                    try {
                        discountAmount = discountText.isEmpty() ? 0 : Double.parseDouble(discountText);
                        if (discountAmount < 0) {
                            throw new IllegalArgumentException("Discount amount cannot be negative.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Discount amount must be a valid number.");
                    }

                    GymMember targetMember = null;
                    for (GymMember member : list) {
                        if (member.getId() == id) {
                            targetMember = member;
                            break;
                        }
                    }
                    if (targetMember == null) {
                        throw new IllegalArgumentException("Member ID not found.");
                    }

                    if (targetMember instanceof PremiumMember && discountAmount > 0) {
                        PremiumMember pm = (PremiumMember) targetMember;
                        pm.setDiscountAmount(discountAmount);
                    }

                    targetMember.makePayment(amount);
                    String memberType = (targetMember instanceof RegularMember) ? "Regular" : "Premium";
                    JOptionPane.showMessageDialog(fr8, "Payment of " + amount + " processed successfully for " + memberType + 
                        " Member!" + (discountAmount > 0 ? " Discount applied: " + discountAmount : ""));
                    idField.setText("");
                    amountField.setText("");
                    discountField.setText("0.00");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(fr8, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(fr8, "Cannot process payment: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(fr8, "Unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPanel.add(payButton);

        JButton payDueButton = createStyledButton("Pay Due Amount");
        payDueButton.setBounds(700, 150, 200, 40);
        payDueButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        payDueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = idField.getText().trim();
                    String discountText = discountField.getText().trim();

                    if (idText.isEmpty()) {
                        throw new IllegalArgumentException("ID cannot be empty.");
                    }
                    int id;
                    try {
                        id = Integer.parseInt(idText);
                        if (id <= 0) {
                            throw new IllegalArgumentException("ID must be a positive number.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("ID must be a numeric value.");
                    }

                    double discountAmount;
                    try {
                        discountAmount = discountText.isEmpty() ? 0 : Double.parseDouble(discountText);
                        if (discountAmount < 0) {
                            throw new IllegalArgumentException("Discount amount cannot be negative.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Discount amount must be a valid number.");
                    }

                    GymMember targetMember = null;
                    for (GymMember member : list) {
                        if (member.getId() == id) {
                            targetMember = member;
                            break;
                        }
                    }
                    if (targetMember == null) {
                        throw new IllegalArgumentException("Member ID not found.");
                    }

                    double dueAmount = 0;
                    String memberType = "";
                    if (targetMember instanceof RegularMember) {
                        RegularMember rm = (RegularMember) targetMember;
                        memberType = "Regular";
                        if (!rm.getIsFullPayment()) {
                            dueAmount = rm.getPrice();
                        }
                    } else if (targetMember instanceof PremiumMember) {
                        PremiumMember pm = (PremiumMember) targetMember;
                        memberType = "Premium";
                        dueAmount = pm.getPremiumCharge() - pm.getPaidAmount() - discountAmount;
                        if (discountAmount > 0) {
                            pm.setDiscountAmount(discountAmount);
                        }
                    }

                    if (dueAmount <= 0) {
                        throw new IllegalStateException("No payment due for this member.");
                    }

                    targetMember.makePayment(dueAmount);
                    JOptionPane.showMessageDialog(fr8, "Due amount of " + String.format("%.2f", dueAmount) + 
                        " paid successfully for " + memberType + " Member!" + 
                        (discountAmount > 0 ? " Discount applied: " + discountAmount : ""));
                    idField.setText("");
                    amountField.setText("");
                    discountField.setText("0.00");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(fr8, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(fr8, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(fr8, "Unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPanel.add(payDueButton);

        JButton back = createStyledButton("Home");
        back.setBounds(950, 150, 200, 40);
        back.setFont(new Font("Segoe UI", Font.BOLD, 16));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr8.dispose();
                new GymGUI();
            }
        });
        contentPanel.add(back);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(50, 70, 1300, 750);
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(new LineBorder(new Color(0, 204, 204), 1));
        mainPanel.setLayout(null);
        contentPanel.add(mainPanel);

        fr8.add(contentPanel, BorderLayout.CENTER);
        fr8.setVisible(true);
    }

    private String[] generateNumbers(int start, int end) {
        String[] numbers = new String[end - start + 1];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = String.valueOf(start + i);
        }
        return numbers;
    }

    private String[] generateYears(int start, int end) {
        String[] years = new String[end - start + 1];
        for (int i = 0; i < years.length; i++) {
            years[i] = String.valueOf(start + i);
        }
        return years;
    }

    private String[] getMonths() {
        return new String[]{"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GymGUI();
            }
        });
    }
}