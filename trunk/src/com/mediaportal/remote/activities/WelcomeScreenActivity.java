package com.mediaportal.remote.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.mediaportal.remote.R;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.api.gmawebservice.GmaWebserviceApi;
import com.mediaportal.remote.api.tv4home.Tv4HomeApi;
import com.mediaportal.remote.api.wifiremote.WifiRemoteMpController;
import com.mediaportal.remote.data.RemoteClient;

public class WelcomeScreenActivity extends Activity {
	private class StartupTask extends AsyncTask<RemoteClient, String, Boolean> {

		private Context mContext;

		public StartupTask(Context _context) {
			mContext = _context;
		}

		@Override
		protected Boolean doInBackground(RemoteClient... _clients) {
			

         
         DataHandler.setupRemoteHandler(_clients[0]);
         DataHandler.setCurrentRemoteInstance(_clients[0].getClientId());
         
         try {
            Thread.sleep(0);//for testing, show welcomescreen longer than necessary
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

			Intent myIntent = new Intent(mContext,
					HomeActivity.class);
			startActivity(myIntent);
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.welcomescreen);
		
		//Client for diebagger -> until setting screen ready
      final RemoteClient client = new RemoteClient(0, "Bagga Server");
      
      GmaWebserviceApi api = new GmaWebserviceApi("10.0.0.27", 4322);
      client.setRemoteAccessApi(api);
      
      Tv4HomeApi tvApi = new Tv4HomeApi("10.1.0.166", 4321);
      client.setTvControlApi(tvApi);
      
      WifiRemoteMpController clientApi = new WifiRemoteMpController("10.0.0.27", 8017);
      client.setClientControlApi(clientApi);
		
      
      
      RemoteClient[] items = new RemoteClient[1];
      items[0] = client;
      
		Spinner spinner = (Spinner) findViewById(R.id.SpinnerSelectClients);
		ArrayAdapter<RemoteClient> adapter = new ArrayAdapter<RemoteClient>(this,
		            android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		final ProgressBar progress = (ProgressBar)findViewById(R.id.ProgressBarWelcomeScreen);
		final Button connectButton = (Button)findViewById(R.id.ButtonConnect);
		connectButton.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View _view) {
            StartupTask task = new StartupTask(_view.getContext());
            task.execute(client);  
            progress.setVisibility(View.VISIBLE);
            progress.setIndeterminate(true);
            connectButton.setEnabled(false);
         }
      });
		

	}
	
	  @Override
	   public void onAttachedToWindow() {
	      super.onAttachedToWindow();
	      Window window = getWindow();
	      window.setFormat(PixelFormat.RGBA_8888);
	   }
}