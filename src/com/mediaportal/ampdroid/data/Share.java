package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;

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

   @JsonProperty("ShareId")
   public int getShareId() {
      return ShareId;
   }

   @JsonProperty("ShareId")
   public void setShareId(int shareId) {
      ShareId = shareId;
   }

   @JsonProperty("Path")
   public String getPath() {
      return Path;
   }

   @JsonProperty("Path")
   public void setPath(String path) {
      Path = path;
   }

   @JsonProperty("Name")
   public String getName() {
      return Name;
   }

   @JsonProperty("Name")
   public void setName(String name) {
      Name = name;
   }

   @JsonProperty("Extensions")
   public String[] getExtensions() {
      return Extensions;
   }

   @JsonProperty("Extensions")
   public void setExtensions(String[] extensions) {
      Extensions = extensions;
   }

   @JsonProperty("PinCode")
   public String getPinCode() {
      return PinCode;
   }

   @JsonProperty("PinCode")
   public void setPinCode(String pinCode) {
      PinCode = pinCode;
   }

   @JsonProperty("IsFtp")
   public boolean isIsFtp() {
      return IsFtp;
   }

   @JsonProperty("IsFtp")
   public void setIsFtp(boolean isFtp) {
      IsFtp = isFtp;
   }

   @JsonProperty("FtpServer")
   public String getFtpServer() {
      return FtpServer;
   }

   @JsonProperty("FtpServer")
   public void setFtpServer(String ftpServer) {
      FtpServer = ftpServer;
   }

   @JsonProperty("FtpPort")
   public int getFtpPort() {
      return FtpPort;
   }

   @JsonProperty("FtpPort")
   public void setFtpPort(int ftpPort) {
      FtpPort = ftpPort;
   }

   @JsonProperty("FtpPath")
   public String getFtpPath() {
      return FtpPath;
   }

   @JsonProperty("FtpPath")
   public void setFtpPath(String ftpPath) {
      FtpPath = ftpPath;
   }

   @JsonProperty("FtpLogin")
   public String getFtpLogin() {
      return FtpLogin;
   }

   @JsonProperty("FtpLogin")
   public void setFtpLogin(String ftpLogin) {
      FtpLogin = ftpLogin;
   }

   @JsonProperty("FtpPassword")
   public String getFtpPassword() {
      return FtpPassword;
   }

   @JsonProperty("FtpPassword")
   public void setFtpPassword(String ftpPassword) {
      FtpPassword = ftpPassword;
   }
   
   
}
