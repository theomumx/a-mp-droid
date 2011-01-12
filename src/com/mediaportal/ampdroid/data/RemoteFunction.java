package com.mediaportal.remote.data;

public class RemoteFunction {
   private int Id;
   private String Name;
   private String Description;
   private String Image;
   
   public int getId() {
      return Id;
   }
   public void setId(int id) {
      this.Id = id;
   }
   public String getName() {
      return Name;
   }
   public void setName(String name) {
      this.Name = name;
   }
   public String getDescription() {
      return Description;
   }
   public void setDescription(String description) {
      this.Description = description;
   }
   public String getImage() {
      return Image;
   }
   public void setImage(String image) {
      this.Image = image;
   }
   
   public RemoteFunction(int _id, String _name, String _description, String _image)
   {
      Id = _id;
      Name = _name;
      Description = _description;
      Image = _image;
   }
   public RemoteFunction() {
      // TODO Auto-generated constructor stub
   }
}
