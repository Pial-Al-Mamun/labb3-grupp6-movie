package storage;

import data.HorrorMovie;

import java.util.List;

public class HorrorMovieStorage extends BaseMovieStorage<HorrorMovie> {
    public  HorrorMovieStorage(List<HorrorMovie> movies) {
        super(movies);
    }

    @Override
    protected HorrorMovie createMovie(int id, String title) {
        return new HorrorMovie(id, title);
    }
}
