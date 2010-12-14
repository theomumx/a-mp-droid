package com.mediaportal.remote.api;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

import com.mediaportal.remote.data.Movie;
import com.mediaportal.remote.data.MovieFull;
import com.mediaportal.remote.data.MusicAlbum;
import com.mediaportal.remote.data.SupportedFunctions;
import com.mediaportal.remote.data.SeriesEpisode;
import com.mediaportal.remote.data.SeriesSeason;
import com.mediaportal.remote.data.Series;
import com.mediaportal.remote.data.SeriesFull;


public interface IRemoteAccessApi {  
	ArrayList<Movie> getAllMovies();
	int getMovieCount();
	ArrayList<Movie> getMovies(int _start, int _end);
	MovieFull getMovieDetails(int _movieId);
	Bitmap getBitmap(String _id);
	ArrayList<Series> getAllSeries();
	ArrayList<Series> getSeries(int _start, int _end);
	SeriesFull getFullSeries(int _seriesId);
	int getSeriesCount();
	ArrayList<SeriesSeason> getAllSeasons(int seriesId);
	ArrayList<SeriesEpisode> getAllEpisodes(int seriesId);
	ArrayList<SeriesEpisode> getAllEpisodesForSeason(int seriesId, int seasonNumber);
	SupportedFunctions getSupportedFunctions();
	ArrayList<MusicAlbum> getAllAlbums();
	List<MusicAlbum> getAlbums(int _start, int _end);
}
