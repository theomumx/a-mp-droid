package com.mediaportal.remote.api;

import com.mediaportal.remote.data.commands.RemoteKey;

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
