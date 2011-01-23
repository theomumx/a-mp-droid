package com.mediaportal.ampdroid.api;

import java.util.ArrayList;
import java.util.List;

import com.mediaportal.ampdroid.data.CacheItemsSetting;
import com.mediaportal.ampdroid.data.Movie;
import com.mediaportal.ampdroid.data.MovieFull;
import com.mediaportal.ampdroid.data.Series;
import com.mediaportal.ampdroid.data.SeriesFull;

public interface IMediaAccessDatabase {
   //Movies
   ArrayList<Movie> getAllMovies();
   List<Movie> getMovies(int _start, int _end);
   void saveMovie(Movie _movie);
   CacheItemsSetting getMovieCount();
   CacheItemsSetting setMovieCount(int movieCount);
   MovieFull getMovieDetails(int _movieId);
   void saveMovieDetails(MovieFull _movie);
   
   //Series
   List<Series> getSeries(int _start, int _end);
   void saveSeries(Series _series);
   
   public void open();
   public void close();
   List<Series> getAllSeries();
   CacheItemsSetting getSeriesCount();
   CacheItemsSetting setSeriesCount(int _seriesCount);
   SeriesFull getFullSeries(int _seriesId);
   void saveSeriesDetails(SeriesFull series);
   

   
   

}
