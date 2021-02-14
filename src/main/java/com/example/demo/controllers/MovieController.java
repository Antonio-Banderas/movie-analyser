package com.example.demo.controllers;

import com.example.demo.models.Movie;
import com.example.demo.services.MovieAnalysisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


//This is a bean - it is handled by the Spring framework
@Controller
public class MovieController {
    MovieAnalysisService movieService = new MovieAnalysisService("src/main/resources/static/film-new.csv");

    // Index
    @ResponseBody
    @GetMapping("/")
    public String helloWorld() {
        return "<div align=center> <br> [ IP and location logged ] <br> </div>";
    }

    // --- First movie from list --- //
    @ResponseBody
    @GetMapping("/getFirst")
    public String getFirst() {
        Movie firstMovieOfList = movieService.getFirstMovieOfList();
        return firstMovieOfList.getInfo();
    }

    // --- Random movie from list --- //
    @ResponseBody
    @GetMapping("/getRandom")
    public String getRandom() {
        Movie randomMovie = movieService.getRandomMovieOfList();
        return randomMovie.getInfo();
    }

    // --- 10 random movies, sorted by popularity --- //
    @ResponseBody
    @GetMapping("/getTenSortByPopularity")
    public String getTenSortByPopularity() {
        return movieService.getTenSortByPopularity();
    }


    // --- How many movies in the data-set has won an award // howManyWonAnAward
    @ResponseBody
    @GetMapping("/howManyWonAnAward")
    public int howManyWonAnAward() {
        return movieService.howManyWonAnAward();
    }
}


