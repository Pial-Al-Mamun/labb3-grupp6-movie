import data.DramaMovie;
import storage.DramaMovieStorage;
import storage.MovieStorage;

import java.util.List;

class DramaMovieStorageTest extends BaseTestStorage<DramaMovie> {
    @Override
    protected MovieStorage<DramaMovie> createStorage(List<DramaMovie> movies) {
        return new DramaMovieStorage(movies);
    }

    @Override
    protected DramaMovie createMovie(int id, String title) {
        return new DramaMovie(id, title);
    }
}
