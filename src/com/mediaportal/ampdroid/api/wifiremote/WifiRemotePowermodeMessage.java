package com.mediaportal.ampdroid.api.wifiremote;

import com.mediaportal.ampdroid.api.PowerModes;

public class WifiRemotePowermodeMessage extends WifiRemoteMessage {

   public WifiRemotePowermodeMessage(PowerModes _mode) {
      this.Type = "powermode";
      switch (_mode) {
      case Logoff:
         this.PowerMode = "logoff";
         break;
      case Suspend:
         this.PowerMode = "suspend";
         break;
      case Hibernate:
         this.PowerMode = "hibernate";
         break;
      case Reboot:
         this.PowerMode = "reboot";
         break;
      case Shutdown:
         this.PowerMode = "shutdown";
         break;
      case Exit:
         this.PowerMode = "exit";
         break;
      }

   }

   public String PowerMode;

}
