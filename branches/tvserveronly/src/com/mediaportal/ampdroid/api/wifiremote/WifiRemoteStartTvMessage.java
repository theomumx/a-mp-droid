package com.mediaportal.ampdroid.api.wifiremote;


public class WifiRemoteStartTvMessage extends WifiRemoteMessage {

   public WifiRemoteStartTvMessage(int _channelId){
      this.Type = "tune";
      this.ChannelId = _channelId;
   }
   
   public int ChannelId;
}
