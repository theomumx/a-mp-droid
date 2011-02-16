package com.mediaportal.ampdroid.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mediaportal.ampdroid.api.IMediaAccessDatabase;
import com.mediaportal.ampdroid.data.CacheItemsSetting;
import com.mediaportal.ampdroid.data.Movie;
import com.mediaportal.ampdroid.data.MovieFull;
import com.mediaportal.ampdroid.data.Series;
import com.mediaportal.ampdroid.data.SeriesEpisode;
import com.mediaportal.ampdroid.data.SeriesFull;
import com.mediaportal.ampdroid.data.SeriesSeason;

public class MediaAccessDatabaseHandler implements IMediaAccessDatabase {
   private final String CLIENT_ID = "ClientId";
   Context mContext;
   private MediaDatabaseHelper mDbHelper;
   private SQLiteDatabase mDatabase;
   private int mClientId;

   public int getClientId() {
      return mClientId;
   }

   public void setClientId(int clientId) {
      mClientId = clientId;
   }

   public MediaAccessDatabaseHandler(Context _context, int _clientId) {
      mContext = _context;
      mDbHelper = new MediaDatabaseHelper(mContext, "ampdroid_media", null, 42);
      mClientId = _clientId;
   }

   @Override
   public void open() {
      mDatabase = mDbHelper.getWritableDatabase();
   }

   @Override
   public void close() {
      mDatabase.close();
   }

