package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ConfigurationProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.model.DashboardProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.UiLayout;
import ch.bfh.bti7081.s2016.white.sne.ui.view.UiLayoutImpl;

/**
 * Handles the action for the ui layout
 * @author mcdizzu
 *
 */
public class UiPresenter implements UiLayout.UiLayoutListener{
	
	private UiLayoutImpl view;

	public UiPresenter(UiLayoutImpl view) {
		this.view = view;
		this.view.addListener(this);
		
	}
	
	public Component getView() {
		return (UiLayoutImpl)this.view;
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
		DashboardPresenter db = new DashboardPresenter(new DashboardProvider(config), new DashboardViewImpl());
		// Only this button actually does something in the menu. Here we
		// navigate to a dummy view.
		view.setContent(db.getView());
		 //view.getNavigationManager().navigateTo(db.getView());
		
	}

	@Override
	public void showConfiguration() {
		menuClosed();
		
		ConfigurationProvider prov = new ConfigurationProvider(new User(UiLayoutImpl.getUser()));
		ConfigurationView cv = new ConfigurationViewImpl();
		ConfigurationPresenter cp = new ConfigurationPresenter(prov, cv);
		// Only this button actually does something in the menu. Here we
		// navigate to a dummy view.
		view.setContent(cp.getView());
		// getNavigationManager().navigateTo(db.getView());
		
	}

	@Override
	public void showReport() {
		// TODO Auto-generated method stub
		
	}

	
}
