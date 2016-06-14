package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.bl.LoginFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;



public class LoginViewImpl extends NavigationView implements LoginView {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(LoginViewImpl.class);
	
	/**
	 * Class serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	private VerticalLayout grid;
	private List<LoginViewListener> listeners;
	private ArrayList<User> userList;
	private LoginFacadeImpl facade;
	
	private NativeSelect nameSelector;
	private PasswordField passwordField;
	private Button loginButton;
	
	public LoginViewImpl() {
		logger.debug("->");
		super.setCaption("Welcome to SNE! ");
		listeners = new ArrayList<LoginViewListener>();
		grid = new VerticalLayout();
		//grid.setSizeFull();
		grid.setSpacing(true);
		
		nameSelector = new NativeSelect();
		passwordField = new PasswordField();
		
		loginButton = new Button("Login");
		
		try {
			facade = new LoginFacadeImpl();
			userList = facade.getUsers();
		} catch (SneException e1) {
			logger.error(e1.getStackTrace(), e1);
			
		}
		
		nameSelector.setStyleName("configselect");
		nameSelector.setNullSelectionAllowed(false);
		
		nameSelector.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
		for(User c: userList) {
			nameSelector.addItem(c.getUserName());
			nameSelector.setItemCaption(c.getUserName(), c.getUserName());	
		}
		
		nameSelector.setWidth("250px");
		passwordField.setWidth("250px");
		loginButton.setWidth("150px");
		loginButton.setHeight("50px");

		
		loginButton.addClickListener(e -> handleClickLogin());		
		
		grid.addComponent(nameSelector);
		grid.addComponent(passwordField);
		grid.addComponent(loginButton);
		
		grid.setComponentAlignment(nameSelector, Alignment.MIDDLE_CENTER);
		grid.setComponentAlignment(passwordField, Alignment.MIDDLE_CENTER);
		grid.setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);
		
		nameSelector.setCaption("Username");
		passwordField.setCaption("Password");
		
		this.setContent(grid);
		logger.debug("<-");
	}
	
	public void handleClickLogin() {
		logger.debug("->");
		for(LoginViewListener listener : listeners)
			try {
				listener.loginClick();
			} catch (NoSuchAlgorithmException e) {
				SneException se = new SneException("A problem has occured while checking your password! ", e);
				// log error
				logger.error("Error with the encryption of the password" + se.getMessage(), se);
				Notification.show(se.getMessage(), Notification.Type.ERROR_MESSAGE);
			}
	}

	@Override
	public void addListener(LoginViewListener listener) {
		logger.debug("->");
		logger.debug("<-");
		listeners.add(listener);		
	}
	
	public User getLoginName() {
		logger.debug("->");
		for(User u : userList) {
			System.out.println("arschloch: " +nameSelector.getValue());
			System.out.println(u.getUserName());
			if(nameSelector.getValue().equals(u.getUserName())) {
				logger.debug("<- return user");
				return u;
			}
		}
		logger.debug("<- return null");
		return null;
	}
	
	public String getPassword() {
		logger.debug("->");
		logger.debug("<-");
		return passwordField.getValue();
	}
	
}
