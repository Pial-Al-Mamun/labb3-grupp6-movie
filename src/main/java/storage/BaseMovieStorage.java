package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.Movie;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public abstract class BaseMovieStorage<T extends Movie> implements MovieStorage<T>, AutoCloseable {
    protected String genreKey;
    protected final URI path;

    private List<T> movies;

    public BaseMovieStorage(String path, String genreKey) throws IOException {
        this.path = URI.create(path);
        this.genreKey = genreKey;

        var file = Path.of(this.path);

        var fileExists = file.toFile().exists();

        if (!fileExists) {
            throw new IOException("File not found: " + this.path);
        }

        var fileContent = Files.readAllLines(file);

        var objectMapper = new ObjectMapper();

        var movieMap = objectMapper.readValue(
                fileContent.toString(),
                new TypeReference<Map<String, List<Movie>>>() {
                }
        );

        this.movies = (List<T>) movieMap.get(genreKey);
    }

    @Override
    public void save(T movie) throws IOException {
    }

    @Override
    public List<T> findAll() throws IOException {
        return List.of();
    }

    @Override
    public T findById(int id) throws IOException {
        return movies.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void close() throws Exception {
        // Implementation for closing the storage
    }
}
