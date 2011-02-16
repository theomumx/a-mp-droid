package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mediaportal.ampdroid.database.ColumnProperty;

public class TvChannelGroup {
   private String GroupName;
   private int IdGroup;
   private boolean IsChanged;
   private int SortOrder;
	
	@Override
   public String toString(){
	   if(GroupName != null){
	      return GroupName;
	   }
	   else return "[Unknown Group]";
	}
   
   @ColumnProperty(value="GroupName", type="text")
	@JsonProperty("GroupName")
	public String getGroupName() {
		return GroupName;
	}
   
   @ColumnProperty(value="GroupName", type="text")
	@JsonProperty("GroupName")
	public void setGroupName(String groupName) {
	   GroupName = groupName;
	}
   
   @ColumnProperty(value="IdGroup", type="integer")
   @JsonProperty("IdGroup")
	public int getIdGroup() {
		return IdGroup;
	}
   
   @ColumnProperty(value="IdGroup", type="integer")
	@JsonProperty("IdGroup")
	public void setIdGroup(int idGroup) {
	   IdGroup = idGroup;
	}
   
   @ColumnProperty(value="IsChanged", type="boolean")
	@JsonProperty("IsChanged")
	public boolean isIsChanged() {
		return IsChanged;
	}
   
   @ColumnProperty(value="IsChanged", type="boolean")
   @JsonProperty("IsChanged")
	public void setIsChanged(boolean isChanged) {
	   IsChanged = isChanged;
	}
   
   @ColumnProperty(value="SortOrder", type="integer")
   @JsonProperty("SortOrder")
	public int getSortOrder() {
		return SortOrder;
	}
   
   @ColumnProperty(value="SortOrder", type="integer")
	@JsonProperty("SortOrder")
	public void setSortOrder(int sortOrder) {
	   SortOrder = sortOrder;
	}
}
