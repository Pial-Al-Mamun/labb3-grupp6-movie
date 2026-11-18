package storage;

import data.ComedyMovie;

import java.util.List;

public class ComedyMovieStorage extends BaseMovieStorage<ComedyMovie> {
    public ComedyMovieStorage(List<ComedyMovie> movies) {
        super(movies);
    }

    @Override
    protected ComedyMovie createMovie(int id, String title) {
        return new ComedyMovie(id, title);
    }
}
