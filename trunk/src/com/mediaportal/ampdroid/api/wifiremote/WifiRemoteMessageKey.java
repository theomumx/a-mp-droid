package com.mediaportal.ampdroid.api.wifiremote;

import com.mediaportal.ampdroid.data.commands.RemoteKey;

public class WifiRemoteMessageKey extends WifiRemoteMessage {
   public WifiRemoteMessageKey(RemoteKey _key){
      this.Type = "command";
      this.Command = _key.getAction();
   }
   
   public String Command;
}
