package storage;

import data.BaseMovie;

import java.util.List;

public abstract class BaseMovieStorage<T extends BaseMovie> implements MovieStorage<T>{

    private final List<T> movies;

    public BaseMovieStorage(List<T> movies) {
        this.movies = movies;
    }

    @Override
    public void save(T movie) {
        if (findById(movie.getId()) != null) {
            return;
        }
        movies.add(movie);
    }

    protected abstract T createMovie(int id, String title);

    public T createAndSaveMovie(int id, String title) {
        T movie = createMovie(id, title);
        save(movie);
        return movie;
    }

    @Override
    public List<T> findAll() {
        return movies;
    }

    @Override
    public T findById(int id) {
        return movies.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
