package com.mediaportal.ampdroid.remote;

import org.codehaus.jackson.annotate.JsonProperty;

public class RemoteVolumeMessage {
   private int mVolume;
   private boolean mIsMuted;
   
   @JsonProperty("Volume")
   public int getVolume() {
      return mVolume;
   }
   
   @JsonProperty("Volume")
   public void setVolume(int volume) {
      mVolume = volume;
   }
   
   @JsonProperty("IsMuted")
   public boolean isIsMuted() {
      return mIsMuted;
   }
   
   @JsonProperty("IsMuted")
   public void setIsMuted(boolean isMuted) {
      mIsMuted = isMuted;
   }
   
   
}
