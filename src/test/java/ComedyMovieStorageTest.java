import data.ComedyMovie;
import storage.ComedyMovieStorage;
import storage.MovieStorage;

import java.util.List;

class ComedyMovieStorageTest extends BaseTestStorage<ComedyMovie> {
    @Override
    protected MovieStorage<ComedyMovie> createStorage(List<ComedyMovie> movies) {
        return new ComedyMovieStorage(movies);
    }

    @Override
    protected ComedyMovie createMovie(int id, String title) {
        return new ComedyMovie(id, title);
    }
}
