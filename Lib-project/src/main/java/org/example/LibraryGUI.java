package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class LibraryGUI extends JFrame {
    private Library library;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField isbnField;
    private JTextArea outputArea;

    public LibraryGUI() {
        library = new Library();
        setTitle("Library Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Создание компонентов GUI
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        isbnField = new JTextField(20);
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);

        JButton addButton = new JButton("Add Book");
        JButton removeButton = new JButton("Remove Book");
        JButton searchButton = new JButton("Search Book");
        JButton listButton = new JButton("List All Books");

        //Панель для ввода данных о книге
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("ISBN:"));
        inputPanel.add(isbnField);

        //Панель для кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(listButton);


        //Добавление компонентов на основную панель
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        //Добавление основной панели в окно
        add(mainPanel);

        //Обработчик событий для кнопок
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String isbn = isbnField.getText();
                if (!title.isEmpty() && !author.isEmpty() && !isbn.isEmpty()) {
                    Book book = new Book(title, author, isbn);
                    library.addBook(book);
                    outputArea.append("Book added: " + book + "\n");
                } else {
                    outputArea.append("Please enter title, author, and ISBN. \n");
                }
                clearInputFields();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = isbnField.getText();
                if (!isbn.isEmpty()) {
                    Book bookToRemove = library.findBookByIsbn(isbn);
                    if (bookToRemove != null) {
                        library.removeBook(isbn);
                        outputArea.append("Book removed: " + bookToRemove + "\n");
                    } else {
                        outputArea.append("Book not found with ISBN: " + isbn + "\n");
                    }
                } else {
                    outputArea.append("Please enter ISBN to remove a book. \n");
                }
                clearInputFields();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = isbnField.getText();
                if (!isbn.isEmpty()) {
                    Book book = library.findBookByIsbn(isbn);
                    if (book != null) {
                        outputArea.append("Book found: " + book + "\n");
                    } else {
                        outputArea.append("Book not found with ISBN: " + isbn + "\n");
                    }
                } else {
                    outputArea.append("Please enter ISBN to search for a book. \n");
                }
                clearInputFields();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.append("Listing all books:\n");
                library.listAllBooks(outputArea);
            }
        });
    }

                private void clearInputFields() {
                    titleField.setText("");
                    authorField.setText("");
                    isbnField.setText("");
                }


                public static void main(String[] args) {
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run(){
                            new LibraryGUI().setVisible(true);
                        }
        });
    }
}
