package com.mediaportal.ampdroid.api.wifiremote;


public class WifiRemotePlayFileMessage  extends WifiRemoteMessage {
   public WifiRemotePlayFileMessage(String _file){
      this.Type = "video";
      this.Command = _file;
   }
   
   public String Command;
}
