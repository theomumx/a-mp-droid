package com.mediaportal.ampdroid.api;

public interface IClientControlListener {

   public void messageReceived(Object _message);
   public void stateChanged(String _state);

}
