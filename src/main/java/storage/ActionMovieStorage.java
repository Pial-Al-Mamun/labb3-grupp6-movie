package storage;

import data.ActionMovie;

import java.util.List;

public class ActionMovieStorage extends BaseMovieStorage<ActionMovie> {
    public ActionMovieStorage(List<ActionMovie> movies) {
        super(movies);
    }

    @Override
    protected ActionMovie createMovie(int id, String title) {
        return new ActionMovie(id, title);
    }
}
