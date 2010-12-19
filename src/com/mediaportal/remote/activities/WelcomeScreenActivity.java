package com.mediaportal.remote.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.mediaportal.remote.R;
import com.mediaportal.remote.api.RemoteHandler;
import com.mediaportal.remote.api.gmawebservice.GmaWebserviceApi;
import com.mediaportal.remote.api.tv4home.Tv4HomeApi;
import com.mediaportal.remote.api.wifiremote.WifiRemoteMpController;
import com.mediaportal.remote.data.RemoteClient;

public class WelcomeScreenActivity extends Activity {
	private class StartupTask extends AsyncTask<String, String, Boolean> {

		private Context mContext;

		public StartupTask(Context _context) {
			mContext = _context;
		}

		@Override
		protected Boolean doInBackground(String... _clubs) {
			
         RemoteClient client = new RemoteClient(0);
         
         GmaWebserviceApi api = new GmaWebserviceApi("bagga-laptop", 4322);
         client.setRemoteAccessApi(api);
         
         Tv4HomeApi tvApi = new Tv4HomeApi("10.1.0.167", 4321);
         client.setTvControlApi(tvApi);
         
         WifiRemoteMpController clientApi = new WifiRemoteMpController("10.1.0.247", 8017);
         client.setClientControlApi(clientApi);
         
         RemoteHandler.setupRemoteHandler(client);
         RemoteHandler.setCurrentRemoteInstance(client.getClientId());
         
         try {
            Thread.sleep(0);//for testing, show welcomescreen longer than necessary
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         return true;
		}

		@Override
		protected void onProgressUpdate(String... result) {

		}

		@Override
		protected void onPostExecute(Boolean result) {

			Intent myIntent = new Intent(mContext,
					HomeActivity.class);
			startActivity(myIntent);
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcomescreen);
		
		StartupTask task = new StartupTask(this);
		task.execute();
	}
}