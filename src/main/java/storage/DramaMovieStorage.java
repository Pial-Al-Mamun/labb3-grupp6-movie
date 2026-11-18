package storage;

import data.DramaMovie;

import java.util.List;

public class DramaMovieStorage extends BaseMovieStorage<DramaMovie> {
    public DramaMovieStorage(List<DramaMovie> movies) {
        super(movies);
    }

    @Override
    protected DramaMovie createMovie(int id, String title) {
        return new DramaMovie(id, title);
    }
}
