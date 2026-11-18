package data;

public class DramaMovie extends BaseMovie implements Movie {
    public DramaMovie() {
        super();
    }

    public DramaMovie(int id, String title) {
        super(id, title);
    }

    @Override
    public String getCategory() {
        return "Drama";
    }
}