   @Override
   public ArrayList<Movie> getAllMovies() {
      try {
         Cursor result = mDatabase.query(Movie.TABLE_NAME, null, CLIENT_ID + "=" + mClientId, null,
               null, null, null);
         ArrayList<Movie> movies = null;
         if (result.moveToFirst()) {
            movies = (ArrayList<Movie>) SqliteAnnotationsHelper.getObjectsFromCursor(result,
                  Movie.class, 0);
         }

         result.close();
         return movies;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      // something went wrong obviously
      return null;
   }

   @Override
   public List<Movie> getMovies(int _start, int _end) {
      try {
         Cursor result = mDatabase.query(Movie.TABLE_NAME, null, CLIENT_ID + "=" + mClientId, null,
               null, null, null);
         ArrayList<Movie> movies = null;
         if (result.getCount() > _end) {
            result.move(_start + 1);
            movies = (ArrayList<Movie>) SqliteAnnotationsHelper.getObjectsFromCursor(result,
                  Movie.class, _end - _start + 1);
         }
         result.close();
         return movies;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      // something went wrong obviously
      return null;
   }

   @Override
   public void saveMovie(Movie movie) {
      try {
         ContentValues dbValues = SqliteAnnotationsHelper.getContentValuesFromObject(movie,
               Movie.class);
         dbValues.put(CLIENT_ID, mClientId);

         int rows = mDatabase.update(Movie.TABLE_NAME, dbValues, null, new String[] {
               CLIENT_ID + "=" + mClientId, "Id=" + movie.getId() });

         if (rows != 1) {
            mDatabase.insert("Movies", null, dbValues);
         }
         return;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      // something went wrong obviously
   }

   @Override
   public CacheItemsSetting getMovieCount() {
      try {
         Cursor result = mDatabase.query(CacheItemsSetting.TABLE_NAME, null, CLIENT_ID + "="
               + mClientId + " AND CacheType=" + Movie.CACHE_ID, null, null, null, null);

         result.moveToFirst();
         CacheItemsSetting setting = (CacheItemsSetting) SqliteAnnotationsHelper
               .getObjectFromCursor(result, CacheItemsSetting.class);
         result.close();
         return setting;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      // something went wrong obviously
      return null;
   }

   @Override
   public CacheItemsSetting setMovieCount(int _movieCount) {
      try {
         CacheItemsSetting setting = new CacheItemsSetting(Movie.CACHE_ID, _movieCount, new Date());
         ContentValues dbValues = SqliteAnnotationsHelper.getContentValuesFromObject(setting,
               CacheItemsSetting.class);
         dbValues.put(CLIENT_ID, mClientId);

         int rows = mDatabase.update(CacheItemsSetting.TABLE_NAME, dbValues, CLIENT_ID + "="
               + mClientId + " AND CacheType=" + Movie.CACHE_ID, null);

         if (rows != 1) {
            mDatabase.insert(CacheItemsSetting.TABLE_NAME, null, dbValues);
         }
         return setting;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      return null;
   }

   @Override
   public MovieFull getMovieDetails(int _movieId) {
      try {
         Cursor result = mDatabase.query("MovieDetails", null, CLIENT_ID + "=" + mClientId
               + " AND Id=" + _movieId, null, null, null, null);
         MovieFull movie = null;
         if (result.getCount() == 1) {
            result.moveToFirst();
            movie = (MovieFull) SqliteAnnotationsHelper
                  .getObjectFromCursor(result, MovieFull.class);
         }

         result.close();
         return movie;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      // something went wrong obviously
      return null;
   }

   @Override
   public void saveMovieDetails(MovieFull _movie) {
      try {
         ContentValues dbValues = SqliteAnnotationsHelper.getContentValuesFromObject(_movie,
               MovieFull.class);
         dbValues.put(CLIENT_ID, mClientId);

         int rows = mDatabase.update("MovieDetails", dbValues, CLIENT_ID + "=" + mClientId
               + " AND Id=" + _movie.getId(), null);

         if (rows != 1) {
            mDatabase.insert("MovieDetails", null, dbValues);
         }
         return;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
   }

   @Override
   public List<Series> getSeries(int _start, int _end) {
      try {
         Cursor result = mDatabase.query("Series", null, CLIENT_ID + "=" + mClientId, null, null,
               null, null);
         ArrayList<Series> series = null;
         if (result.getCount() > _end) {
            result.move(_start + 1);
            series = (ArrayList<Series>) SqliteAnnotationsHelper.getObjectsFromCursor(result,
                  Series.class, _end - _start + 1);
         }

         result.close();
         return series;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      // something went wrong obviously
      return null;
   }

   @Override
   public void saveSeries(Series _series) {
      try {
         ContentValues dbValues = SqliteAnnotationsHelper.getContentValuesFromObject(_series,
               Series.class);
         dbValues.put(CLIENT_ID, mClientId);

         int rows = mDatabase.update("Series", dbValues, CLIENT_ID + "=" + mClientId + " AND Id="
               + _series.getId(), null);

         if (rows != 1) {
            mDatabase.insert("Series", null, dbValues);
         }
         return;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      // something went wrong obviously
   }

   @Override
   public List<Series> getAllSeries() {
      try {
         Cursor result = mDatabase.query("Series", null, CLIENT_ID + "=" + mClientId, null, null,
               null, null);

         result.moveToFirst();
         ArrayList<Series> series = (ArrayList<Series>) SqliteAnnotationsHelper
               .getObjectsFromCursor(result, Series.class, 0);

         result.close();
         return series;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      // something went wrong obviously
      return null;
   }

   @Override
   public CacheItemsSetting getSeriesCount() {
      try {
         Cursor result = mDatabase.query(CacheItemsSetting.TABLE_NAME, null, CLIENT_ID + "="
               + mClientId + " AND CacheType=" + Series.CACHE_ID, null, null, null, null);

         result.moveToFirst();
         CacheItemsSetting setting = (CacheItemsSetting) SqliteAnnotationsHelper
               .getObjectFromCursor(result, CacheItemsSetting.class);
         result.close();
         return setting;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      // something went wrong obviously
      return null;
   }

   @Override
   public CacheItemsSetting setSeriesCount(int _seriesCount) {
      try {
         CacheItemsSetting setting = new CacheItemsSetting(Series.CACHE_ID, _seriesCount,
               new Date());
         ContentValues dbValues = SqliteAnnotationsHelper.getContentValuesFromObject(setting,
               CacheItemsSetting.class);
         dbValues.put(CLIENT_ID, mClientId);

         int rows = mDatabase.update(CacheItemsSetting.TABLE_NAME, dbValues, "CacheType="
               + Series.CACHE_ID, null);

         if (rows != 1) {
            mDatabase.insert(CacheItemsSetting.TABLE_NAME, null, dbValues);
         }
         return setting;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      return null;
   }

   @Override
   public SeriesFull getFullSeries(int _seriesId) {
      try {
         Cursor result = mDatabase.query("SeriesDetails", null, CLIENT_ID + "=" + mClientId
               + " AND Id=" + _seriesId, null, null, null, null);
         SeriesFull series = null;
         if (result.getCount() == 1) {
            result.moveToFirst();
            series = (SeriesFull) SqliteAnnotationsHelper.getObjectFromCursor(result,
                  SeriesFull.class);
         }
         result.close();
         return series;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      // something went wrong obviously
      return null;
   }

   @Override
   public void saveSeriesDetails(SeriesFull series) {
      try {
         ContentValues dbValues = SqliteAnnotationsHelper.getContentValuesFromObject(series,
               SeriesFull.class);
         dbValues.put(CLIENT_ID, mClientId);

         int rows = mDatabase.update("SeriesDetails", dbValues, CLIENT_ID + "=" + mClientId
               + " AND Id=" + series.getId(), null);

         if (rows != 1) {
            mDatabase.insert("SeriesDetails", null, dbValues);
         }
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
   }

   @Override
   public List<SeriesSeason> getAllSeasons(int _seriesId) {
      try {
         Cursor result = mDatabase.query("SeriesSeason", null, CLIENT_ID + "=" + mClientId
               + " AND SeriesId=" + _seriesId, null, null, null, null);

         result.moveToFirst();
         ArrayList<SeriesSeason> series = (ArrayList<SeriesSeason>) SqliteAnnotationsHelper
               .getObjectsFromCursor(result, SeriesSeason.class, 0);

         result.close();
         return series;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      // something went wrong obviously
      return null;
   }

   @Override
   public void saveSeason(SeriesSeason _season) {
      try {
         ContentValues dbValues = SqliteAnnotationsHelper.getContentValuesFromObject(_season,
               SeriesFull.class);
         dbValues.put(CLIENT_ID, mClientId);

         int rows = mDatabase.update("SeriesSeason", dbValues, CLIENT_ID + "=" + mClientId
               + " AND Id=" + _season.getId(), null);

         if (rows != 1) {
            mDatabase.insert("SeriesDetails", null, dbValues);
         }
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
   }

   @Override
   public List<SeriesEpisode> getAllEpisodes(int _seriesId) {
      try {
         Cursor result = mDatabase.query("SeriesEpisode", null, CLIENT_ID + "=" + mClientId
               + " AND SeriesId=" + _seriesId, null, null, null, null);

         result.moveToFirst();
         ArrayList<SeriesEpisode> series = (ArrayList<SeriesEpisode>) SqliteAnnotationsHelper
               .getObjectsFromCursor(result, SeriesEpisode.class, 0);

         result.close();
         return series;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      // something went wrong obviously
      return null;
   }

   @Override
   public void saveEpisode(SeriesEpisode _episode) {
      try {
         ContentValues dbValues = SqliteAnnotationsHelper.getContentValuesFromObject(_episode,
               SeriesFull.class);
         dbValues.put(CLIENT_ID, mClientId);

         int rows = mDatabase.update("SeriesEpisode", dbValues, null, new String[] {
               CLIENT_ID + "=" + mClientId, "Id=" + _episode.getId() });

         if (rows != 1) {
            mDatabase.insert("SeriesDetails", null, dbValues);
         }
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
   }

   @Override
   public List<SeriesEpisode> getAllEpisodesForSeason(int _seriesId, int _seasonNumber) {
      try {
         Cursor result = mDatabase.query("SeriesEpisode", null, CLIENT_ID + "=" + mClientId
               + " AND SeasonNumber=" + _seasonNumber + " AND SeriesId=" + _seriesId, null, null,
               null, null);

         result.moveToFirst();
         ArrayList<SeriesEpisode> series = (ArrayList<SeriesEpisode>) SqliteAnnotationsHelper
               .getObjectsFromCursor(result, SeriesEpisode.class, 0);

         result.close();
         return series;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      // something went wrong obviously
      return null;
   }
}
