package com.zeedle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeedle.daoimpl.FriendsDAOImpl;
import com.zeedle.model.Friends;

@Transactional
@Service
public class FriendsService {
	@Autowired (required=true)
	FriendsDAOImpl friendsDAO;
	
	public List<Friends> getMyFriend(int userID) {
		return friendsDAO.getMyFriend(userID);
	}
	
	public Friends get(int userID, int friendID) {
		return friendsDAO.get(userID, friendID);
	}
	
	public Friends get(int id) {
		return friendsDAO.get(id);
	}
	
	public boolean save(Friends friends) {
		return friendsDAO.save(friends);
	}
	
	public boolean update(Friends friends) {
		return friendsDAO.update(friends);
	}
	
	public boolean delete(int userID, int friendID) {
		return friendsDAO.delete(userID, friendID);
		
	}
	
	public List<Friends> getNewFriendRequests(int userID) {
		return friendsDAO.getNewFriendRequests(userID);
	}
	
	public void setOnline(int loggedInUserID) {
		friendsDAO.setOnline(loggedInUserID);
	}
	
	public void setOffLine(int loggedInUserID) {
		friendsDAO.setOffLine(loggedInUserID);
	}
}
