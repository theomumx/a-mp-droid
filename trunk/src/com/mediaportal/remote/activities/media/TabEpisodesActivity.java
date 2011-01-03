package com.mediaportal.remote.activities.media;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import com.mediaportal.remote.activities.lists.views.EpisodePosterViewAdapter;
import com.mediaportal.remote.activities.quickactions.ActionItem;
import com.mediaportal.remote.activities.quickactions.QuickAction;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.api.ItemDownloaderService;
import com.mediaportal.remote.data.EpisodeDetails;
import com.mediaportal.remote.data.EpisodeFile;
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

         ArrayList<SeriesEpisode> seasons = service.getAllEpisodesForSeason(seriesId, seasonNumber);
         adapter = new LazyLoadingAdapter(this, R.layout.listitem_thumb);
         for (int i = 0; i < seasons.size(); i++) {
            SeriesEpisode e = seasons.get(i);
            EpisodeDetails details = service.getEpisode(e.getId());
            adapter.AddItem(new EpisodePosterViewAdapter(seriesId, details));
         }

         m_listView.setAdapter(adapter);

      } else {// activity called without movie id (shouldn't happen ;))

      }

      m_listView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            ILoadingAdapterItem selectedItem = (ILoadingAdapterItem) m_listView
                  .getItemAtPosition(position);

            SeriesEpisode selectedEp = (SeriesEpisode) selectedItem.getItem();
         }
      });

      m_listView.setOnItemLongClickListener(new OnItemLongClickListener() {
         @Override
         public boolean onItemLongClick(AdapterView<?> arg0, View v, final int arg2, long arg3) {
            QuickAction qa = new QuickAction(v);
            ActionItem sdCardAction = new ActionItem();

            sdCardAction.setTitle("Download to sd card");
            sdCardAction.setIcon(getResources().getDrawable(R.drawable.quickaction_sdcard));

            final EpisodeDetails selected = (EpisodeDetails) ((ILoadingAdapterItem) arg0
                  .getItemAtPosition(arg2)).getItem();

            sdCardAction.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                  EpisodeFile epFile = selected.getEpisodeFile();
                  Filename fileInfo = new Filename(epFile.getFileName(), '\\', '.');
                  String url = "http://10.1.0.247:4322/json/FS_GetMediaItem/?path="
                        + URLEncoder.encode(epFile.getFileName());
                  String name = fileInfo.filename() + "." + fileInfo.extension();
                  Intent download = new Intent(v.getContext(), ItemDownloaderService.class);
                  download.putExtra("url", url);
                  download.putExtra("name", name);
                  startService(download);

                  Toast
                        .makeText(v.getContext(), "Url: " + epFile.getFileName(),
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

   /**
    * This class assumes that the string used to initialize fullPath has a
    * directory path, filename, and extension. The methods won't work if it
    * doesn't.
    */
   class Filename {
      private String fullPath;
      private char pathSeparator, extensionSeparator;

      public Filename(String str, char sep, char ext) {
         fullPath = str;
         pathSeparator = sep;
         extensionSeparator = ext;
      }

      public String extension() {
         int dot = fullPath.lastIndexOf(extensionSeparator);
         return fullPath.substring(dot + 1);
      }

      public String filename() { // gets filename without extension
         int dot = fullPath.lastIndexOf(extensionSeparator);
         int sep = fullPath.lastIndexOf(pathSeparator);
         return fullPath.substring(sep + 1, dot);
      }

      public String path() {
         int sep = fullPath.lastIndexOf(pathSeparator);
         return fullPath.substring(0, sep);
      }
   }

   @Override
   public void onDestroy() {
      adapter.imageLoader.stopThread();
      m_listView.setAdapter(null);
      super.onDestroy();
   }
}
