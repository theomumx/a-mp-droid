package com.mediaportal.ampdroid.remote;

import org.codehaus.jackson.annotate.JsonProperty;

public class RemotePlugin {
   private String mName;
   private int mWindowId;
   private byte[] mIcon;
   
   @JsonProperty("Name")
   public String getName() {
      return mName;
   }
   
   @JsonProperty("Name")
   public void setName(String name) {
      mName = name;
   }
   
   @JsonProperty("WindowId")
   public int getWindowId() {
      return mWindowId;
   }
   
   @JsonProperty("WindowId")
   public void setWindowId(int windowId) {
      mWindowId = windowId;
   }
   
   @JsonProperty("Icon")
   public byte[] getIcon() {
      return mIcon;
   }
   
   @JsonProperty("Icon")
   public void setIcon(byte[] icon) {
      mIcon = icon;
   }
}
