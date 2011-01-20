package com.mediaportal.ampdroid.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.mediaportal.ampdroid.api.IMediaAccessDatabase;
import com.mediaportal.ampdroid.data.Movie;

public class MediaAccessDatabaseHandler implements IMediaAccessDatabase {

   Context mContext;
   private MediaDatabaseHelper mDbHelper;
   private SQLiteDatabase mDatabase;

   public MediaAccessDatabaseHandler(Context _context) {
      mContext = _context;
      mDbHelper = new MediaDatabaseHelper(mContext, "amped_media", null, 5);
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
         Cursor result = mDatabase.query("Movies", null, null, null, null, null, null);
         ArrayList<Movie> movies = null;
         if (result.moveToFirst()) {
            movies = (ArrayList<Movie>) SqliteAnnotationsHelper
                  .getObjectsFromCursor(result, Movie.class, 0);
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
         Cursor result = mDatabase.query("Movies", null, null, null, null, null, null);

         result.move(_start+1);
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

         int rows = mDatabase.update("Movies", clubValues, "Id=" + movie.getId(), null);

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
   public int getMovieCount() {
      try {
         Cursor result = mDatabase.query("Movies", null, null, null, null, null, null);
         int count = result.getCount();
         result.close();
         return count;
      } catch (Exception ex) {
         Log.e("Database", ex.getMessage());
      }
      // something went wrong obviously
      return -99;
   }

}
