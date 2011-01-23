package com.mediaportal.ampdroid.database;

import com.mediaportal.ampdroid.data.CacheItemsSetting;
import com.mediaportal.ampdroid.data.Movie;
import com.mediaportal.ampdroid.data.MovieFull;
import com.mediaportal.ampdroid.data.Series;
import com.mediaportal.ampdroid.data.SeriesFull;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MediaDatabaseHelper extends SQLiteOpenHelper {
   private String mSeriesTableString;
   private String mMovieTableString;
   private String mMovieDetailsTableString;
   private String mSeriesDetailsTableString;
   private String mCacheSettingsString;

   public MediaDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
      super(context, name, factory, version);
      mSeriesTableString = SqliteAnnotationsHelper.getCreateTableStringFromClass(Series.class);
      mSeriesDetailsTableString = SqliteAnnotationsHelper
            .getCreateTableStringFromClass(SeriesFull.class);

      mMovieTableString = SqliteAnnotationsHelper.getCreateTableStringFromClass(Movie.class);
      mMovieDetailsTableString = SqliteAnnotationsHelper
            .getCreateTableStringFromClass(MovieFull.class);
      mCacheSettingsString = SqliteAnnotationsHelper
            .getCreateTableStringFromClass(CacheItemsSetting.class);
   }

   @Override
   public void onCreate(SQLiteDatabase _db) {
      _db.execSQL(mSeriesTableString);
      _db.execSQL(mMovieTableString);
      _db.execSQL(mMovieDetailsTableString);
      _db.execSQL(mSeriesDetailsTableString);
      _db.execSQL(mCacheSettingsString);
   }

   @Override
   public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
      _db.execSQL("DROP TABLE IF EXISTS " + "Series");
      _db.execSQL("DROP TABLE IF EXISTS " + "Movies");
      _db.execSQL("DROP TABLE IF EXISTS " + "Movie");
      _db.execSQL("DROP TABLE IF EXISTS " + "MovieDetails");
      _db.execSQL("DROP TABLE IF EXISTS " + "SeriesDetails");
      _db.execSQL("DROP TABLE IF EXISTS " + "CacheSettings");
      onCreate(_db);

   }
}
