import data.BaseMovie;
import org.junit.jupiter.api.Test;
import storage.MovieStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class BaseTestStorage<T extends BaseMovie> {
    protected abstract MovieStorage<T> createStorage(List<T> movies);

    protected abstract T createMovie(int id, String title);

    @Test
    void savesMovieAndReadsItById() throws IOException {
        MovieStorage<T> storage = createStorage(new ArrayList<>());
        T movie = createMovie(1, "The Thing");

        storage.save(movie);

        T loadedMovie = storage.findById(1);
        assertEquals(movie.getId(), loadedMovie.getId());
        assertEquals(movie.getTitle(), loadedMovie.getTitle());
    }

    @Test
    void doesNotStoreDuplicateIdsAndKeepsFirstMovie() throws IOException {
        MovieStorage<T> storage = createStorage(new ArrayList<>());
        storage.save(createMovie(1, "First title"));
        storage.save(createMovie(1, "Second title"));

        assertEquals(1, storage.findAll().size());
        assertEquals("First title", storage.findAll().get(0).getTitle());
    }

    @Test
    void findAllIsEmptyBeforeSavingAnyMovies() throws IOException {
        MovieStorage<T> storage = createStorage(new ArrayList<>());

        assertTrue(storage.findAll().isEmpty());
    }

    @Test
    void returnsNullWhenIdIsMissing() throws IOException {
        MovieStorage<T> storage = createStorage(new ArrayList<>());
        storage.save(createMovie(1, "Saved movie"));

        assertNull(storage.findById(404));
    }
}
