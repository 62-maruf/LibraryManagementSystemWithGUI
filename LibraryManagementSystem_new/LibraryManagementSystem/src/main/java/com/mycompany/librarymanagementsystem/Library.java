package com.mycompany.librarymanagementsystem;

import java.util.ArrayList;

public class Library {

    static ArrayList<String> bookNames = new ArrayList<>();
    static ArrayList<Integer> bookIds = new ArrayList<>();
    static ArrayList<Boolean> bookStatus = new ArrayList<>();

    static ArrayList<String> memberNames = new ArrayList<>();
    static ArrayList<Integer> memberIds = new ArrayList<>();

    static {
        addBook(101, "Object Oriented Programming");
        addBook(102, "Digital Logic Deisgn");
        addBook(103, "Discrete Mathematics");
        addBook(104, "Engineering Physics-II");

        addMember(1, "Sumaia");
        addMember(2, "Ahmadullah");
        addMember(3, "Maruf");
        addMember(4, "Sourov");
        addMember(5, "Mashiur");
    }

    public static String addBook(int id, String name) {
        if (bookIds.contains(id)) {
            return "Book ID already exists!";
        }
        bookIds.add(id);
        bookNames.add(name);
        bookStatus.add(false);
        return "Book added successfully!";
    }

    public static String addMember(int id, String name) {
        if (memberIds.contains(id)) {
            return "Member ID already exists!";
        }
        memberIds.add(id);
        memberNames.add(name);
        return "Member registered successfully!";
    }

    public static String issueBook(int id) {
        int index = bookIds.indexOf(id);
        if (index == -1) {
            return "Book not found!";
        }
        if (bookStatus.get(index)) {
            return "Book is already issued!";
        }
        bookStatus.set(index, true);
        return "Book issued successfully!";
    }

    public static String returnBook(int id) {
        int index = bookIds.indexOf(id);
        if (index == -1) {
            return "Book not found!";
        }
        if (!bookStatus.get(index)) {
            return "This book was not issued!";
        }
        bookStatus.set(index, false);
        return "Book returned successfully!";
    }

    public static String renameBook(int id, String name) {
        int index = bookIds.indexOf(id);
        if (index == -1) {
            return "Book not found!";
        }
        bookNames.set(index, name);
        return "Book renamed successfully!";
    }

    public static String deleteBook(int id) {
        int index = bookIds.indexOf(id);
        if (index == -1) {
            return "Book not found!";
        }
        if (bookStatus.get(index)) {
            return "Issued book cannot be deleted!";
        }
        bookIds.remove(index);
        bookNames.remove(index);
        bookStatus.remove(index);
        return "Book deleted successfully!";
    }

    public static String renameMember(int id, String name) {
        int index = memberIds.indexOf(id);
        if (index == -1) {
            return "Member not found!";
        }
        memberNames.set(index, name);
        return "Member renamed successfully!";
    }

    public static String deleteMember(int id) {
        int index = memberIds.indexOf(id);
        if (index == -1) {
            return "Member not found!";
        }
        memberIds.remove(index);
        memberNames.remove(index);
        return "Member deleted successfully!";
    }
}
