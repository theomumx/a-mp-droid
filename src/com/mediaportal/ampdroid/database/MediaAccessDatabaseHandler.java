package com.mediaportal.ampdroid.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.mediaportal.ampdroid.api.IMediaAccessDatabase;
import com.mediaportal.ampdroid.data.CacheItemsSetting;
import com.mediaportal.ampdroid.data.Movie;
import com.mediaportal.ampdroid.data.MovieFull;
import com.mediaportal.ampdroid.data.Series;
import com.mediaportal.ampdroid.data.SeriesFull;

public class MediaAccessDatabaseHandler implements IMediaAccessDatabase {

   Context mContext;
   private MediaDatabaseHelper mDbHelper;
   private SQLiteDatabase mDatabase;

   public MediaAccessDatabaseHandler(Context _context) {
      mContext = _context;
      mDbHelper = new MediaDatabaseHelper(mContext, "ampdroid_media", null, 20);
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
         Cursor result = mDatabase.query(Movie.TABLE_NAME, null, null, null, null, null, null);
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
         Cursor result = mDatabase.query(Movie.TABLE_NAME, null, null, null, null, null, null);

         result.move(_start + 1);
         ArrayList<Movie> movies = (ArrayList<Movie>) SqliteAnnotationsHelper.getObjectsFromCursor(
               result, Movie.class, _end - _start + 1);

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
         ContentValues clubValues = SqliteAnnotationsHelper.getContentValuesFromObject(movie,
               Movie.class);

         int rows = mDatabase.update(Movie.TABLE_NAME, clubValues, "Id=" + movie.getId(), null);

         if (rows != 1) {
            mDatabase.insert("Movies", null, clubValues);
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
         Cursor result = mDatabase.query(CacheItemsSetting.TABLE_NAME, null, "CacheType="
               + Movie.CACHE_ID, null, null, null, null);

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

         int rows = mDatabase.update(CacheItemsSetting.TABLE_NAME, dbValues, "CacheType="
               + Movie.CACHE_ID, null);

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
         Cursor result = mDatabase.query("MovieDetails", null, "Id=" + _movieId, null, null, null,
               null);

         result.moveToFirst();
         MovieFull movie = (MovieFull) SqliteAnnotationsHelper.getObjectFromCursor(result,
               MovieFull.class);

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

         int rows = mDatabase.update("MovieDetails", dbValues, "Id=" + _movie.getId(), null);

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
         Cursor result = mDatabase.query("Series", null, null, null, null, null, null);
         ArrayList<Series> series = null;
         if (result.getCount() > _start) {
            if (result.getCount() > _end) {
               result.move(_start + 1);
               series = (ArrayList<Series>) SqliteAnnotationsHelper.getObjectsFromCursor(result,
                     Series.class, _end - _start + 1);
            }
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
         ContentValues clubValues = SqliteAnnotationsHelper.getContentValuesFromObject(_series,
               Series.class);

         int rows = mDatabase.update("Series", clubValues, "Id=" + _series.getId(), null);

         if (rows != 1) {
            mDatabase.insert("Series", null, clubValues);
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
         Cursor result = mDatabase.query("Series", null, null, null, null, null, null);

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
         Cursor result = mDatabase.query(CacheItemsSetting.TABLE_NAME, null, "CacheType="
               + Series.CACHE_ID, null, null, null, null);

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
         Cursor result = mDatabase.query("SeriesDetails", null, "Id=" + _seriesId, null, null,
               null, null);
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

         int rows = mDatabase.update("SeriesDetails", dbValues, "Id=" + series.getId(), null);

         if (rows != 1) {
            mDatabase.insert("SeriesDetails", null, dbValues);
         }
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
   }
}
