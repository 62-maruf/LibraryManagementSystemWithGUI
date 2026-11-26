# Library Management System

A simple desktop Library Management System built with **Java** and **Swing**. This is my Object Oriented Programming (OOP) Sessional Lab project.

## Features

- View all books with their status (Available / Issued)
- Add a new book
- Issue a book
- Return a book
- Rename a book
- Delete a book (issued books cannot be deleted)
- View all members
- Register a new member
- Rename a member
- Delete a member
- Message popups and a status bar for every action

## Technologies Used

- Java
- Java Swing (GUI)
- Maven

## Project Structure

```
LibraryManagementSystem
├── pom.xml
└── src/main/java/com/mycompany/librarymanagementsystem
    ├── Main.java
    ├── Library.java
    └── LibraryGUI.java
```

| File | Purpose |
|------|---------|
| `Main.java` | Starts the application |
| `Library.java` | Book and member logic |
| `LibraryGUI.java` | Swing user interface |

## Run Commands

Make sure the **JDK** is installed first.

**1. Clone the repository**

```
git clone https://github.com/62-maruf/LibraryManagementSystem.git
cd LibraryManagementSystem/LibraryManagementSystem
```

**2. Run with Maven**

```
mvn compile exec:java
```

**Or run without Maven**

```
javac -d out src/main/java/com/mycompany/librarymanagementsystem/*.java
java -cp out com.mycompany.librarymanagementsystem.Main
```

> If your JDK is older than the version set in `pom.xml`, change `maven.compiler.release` to your JDK version.

## How to Use

1. Open the **Books** tab to add, issue, return, rename or delete books.
2. Open the **Members** tab to register, rename or delete members.
3. Select a row in the table, then click the button for the action you want.

## Sample Data

The program starts with 4 sample books and 5 sample members so you can try it right away.

## Author

**Maruf Hasan (Mahin)**
B.Sc. in Computer Science and Engineering, Brahmaputra International University

GitHub: [62-maruf](https://github.com/62-maruf)
