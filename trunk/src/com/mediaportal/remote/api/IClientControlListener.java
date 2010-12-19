package com.mediaportal.remote.api;

public interface IClientControlListener {

   public void messageReceived(String _message);
   public void stateChanged(String _state);

}
