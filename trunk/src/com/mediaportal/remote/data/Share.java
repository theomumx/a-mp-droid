package com.mediaportal.remote.data;

public class Share {
   public int ShareId;
   public String Path;
   public String Name;
   public String[] Extensions;
   public String PinCode;
   public boolean IsFtp;
   public String FtpServer;
   public int FtpPort;
   public String FtpPath;
   public String FtpLogin;
   public String FtpPassword;
   
   public Share(){
      super();
   }
   
   @Override
   public String toString(){
      return Name;
   }
}
