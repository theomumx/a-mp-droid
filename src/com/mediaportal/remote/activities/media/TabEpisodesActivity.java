package com.mediaportal.remote.activities.media;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.lists.ILoadingAdapterItem;
import com.mediaportal.remote.activities.lists.LazyLoadingAdapter;
import com.mediaportal.remote.activities.lists.views.EpisodePosterViewAdapter;
import com.mediaportal.remote.activities.quickactions.QuickAction;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.data.Series;
import com.mediaportal.remote.data.SeriesEpisode;
import com.mediaportal.remote.data.SeriesFull;

public class TabEpisodesActivity extends Activity {
	private ListView m_listView;
	private LazyLoadingAdapter adapter;
	MediaPlayer mMediaPlayer;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabepisodesactivity);
		m_listView = (ListView) findViewById(R.id.ListView);
		mMediaPlayer = new MediaPlayer();

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			int seriesId = extras.getInt("series_id");
			int seasonNumber = extras.getInt("season_number");
			
         DataHandler service = DataHandler.getCurrentRemoteInstance();

			ArrayList<SeriesEpisode> seasons = service.getAllEpisodesForSeason(
					seriesId, seasonNumber);
			adapter = new LazyLoadingAdapter(this, R.layout.listitem_thumb);
			for (int i = 0; i < seasons.size(); i++) {
			   SeriesEpisode e = seasons.get(i);
				adapter.AddItem(new EpisodePosterViewAdapter(e));
			}

			m_listView.setAdapter(adapter);

		} else {// activity called without movie id (shouldn't happen ;))

		}

		m_listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				ILoadingAdapterItem selectedItem = (ILoadingAdapterItem) m_listView
						.getItemAtPosition(position);
				
				SeriesEpisode selectedEp = (SeriesEpisode) selectedItem.getItem();
			}
		});
		
      m_listView.setOnItemLongClickListener(new OnItemLongClickListener() {
         @Override
         public boolean onItemLongClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
            QuickAction qa = new QuickAction(v);
            
            qa.setAnimStyle(QuickAction.ANIM_AUTO);

            qa.show();
            return true;
         }
      });

	}

	@Override
	public void onDestroy() {
		adapter.imageLoader.stopThread();
		m_listView.setAdapter(null);
		super.onDestroy();
	}
}
