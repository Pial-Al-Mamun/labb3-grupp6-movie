import data.BaseMovie;
import storage.BaseMovieStorage;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<String, BaseMovieStorage<? extends BaseMovie>> storage = null;
    private static final Scanner INPUT = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        MoviesLoader.init();
        storage = MoviesLoader.getStorages();
        var finished = false;
        while (!finished) {
            var choice = displayMenu();
            switch (choice) {
                case 1 -> {
                    var genre = printMoviesGenre();
                    specificGenreMenu(genre);
                }
                case 2 -> {
                    for (var entry : storage.entrySet()) {
                        System.out.println("Genre: " + entry.getKey());
                        entry.getValue().findAll().forEach(movie ->
                                System.out.println(movie.getId() + ": " + movie.getTitle()));
                    }
                }
                case 3 -> {
                    finished = true;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static Integer displayMenu() {
        System.out.println("Welcome to the Movie Storage System!");
        System.out.println("Please select an option:");
        System.out.println("1. Show movies in a specific genre");
        System.out.println("2. Show all the movies of all the genres");
        System.out.println("3. Exit");
        return Integer.parseInt(INPUT.nextLine());
    }

    private static void specificGenreMenu(String genre) {
        var finished = false;
        while (!finished) {
            System.out.println("Please select an option:");
            System.out.println("0. List all movies");
            System.out.println("1. Find a movie by ID");
            System.out.println("2. Add a new movie");
            System.out.println("3. Exit");
            var choice = Integer.parseInt(INPUT.nextLine());

            switch (choice) {
                case 1 -> {
                    storage.get(genre)
                            .findAll()
                            .forEach(movie ->
                                    System.out.println(movie.getId() + ": " + movie.getTitle()));
                }
                case 2 -> {
                    System.out.println("Please enter the ID of the movie:");
                    var id = Integer.parseInt(INPUT.nextLine());
                    var movie = storage.get(genre).findById(id);

                    if (movie == null) {
                        System.out.println("Movie not found.");
                    }

                    System.out.println(movie.getId() + ": " + movie.getTitle());
                }

                case 3 -> {
                    System.out.println("Please enter the ID of the movie:");
                    var id = Integer.parseInt(INPUT.nextLine());
                    System.out.println("Please enter the title of the movie:");
                    var title = INPUT.nextLine();
                    storage.get(genre).createAndSaveMovie(id, title);
                    System.out.println("Movie added successfully.");
                }
            }
        }
    }
    private static String printMoviesGenre () {
        for (int i = 0; i < MoviesConstant.GENRES.size(); i++) {
            System.out.println((i + 1) + ". " + MoviesConstant.GENRES.get(i));
        }
        System.out.println("Please select a genre:");
        var genreChoice = Integer.parseInt(INPUT.nextLine());
        if (genreChoice < 1 || genreChoice > MoviesConstant.GENRES.size()) {
            throw new IllegalArgumentException("Invalid option. Please try again.");
        }
        return MoviesConstant.GENRES.get(genreChoice - 1);
    }
}
