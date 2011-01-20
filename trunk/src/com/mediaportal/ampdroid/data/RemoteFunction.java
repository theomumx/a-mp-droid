package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;

public class RemoteFunction {
   private int Id;
   private String Name;
   private String Description;
   private String Image;
   
   public RemoteFunction(int _id, String _name, String _description, String _image) {
      Id = _id;
      Name = _name;
      Description = _description;
      Image = _image;
   }

   public RemoteFunction() {
      // TODO Auto-generated constructor stub
   }
   
   @JsonProperty("Id")
   public int getId() {
      return Id;
   }
   
   @JsonProperty("Id")
   public void setId(int id) {
      this.Id = id;
   }
   
   @JsonProperty("Name")
   public String getName() {
      return Name;
   }
   
   @JsonProperty("Name")
   public void setName(String name) {
      this.Name = name;
   }
   
   @JsonProperty("Description")
   public String getDescription() {
      return Description;
   }
   
   @JsonProperty("Description")
   public void setDescription(String description) {
      this.Description = description;
   }
   
   @JsonProperty("Image")
   public String getImage() {
      return Image;
   }
   
   @JsonProperty("Image")
   public void setImage(String image) {
      this.Image = image;
   }
}
