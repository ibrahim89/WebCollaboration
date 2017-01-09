package com.zeedle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeedle.daoimpl.FriendDAOImpl;
import com.zeedle.model.Friend;

@Transactional
@Service
public class FriendService {
	@Autowired (required=true)
	FriendDAOImpl friendDAO;
	
	public boolean save(Friend friend){
		return friendDAO.save(friend);
	}

	public boolean update(Friend friend){
		return friendDAO.update(friend);
	}
	
	public void delete(int id, int fId) {
		friendDAO.delete(id, fId);
	}
	
	public Friend get(int id, int  friendId) {
		return friendDAO.get(id, friendId);
	}
	
	public List<Friend> getMyFriends(int id) {
		return friendDAO.getMyFriends(id);
	}

	public List<Friend> getNewFriendRequests(int friendId) {
		return friendDAO.getNewFriendRequests(friendId);
	}
	
	public void setOnline(int friendId) {
		friendDAO.setOnline(friendId);
	}
	
	public void setOffLine(int friendId) {
		friendDAO.setOffLine(friendId);
	}
	
	public List<Friend> getMyFriend(int friendId) {
	return friendDAO.getMyFriend(friendId);
	}
}
