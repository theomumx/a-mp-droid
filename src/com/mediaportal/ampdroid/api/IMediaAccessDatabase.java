package com.mediaportal.ampdroid.api;

import java.util.ArrayList;
import java.util.List;

import com.mediaportal.ampdroid.data.Movie;

public interface IMediaAccessDatabase {
   ArrayList<Movie> getAllMovies();
   List<Movie> getMovies(int _start, int _end);
   void saveMovie(Movie _movie);
   
   public void open();
   public void close();
   int getMovieCount();
   

}
