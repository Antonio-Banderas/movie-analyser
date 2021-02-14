package com.example.demo.services;

import com.example.demo.models.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class MovieAnalysisService {

    ArrayList<Movie> movieList;

    public MovieAnalysisService(String path) {

        movieList = createMovieList(path);

    }

    /* Sanitizes the list of movies from the file and scan
     into an Arraylist<Movie> we can work with onwards */
    private ArrayList<Movie> createMovieList(String path) {

        // Create file from path
        File movieFile = new File(path);

        // Create a scan of the file
        Scanner scanOfMovieFile = null;
        try {
            scanOfMovieFile = new Scanner(movieFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Skip the first 2 lines of metadata in the CSV file in the scan
        scanOfMovieFile.nextLine();
        scanOfMovieFile.nextLine();

        // Dissect every line of the scan to a movie object, while adding it the final ArrayList <Movie>
        ArrayList<Movie> movieList = new ArrayList<>();
        while (scanOfMovieFile.hasNextLine()) {

            // Split
            String[] firstMovieAsArray = scanOfMovieFile.nextLine().split(";");

            Movie temp = new Movie(
                    firstMovieAsArray[0],   // year
                    firstMovieAsArray[1],   // length
                    firstMovieAsArray[2],   // title
                    firstMovieAsArray[3],   // subject
                    Integer.parseInt(firstMovieAsArray[4]),       // popularity
                    firstMovieAsArray[5]    // awards


            );
            movieList.add(temp);
        }

        // Return your beautiful work
        return movieList;
    }

    // Get first movie from list
    public Movie getFirstMovieOfList() {

        return movieList.get(0);

    }

    // Get random movie from list
    public Movie getRandomMovieOfList() {

        // Get size of the whole movie list
        int sizeOfMovieList = movieList.size();

        // Make a randomizer that picks one of the 1584 films
        Random randomizer = new Random();
        int randomMovie = randomizer.nextInt(sizeOfMovieList);

        // Now that a random number has been founed, pick that movie out from the list and return it
        return movieList.get(randomMovie);
    }

    // Get 10 random movies and sort by popularity
    public String getTenSortByPopularity() {

        ArrayList<Movie> tenRandomMovies = new ArrayList<Movie>();

        boolean state;

        // Get 10 random movies, while checking for same movie
        for (int i = 0; i < 10; i++) {
            do {
                state = false;
                if (!(tenRandomMovies.contains(getRandomMovieOfList()))) {
                    tenRandomMovies.add(getRandomMovieOfList());
                } else state = true;

            } while (state);
        }

        // Sort the list
        Collections.sort(tenRandomMovies);

        // Stylize and return
        StringBuilder tmpMovieList = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            tmpMovieList.append(tenRandomMovies.get(i).getInfo()).append("<br>");
        }
        return tmpMovieList.toString();

    }

    // Get how many movies in the data-set has won an award //
    public int howManyWonAnAward(){
        
        int awardCount = 0;

        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).hasAwards().equalsIgnoreCase("yes")){
                awardCount++;
            }
        }
        
        return awardCount;
        
    }
}
