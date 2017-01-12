package com.zeedle.dao;

import java.util.List;

import com.zeedle.model.Friends;

public interface FriendsDAO {
public List<Friends> getMyFriend(int userID);
	
	public Friends get(int userID, int friendID);
	public Friends get(int id);
		public boolean save(Friends friends);
		public boolean update(Friends friends);
		public boolean delete(int userID, int friendID);
		public List<Friends> getNewFriendRequests(int userID);
		public void setOnline(int userID);
		public void setOffLine(int loggedInUserID);
}
