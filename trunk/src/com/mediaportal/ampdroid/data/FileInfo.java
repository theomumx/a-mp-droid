package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mediaportal.ampdroid.database.ColumnProperty;
import com.mediaportal.ampdroid.lists.Utils;

public class FileInfo {
   private String FullPath;
   private boolean IsFolder;

   public FileInfo(String _fullPath, boolean _isFolder) {
      FullPath = _fullPath;
      IsFolder = _isFolder;
   }
   
   public FileInfo(){
      IsFolder = false;
   }

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
         if(IsFolder){
            return Utils.getFolderNameWithoutExtension(FullPath, "\\");
         }
         else{
            return Utils.getFileNameWithExtension(FullPath, "\\");
         }
      } else {
         return "Unknown";
      }
   }
   
   @ColumnProperty(value = "IsFolder", type = "boolean")
   public void setFolder(boolean isFolder) {
      IsFolder = isFolder;
   }
   
   @ColumnProperty(value = "IsFolder", type = "boolean")
   public boolean isFolder() {
      return IsFolder;
   }
}
