package ch.bfh.bti7081.s2016.white.sne.ui.model;

import java.util.ArrayList;

import ch.bfh.bti7081.s2016.white.sne.bl.LoginFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.LoginFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.User;

public class LoginProvider {
	private LoginFacade facade;

	
	public LoginProvider() {
		facade = new LoginFacadeImpl();
	}
	
	public User checkPassword(String username, String password){
		return facade.checkPassword(username, password);
	}	
	
	public User getUser(String username) {
		return facade.getUser(username);
	}
	
}