package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ConfigurationProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.model.DashboardProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.SneMenuView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.SneMenuViewImpl;

/**
 * Handles the action for the ui layout
 * @author mcdizzu
 *
 */
public class SneMenuPresenter implements SneMenuView.SneMenuListener{
	
	private static final String USER = "T-Boy";
	private SneMenuViewImpl view;

	public SneMenuPresenter(SneMenuViewImpl view) {
		this.view = view;
		this.view.addListener(this);
		
	}
	
	public Component getView() {
		return (SneMenuViewImpl)this.view;
	}

	@Override
	public void menuOpened() {
		//this.view.getRoot().addComponent(new Label("opened!"));
		this.view.getMenu().open();
		
	}

	@Override
	public void menuClosed() {
		//this.view.getRoot().addComponent(new Label("closed!"));
		this.view.getMenu().close();		
	}

	@Override
	public void showDashboard(Configuration config) {
		menuClosed();
		DashboardPresenter db = new DashboardPresenter(new DashboardProvider(config), new DashboardViewImpl(config));
		// Only this button actually does something in the menu. Here we
		// navigate to a dummy view.
		//view.setContent(db.getView());
		 view.getNavigationManager().navigateTo(db.getView());
		
	}

	@Override
	public void showConfiguration() {
		// TODO automate with the nav listener
		menuClosed();				
		ConfigurationProvider prov = new ConfigurationProvider(new User(USER));
		ConfigurationView view = new ConfigurationViewImpl();
		ConfigurationPresenter cv = new ConfigurationPresenter(prov,view);
		// Only this button actually does something in the menu. Here we
		// navigate to a dummy view.
		
		this.view.navigateTo(cv.getView());
		
	}

	@Override
	public void showReport() {
		// TODO Auto-generated method stub
		
	}

	
}