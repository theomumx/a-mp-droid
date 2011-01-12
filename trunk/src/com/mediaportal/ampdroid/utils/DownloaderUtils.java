package com.mediaportal.ampdroid.utils;

import android.os.Environment;

import com.mediaportal.ampdroid.data.SeriesEpisode;

public class DownloaderUtils {
   public static String getTvEpisodePath(String _seriesName, SeriesEpisode _episode) {
      String dirName = "Series/" + _seriesName + "/Season."
            + getNumberWithTrailingZero(_episode.getSeasonNumber()) + "/";
      
      return dirName;
   }

   private static String getNumberWithTrailingZero(int _number) {
      if (_number < 10)
         return "0" + _number;
      return String.valueOf(_number);
   }

   public static String getBaseDirectory() {
      return Environment.getExternalStorageDirectory() + "/aMPdroid/";
   }
}
