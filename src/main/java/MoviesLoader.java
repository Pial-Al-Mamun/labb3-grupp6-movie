import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.*;
import storage.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MoviesLoader implements AutoCloseable {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static Map<String, BaseMovieStorage<? extends BaseMovie>> storages = null;

    /**
     * Loads the movies from the JSON file and returns a map of movie storages.
     *
     * @return a map of movie storages, where the key is the genre and the value is the corresponding storage
     * @throws IOException if there is an error reading or parsing the JSON file
     */
    public static void init() throws IOException {
        var moviesFile = mapper.readTree(new File(MoviesConstant.FILE_PATH));
        Map<String, BaseMovieStorage<? extends BaseMovie>> storage =
                new LinkedHashMap<>();

        for (String genre : MoviesConstant.GENRES) {
            var genreNode = moviesFile.get(genre);

            if (genreNode == null || !genreNode.isArray()) {
                throw new IOException("The movie file must contain a " + genre + " array.");
            }

            switch (genre) {
                case "horror" -> {
                    var horrorMovies = mapper.convertValue(
                            genreNode,
                            new TypeReference<List<HorrorMovie>>() {
                            }
                    );
                    storage.put(genre, new HorrorMovieStorage(horrorMovies));
                }
                case "drama" -> {
                    var dramaMovies = mapper.convertValue(
                            genreNode,
                            new TypeReference<List<DramaMovie>>() {
                            }
                    );
                    storage.put(genre, new DramaMovieStorage(dramaMovies));
                }
                case "comedy" -> {
                    var comedyMovies = mapper.convertValue(
                            genreNode,
                            new TypeReference<List<ComedyMovie>>() {
                            }
                    );
                    storage.put(genre, new ComedyMovieStorage(comedyMovies));
                }
                case "action" -> {
                    var actionMovies = mapper.convertValue(
                            genreNode,
                            new TypeReference<List<ActionMovie>>() {
                            }
                    );
                    storage.put(genre, new ActionMovieStorage(actionMovies));
                }
                default -> throw new IOException("Unsupported movie genre: " + genre);
            }
        }

        storages = Map.copyOf(storage);
    }

    public static Map<String, BaseMovieStorage<? extends BaseMovie>> getStorages() {
        return storages;
    }

    @Override
    public void close() throws IOException {
        if (storages == null) {
            return;
        }

        var moviesByGenre = new LinkedHashMap<>();
        for (String genre : MoviesConstant.GENRES) {
            var storage = storages.get(genre);
            if (storage == null) {
                throw new IOException("No movie storage found for genre: " + genre);
            }
            moviesByGenre.put(genre, mapper.valueToTree(storage.findAll()));
        }

        var target = new File(MoviesConstant.FILE_PATH).toPath();
        var parent = target.toAbsolutePath().getParent();
        var temporaryFile = Files.createTempFile(parent, target.getFileName().toString(), ".tmp");

        try {
            mapper.writeValue(temporaryFile.toFile(), moviesByGenre);
            try {
                Files.move(temporaryFile, target, StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.ATOMIC_MOVE);
            } catch (AtomicMoveNotSupportedException e) {
                Files.move(temporaryFile, target, StandardCopyOption.REPLACE_EXISTING);
            }
        } finally {
            Files.deleteIfExists(temporaryFile);
        }
    }
}
