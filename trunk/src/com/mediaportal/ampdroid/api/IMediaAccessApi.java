package com.mediaportal.ampdroid.api;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

import com.mediaportal.ampdroid.data.EpisodeDetails;
import com.mediaportal.ampdroid.data.FileInfo;
import com.mediaportal.ampdroid.data.Movie;
import com.mediaportal.ampdroid.data.MovieFull;
import com.mediaportal.ampdroid.data.MusicAlbum;
import com.mediaportal.ampdroid.data.Series;
import com.mediaportal.ampdroid.data.SeriesEpisode;
import com.mediaportal.ampdroid.data.SeriesFull;
import com.mediaportal.ampdroid.data.SeriesSeason;
import com.mediaportal.ampdroid.data.SupportedFunctions;
import com.mediaportal.ampdroid.data.VideoShare;

public interface IMediaAccessApi extends IApiInterface {
   ArrayList<Movie> getAllMovies();

   int getMovieCount();

   ArrayList<Movie> getMovies(int _start, int _end);

   MovieFull getMovieDetails(int _movieId);

   Bitmap getBitmap(String _id);

   Bitmap getBitmap(String _url, int _maxWidth, int _maxHeight);

   ArrayList<Series> getAllSeries();

   ArrayList<Series> getSeries(int _start, int _end);

   SeriesFull getFullSeries(int _seriesId);

   int getSeriesCount();

   ArrayList<SeriesSeason> getAllSeasons(int seriesId);

   ArrayList<SeriesEpisode> getAllEpisodes(int seriesId);

   ArrayList<SeriesEpisode> getAllEpisodesForSeason(int seriesId, int seasonNumber);
   
   ArrayList<SeriesEpisode> getEpisodesForSeason(int _seriesId, int _seasonNumber, int _begin,
         int _end);
   
   int getEpisodesCountForSeason(int _seriesId, int _seasonNumber);

   SupportedFunctions getSupportedFunctions();

   ArrayList<MusicAlbum> getAllAlbums();

   List<MusicAlbum> getAlbums(int _start, int _end);

   String getDownloadUri(String _filePath);

   ArrayList<VideoShare> getVideoShares();

   EpisodeDetails getEpisode(int _seriesId, int _episodeId);

   ArrayList<FileInfo> getFilesForFolder(String _path);



}
