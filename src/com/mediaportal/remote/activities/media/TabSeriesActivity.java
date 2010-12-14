package com.mediaportal.remote.activities.media;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.lists.LazyLoadingAdapter;
import com.mediaportal.remote.api.RemoteHandler;
import com.mediaportal.remote.data.Series;

public class TabSeriesActivity extends Activity {
	private ListView m_listView;
	private LazyLoadingAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabseriesactivity);

		RemoteHandler service = RemoteHandler.getCurrentRemoteInstance();
		List<Series> series = service.getAllSeries();
		// List<TvSeason> season = service.getAllSeasons(series.get(0).getId());

		adapter = new LazyLoadingAdapter(this);

		if (series != null) {
			for (Series s : series) {
				adapter.AddItem(s);
			}
		}

		m_listView = (ListView) findViewById(R.id.ListViewVideos);
		m_listView.setAdapter(adapter);

		m_listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Series selectedSeries = (Series) m_listView
						.getItemAtPosition(position);
				Toast toast = Toast.makeText(v.getContext(), selectedSeries
						.getPrettyName(), Toast.LENGTH_SHORT);
				toast.show();
			}
		});
	}
}
