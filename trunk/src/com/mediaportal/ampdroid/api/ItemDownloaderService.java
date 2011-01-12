package com.mediaportal.remote.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.mediaportal.remote.R;
import com.mediaportal.remote.lists.Utils;
import com.mediaportal.remote.utils.DownloaderUtils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class ItemDownloaderService extends Service {
   public static final String ITEM_DOWNLOAD_STARTED = "download_started";
   public static final String ITEM_DOWNLOAD_PROGRESSED = "download_progressed";
   public static final String ITEM_DOWNLOAD_FINISHED = "download_finished";
   public static final int NOTIFICATION_ID = 44;

   private Intent mIntent;
   private ArrayList<DownloadJob> mDownloadJobs;
   private AsyncTask<String, Integer, Boolean> mDownloader;

   private class DownloadJob {
      private int mId;
      private String mName;
      private String mUrl;

      public int getId() {
         return mId;
      }

      public void setId(int _id) {
         this.mId = _id;
      }

      public String getName() {
         return mName;
      }

      public void setName(String _name) {
         this.mName = _name;
      }

      public String getUrl() {
         return mUrl;
      }

      public void setUrl(String _url) {
         this.mUrl = _url;
      }

      private DownloadJob(int _id, String _name, String _url) {
         mId = _id;
         mName = _name;
         mUrl = _url;
      }
   }

   private class DownloaderTask extends AsyncTask<String, Integer, Boolean> {
      private Notification mNotification;
      private NotificationManager mNotificationManager;
      private DownloadJob mCurrentJob;
      private int mNumberOfJobs;

      private DownloaderTask() {
         mNumberOfJobs = 0;
      }

      @Override
      protected Boolean doInBackground(String... params) {
         while (mDownloadJobs != null && mDownloadJobs.size() > 0) {
            DownloadJob topmostTask = null;
            synchronized (mDownloadJobs) {
               topmostTask = mDownloadJobs.get(0);
               mDownloadJobs.remove(0);
            }

            if (downloadFile(topmostTask)) {
               // download succeeded -> next file

            } else {
               // TODO: download failed -> retry?
            }
         }

         return true;
      }

      private boolean downloadFile(DownloadJob _job) {
         try {
            mNumberOfJobs++;
            mCurrentJob = _job;
            URL myFileUrl = new URL(_job.getUrl());
            String myFileName = _job.getName();

            // configure the intent
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                  mIntent, 0);

            // configure the notification
            mNotification = new Notification(R.drawable.quickaction_sdcard, "Download",
                  System.currentTimeMillis());
            mNotification.flags = mNotification.flags | Notification.FLAG_ONGOING_EVENT;
            mNotification.contentView = new RemoteViews(getApplicationContext().getPackageName(),
                  R.layout.download_progress);
            mNotification.contentIntent = pendingIntent;
            mNotification.contentView.setImageViewResource(R.id.status_icon, R.drawable.icon);

            createNotificationText(0);

            mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(
                  getApplicationContext().NOTIFICATION_SERVICE);
            mNotificationManager.notify(NOTIFICATION_ID, mNotification);

            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream inputStream = conn.getInputStream();

            File downloadFile = new File(DownloaderUtils.getBaseDirectory() + "/" + myFileName);
            File donwloadDir = new File(Utils.getFolder(downloadFile.toString(), "/"));
            
            if (!donwloadDir.exists()) {
               if (donwloadDir.mkdirs()) {
                  Log.d("ItemDownloaderService", "created directory on sd card");
               }
            }

            OutputStream out = new FileOutputStream(downloadFile);
            byte buf[] = new byte[1024];
            int fileSize = conn.getContentLength();
            long read = 0;
            int currentProgress = 0;
            int len;
            while ((len = inputStream.read(buf)) > 0) {
               out.write(buf, 0, len);

               read += len;
               int progress = (int) (read * 100 / fileSize);
               if (progress > currentProgress) {
                  currentProgress = progress;

                  publishProgress(progress);
               }
            }
            out.close();
            inputStream.close();

            return true;
         } catch (MalformedURLException e) {
            e.printStackTrace();
         } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         } catch (Exception e) {
            e.printStackTrace();
         }

         return false;
      }

      private void createNotificationText(int _progress) {
         int totalDownloads = mNumberOfJobs + mDownloadJobs.size();
         String text = "Donwload "
               + Utils.getFileNameWithExtension(mCurrentJob.getName(), "/")
               + (mDownloadJobs.size() > 0 ? " (" + mNumberOfJobs + "/" + totalDownloads + ")" : "");
         mNotification.contentView.setTextViewText(R.id.status_text, text);
         mNotification.contentView.setProgressBar(R.id.status_progress, 100, _progress, false);

      }

      @Override
      protected void onPostExecute(Boolean result) {
         stopSelf();
         mNotificationManager.cancel(NOTIFICATION_ID);
         Notification notification = new Notification(R.drawable.mp_logo_2, "Notify",
               System.currentTimeMillis());
         if (result) {
            notification.setLatestEventInfo(getApplicationContext(), "aMPdroid",
                  "Downloads finished", PendingIntent.getActivity(getApplicationContext(), 0,
                        mIntent, PendingIntent.FLAG_CANCEL_CURRENT));
         } else {
            notification.setLatestEventInfo(getApplicationContext(), "aMPdroid",
                  "Downloads failed", PendingIntent.getActivity(getApplicationContext(), 0,
                        mIntent, PendingIntent.FLAG_CANCEL_CURRENT));
         }

         mNotificationManager.notify(49, notification);

         super.onPostExecute(result);
      }

      @Override
      protected void onProgressUpdate(Integer... values) {
         int progress = values[0];
         createNotificationText(progress);
         // inform the progress bar of updates in progress
         mNotificationManager.notify(NOTIFICATION_ID, mNotification);
      }

   }

   @Override
   public IBinder onBind(Intent intent) {
      mIntent = intent;
      return null;
   }

   @Override
   public void onCreate() {
      mDownloadJobs = new ArrayList<DownloadJob>();
   }

   @Override
   public int onStartCommand(Intent intent, int flags, int startId) {
      if ((flags & START_FLAG_RETRY) == 0) {

      } else {

      }

      String url = intent.getStringExtra("url");
      String fName = intent.getStringExtra("name");

      synchronized (mDownloadJobs) {
         mDownloadJobs.add(new DownloadJob(0, fName, url));
      }

      if (mDownloader == null || mDownloader.isCancelled()) {
         mDownloader = new DownloaderTask().execute();
      }

      return Service.START_STICKY;
   }

   private void broadcastProgress(String _url, int _progress) {

   }

   private void broadcastStarted(String _url) {

   }

   private void broadcastFinished(String _url) {

   }

}
