package com.mediaportal.ampdroid.api;

import java.util.ArrayList;

import com.mediaportal.ampdroid.data.Movie;

public interface IMediaAccessDatabase {
   ArrayList<Movie> getAllMovies();
   void saveMovie(Movie _movie);

}
