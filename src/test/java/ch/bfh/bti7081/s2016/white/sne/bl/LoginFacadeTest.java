/**
 * 
 */
package ch.bfh.bti7081.s2016.white.sne.bl;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * @author eller1
 *
 */
public class LoginFacadeTest {

	/**
	 * facade for testing
	 */
	LoginFacade facade;
	
	/**
	 * Username used for testing
	 */
	private static final String USERNAME = "kevin.meier";
	
	/**
	 * kevin.meier's password as sha256 hash
	 */
	private static final String PASSWORD_HASH = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3";
	
	/**
	 * Setup test
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.facade = new LoginFacadeImpl();
	}
	
	/**
	 * test password checking
	 */
	@Test
	public void testCheckPassword() {
		User returnUser;
		try {
			returnUser = facade.checkPassword(USERNAME, PASSWORD_HASH);
			if (returnUser == null) {
				fail("password check failed");
			}
		} catch (SneException e) {
			fail("password check failed");
		}
		assert(true);
	}

	/**
	 * test getting one user
	 */
	@Test
	public void testGetUser() {
		User returnUser;
		try {
			returnUser = facade.getUser(USERNAME);
			if (returnUser == null) {
				fail("getUser() failed");
			}
		} catch (SneException e) {
			fail("getUser() failed");
		}
		assert(true);
	}
	
	/**
	 * test getting all users
	 */
	@Test
	public void testGetUsers() {
		ArrayList<User> returnUsers;
		try {
			returnUsers = facade.getUsers();
			if (returnUsers.isEmpty()) {
				fail("no users were returned");
			}
		} catch (SneException e) {
			fail("getUsers() failed");
		}
		assert(true);
	}
}
