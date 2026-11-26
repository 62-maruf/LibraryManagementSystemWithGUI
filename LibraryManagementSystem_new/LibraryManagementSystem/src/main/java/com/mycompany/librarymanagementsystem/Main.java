package com.mycompany.librarymanagementsystem;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryGUI::new);
    }
}
