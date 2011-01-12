package com.mediaportal.remote.lists;

public class TvServerFeature {
   private String mName;

   private String mDescription;
   private int mIcon;
   
   public String getName() {
      return mName;
   }

   public void setName(String mName) {
      this.mName = mName;
   }

   public String getDescription() {
      return mDescription;
   }

   public void setDescription(String _description) {
      this.mDescription = _description;
   }

   public int getIcon() {
      return mIcon;
   }

   public void setIcon(int _icon) {
      this.mIcon = _icon;
   }


   public TvServerFeature(String _name, String _description, int _icon) {
      mName = _name;
      mDescription = _description;
      mIcon = _icon;
   }
}
