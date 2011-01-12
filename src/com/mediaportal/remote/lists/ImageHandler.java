package com.mediaportal.remote.lists;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Stack;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.mediaportal.remote.R;
import com.mediaportal.remote.api.DataHandler;

public class ImageHandler {

   public static int ImagePrefferedWidth = 400;// width of loaded image ->
   // default = 400
   public static int ImagePrefferedHeight = 300;// height of loaded image ->
   // default = 400

   public static int MEMORY_CACHE_SIZE = 20;

   // the simplest in-memory cache implementation. This should be replaced with
   // something like SoftReference or BitmapOptions.inPurgeable(since 1.6)
   private LinkedHashMap<String, Bitmap> memoryCache = new CacheHashMap(MEMORY_CACHE_SIZE);

   private File cacheDir;
   private Context mContext;

   public ImageHandler(Context context) {
      // Make the background thead low priority. This way it will not affect
      // the UI performance
      mContext = context;
      photoLoaderThread.setPriority(Thread.NORM_PRIORITY - 1);

      // Find the dir to save cached images
      if (android.os.Environment.getExternalStorageState().equals(
            android.os.Environment.MEDIA_MOUNTED))
         cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "aMPdroid/Cache");
      else
         cacheDir = context.getCacheDir();
      if (!cacheDir.exists())
         cacheDir.mkdirs();
   }

   final int stub_id = R.drawable.mp_logo_2;

   public void DisplayImage(String url, String cache, Activity activity, ImageView imageView) {
      Bitmap bitmap = null;
      //TODO: memory cache will always be empty -> at some point reintroduce memory caching
      if (memoryCache.containsKey(url)) {
         bitmap = memoryCache.get(url);
         imageView.setImageBitmap(bitmap);
      } else {
         queuePhoto(url, cache, activity, imageView);
      }

      if (bitmap == null) {
         imageView.setImageResource(stub_id);
      }
   }

   private void queuePhoto(String url, String cache, Activity activity, ImageView imageView) {
      // This ImageView may be used for other images before. So there may be
      // some old tasks in the queue. We need to discard them.
      photosQueue.Clean(imageView);
      PhotoToLoad p = new PhotoToLoad(url, cache, imageView);
      synchronized (photosQueue.photosToLoad) {
         photosQueue.photosToLoad.push(p);
         photosQueue.photosToLoad.notifyAll();
      }

      // start thread if it's not started yet
      if (photoLoaderThread.getState() == Thread.State.NEW)
         photoLoaderThread.start();
   }

   public String getHashOfFileName(String url) {
      return String.valueOf(url.hashCode());
   }

   public Bitmap getBitmap(String url, String cache, boolean thumb) {
      if (url != null && !url.equals("")) {

         File file = new File(cacheDir + File.separator + cache);

         // from SD cache
         Bitmap b = decodeFile(file, thumb);

         if (b != null)
            return b;

         // from web
         try {
            DataHandler service = DataHandler.getCurrentRemoteInstance();
            b = service.getImage(url, 200, 200);
         } catch (Exception ex) {
            ex.printStackTrace();
            return null;
         }

         if (b != null) {
            // save bitmap to sd card for caching
            try {

               if (android.os.Environment.getExternalStorageState().equals(
                     android.os.Environment.MEDIA_MOUNTED)) {
                  File parentDir = file.getParentFile();
                  if (!parentDir.exists()) {
                     parentDir.mkdirs();
                  }

                  FileOutputStream f = new FileOutputStream(file);
                  b.compress(Bitmap.CompressFormat.JPEG, 100, f);
                  f.flush();
                  f.close();
               } else {
                  // todo: write to internal memory here???
               }

            } catch (FileNotFoundException e) {
               e.printStackTrace();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

         return b;
      }
      return null;
   }

   // decodes image and scales it to reduce memory consumption
   private Bitmap decodeFile(File file, boolean thumb) {
      try {
         if (!file.exists())
            return null;

         InputStream is = new FileInputStream(file);
         if (thumb) {
            // decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;

            BitmapFactory.decodeStream(is, null, o);

            // Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE = 70;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
               if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
                  break;
               width_tmp /= 2;
               height_tmp /= 2;
               scale++;
            }

            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            is = mContext.openFileInput(file.toString());
            return BitmapFactory.decodeStream(is, null, o2);
         } else {
            // full size
            BitmapFactory.Options o = new BitmapFactory.Options();
            // o.
            return BitmapFactory.decodeStream(is, null, o);
         }

      } catch (FileNotFoundException e) {
      }
      return null;
   }

   // Task for the queue
   private class PhotoToLoad {
      public String url;
      public String cache;
      public ImageView imageView;

      public PhotoToLoad(String _url, String _cache, ImageView i) {
         url = _url;
         cache = _cache;
         imageView = i;
      }
   }

   PhotosQueue photosQueue = new PhotosQueue();

   public void stopThread() {
      photoLoaderThread.interrupt();
   }

   // stores list of photos to download
   class PhotosQueue {
      private Stack<PhotoToLoad> photosToLoad = new Stack<PhotoToLoad>();

      // removes all instances of this ImageView
      public void Clean(ImageView image) {
         for (int j = 0; j < photosToLoad.size();) {
            if (photosToLoad.get(j).imageView == image)
               photosToLoad.remove(j);
            else
               ++j;
         }
      }
   }

   class PhotosLoader extends Thread {
      public void run() {
         try {
            while (true) {
               // thread waits until there are any images to load in the
               // queue
               if (photosQueue.photosToLoad.size() == 0)
                  synchronized (photosQueue.photosToLoad) {
                     photosQueue.photosToLoad.wait();
                  }
               if (photosQueue.photosToLoad.size() != 0) {
                  PhotoToLoad photoToLoad;
                  synchronized (photosQueue.photosToLoad) {
                     photoToLoad = photosQueue.photosToLoad.pop();
                  }
                  Bitmap bmp = getBitmap(photoToLoad.url, photoToLoad.cache, false);
                  
                  //TODO: implement memory caching to further improve performance
                  memoryCache.put(photoToLoad.url, bmp);
                  //if (memoryCache.size() > MEMORY_CACHE_SIZE) {
                   //  // cache.
                  //}

                  if (((String) photoToLoad.imageView.getTag()).equals(photoToLoad.url)) {
                     BitmapDisplayer bd = new BitmapDisplayer(bmp, photoToLoad.imageView);
                     Activity a = (Activity) photoToLoad.imageView.getContext();
                     a.runOnUiThread(bd);
                  }
               }
               if (Thread.interrupted())
                  break;
            }
         } catch (InterruptedException e) {
            // allow thread to exit
         }
      }
   }

   PhotosLoader photoLoaderThread = new PhotosLoader();

   // Used to display bitmap in the UI thread
   class BitmapDisplayer implements Runnable {
      Bitmap bitmap;
      ImageView imageView;

      public BitmapDisplayer(Bitmap b, ImageView i) {
         bitmap = b;
         imageView = i;
      }

      public void run() {
         if (bitmap != null)
            imageView.setImageBitmap(bitmap);
         else
            imageView.setImageResource(stub_id);
      }
   }

   public void clearCache() {
      // clear memory cache
      memoryCache.clear();
   }
   

   public void deleteImage(String url) {
      String filename = getHashOfFileName(url);
      File fileToDelete = new File(filename);
      fileToDelete.delete();
   }

}
