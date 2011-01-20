package com.mediaportal.ampdroid.data;

import com.mediaportal.ampdroid.database.ColumnProperty;
import com.mediaportal.ampdroid.database.TableProperty;

@TableProperty("RemoteClients")
public class RemoteClientSetting {
   private int mClientId;
   private String mClientName;
   private String mClientDescription;
   
   private String mRemoteAccessServer;
   private int mRemoteAccessPort;
   
   private String mTvServer;
   private int mTvPort;
   
   private String mRemoteControlServer;
   private int mRemoteControlPort;
   
   @ColumnProperty(value="ClientId", type="integer")
   public int getClientId() {
      return mClientId;
   }

   @ColumnProperty(value="ClientId", type="integer")
   public void setClientId(int clientId) {
      this.mClientId = clientId;
   }

   @ColumnProperty(value="ClientName", type="text")
   public void setClientName(String clientName) {
      this.mClientName = clientName;
   }

   @ColumnProperty(value="ClientName", type="text")
   public String getClientName() {
      return mClientName;
   }

   @ColumnProperty(value="ClientDescription", type="text")
   public String getClientDescription() {
      return mClientDescription;
   }

   @ColumnProperty(value="ClientDescription", type="text")
   public void setClientDescription(String clientDescription) {
      this.mClientDescription = clientDescription;
   }

   @ColumnProperty(value="RemoteAccessServer", type="text")
   public String getRemoteAccessServer() {
      return mRemoteAccessServer;
   }

   @ColumnProperty(value="RemoteAccessServer", type="text")
   public void setRemoteAccessServer(String mRemoteAccessServer) {
      this.mRemoteAccessServer = mRemoteAccessServer;
   }

   @ColumnProperty(value="RemoteAccessPort", type="integer")
   public int getRemoteAccessPort() {
      return mRemoteAccessPort;
   }

   @ColumnProperty(value="RemoteAccessPort", type="integer")
   public void setRemoteAccessPort(int mRemoteAccessPort) {
      this.mRemoteAccessPort = mRemoteAccessPort;
   }

   @ColumnProperty(value="TvServer", type="text")
   public String getTvServer() {
      return mTvServer;
   }

   @ColumnProperty(value="TvServer", type="text")
   public void setTvServer(String mTvServer) {
      this.mTvServer = mTvServer;
   }

   @ColumnProperty(value="TvPort", type="integer")
   public int getTvPort() {
      return mTvPort;
   }

   @ColumnProperty(value="TvPort", type="integer")
   public void setTvPort(int mTvPort) {
      this.mTvPort = mTvPort;
   }

   @ColumnProperty(value="RemoteControlServer", type="text")
   public String getRemoteControlServer() {
      return mRemoteControlServer;
   }

   @ColumnProperty(value="RemoteControlServer", type="text")
   public void setRemoteControlServer(String mRemoteControlServer) {
      this.mRemoteControlServer = mRemoteControlServer;
   }

   @ColumnProperty(value="RemoteControlPort", type="integer")
   public int getRemoteControlPort() {
      return mRemoteControlPort;
   }

   @ColumnProperty(value="RemoteControlPort", type="integer")
   public void setRemoteControlPort(int mRemoteControlPort) {
      this.mRemoteControlPort = mRemoteControlPort;
   }
}
