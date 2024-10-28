/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_password_generator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ali Sultani
 */

public class View extends JFrame implements PasswordListener {

    final private JTextField passwordField;
    final private JButton generateButton, saveButton, historyButton, clearButton, closeButton;
    final private JSlider lengthSlider;
    final private JCheckBox includeNumbers, includeSymbols, includeLowercase, includeUppercase;
    final private JTable passwordTable;
    final private DefaultTableModel tableModel;
    final private JDialog historyDialog;

    public View() {
        // Frame setup
        this.setTitle("Password Generator");
        this.setSize(600, 400);
        this.setMinimumSize(new Dimension(600, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        // Password display field
        passwordField = new JTextField();
        passwordField.setEditable(false);
        passwordField.setBounds(10, 10, 560, 30);
        this.add(passwordField);

        // Generate and Copy buttons
        generateButton = new JButton("Generate");
        generateButton.setBounds(10, 50, 270, 30);
        this.add(generateButton);

        saveButton = new JButton("Save");
        saveButton.setBounds(300, 50, 270, 30);
        this.add(saveButton);

        // Password History button
        historyButton = new JButton("Saved Passwords");
        historyButton.setBounds(10, 90, 560, 30);
        this.add(historyButton);

        // Password Length Slider
        JLabel lengthLabel = new JLabel("Password Length");
        lengthLabel.setBounds(10, 130, 560, 20);
        this.add(lengthLabel);

        lengthSlider = new JSlider(1, 20, 8);
        lengthSlider.setMajorTickSpacing(2);
        lengthSlider.setPaintTicks(true);
        lengthSlider.setPaintLabels(true);
        lengthSlider.setBounds(10, 150, 560, 50);
        this.add(lengthSlider);

        // Configuration option checkboxes
        includeNumbers = new JCheckBox("Allow Numbers");
        includeSymbols = new JCheckBox("Allow Symbols");
        includeLowercase = new JCheckBox("Allow Lowercase");
        includeUppercase = new JCheckBox("Allow Uppercase");
        includeNumbers.setBounds(10, 210, 200, 30);
        includeSymbols.setBounds(220, 210, 200, 30);
        includeLowercase.setBounds(10, 250, 200, 30);
        includeUppercase.setBounds(220, 250, 200, 30);
        this.add(includeNumbers);
        this.add(includeSymbols);
        this.add(includeLowercase);
        this.add(includeUppercase);

        // Setup the saved passwords dialog
        historyDialog = new JDialog(this, "Saved Passwords", true);
        historyDialog.setSize(600, 400);
        historyDialog.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Saved Passwords"}, 0);
        passwordTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(passwordTable);
        historyDialog.add(scrollPane, BorderLayout.CENTER);

        clearButton = new JButton("Clear All");
        closeButton = new JButton("Close");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(clearButton);
        buttonPanel.add(closeButton);
        historyDialog.add(buttonPanel, BorderLayout.SOUTH);

        historyDialog.setLocationRelativeTo(this); // Center the dialog over the main window
        historyDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        historyDialog.setVisible(false);

        this.setVisible(true);
    }

    // Methods for adding action listeners
    public void addActionListener(ActionListener listener) {
        generateButton.addActionListener(listener);
        saveButton.addActionListener(listener);
        historyButton.addActionListener(listener);
        clearButton.addActionListener(listener);
        closeButton.addActionListener(listener);
    }

    // Display password in the password field
    public void setPasswordField(String password) {
        passwordField.setText(password);
    }

    // Get the text in the password field
    public String getPasswordField() {
        return passwordField.getText();
    }

    // Show a message to the user
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Show or hide the password history dialog
    public void showPasswordHistory(boolean show) {
        historyDialog.setVisible(show);
    }

    // Update password history table
    public void updatePasswordHistory(String[] passwords) {
        tableModel.setRowCount(0); // Clear existing rows
        for (String password : passwords) {
            tableModel.addRow(new Object[]{password});
        }
    }

    // Get configuration details
    public GeneratorConfig getConfig() {
        GeneratorConfig config = new GeneratorConfig();
        config.setIncludeNumbers(includeNumbers.isSelected());
        config.setIncludeSymbols(includeSymbols.isSelected());
        config.setIncludeLowerCase(includeLowercase.isSelected());
        config.setIncludeUpperCase(includeUppercase.isSelected());
        config.setPasswordLength(lengthSlider.getValue());
        return config;
    }

    @Override
    public void onPasswordsUpdated(String message) {
        updatePasswordHistory(message.split("\n"));
    }

    @Override
    public void onConfigUpdated(String configDetails) {
        JOptionPane.showMessageDialog(this, "Configuration Updated: \n" + configDetails);
    }
}
