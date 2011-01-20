package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;


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

   @JsonProperty("DisplayName")
   public String getDisplayName() {
      return DisplayName;
   }
   
   @JsonProperty("DisplayName")
   public void setDisplayName(String displayName) {
      DisplayName = displayName;
   }

   @JsonProperty("IdChannel")
   public int getIdChannel() {
      return IdChannel;
   }

   @JsonProperty("IdChannel")
   public void setIdChannel(int idChannel) {
      IdChannel = idChannel;
   }

   @JsonProperty("Name")
   public String getName() {
      return Name;
   }

   @JsonProperty("Name")
   public void setName(String name) {
      Name = name;
   }
}
