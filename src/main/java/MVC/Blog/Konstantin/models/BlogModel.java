package MVC.Blog.Konstantin.models;

public class BlogModel {
    private int id;
    private String text;
    private String author;
    private String publicationTime;

    public BlogModel(){};
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(String publicationTime) {
        this.publicationTime = publicationTime;
    }

    public BlogModel(int id, String text, String author, String publicationTime){
        this.id = id;
        this.text = text;
        this.author = author;
        this.publicationTime = publicationTime;
    }
}
