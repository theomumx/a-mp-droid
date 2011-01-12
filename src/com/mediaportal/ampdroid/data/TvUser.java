package com.mediaportal.remote.data;

import java.util.Date;

public class TvUser {
   int CardId;
   Date HeartBeat;
   int IdChannel;
   boolean IsAdmin;
   String Name;
   int SubChannel;
   int TvStoppedReason;
   
   @Override
   public String toString(){
      return Name;
   }
   
   public int getCardId() {
      return CardId;
   }
   public void setCardId(int cardId) {
      CardId = cardId;
   }
   public Date getHeartBeat() {
      return HeartBeat;
   }
   public void setHeartBeat(Date heartBeat) {
      HeartBeat = heartBeat;
   }
   public int getIdChannel() {
      return IdChannel;
   }
   public void setIdChannel(int idChannel) {
      IdChannel = idChannel;
   }
   public boolean isIsAdmin() {
      return IsAdmin;
   }
   public void setIsAdmin(boolean isAdmin) {
      IsAdmin = isAdmin;
   }
   public String getName() {
      return Name;
   }
   public void setName(String name) {
      Name = name;
   }
   public int getSubChannel() {
      return SubChannel;
   }
   public void setSubChannel(int subChannel) {
      SubChannel = subChannel;
   }
   public int getTvStoppedReason() {
      return TvStoppedReason;
   }
   public void setTvStoppedReason(int tvStoppedReason) {
      TvStoppedReason = tvStoppedReason;
   }
   
   
}
