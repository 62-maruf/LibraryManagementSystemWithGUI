package com.mycompany.librarymanagementsystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.function.BiFunction;
import java.util.function.Function;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class LibraryGUI extends JFrame {

    private final DefaultTableModel bookModel = new DefaultTableModel(new String[]{"Book ID", "Book Name", "Status"}, 0);
    private final DefaultTableModel memberModel = new DefaultTableModel(new String[]{"Member ID", "Member Name"}, 0);
    private final JTable bookTable = new JTable(bookModel);
    private final JTable memberTable = new JTable(memberModel);
    private final JLabel statusLabel = new JLabel("Welcome to the library!");

    public LibraryGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        setTitle("Library Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 480);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Books", createBooksPanel());
        tabs.addTab("Members", createMembersPanel());

        statusLabel.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

        add(tabs, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        refresh();
        setVisible(true);
    }

    private JPanel createBooksPanel() {
        JTextField idField = new JTextField(6);
        JTextField nameField = new JTextField(15);
        JButton addButton = new JButton("Add Book");
        JButton issueButton = new JButton("Issue");
        JButton returnButton = new JButton("Return");
        JButton renameButton = new JButton("Rename");
        JButton deleteButton = new JButton("Delete");

        addButton.addActionListener(e -> add(idField, nameField, Library::addBook));
        issueButton.addActionListener(e -> act(bookTable, "book", Library::issueBook));
        returnButton.addActionListener(e -> act(bookTable, "book", Library::returnBook));
        renameButton.addActionListener(e -> rename(bookTable, "book", Library::renameBook));
        deleteButton.addActionListener(e -> delete(bookTable, "book", Library::deleteBook));

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER));
        actions.add(issueButton);
        actions.add(returnButton);
        actions.add(renameButton);
        actions.add(deleteButton);

        return buildPanel(bookTable, form(idField, nameField, addButton), actions);
    }

    private JPanel createMembersPanel() {
        JTextField idField = new JTextField(6);
        JTextField nameField = new JTextField(15);
        JButton addButton = new JButton("Register Member");
        JButton renameButton = new JButton("Rename");
        JButton deleteButton = new JButton("Delete");

        addButton.addActionListener(e -> add(idField, nameField, Library::addMember));
        renameButton.addActionListener(e -> rename(memberTable, "member", Library::renameMember));
        deleteButton.addActionListener(e -> delete(memberTable, "member", Library::deleteMember));

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER));
        actions.add(renameButton);
        actions.add(deleteButton);

        return buildPanel(memberTable, form(idField, nameField, addButton), actions);
    }

    private JPanel form(JTextField idField, JTextField nameField, JButton addButton) {
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("ID:"));
        form.add(idField);
        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(addButton);
        return form;
    }

    private JPanel buildPanel(JTable table, JPanel top, JPanel bottom) {
        table.setRowHeight(24);
        table.setDefaultEditor(Object.class, null);

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);
        return panel;
    }

    private void add(JTextField idField, JTextField nameField, BiFunction<Integer, String, String> action) {
        Integer id = readId(idField);
        if (id == null || nameField.getText().isBlank()) {
            show("Enter a valid ID and name!");
            return;
        }
        show(action.apply(id, nameField.getText().trim()));
        idField.setText("");
        nameField.setText("");
    }

    private void act(JTable table, String type, Function<Integer, String> action) {
        Integer id = selectedId(table, type);
        if (id != null) {
            show(action.apply(id));
        }
    }

    private void rename(JTable table, String type, BiFunction<Integer, String, String> action) {
        Integer id = selectedId(table, type);
        if (id == null) {
            return;
        }
        String oldName = (String) table.getValueAt(table.getSelectedRow(), 1);
        String newName = JOptionPane.showInputDialog(this, "Enter new " + type + " name:", oldName);
        if (newName == null || newName.isBlank()) {
            return;
        }
        show(action.apply(id, newName.trim()));
    }

    private void delete(JTable table, String type, Function<Integer, String> action) {
        Integer id = selectedId(table, type);
        if (id == null) {
            return;
        }
        int answer = JOptionPane.showConfirmDialog(this, "Delete this " + type + "?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            show(action.apply(id));
        }
    }

    private Integer readId(JTextField field) {
        try {
            return Integer.parseInt(field.getText().trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer selectedId(JTable table, String type) {
        int row = table.getSelectedRow();
        if (row == -1) {
            show("Select a " + type + " first!");
            return null;
        }
        return (Integer) table.getValueAt(row, 0);
    }

    private void show(String message) {
        statusLabel.setText(message);
        refresh();
        JOptionPane.showMessageDialog(this, message);
    }

    private void refresh() {
        bookModel.setRowCount(0);
        for (int i = 0; i < Library.bookIds.size(); i++) {
            String status = Library.bookStatus.get(i) ? "Issued" : "Available";
            bookModel.addRow(new Object[]{Library.bookIds.get(i), Library.bookNames.get(i), status});
        }

        memberModel.setRowCount(0);
        for (int i = 0; i < Library.memberIds.size(); i++) {
            memberModel.addRow(new Object[]{Library.memberIds.get(i), Library.memberNames.get(i)});
        }
    }
}
