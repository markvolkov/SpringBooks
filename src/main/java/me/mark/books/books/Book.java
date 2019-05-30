package me.mark.books.books;

import com.mongodb.BasicDBObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {

    @Id
    private int id;
    private String title;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Book(BasicDBObject object) {
        this.id = object.getInt("id");
        this.title = object.getString("title");
    }

    public Book() {}

    public BasicDBObject toDbObject() {
        BasicDBObject object = new BasicDBObject();
        object.put("id", id);
        object.put("title", title);
        return object;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
