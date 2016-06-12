package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.ArrayList;

import ch.bfh.bti7081.s2016.white.sne.dao.UserDAOImpl;
import ch.bfh.bti7081.s2016.white.sne.data.User;

public class LoginFacadeImpl implements LoginFacade {

	private UserDAOImpl dao;
	
	public LoginFacadeImpl() {
		dao = new UserDAOImpl();
	}
	
	@Override
	public User checkPassword(String username, String password) {
		ArrayList<User> userList = getUsers();
		for(User u: userList) {
			if(u.getUserName() == username) {
				if(u.getPassword() == password) {
					return u;
				}
			}
		}
		return null;
	}

	@Override
	public ArrayList<User> getUsers() {
		return dao.getUserlist();
	}
	
	public User getUser(String username) {
		ArrayList<User> userList = getUsers();
		for (User u: userList) {
			if(u.getUserName() == username) {
				return u;
			}
		}
		return null;
	}
}
