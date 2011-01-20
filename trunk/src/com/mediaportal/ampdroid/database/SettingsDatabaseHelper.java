package com.mediaportal.ampdroid.database;

import java.lang.annotation.Annotation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.mediaportal.ampdroid.data.Movie;
import com.mediaportal.ampdroid.data.RemoteClientSetting;
import com.mediaportal.ampdroid.data.RemoteFunction;
import com.mediaportal.ampdroid.data.Series;
import com.mediaportal.ampdroid.data.TvChannelDetails;

public class SettingsDatabaseHelper extends SQLiteOpenHelper  {
   private String mRemoteClientTableString;
   private String mFunctionsTableString;
   public SettingsDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
      super(context, name, factory, version);

      mRemoteClientTableString = SqliteAnnotationsHelper.getCreateTableStringFromClass(RemoteClientSetting.class);
      mFunctionsTableString = SqliteAnnotationsHelper.getCreateTableStringFromClass(RemoteFunction.class);

   }

   @Override
   public void onCreate(SQLiteDatabase _db) {
      _db.execSQL(mRemoteClientTableString);
      _db.execSQL(mFunctionsTableString);

   }

   @Override
   public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
      _db.execSQL("DROP TABLE IF EXISTS " + "RemoteClients");
      _db.execSQL("DROP TABLE IF EXISTS " + "RemoteFunctions");
      onCreate(_db);

   }


}
