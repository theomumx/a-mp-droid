package com.mediaportal.ampdroid.utils;

import android.os.Environment;

import com.mediaportal.ampdroid.data.FileInfo;
import com.mediaportal.ampdroid.data.Movie;
import com.mediaportal.ampdroid.data.SeriesEpisode;
import com.mediaportal.ampdroid.data.VideoShare;

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

   public static String getVideoPath(VideoShare _share, FileInfo _file) {
      String relativeDir = _file.getFullPath().replace(_share.getPath(), "");
      String dirName = "Shares/" + _share.Name + relativeDir.replace('\\', '/');
      
      return dirName;
   }

   public static String getMoviePath(Movie _movie) {
      String dirName = "Movies/" + _movie.getName() + "/";
      return dirName;
   }
}
