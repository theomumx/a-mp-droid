package com.mediaportal.ampdroid.api.wifiremote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import android.os.AsyncTask;

import com.google.myjson.Gson;
import com.google.myjson.GsonBuilder;
import com.mediaportal.ampdroid.api.IClientControlApi;
import com.mediaportal.ampdroid.api.IClientControlListener;
import com.mediaportal.ampdroid.data.ClientPlugin;
import com.mediaportal.ampdroid.data.commands.RemoteKey;

public class WifiRemoteMpController implements IClientControlApi {
   private String server;
   private int port;
   private Socket socket;
   DataInputStream instream;
   DataOutputStream outstream;
   TcpListenerTask tcpreader;
   List<IClientControlListener> listeners;

   private class TcpListenerTask extends AsyncTask<DataInputStream, String, String> {
      private List<IClientControlListener> listeners;
      private boolean listening = false;;

      public boolean isListening() {
         return listening;
      }

      public void setListening(boolean listening) {
         this.listening = listening;
      }

      public TcpListenerTask(List<IClientControlListener> _listeners) {
         listeners = _listeners;
      }
    

      @Override
      protected String doInBackground(DataInputStream... params) {
         listening = true;
         while (listening) {
            try {
               String response = instream.readLine();
               
               publishProgress(response);
            } catch (IOException e) {
               e.printStackTrace();
               listening = false;
            } catch (Exception e) {
               e.printStackTrace();
               listening = false;
            }
         }
         this.listening = false;
         return "Socket closed";
      }

      /*
       * (non-Javadoc)
       * 
       * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
       */
      @Override
      protected void onPostExecute(String result) {
         publishState(result);
      }

      /*
       * (non-Javadoc)
       * 
       * @see android.os.AsyncTask#onPreExecute()
       */
      @Override
      protected void onPreExecute() {
         // Things to be done before execution of long running operation. For
         // example showing ProgessDialog
      }

      /*
       * (non-Javadoc)
       * 
       * @see android.os.AsyncTask#onProgressUpdate(Progress[])
       */
      @Override
      protected void onProgressUpdate(String... values) {
         // Things to be done while execution of long running operation is in
         // progress. For example updating ProgessDialog
         for (IClientControlListener l : listeners) {
            l.messageReceived(values[0]);
         }
      }
   }

   public WifiRemoteMpController(String _server, int _port) {
      super();
      this.server = _server;
      this.port = _port;
      this.listeners = new ArrayList<IClientControlListener>();
   }
   
   @Override
   public String getServer() {
      return server;
   }

   @Override
   public int getPort() {
      return port;
   }
   
   @Override
   public String getAddress() {
      return server;
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

   /**
    * Connect to client
    */
   public boolean connect() {
      try {
         socket = new Socket();
         SocketAddress socketAddress = new InetSocketAddress(server, port);

         socket.connect(socketAddress, 2000);

         // outgoing stream redirect to socket
         outstream = new DataOutputStream(socket.getOutputStream());
         instream = new DataInputStream(socket.getInputStream());

         
         tcpreader = new TcpListenerTask(listeners);
         tcpreader.execute(instream);

         publishState("State: " + socket.isConnected());

         return true;

      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return false;
   }

   private void publishState(String _state) {
      for (IClientControlListener l : listeners) {
         l.stateChanged(_state);
      }

   }

   @Override
   public void disconnect() {
      try {
         tcpreader.cancel(true);
         socket.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   @Override
   public void addApiListener(IClientControlListener _listener) {
      listeners.add(_listener);
   }

   @Override
   public void sendKeyCommand(RemoteKey _key) {
      GsonBuilder gsonb = new GsonBuilder();
      Gson gson = gsonb.create();
      //String msgString = gson.toJson(new WifiRemoteMessage("command","", _key.getAction()));
      String msgString = gson.toJson(new WifiRemoteMessageKey(_key));
      writeLine(msgString);
   }
   
   @Override
   public void sendKeyDownCommand(RemoteKey _key, int _timeout) {
      GsonBuilder gsonb = new GsonBuilder();
      Gson gson = gsonb.create();
      //String msgString = gson.toJson(new WifiRemoteMessage("command","", _key.getAction()));
      String msgString = gson.toJson(new WifiRemoteKeyDownMessage(_key, _timeout));
      writeLine(msgString);
   }
   
   @Override
   public void sendKeyUpCommand() {
      GsonBuilder gsonb = new GsonBuilder();
      Gson gson = gsonb.create();
      //String msgString = gson.toJson(new WifiRemoteMessage("command","", _key.getAction()));
      String msgString = gson.toJson(new WifiRemoteKeyUpMessage());
      writeLine(msgString);
   }
   
   @Override
   public void sendPlayFileCommand(String _file) {
      GsonBuilder gsonb = new GsonBuilder();
      Gson gson = gsonb.create();
      String msgString = gson.toJson(new WifiRemotePlayFileMessage(_file));

      writeLine(msgString);
   }

   private boolean writeLine(String msgString) {
      try {
         if (outstream != null) {
            byte[] buffer = msgString.getBytes();
            outstream.write(buffer,0, buffer.length);
            // outstream.writeUTF(msgString);
            // outstream.writeChars(msgString);
            outstream.writeByte(13);
            outstream.writeByte(10);

            outstream.flush();
            return true;
         }
      } catch (IOException e) {}
      return false;
   }

   @Override
   public boolean isConnected() {
      return (socket != null && socket.isConnected() && tcpreader != null && tcpreader
            .isListening());
   }

   @Override
   public int getVolume() {
      return 0;
   }

   @Override
   public void setVolume(int level) {
      GsonBuilder gsonb = new GsonBuilder();
      Gson gson = gsonb.create();
      String msgString = gson.toJson(new WifiRemoteMessageSetVolume(level));

      writeLine(msgString);
   }

   @Override
   public void startVideo(String _path){
      GsonBuilder gsonb = new GsonBuilder();
      Gson gson = gsonb.create();
      String msgString = gson.toJson(new WifiRemotePlayFileMessage(_path));

      writeLine(msgString);
   }

   @Override
   public void playChannelOnClient(int _channel) {
      GsonBuilder gsonb = new GsonBuilder();
      Gson gson = gsonb.create();
      String msgString = gson.toJson(new WifiRemoteStartTvMessage(_channel));

      writeLine(msgString);      
   }

   @Override
   public List<ClientPlugin> getPlugins(){
      
      return null;
   }
   
   @Override
   public void openPlugin(ClientPlugin _plugin){
      
   }


}
