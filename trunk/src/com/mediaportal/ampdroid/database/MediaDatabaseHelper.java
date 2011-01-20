package com.mediaportal.ampdroid.database;

import com.mediaportal.ampdroid.data.Movie;
import com.mediaportal.ampdroid.data.Series;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MediaDatabaseHelper extends SQLiteOpenHelper {
   private String mSeriesTableString;
   private String mMovieTableString;
   public MediaDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
      super(context, name, factory, version);
      mSeriesTableString = SqliteAnnotationsHelper.getCreateTableStringFromClass(Series.class);
      mMovieTableString = SqliteAnnotationsHelper.getCreateTableStringFromClass(Movie.class);
      
   }

   @Override
   public void onCreate(SQLiteDatabase _db) {
      _db.execSQL(mSeriesTableString);
      _db.execSQL(mMovieTableString);

   }

   @Override
   public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
      _db.execSQL("DROP TABLE IF EXISTS " + "Series");
      _db.execSQL("DROP TABLE IF EXISTS " + "Movies");
      _db.execSQL("DROP TABLE IF EXISTS " + "Movie");
      onCreate(_db);

   }
}
