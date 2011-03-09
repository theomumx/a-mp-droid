package com.mediaportal.ampdroid.remote;

import org.codehaus.jackson.annotate.JsonProperty;

public class RemoteWelcomeMessage {
   private int mServerVersion;
   private RemoteStatusMessage mStatus;
   private RemoteVolumeMessage mVolume;

   @JsonProperty("Server_Version")
   public void setServerVersion(int serverVersion) {
      mServerVersion = serverVersion;
   }

   @JsonProperty("Server_Version")
   public int getServerVersion() {
      return mServerVersion;
   }

   @JsonProperty("Status")
   public void setStatus(RemoteStatusMessage status) {
      mStatus = status;
   }

   @JsonProperty("Status")
   public RemoteStatusMessage getStatus() {
      return mStatus;
   }

   @JsonProperty("Volume")
   public void setVolume(RemoteVolumeMessage volume) {
      mVolume = volume;
   }

   @JsonProperty("Volume")
   public RemoteVolumeMessage getVolume() {
      return mVolume;
   }
}
