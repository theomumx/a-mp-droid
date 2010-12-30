package com.mediaportal.remote.api.mpclient;

import java.lang.reflect.Type;

import org.json.JSONObject;

import com.google.myjson.Gson;
import com.google.myjson.GsonBuilder;
import com.google.myjson.reflect.TypeToken;
import com.mediaportal.remote.api.IClientControlApi;
import com.mediaportal.remote.api.IClientControlListener;
import com.mediaportal.remote.api.rest.RestClient;
import com.mediaportal.remote.api.rest.RestClient.RequestMethod;
import com.mediaportal.remote.data.commands.RemoteKey;

public class MPClientController implements IClientControlApi {

   


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
}
