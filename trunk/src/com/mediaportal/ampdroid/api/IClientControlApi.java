package com.mediaportal.ampdroid.api;

import com.mediaportal.ampdroid.data.commands.RemoteKey;

public interface IClientControlApi extends IApiInterface {
   boolean connect();
   void disconnect();
   boolean isConnected();
   
   void setTimeOut(int _timeout);
   int getTimeOut();
   
   void addApiListener(IClientControlListener _listener);
   
   void sendKeyCommand(RemoteKey _key);
   void setVolume(int level);
   int getVolume();
   
}
