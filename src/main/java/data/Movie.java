package data;

public class Movie {
    protected int id;

    protected String title;

    public Movie() {
    }
    public Movie(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
