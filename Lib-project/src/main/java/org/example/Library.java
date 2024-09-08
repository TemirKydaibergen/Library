package org.example;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

public class Library implements LibraryManagementSystem {

    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    @Override
    public void addBook(Book book){
        System.out.println("Book added: " + book);
    }

    @Override
    public void removeBook(String isbn) {
        Book bookToRemove = findBookByIsbn(isbn);
        if(bookToRemove != null){
            books.remove(bookToRemove);
        } else {
            System.out.println("Book not found with ISBN: " + isbn);
        }
    }

    @Override
    public Book findBookByIsbn(String isbn){
        for(Book book : books){
            if (book.getIsbn().equals(isbn)){
                return book;
            }
        }
        return null;
    }

    @Override
    public void listAllBooks(){

    }

    @Override
    public void listAllBooks(JTextArea outputArea){
        if(books.isEmpty()){
            outputArea.append("No books available in the library. \n");
        } else {
            for (Book book : books){
                outputArea.append(book.toString() + "\n");
            }
        }
    }
}
