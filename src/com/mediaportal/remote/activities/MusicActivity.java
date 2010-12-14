package com.mediaportal.remote.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.mediaportal.remote.api.RemoteHandler;
import com.mediaportal.remote.data.MusicAlbum;

public class MusicActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.homescreen);

		RemoteHandler service = RemoteHandler.getCurrentRemoteInstance();

		try {
			List<MusicAlbum> albums = service.getAlbums(0, 10);
			Object i = albums;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
