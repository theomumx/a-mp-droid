package com.mediaportal.ampdroid.api.wifiremote;

public class WifiRemoteMessageSetVolume extends WifiRemoteMessage {
   public WifiRemoteMessageSetVolume(int _volume) {
      this.Type = "volume";
      this.Volume = _volume;
   }

   public int Volume;
}
