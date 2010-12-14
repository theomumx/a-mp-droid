package com.mediaportal.remote.data;

import com.mediaportal.remote.api.IClientControlApi;
import com.mediaportal.remote.api.IRemoteAccessApi;
import com.mediaportal.remote.api.ITvControlApi;

public class RemoteClient {
   private int clientId;
   private IRemoteAccessApi remoteAccessApi;
   private ITvControlApi tvControlApi;
   private IClientControlApi clientControlApi;
   
   public RemoteClient(int _clientId){
      clientId = _clientId;
   }
   
   public RemoteClient(int _clientId, IRemoteAccessApi _remoteAccessApi, ITvControlApi _tvControlApi, IClientControlApi _clientControlApi){
      this(_clientId);
      remoteAccessApi = _remoteAccessApi;
      tvControlApi = _tvControlApi;
      clientControlApi = _clientControlApi;
   }

   public int getClientId() {
      return clientId;
   }

   public void setClientId(int clientId) {
      this.clientId = clientId;
   }

   public IRemoteAccessApi getRemoteAccessApi() {
      return remoteAccessApi;
   }

   public void setRemoteAccessApi(IRemoteAccessApi remoteAccessApi) {
      this.remoteAccessApi = remoteAccessApi;
   }

   public ITvControlApi getTvControlApi() {
      return tvControlApi;
   }

   public void setTvControlApi(ITvControlApi tvControlApi) {
      this.tvControlApi = tvControlApi;
   }

   public IClientControlApi getClientControlApi() {
      return clientControlApi;
   }

   public void setClientControlApi(IClientControlApi clientControlApi) {
      this.clientControlApi = clientControlApi;
   }
   
   
}
