package com.mediaportal.ampdroid.data;

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
	
	public String getGroupName() {
		return GroupName;
	}
	public void setGroupName(String groupName) {
		GroupName = groupName;
	}
	public int getIdGroup() {
		return IdGroup;
	}
	public void setIdGroup(int idGroup) {
		IdGroup = idGroup;
	}
	public boolean isIsChanged() {
		return IsChanged;
	}
	public void setIsChanged(boolean isChanged) {
		IsChanged = isChanged;
	}
	public int getSortOrder() {
		return SortOrder;
	}
	public void setSortOrder(int sortOrder) {
		SortOrder = sortOrder;
	}
}
