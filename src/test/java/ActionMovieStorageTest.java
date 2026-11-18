import data.ActionMovie;
import storage.ActionMovieStorage;
import storage.MovieStorage;

import java.util.List;

class ActionMovieStorageTest extends BaseTestStorage<ActionMovie> {
    @Override
    protected MovieStorage<ActionMovie> createStorage(List<ActionMovie> movies) {
        return new ActionMovieStorage(movies);
    }

    @Override
    protected ActionMovie createMovie(int id, String title) {
        return new ActionMovie(id, title);
    }
}
