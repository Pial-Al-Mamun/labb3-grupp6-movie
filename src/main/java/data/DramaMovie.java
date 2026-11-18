package data;

public class DramaMovie extends BaseMovie implements Movie {
    @Override
    public String getCategory() {
        return "Drama";
    }
}
