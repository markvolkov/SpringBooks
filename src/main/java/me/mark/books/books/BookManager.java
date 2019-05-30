package me.mark.books.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookManager {

    @Autowired
    private BookRepository repository;
    private Map<Integer, Book> bookMap = new HashMap<>();

    private void loadFromDb() {
        List<Book> found = repository.findAll();
        if (found.isEmpty()) return;
        for (Book book : found) {
            bookMap.put(book.getId(), book);
        }
    }

    public Book getBook(int id) {
        if (bookMap.isEmpty()) {
            loadFromDb();
        }
        Book book = bookMap.get(id);
        if (book == null) return null;
        return book;
    }

    public void addBook(String title) {
        if (bookMap.isEmpty()) {
            loadFromDb();
        }
        Book book = new Book(bookMap.size() + 1, title);
        bookMap.put(book.getId(), book);
        saveToDb(book);
    }

    public Collection getAllBooks() {
        if (bookMap.isEmpty()) {
            loadFromDb();
        }
        return bookMap.values();
    }

    public void deleteBook(int id) {
        Book book = getBook(id);
        if (book == null) return;
        deleteFromDb(book);
        bookMap.remove(book.getId());
    }

    public void deleteBook(List<Integer> toDelete) {
        for (int id : toDelete)
            deleteBook(id);
    }

    private void saveToDb(Book book) {
        repository.save(book);
    }

    public void updateToDb(int id, String title) {
        Book book = getBook(id);
        if (book == null) return;
        book.setTitle(title);
        repository.save(book);
    }

    private void deleteFromDb(Book book) {
        repository.delete(book);
    }

    public Map<Integer, Book> getBookMap() {
        return bookMap;
    }

}
