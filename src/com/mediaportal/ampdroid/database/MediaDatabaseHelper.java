package com.mediaportal.ampdroid.database;

import com.mediaportal.ampdroid.data.CacheItemsSetting;
import com.mediaportal.ampdroid.data.EpisodeDetails;
import com.mediaportal.ampdroid.data.Movie;
import com.mediaportal.ampdroid.data.MovieFull;
import com.mediaportal.ampdroid.data.RemoteFunction;
import com.mediaportal.ampdroid.data.Series;
import com.mediaportal.ampdroid.data.SeriesEpisode;
import com.mediaportal.ampdroid.data.SeriesFull;
import com.mediaportal.ampdroid.data.SeriesSeason;
import com.mediaportal.ampdroid.data.SupportedFunctions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MediaDatabaseHelper extends SQLiteOpenHelper {
   private String mSeriesTableString;
   private String mMovieTableString;
   private String mMovieDetailsTableString;
   private String mSeriesDetailsTableString;
   private String mSeriesSeasonTableString;
   private String mSeriesEpisodeTableString;
   private String mSeriesEpisodeDetailsTableString;
   private String mCacheSettingsString;
   private String mSupportedFunctionsString;

   public MediaDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
      super(context, name, factory, version);
      mSeriesTableString = SqliteAnnotationsHelper
            .getCreateTableStringFromClass(Series.class, true);
      mSeriesDetailsTableString = SqliteAnnotationsHelper.getCreateTableStringFromClass(
            SeriesFull.class, true);
      mSeriesSeasonTableString = SqliteAnnotationsHelper.getCreateTableStringFromClass(
            SeriesSeason.class, true);
      mSeriesEpisodeTableString = SqliteAnnotationsHelper.getCreateTableStringFromClass(
            SeriesEpisode.class, true);
      mSeriesEpisodeDetailsTableString = SqliteAnnotationsHelper.getCreateTableStringFromClass(
            EpisodeDetails.class, true);

      mMovieTableString = SqliteAnnotationsHelper.getCreateTableStringFromClass(Movie.class, true);
      mMovieDetailsTableString = SqliteAnnotationsHelper.getCreateTableStringFromClass(
            MovieFull.class, true);
      mCacheSettingsString = SqliteAnnotationsHelper.getCreateTableStringFromClass(
            CacheItemsSetting.class, true);
      mSupportedFunctionsString = SqliteAnnotationsHelper.getCreateTableStringFromClass(
            SupportedFunctions.class, true);
   }

   @Override
   public void onCreate(SQLiteDatabase _db) {
      _db.execSQL(mSeriesTableString);
      _db.execSQL(mSeriesDetailsTableString);
      _db.execSQL(mSeriesSeasonTableString);
      _db.execSQL(mSeriesEpisodeTableString);
      _db.execSQL(mSeriesEpisodeDetailsTableString);
      
      _db.execSQL(mMovieTableString);
      _db.execSQL(mMovieDetailsTableString);
      
      _db.execSQL(mCacheSettingsString);
      _db.execSQL(mSupportedFunctionsString);
   }

   @Override
   public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
      _db.execSQL("DROP TABLE IF EXISTS " + "Series");
      _db.execSQL("DROP TABLE IF EXISTS " + "SeriesDetails");
      _db.execSQL("DROP TABLE IF EXISTS " + "SeriesSeason");
      _db.execSQL("DROP TABLE IF EXISTS " + "SeriesSeasons");
      _db.execSQL("DROP TABLE IF EXISTS " + "SeriesEpisodes");
      _db.execSQL("DROP TABLE IF EXISTS " + "SeriesEpisodeDetails");
      _db.execSQL("DROP TABLE IF EXISTS " + "SeriesDetails");
      
      _db.execSQL("DROP TABLE IF EXISTS " + "Movies");
      _db.execSQL("DROP TABLE IF EXISTS " + "Movie");
      _db.execSQL("DROP TABLE IF EXISTS " + "MovieDetails");
      
      _db.execSQL("DROP TABLE IF EXISTS " + "CacheSettings");
      _db.execSQL("DROP TABLE IF EXISTS " + "SupportedFunctions");
      
      onCreate(_db);

   }
}
