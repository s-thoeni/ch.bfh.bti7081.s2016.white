package ch.bfh.bti7081.s2016.white.sne.dao;

import java.util.ArrayList;

import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

public interface UserDAO {
	public ArrayList<User> getUserlist() throws SneException;
}
