package com.example.demo.models;

public class Movie implements Comparable<Movie>  {

    private String year;
    private String length;
    private String title;
    private String subject;
    private int popularity;
    private String awards;

    public Movie(String year, String length, String title,
                 String subject, int popularity, String awards){

        this.year       = year;
        this.length     = length;
        this.title      = title;
        this.subject    = subject;
        this.popularity = popularity;
        this.awards     = awards;
        }


    public String getTitle(){
        return this.title;
    }

    public int getPopularity() {
        return this.popularity;
    }

    public String hasAwards() {
        return awards;
    }

    public String getInfo(){
        return "Score: " + this.popularity + " | Title: " + this.title + "      " + this.awards;
    }


    @Override
    public int compareTo(Movie o) {
        return Integer.compare(this.popularity, o.popularity);
    }
}
