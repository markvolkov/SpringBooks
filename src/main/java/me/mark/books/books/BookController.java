package me.mark.books.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookManager bookManager;

    @RequestMapping(value = "/book/add", method = RequestMethod.POST)
    public void addBook(@RequestParam(name = "title", defaultValue = "default-title") String title) {
        bookManager.addBook(title);
        System.out.println("Added book with title " + title);
    }

    @RequestMapping(value = "/book/get", method = RequestMethod.GET)
    public @ResponseBody Book getBookById(@RequestParam(value = "id", defaultValue = "-1") int id) {
        return bookManager.getBook(id);
    }

    @RequestMapping(value = "/book/getall", method = RequestMethod.GET)
    public @ResponseBody Collection<Book> getAllBooks() {
        return bookManager.getAllBooks();
    }

    @RequestMapping(value = "/book/update", method = RequestMethod.POST)
    public void updateBook(@RequestParam(value = "id", defaultValue = "-1") int id, @RequestParam(value = "title", defaultValue = "default-title") String title) {
        bookManager.updateToDb(id, title);
        System.out.println("Updated book to new title " + title);
    }

    @RequestMapping(value = "/book/delete", method = RequestMethod.POST)
    public void deleteBook(@RequestParam(value = "id", defaultValue = "-1") int id) {
        bookManager.deleteBook(id);
        System.out.println("Deleted book with id " + id);
    }

    @RequestMapping(value = "/book/deleteall", method = RequestMethod.POST)
    public void deleteBooks(@RequestParam(value = "ids") List<Integer> ids) {
        bookManager.deleteBook(ids);
        System.out.println("Deleted " + ids.size() + " books");
    }






}
