package com.mediaportal.remote.api;

import java.util.ArrayList;

import com.mediaportal.remote.data.Movie;

public interface IMediaAccessDatabase {
   ArrayList<Movie> getAllMovies();
   void saveMovie(Movie _movie);

}
