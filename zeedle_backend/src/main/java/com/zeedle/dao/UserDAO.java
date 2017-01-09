package com.zeedle.dao;

import java.util.List;

import com.zeedle.model.UserDetail;

public interface UserDAO {
	
	 List<UserDetail> getAllUser();
	 UserDetail getUserById(int id);
	 UserDetail getByName(String name);
	    boolean addUser(UserDetail user);
	    boolean updateUser(UserDetail user);
	    void deleteUser(int id);
	    UserDetail authenticate(String name, String password);
	    public void setOnline(int loggedInUserId);
		public void setOffLine(int loggedInUserId);


}
