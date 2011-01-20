package com.mediaportal.ampdroid.data;

import org.codehaus.jackson.annotate.JsonProperty;

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
	
	@JsonProperty("GroupName")
	public String getGroupName() {
		return GroupName;
	}
	
	@JsonProperty("GroupName")
	public void setGroupName(String groupName) {
	   GroupName = groupName;
	}
	  
   @JsonProperty("IdGroup")
	public int getIdGroup() {
		return IdGroup;
	}
	
	@JsonProperty("IdGroup")
	public void setIdGroup(int idGroup) {
	   IdGroup = idGroup;
	}
	
	@JsonProperty("IsChanged")
	public boolean isIsChanged() {
		return IsChanged;
	}
	  
   @JsonProperty("IsChanged")
	public void setIsChanged(boolean isChanged) {
	   IsChanged = isChanged;
	}
	  
   @JsonProperty("SortOrder")
	public int getSortOrder() {
		return SortOrder;
	}
	
	@JsonProperty("SortOrder")
	public void setSortOrder(int sortOrder) {
	   SortOrder = sortOrder;
	}
}
