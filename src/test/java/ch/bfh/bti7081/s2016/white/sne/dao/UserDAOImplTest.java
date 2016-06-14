package ch.bfh.bti7081.s2016.white.sne.dao;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

public class UserDAOImplTest {

	private UserDAOImpl dao, dao2;
	private User user1, user2;
	private String dbName = "conf.db";

	@Before
	public void setUp() throws Exception {
		this.dao = new UserDAOImpl();
		this.user1 = new User("kevin.meier");
	}

	@Test
	public void testUserDAOImpl() {
		try {
			dao2 = new UserDAOImpl();
		} catch (SneException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetUserlist() {
		ArrayList<User> l = null;
		try {
			l = dao.getUserlist();
		} catch (SneException e) {
			fail(e.getMessage());
		}

		try {
			for (User u : l) {
				String s = u.getUserName();
				switch (s) {
				case "lucas.wirtz": ;
				case "kathrin.krueger": ;
				case "alice.lang": ;
				case "carolin.gruber": ;
				case "konrad.beck": ;
				case "simone.fisher": ;
				case "kevin.meier": continue;
				default: fail("not all users present");
				}
			}
		} catch (NullPointerException e) {
			fail("no users received");
		}

	}

	@Test
	public void testGetUserId() {
		try {
			if (dao.getUserId(dbName, user1) != 7) {
				fail("user 'kevin.meier' not found");
			}
		} catch (SneException e) {
			fail("an unexcpected exception occured");
		}
	}

}
