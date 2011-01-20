package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class TvRtspClient {
   Date DateTimeStarted;
   String Description;
   String IpAdress;
   boolean IsActive;
   String StreamName;

   
   @JsonProperty("DateTimeStarted")
   public Date getDateTimeStarted() {
      return DateTimeStarted;
   }
   
   @JsonProperty("DateTimeStarted")
   public void setDateTimeStarted(Date dateTimeStarted) {
      DateTimeStarted = dateTimeStarted;
   }
   
   @JsonProperty("Description")
   public String getDescription() {
      return Description;
   }
   
   @JsonProperty("Description")
   public void setDescription(String description) {
      Description = description;
   }
   
   @JsonProperty("IpAdress")
   public String getIpAdress() {
      return IpAdress;
   }
   
   @JsonProperty("IpAdress")
   public void setIpAdress(String ipAdress) {
      IpAdress = ipAdress;
   }
   
   @JsonProperty("IsActive")
   public boolean isIsActive() {
      return IsActive;
   }
   
   @JsonProperty("IsActive")
   public void setIsActive(boolean isActive) {
      IsActive = isActive;
   }
   
   @JsonProperty("StreamName")
   public String getStreamName() {
      return StreamName;
   }
   
   @JsonProperty("StreamName")
   public void setStreamName(String streamName) {
      StreamName = streamName;
   }

}
