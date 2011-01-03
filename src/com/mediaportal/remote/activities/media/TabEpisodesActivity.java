package com.mediaportal.remote.activities.media;

import java.net.URLEncoder;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.lists.ILoadingAdapterItem;
import com.mediaportal.remote.activities.lists.LazyLoadingAdapter;
import com.mediaportal.remote.activities.lists.Utils;
import com.mediaportal.remote.activities.lists.views.EpisodePosterViewAdapter;
import com.mediaportal.remote.activities.quickactions.ActionItem;
import com.mediaportal.remote.activities.quickactions.QuickAction;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.api.ItemDownloaderService;
import com.mediaportal.remote.data.EpisodeDetails;
import com.mediaportal.remote.data.EpisodeFile;
import com.mediaportal.remote.data.SeriesEpisode;

public class TabEpisodesActivity extends Activity {
   private ListView mlistView;
   private LazyLoadingAdapter mAdapter;
   MediaPlayer mMediaPlayer;

   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.tabepisodesactivity);
      mlistView = (ListView) findViewById(R.id.ListView);
      mMediaPlayer = new MediaPlayer();

      Bundle extras = getIntent().getExtras();
      if (extras != null) {
         int seriesId = extras.getInt("series_id");
         int seasonNumber = extras.getInt("season_number");

         DataHandler service = DataHandler.getCurrentRemoteInstance();

         ArrayList<SeriesEpisode> seasons = service.getAllEpisodesForSeason(seriesId, seasonNumber);
         mAdapter = new LazyLoadingAdapter(this, R.layout.listitem_thumb);
         for (int i = 0; i < seasons.size(); i++) {
            SeriesEpisode e = seasons.get(i);
            EpisodeDetails details = service.getEpisode(e.getId());
            mAdapter.AddItem(new EpisodePosterViewAdapter(seriesId, details));
         }

         mlistView.setAdapter(mAdapter);

      } else {// activity called without movie id (shouldn't happen ;))

      }

      mlistView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> _adapter, View _view, int _position, long _id) {
            ILoadingAdapterItem selectedItem = (ILoadingAdapterItem) mlistView
                  .getItemAtPosition(_position);

            SeriesEpisode selectedEp = (SeriesEpisode) selectedItem.getItem();
         }
      });

      mlistView.setOnItemLongClickListener(new OnItemLongClickListener() {
         @Override
         public boolean onItemLongClick(AdapterView<?> _item, View _view, final int _position, long _id) {
            QuickAction qa = new QuickAction(_view);
            ActionItem sdCardAction = new ActionItem();

            sdCardAction.setTitle("Download to sd card");
            sdCardAction.setIcon(getResources().getDrawable(R.drawable.quickaction_sdcard));

            final EpisodeDetails selected = (EpisodeDetails) ((ILoadingAdapterItem) _item
                  .getItemAtPosition(_position)).getItem();

            sdCardAction.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View _view) {
                  EpisodeFile epFile = selected.getEpisodeFile();
                  String fileName = Utils.getFileNameWithExtension(epFile.getFileName(), "\\");
                  String url = "http://10.1.0.247:4322/json/FS_GetMediaItem/?path="
                        + URLEncoder.encode(epFile.getFileName());
                  Intent download = new Intent(_view.getContext(), ItemDownloaderService.class);
                  download.putExtra("url", url);
                  download.putExtra("name", fileName);
                  startService(download);

                  Toast
                        .makeText(_view.getContext(), "Url: " + epFile.getFileName(),
                              Toast.LENGTH_SHORT).show();
               }
            });
            qa.addActionItem(sdCardAction);
            qa.setAnimStyle(QuickAction.ANIM_AUTO);

            qa.show();
            return true;
         }
      });

   }

   @Override
   public void onDestroy() {
      mAdapter.imageLoader.stopThread();
      mlistView.setAdapter(null);
      super.onDestroy();
   }
}
