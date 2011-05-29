package com.mediaportal.ampdroid.api.wifiremote;

public class WifiRemoteOpenWindowMessage extends WifiRemoteMessage {
   public WifiRemoteOpenWindowMessage(int _windowId) {
      this.Type = "window";
      this.Window = _windowId;
   }

   public int Window;
}
