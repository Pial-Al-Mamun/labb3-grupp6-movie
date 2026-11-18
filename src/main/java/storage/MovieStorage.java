package storage;

import data.BaseMovie;

import java.io.IOException;
import java.util.List;

public interface MovieStorage<T extends BaseMovie> {
    void save(T movie) throws IOException;

    T findById(int id) throws IOException;

    List<T> findAll() throws IOException;

}
