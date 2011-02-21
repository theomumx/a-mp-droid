package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mediaportal.ampdroid.database.ColumnProperty;
import com.mediaportal.ampdroid.lists.Utils;

public class FileInfo {
   private String FullPath;

   @ColumnProperty(value = "FullPath", type = "text")
   @JsonProperty("FullPath")
   public void setFullPath(String fileInfo) {
      FullPath = fileInfo;
   }

   @ColumnProperty(value = "FullPath", type = "text")
   @JsonProperty("FullPath")
   public String getFullPath() {
      return FullPath;
   }

   @Override
   public String toString() {
      if (FullPath != null) {
         return Utils.getFileNameWithoutExtension(FullPath, "\\", ".");
      } else {
         return "Unknown";
      }
   }
}
