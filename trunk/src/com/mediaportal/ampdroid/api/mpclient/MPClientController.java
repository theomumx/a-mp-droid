package com.mediaportal.ampdroid.api.mpclient;

import java.lang.reflect.Type;

import org.json.JSONObject;

import com.google.myjson.Gson;
import com.google.myjson.GsonBuilder;
import com.google.myjson.reflect.TypeToken;
import com.mediaportal.ampdroid.api.IClientControlApi;
import com.mediaportal.ampdroid.api.IClientControlListener;
import com.mediaportal.ampdroid.api.rest.RestClient;
import com.mediaportal.ampdroid.api.rest.RestClient.RequestMethod;
import com.mediaportal.ampdroid.data.commands.RemoteKey;

public class MPClientController implements IClientControlApi {

   
   @Override
   public String getAddress() {
      return "";
   }

   public boolean SendKey(RemoteKey _key) {
      MPClientMessage msg = new MPClientMessage("button", _key.getAction(), "");

      MPClientServerMessage response = sendRestMessage(msg);

      return false;

   }
   
   

   private MPClientServerMessage sendRestMessage(MPClientMessage msg) {
      try {
         RestClient client = new RestClient("http://10.1.0.167:55668/" + "mpcc");
         GsonBuilder gsonb = new GsonBuilder();
         Gson gson = gsonb.create();
         String msgString = gson.toJson(msg);
         client.setContentBody(msgString);
         client.Execute(RequestMethod.POST);

         String response = client.getResponse();
         JSONObject j = new JSONObject(response);
         MPClientServerMessage club = null;

         Type objType = new TypeToken<MPClientServerMessage>() {
         }.getType();

         club = gson.fromJson(j.toString(), objType);
         
         return club;
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   @Override
   public void addApiListener(IClientControlListener _listener) {
      
      
   }

   @Override
   public boolean connect() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void disconnect() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void sendKeyCommand(RemoteKey key) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public boolean isConnected() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public int getVolume() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public void setVolume(int level) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public int getTimeOut() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public void setTimeOut(int timeout) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public String getServer() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public int getPort() {
      // TODO Auto-generated method stub
      return 0;
   }
}
