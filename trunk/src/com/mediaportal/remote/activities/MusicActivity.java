package com.mediaportal.remote.activities;

import android.app.Activity;
import android.os.Bundle;

import com.mediaportal.remote.R;

public class MusicActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.musicactivity);

		/*RemoteHandler service = RemoteHandler.getCurrentRemoteInstance();

		try {
			List<MusicAlbum> albums = service.getAlbums(0, 10);
			Object i = albums;
		} catch (Exception e) {
			e.printStackTrace();
		}*/

	}
}
