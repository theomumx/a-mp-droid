package com.mediaportal.remote.api.wifiremote;

public class WifiRemoteMessage {
   public WifiRemoteMessage(String _type, String _command) {
      Type = _type;
      Command = _command;
   }
   public WifiRemoteMessage(String _type, int _volume) {
      Type = _type;
      Volume = _volume;
   }
   
   public String Type;
   public String Command;
   public int Volume;
}
