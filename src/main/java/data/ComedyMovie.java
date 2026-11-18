package data;

public class ComedyMovie extends BaseMovie implements Movie {
    public ComedyMovie() {
        super();
    }

    public ComedyMovie(int id, String title) {
        super(id, title);
    }

    @Override
    public String getCategory() {
        return "Comedy";
    }
}
