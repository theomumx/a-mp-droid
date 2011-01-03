package com.mediaportal.remote.data;

import com.mediaportal.remote.api.IClientControlApi;
import com.mediaportal.remote.api.IMediaAccessApi;
import com.mediaportal.remote.api.ITvControlApi;

public class RemoteClient {
   private int clientId;
   private String clientName;
   private IMediaAccessApi remoteAccessApi;
   private ITvControlApi tvControlApi;
   private IClientControlApi clientControlApi;

   public RemoteClient(int _clientId) {
      clientId = _clientId;
   }
   
   public RemoteClient(int _clientId, String _clientName) {
      this(_clientId);
      clientName = _clientName;
   }

   public RemoteClient(int _clientId, String _clientName, IMediaAccessApi _remoteAccessApi,
         ITvControlApi _tvControlApi, IClientControlApi _clientControlApi) {
      this(_clientId, _clientName);
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

   @Override
   public String toString() {
      if (clientName != null) {
         return clientName;
      } else {
         return "Client" + clientId;
      }
   }

   public IMediaAccessApi getRemoteAccessApi() {
      return remoteAccessApi;
   }

   public void setRemoteAccessApi(IMediaAccessApi remoteAccessApi) {
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

   public void setClientName(String clientName) {
      this.clientName = clientName;
   }

   public String getClientName() {
      return clientName;
   }

}
