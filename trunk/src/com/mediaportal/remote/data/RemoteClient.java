package com.mediaportal.remote.data;

import com.mediaportal.remote.api.IApiInterface;
import com.mediaportal.remote.api.IClientControlApi;
import com.mediaportal.remote.api.IMediaAccessApi;
import com.mediaportal.remote.api.ITvControlApi;

public class RemoteClient {
   private int mClientId;
   private String mClientName;
   private String mClientDescription;
   private IMediaAccessApi mRemoteAccessApi;
   private ITvControlApi mTvControlApi;
   private IClientControlApi mClientControlApi;

   public RemoteClient(int _clientId) {
      mClientId = _clientId;
   }

   public RemoteClient(int _clientId, String _clientName) {
      this(_clientId);
      mClientName = _clientName;
   }

   public RemoteClient(int _clientId, String _clientName, IMediaAccessApi _remoteAccessApi,
         ITvControlApi _tvControlApi, IClientControlApi _clientControlApi) {
      this(_clientId, _clientName);
      mRemoteAccessApi = _remoteAccessApi;
      mTvControlApi = _tvControlApi;
      mClientControlApi = _clientControlApi;
   }

   public int getClientId() {
      return mClientId;
   }

   public void setClientId(int clientId) {
      this.mClientId = clientId;
   }

   public String getClientAddress() {
      if (!compareApiClients()) {//all clients have same address
         return "Different";
      }
      else{
         if(mRemoteAccessApi != null){
            return mRemoteAccessApi.getAddress();
         }
         if(mClientControlApi != null){
            return mClientControlApi.getAddress();
         }
         if(mTvControlApi != null){
            return mTvControlApi.getAddress();
         }
      }
      return "No api defined";//shouldn't be possible
   }

   private boolean compareApiClients() {
      if (mRemoteAccessApi != null) {
         if (!compareApi(mRemoteAccessApi, mTvControlApi)
               || !compareApi(mRemoteAccessApi, mClientControlApi)) {
            return false;
         }
      }
      
      if (mTvControlApi != null) {
         if (!compareApi(mTvControlApi, mRemoteAccessApi)
               || !compareApi(mTvControlApi, mClientControlApi)) {
            return false;
         }
      }
      
      if (mClientControlApi != null) {
         if (!compareApi(mClientControlApi, mRemoteAccessApi)
               || !compareApi(mClientControlApi, mTvControlApi)) {
            return false;
         }
      }

      return true;
   }

   private boolean compareApi(IApiInterface _api1, IApiInterface _api2) {
      if (_api1 == null || _api2 == null)
         return true;
      return _api1.getAddress().equals(_api2.getAddress());
   }

   @Override
   public String toString() {
      if (mClientName != null) {
         return mClientName;
      } else {
         return "Client" + mClientId;
      }
   }

   public IMediaAccessApi getRemoteAccessApi() {
      return mRemoteAccessApi;
   }

   public void setRemoteAccessApi(IMediaAccessApi remoteAccessApi) {
      this.mRemoteAccessApi = remoteAccessApi;
   }

   public ITvControlApi getTvControlApi() {
      return mTvControlApi;
   }

   public void setTvControlApi(ITvControlApi tvControlApi) {
      this.mTvControlApi = tvControlApi;
   }

   public IClientControlApi getClientControlApi() {
      return mClientControlApi;
   }

   public void setClientControlApi(IClientControlApi clientControlApi) {
      this.mClientControlApi = clientControlApi;
   }

   public void setClientName(String clientName) {
      this.mClientName = clientName;
   }

   public String getClientName() {
      return mClientName;
   }

   public String getClientDescription() {
      return mClientDescription;
   }

   public void setClientDescription(String clientDescription) {
      this.mClientDescription = clientDescription;
   }

}
