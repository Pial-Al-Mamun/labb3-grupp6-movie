package storage;

import data.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieStorage<T extends Movie> {
    void save(T movie) throws IOException;
    T findById(int id) throws IOException;
    List<T> findAll() throws IOException;
}
