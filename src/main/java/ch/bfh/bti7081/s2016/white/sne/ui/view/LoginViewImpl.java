package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;

import ch.bfh.bti7081.s2016.white.sne.bl.LoginFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.User;


public class LoginViewImpl extends NavigationView implements LoginView {

	private VerticalLayout grid;
	private List<LoginViewListener> listeners;
	private ArrayList<User> userList;
	private LoginFacadeImpl facade;
	
	private NativeSelect nameSelector;
	private PasswordField passwordField;
	private Button loginButton;
	
	public LoginViewImpl() {
		super.setCaption("Welcomme to SNE! ");
		listeners = new ArrayList<LoginViewListener>();
		grid = new VerticalLayout();
		//grid.setSizeFull();
		
		nameSelector = new NativeSelect();
		passwordField = new PasswordField();
		
		loginButton = new Button("Login");
		
		facade = new LoginFacadeImpl();
		
		userList = facade.getUsers();
		
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
		
	}
	
	public void handleClickLogin() {
		for(LoginViewListener listener : listeners)
			try {
				listener.loginClick();
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Error with the encryption of the password");
				e.printStackTrace();
			}
	}

	@Override
	public void addListener(LoginViewListener listener) {
		listeners.add(listener);		
	}
	
	public User getLoginName() {
		for(User u : userList) {
			System.out.println("arschloch: " +nameSelector.getValue());
			System.out.println(u.getUserName());
			if(nameSelector.getValue().equals(u.getUserName())) {
				return u;
			}
		}
		return null;
	}
	
	public String getPassword() {
		return passwordField.getValue();
	}
	
}
