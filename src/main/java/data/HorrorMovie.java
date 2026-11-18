package data;

public class HorrorMovie extends BaseMovie implements Movie {
    public HorrorMovie() {
        super();
    }

    public HorrorMovie(int id, String title) {
        super(id, title);
    }

    @Override
    public String getCategory() {
        return "Horror";
    }
}