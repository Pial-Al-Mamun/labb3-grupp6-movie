import data.HorrorMovie;
import storage.HorrorMovieStorage;
import storage.MovieStorage;

import java.util.List;

class HorrorMovieStorageTest extends BaseTestStorage<HorrorMovie> {
    @Override
    protected MovieStorage<HorrorMovie> createStorage(List<HorrorMovie> movies) {
        return new HorrorMovieStorage(movies);
    }

    @Override
    protected HorrorMovie createMovie(int id, String title) {
        return new HorrorMovie(id, title);
    }
}
