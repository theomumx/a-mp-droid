package com.mediaportal.remote.activities;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.settings.SettingsActivity;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.api.database.RemoteClientFactory;
import com.mediaportal.remote.data.RemoteClient;
import com.mediaportal.remote.utils.Util;

public class WelcomeScreenActivity extends Activity {
   private class StartupTask extends AsyncTask<RemoteClient, String, Boolean> {

      private Context mContext;

      public StartupTask(Context _context) {
         mContext = _context;
      }

      @Override
      protected Boolean doInBackground(RemoteClient... _clients) {

         DataHandler.setupRemoteHandler(_clients[0], false);
         
         try {
            Thread.sleep(0);// for testing, show welcomescreen longer than
            // necessary
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         return true;
      }

      @Override
      protected void onProgressUpdate(String... _result) {

      }

      @Override
      protected void onPostExecute(Boolean _result) {

         Intent myIntent = new Intent(mContext, HomeActivity.class);
         startActivity(myIntent);
      }
   }

   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.welcomescreen);

      // RemoteClientFactory.openDatabase(this);
      // Client for diebagger -> until setting screen ready
      // final RemoteClient client = new RemoteClient(0, "Bagga Server");

      // GmaWebserviceApi api = new GmaWebserviceApi("10.1.0.247", 4322);
      // client.setRemoteAccessApi(api);

      // Tv4HomeApi tvApi = new Tv4HomeApi("10.1.0.166", 4321);
      // client.setTvControlApi(tvApi);

      // WifiRemoteMpController clientApi = new
      // WifiRemoteMpController("10.1.0.247", 8017);
      // client.setClientControlApi(clientApi);
      // RemoteClientFactory.createRemoteClient(client);
      // RemoteClientFactory.closeDatabase();

     
      //RemoteClientFactory.closeDatabase();





   }
   
   
   
   @Override
   protected void onStart() {
      final ProgressBar progress = (ProgressBar) findViewById(R.id.ProgressBarWelcomeScreen);
      progress.setVisibility(View.INVISIBLE);
      final Button connectButton = (Button) findViewById(R.id.ButtonConnect);
      connectButton.setEnabled(true);
      
      final Spinner spinner = (Spinner) findViewById(R.id.SpinnerSelectClients);
      
      RemoteClientFactory.openDatabase(this);

      List<RemoteClient> clients = RemoteClientFactory.getClients();
      
      connectButton.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View _view) {
            StartupTask task = new StartupTask(_view.getContext());
            RemoteClient client = (RemoteClient) spinner.getSelectedItem();
            if (client != null) {
               task.execute(client);
               progress.setVisibility(View.VISIBLE);
               progress.setIndeterminate(true);
               connectButton.setEnabled(false);
            }
            else{
               Util.showToast(_view.getContext(), "Please select client");
            }
         }
      });
      
      if (clients != null && clients.size() > 0) {
         ArrayAdapter<RemoteClient> adapter = new ArrayAdapter<RemoteClient>(this,
               android.R.layout.simple_spinner_item, clients);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spinner.setAdapter(adapter);
      }
      
      super.onStart();
   }



   @Override
   public boolean onCreateOptionsMenu(Menu _menu) {
      MenuItem settingsItem = _menu.add(0, Menu.FIRST, Menu.NONE, "Settings");
      settingsItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
         @Override
         public boolean onMenuItemClick(MenuItem item) {
            startSettings();
            return true;
         }
      });

      return true;
   }
   
   private void startSettings() {
      Intent settingsIntent = new Intent(this, SettingsActivity.class);
      startActivity(settingsIntent);
      //startActivityForResult(settingsIntent, 0);
   }

   @Override
   public void onAttachedToWindow() {
      super.onAttachedToWindow();
      Window window = getWindow();
      window.setFormat(PixelFormat.RGBA_8888);
   }
}