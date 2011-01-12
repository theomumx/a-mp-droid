package com.mediaportal.ampdroid.api.database;

import java.util.ArrayList;

import android.content.Context;

import com.mediaportal.ampdroid.api.IMediaAccessDatabase;
import com.mediaportal.ampdroid.data.Movie;

public class MediaAccessDatabaseHandler implements IMediaAccessDatabase {

   Context mContext;

   public MediaAccessDatabaseHandler(Context _context) {
      mContext = _context;
   }

   @Override
   public ArrayList<Movie> getAllMovies() {
      /*ServerConfiguration configuration = Db4oClientServer.newServerConfiguration();
      configuration.common().allowVersionUpdates(true);
      configuration.common().activationDepth(DEEP);
      ObjectServer server = Db4oClientServer.openServer(configuration, getDDBBFile(DDBB_FILE), 0);
       */
      return null;
   }

   @Override
   public void saveMovie(Movie movie) {
      // TODO Auto-generated method stub

   }

}
