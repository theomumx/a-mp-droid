package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class TvUser {
   int CardId;
   Date HeartBeat;
   int IdChannel;
   boolean IsAdmin;
   String Name;
   int SubChannel;
   int TvStoppedReason;

   @Override
   public String toString() {
      return Name;
   }

   @JsonProperty("CardId")
   public int getCardId() {
      return CardId;
   }

   @JsonProperty("CardId")
   public void setCardId(int cardId) {
      CardId = cardId;
   }

   @JsonProperty("HeartBeat")
   public Date getHeartBeat() {
      return HeartBeat;
   }

   @JsonProperty("HeartBeat")
   public void setHeartBeat(Date heartBeat) {
      HeartBeat = heartBeat;
   }

   @JsonProperty("IdChannel")
   public int getIdChannel() {
      return IdChannel;
   }

   @JsonProperty("IdChannel")
   public void setIdChannel(int idChannel) {
      IdChannel = idChannel;
   }

   @JsonProperty("IsAdmin")
   public boolean isIsAdmin() {
      return IsAdmin;
   }

   @JsonProperty("IsAdmin")
   public void setIsAdmin(boolean isAdmin) {
      IsAdmin = isAdmin;
   }

   @JsonProperty("Name")
   public String getName() {
      return Name;
   }

   @JsonProperty("Name")
   public void setName(String name) {
      Name = name;
   }

   @JsonProperty("SubChannel")
   public int getSubChannel() {
      return SubChannel;
   }

   @JsonProperty("SubChannel")
   public void setSubChannel(int subChannel) {
      SubChannel = subChannel;
   }

   @JsonProperty("TvStoppedReason")
   public int getTvStoppedReason() {
      return TvStoppedReason;
   }

   @JsonProperty("TvStoppedReason")
   public void setTvStoppedReason(int tvStoppedReason) {
      TvStoppedReason = tvStoppedReason;
   }

}
