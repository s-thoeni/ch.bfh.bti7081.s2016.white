package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.thomas.slidemenu.SlideMenu;
import org.vaadin.thomas.slidemenu.SlideMenuView;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.ui.presenter.DashboardPresenter;
import ch.bfh.bti7081.s2016.white.sne.ui.presenter.SneMenuPresenter;


@SuppressWarnings("serial")
public class SneMenuViewImpl extends SlideMenuView implements SneMenuView{

	private static final String USER = "T_Boy!";
	
	// Get the user-config: 
	Configuration config;
	
	private List<SneMenuListener> listeners;
	
	public SneMenuViewImpl(Configuration config) {
		this.config = config;
		listeners = new ArrayList<SneMenuListener>();
		// add menu items
		buildMenu();

		new SneMenuPresenter(this);
		
		// We can also set the width of the popup, default is 80%
		getMenu().setWidth("70%");
	}
	
	private void buildMenu() {

		// close button
		final Button close = new Button("close menu");
		close.setWidth(null);

		close.addClickListener((ClickEvent event) -> {
			for (SneMenuListener listener : listeners)
				listener.menuClosed();
		});
		getMenu().addComponent(close);

		//User
		Label user = new Label("Hi! " + USER);
		user.addStyleName(SlideMenu.STYLENAME_SECTIONLABEL);
		getMenu().addComponent(user);

		// Buttons with styling (slightly smaller with left-aligned text)
		Button b = new Button("Dashboard");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);

		b.addClickListener((ClickEvent event) -> {
			for (SneMenuListener listener : listeners)
				listener.showDashboard(config);
		});
				

		// Section labels have a bolded style
		b = new Button("Reports");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);
		b.addClickListener((ClickEvent event) -> {
			for (SneMenuListener listener : listeners)
				listener.showReports();
		});
		
		Label l = new Label("Settings:");
		l.addStyleName(SlideMenu.STYLENAME_SECTIONLABEL);
		getMenu().addComponent(l);

		b = new Button("configure Dashboard");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);

		b.addClickListener((ClickEvent event) -> {
			for (SneMenuListener listener : listeners)
				listener.showConfiguration();
		});
		
		b = new Button("configure Alarms");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);

		b.addClickListener((ClickEvent event) -> {
			for (SneMenuListener listener : listeners)
				listener.showAlarms();
		});
	}

	public static String getUser() {
		return USER;
	}
	
	@Override
	public void addListener(SneMenuListener listener) {
		this.listeners.add(listener);

	}
	
	 public NavigationManager getNavigationManager() {
		 return super.getNavigationManager();
	 }

	public void navigateTo(Component view) {
		navigateTo(view);		
	}
}
