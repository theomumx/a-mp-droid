package com.mediaportal.remote.api.database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

import android.content.Context;
import com.mediaportal.remote.data.RemoteClient;

public class RemoteClientFactory {
   public static ODB mDatabase;

   public static void openDatabase(Context _context) {
      if (mDatabase == null) {
         File directory = _context.getDir("database", Context.MODE_PRIVATE);
         String fileName = directory.getAbsolutePath()+"/test-android.neodatis";
         mDatabase = ODBFactory.open(fileName);
      }
   }

   public static boolean isDatabaseOpen() {
      return mDatabase != null;
   }

   public static void closeDatabase() {
      if (mDatabase != null) {
         mDatabase.close();
         mDatabase = null;
      }
   }

   public static List<RemoteClient> getClients() {
      if (mDatabase != null) {
         // Retrieve all users 
         Objects<RemoteClient> users = mDatabase.getObjects(RemoteClient.class);

         List<RemoteClient> clients = new ArrayList<RemoteClient>();

         for (Object c : users) {
            clients.add((RemoteClient) c);
         }

         return clients;
      } else {
         return null;
      }
   }

   public static void createRemoteClient(RemoteClient _client) {
      if (mDatabase != null) {
         mDatabase.store(_client);
      }
   }

   public static void updateRemoteClient(RemoteClient _client) {
      if (mDatabase != null) {
         mDatabase.store(_client);
      }
   }

   public static void removeRemoteClient(RemoteClient _client) {
      if (mDatabase != null) {
         mDatabase.delete(_client);
      }
   }
}
