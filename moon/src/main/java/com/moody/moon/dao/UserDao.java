package com.moody.moon.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.moody.moon.model.User;

@Repository
public class UserDao {
	
	private static List<User> users=new ArrayList<User>();
	
	private static Integer userCount=3;
	
	static {
		users.add(new User(1,"Adam",new Date()));
		users.add(new User(2,"Eva",new Date()));
		users.add(new User(3,"Kali",new Date()));
	}
        
	public List<User> findAll() {
		return users;
	}

	public User findWithId(int id) {
		for (User user : users)
			if (user.getId() == id) {
				return user;
			}
		return null;
	}
	
	public User save(User user) {
		++userCount;
		users.add(new User(userCount,user.getName(),user.getDate()));
		return new User(userCount,user.getName(),user.getDate());
	}
	
	public User deleteById(int id) {
		Iterator<User> itr=users.iterator();
		while(itr.hasNext()) {
			User user=(User)itr.next();
			if(user.getId() == id) {
				itr.remove();
				return user;
			}
			
		}
	return null;
	}
}
