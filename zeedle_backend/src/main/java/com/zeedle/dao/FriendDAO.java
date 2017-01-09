package com.zeedle.dao;

import java.util.List;

import com.zeedle.model.Friend;

public interface FriendDAO {
	public List<Friend> getMyFriends(int id);  //BY USERID
	public List<Friend> getMyFriend(int friendId);
	public Friend get(int id, int friendId );
	public boolean save(Friend friend);
	public boolean update(Friend friend);
	public void delete(int id, int friendId);
	public List<Friend> getNewFriendRequests(int friendId);
	public void setOnline(int friendId);
	public void setOffLine(int friendId);
			// HERE id means userId;
}
