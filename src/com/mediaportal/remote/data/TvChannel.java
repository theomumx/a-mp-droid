package com.mediaportal.remote.data;


public class TvChannel {
   private String DisplayName;
   private int IdChannel;
   private String Name;
   
   @Override
   public String toString() {
      if (DisplayName != null) {
         return DisplayName;
      } else
         return "[Unknown Channel]";
   }

   public String getDisplayName() {
      return DisplayName;
   }

   public void setDisplayName(String displayName) {
      DisplayName = displayName;
   }

   public int getIdChannel() {
      return IdChannel;
   }

   public void setIdChannel(int idChannel) {
      IdChannel = idChannel;
   }

   public String getName() {
      return Name;
   }

   public void setName(String name) {
      Name = name;
   }



}
