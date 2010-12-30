package com.mediaportal.remote.activities.media;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.MusicActivity;
import com.mediaportal.remote.activities.lists.ILoadingAdapterItem;
import com.mediaportal.remote.activities.lists.LazyLoadingAdapter;
import com.mediaportal.remote.activities.lists.views.SeriesPosterViewAdapter;
import com.mediaportal.remote.activities.lists.views.SeriesThumbViewAdapter;
import com.mediaportal.remote.activities.quickactions.ActionItem;
import com.mediaportal.remote.activities.quickactions.QuickAction;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.data.Series;

public class TabSeriesActivity extends Activity {
   private ListView m_listView;
   private LazyLoadingAdapter adapter;

   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.tabseriesactivity);

      DataHandler service = DataHandler.getCurrentRemoteInstance();
      List<Series> series = service.getAllSeries();
      // List<TvSeason> season = service.getAllSeasons(series.get(0).getId());

      adapter = new LazyLoadingAdapter(this, R.layout.listitem_poster);

      if (series != null) {
         for (Series s : series) {
            adapter.AddItem(new SeriesPosterViewAdapter(s));
         }
      }

      m_listView = (ListView) findViewById(R.id.ListViewVideos);
      m_listView.setAdapter(adapter);

      m_listView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            ILoadingAdapterItem selectedItem = (ILoadingAdapterItem) m_listView
                  .getItemAtPosition(position);
            // Toast toast = Toast.makeText(v.getContext(), selectedSeries
            // .getTitle(), Toast.LENGTH_SHORT);
            // toast.show();
            Series selectedSeries = (Series) selectedItem.getItem();
            Intent myIntent = new Intent(v.getContext(), TabSeriesDetailsActivity.class);
            myIntent.putExtra("series_id", selectedSeries.getId());
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //startActivity(myIntent);

            // Create the view using FirstGroup's LocalActivityManager
            View view = TabSeriesActivityGroup.group.getLocalActivityManager().startActivity("series_details",
                  myIntent).getDecorView();

            // Again, replace the view
            TabSeriesActivityGroup.group.replaceView(view);
         }
      });

      final ActionItem chart = new ActionItem();

      chart.setTitle("Chart");
      chart.setIcon(getResources().getDrawable(R.drawable.chart));
      chart.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            Toast.makeText(v.getContext(), "Chart selected", Toast.LENGTH_SHORT).show();
         }
      });

      final ActionItem production = new ActionItem();

      production.setTitle("Products");
      production.setIcon(getResources().getDrawable(R.drawable.production));
      production.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            Toast.makeText(v.getContext(), "Products selected", Toast.LENGTH_SHORT).show();
         }
      });

      m_listView.setOnItemLongClickListener(new OnItemLongClickListener() {
         @Override
         public boolean onItemLongClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
            QuickAction qa = new QuickAction(v);

            qa.addActionItem(chart);
            qa.addActionItem(production);
            qa.setAnimStyle(QuickAction.ANIM_AUTO);

            qa.show();
            return true;
         }
      });

   }
}
