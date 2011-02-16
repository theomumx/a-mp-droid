package com.mediaportal.ampdroid.activities;

import com.mediaportal.ampdroid.R;

import android.app.Activity;
import android.os.Bundle;

public class MusicActivity extends Activity {
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle _savedInstanceState) {
      super.onCreate(_savedInstanceState);
      setContentView(R.layout.musicactivity);

      /*RemoteHandler service = RemoteHandler.getCurrentRemoteInstance();

      
      URL url = service.getDownloadUri("C:\\Users\\DieBagger\\Torrents\\the.big.bang.theory.s04e11.hdtv.xvid-fever.avi");
      Intent i = new Intent(Intent.ACTION_VIEW);
      i.setData(Uri.parse(url.toString()));
      startActivity(i);

       
      URL url = service.getDownloadUri("C:\\Users\\DieBagger\\Music\\sacred_cows_shout.mp3");
      
      Intent intent = new Intent();
      intent.setDataAndType(Uri.parse(url.toString()), "audio/*");
      intent.setAction(Intent.ACTION_VIEW);
      startActivity(intent);
      
     
      MediaPlayer mp = new MediaPlayer();
      try {
         mp.setDataSource(url.toString());
         mp.prepare();
         mp.start();
      } catch (IllegalArgumentException e1) {
         e1.printStackTrace();
      } catch (IllegalStateException e1) {
         e1.printStackTrace();
      } catch (IOException e1) {
         e1.printStackTrace();
      }


      
      InputStream inputStream = null;
      URLConnection conn;
      try {
         conn = url.openConnection();

         HttpURLConnection httpConn = (HttpURLConnection) conn;
         httpConn.setRequestMethod("GET");
         httpConn.connect();

         if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            inputStream = httpConn.getInputStream();
         }
         

         FileOutputStream f;

         f = new FileOutputStream(new File(
               Environment.getExternalStorageDirectory() + "/downloads", "shout.mp3"));
         byte[] buffer = new byte[1024];
         int len1 = 0;
         while ((len1 = inputStream.read(buffer)) > 0) {
            f.write(buffer, 0, len1);
         }
         f.close();

         inputStream.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }*/

      /*
       * RemoteHandler service = RemoteHandler.getCurrentRemoteInstance();
       * 
       * try { List<MusicAlbum> albums = service.getAlbums(0, 10); Object i =
       * albums; } catch (Exception e) { e.printStackTrace(); }
       */

   }
}
