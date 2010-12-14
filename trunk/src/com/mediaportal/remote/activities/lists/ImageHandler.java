package com.mediaportal.remote.activities.lists;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Stack;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.mediaportal.remote.R;


public class ImageHandler {

	public static int ImagePrefferedWidth = 400;// width of loaded image ->
	// default = 400
	public static int ImagePrefferedHeight = 300;// height of loaded image ->
	// default = 400

	// the simplest in-memory cache implementation. This should be replaced with
	// something like SoftReference or BitmapOptions.inPurgeable(since 1.6)
	private HashMap<String, Bitmap> cache = new HashMap<String, Bitmap>();

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
			cacheDir = new File(android.os.Environment
					.getExternalStorageDirectory(), "LazyList");
		else
			cacheDir = context.getCacheDir();
		if (!cacheDir.exists())
			cacheDir.mkdirs();
	}

	final int stub_id = R.drawable.icon;

	public void DisplayImage(String url, Activity activity, ImageView imageView) {
		if (cache.containsKey(url))
			imageView.setImageBitmap(cache.get(url));
		else {
			queuePhoto(url, activity, imageView);
			imageView.setImageResource(R.drawable.mplogo);
		}
	}

	private void queuePhoto(String url, Activity activity, ImageView imageView) {
		// This ImageView may be used for other images before. So there may be
		// some old tasks in the queue. We need to discard them.
		photosQueue.Clean(imageView);
		PhotoToLoad p = new PhotoToLoad(url, imageView);
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

	public Bitmap getBitmap(String url, boolean thumb) {
		if (url != null && !url.equals("")) {
			// I identify images by hashcode. Not a perfect solution, good for
			// the demo.
			String filename = getHashOfFileName(url);
			// File f = new File(filename);

			// from SD cache
			Bitmap b = decodeFile(filename, thumb);
			if (b != null)
				return b;

			// from web
			try {
				URL myFileUrl= new URL("http://bagga-laptop:4321/json/FS_GetImage/?path=" + URLEncoder.encode(url,"UTF-8"));
				HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
	               conn.setDoInput(true);
	               conn.connect();
	               int length = conn.getContentLength();
	               InputStream is = conn.getInputStream();
	               
	               Bitmap bmImg = BitmapFactory.decodeStream(is);
	               
	               return bmImg;
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
			
			/*// from web
			try {
				Bitmap bitmap = null;
				URL uri = new URL(createImageUriWithFittingWidth(url));

				InputStream is = uri.openStream();
				OutputStream os = mContext.openFileOutput(filename,
						Context.MODE_PRIVATE);
				Utils.CopyStream(is, os);
				os.close();
				bitmap = decodeFile(filename, thumb);

				return bitmap;
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}*/
		}
		return null;
	}

	private String createImageUriWithFittingWidth(String imageUri) {
		String base = "";

		return base + "?src=" + imageUri + "&w="
				+ ImageHandler.ImagePrefferedWidth + "&h="
				+ ImageHandler.ImagePrefferedHeight;
	}

	// decodes image and scales it to reduce memory consumption
	private Bitmap decodeFile(String filename, boolean thumb) {
		try {

			InputStream is = mContext.openFileInput(filename);
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
					if (width_tmp / 2 < REQUIRED_SIZE
							|| height_tmp / 2 < REQUIRED_SIZE)
						break;
					width_tmp /= 2;
					height_tmp /= 2;
					scale++;
				}

				// decode with inSampleSize
				BitmapFactory.Options o2 = new BitmapFactory.Options();
				o2.inSampleSize = scale;
				is = mContext.openFileInput(filename);
				return BitmapFactory.decodeStream(is, null, o2);
			}
			else{
				//full size
				BitmapFactory.Options o = new BitmapFactory.Options();
				//o.
				return BitmapFactory.decodeStream(is, null, o);
			}
				
		} catch (FileNotFoundException e) {
		}
		return null;
	}

	// Task for the queue
	private class PhotoToLoad {
		public String url;
		public ImageView imageView;

		public PhotoToLoad(String u, ImageView i) {
			url = u;
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
						Bitmap bmp = getBitmap(photoToLoad.url, true);
						cache.put(photoToLoad.url, bmp);
						if (((String) photoToLoad.imageView.getTag())
								.equals(photoToLoad.url)) {
							BitmapDisplayer bd = new BitmapDisplayer(bmp,
									photoToLoad.imageView);
							Activity a = (Activity) photoToLoad.imageView
									.getContext();
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
		cache.clear();

		// clear SD cache
		File[] files = cacheDir.listFiles();
		for (File f : files)
			f.delete();
	}

	public void deleteImage(String url) {
		String filename = getHashOfFileName(url);
		File fileToDelete = new File(filename);
		fileToDelete.delete();
	}

}
