package data;

public class ActionMovie extends  BaseMovie implements Movie {
    public ActionMovie() {
        super();
    }

    public ActionMovie(int id, String title) {
        super(id, title);
    }

    @Override
    public String getCategory() {
        return "Action";
    }
}
