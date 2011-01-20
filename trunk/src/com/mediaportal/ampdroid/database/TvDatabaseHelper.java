package com.mediaportal.ampdroid.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mediaportal.ampdroid.data.TvChannelDetails;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TvDatabaseHelper extends SQLiteOpenHelper {

   public TvDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
      super(context, name, factory, version);
      // TODO Auto-generated constructor stub
   }

   @Override
   public void onCreate(SQLiteDatabase db) {


   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      // TODO Auto-generated method stub

   }
   

}
