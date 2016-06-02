package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ConfigurationProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.model.DashboardProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ReportSelectProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportSelectViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.SneMenuView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.SneMenuViewImpl;

/**
 * Handles the action for the ui layout
 * 
 * @author mcdizzu
 *
 */
public class SneMenuPresenter implements SneMenuView.SneMenuListener {

	private static final String USER = "lucas.wirtz";
	private SneMenuViewImpl view;

	public SneMenuPresenter(SneMenuViewImpl view) {
		this.view = view;
		this.view.addListener(this);

	}

	public Component getView() {
		return (SneMenuViewImpl) this.view;
	}

	@Override
	public void menuOpened() {
		this.view.getMenu().open();

	}

	@Override
	public void menuClosed() {
		this.view.getMenu().close();
	}

	@Override
	public void showDashboard(Configuration config) {
		menuClosed();
		if (view instanceof DashboardView)
			return;
		DashboardPresenter db = new DashboardPresenter(new DashboardProvider(config), new DashboardViewImpl(config));
		view.getNavigationManager().navigateTo(view);

	}

	@Override
	public void showConfiguration() {
		// TODO automate with the nav listener
		menuClosed();
		ConfigurationProvider prov = new ConfigurationProvider(new User(USER));
		ConfigurationViewImpl view = new ConfigurationViewImpl();
		ConfigurationPresenter cv = new ConfigurationPresenter(prov, view);
		// Only this button actually does something in the menu. Here we
		// navigate to a dummy view.

		this.view.navigateTo(cv.getView());

	}

	@Override
	public void showReports() {
		menuClosed();
		ReportSelectPresenter rsp = new ReportSelectPresenter(new ReportSelectProvider(), new ReportSelectViewImpl());
		view.getNavigationManager().navigateTo(rsp.getView());

	}

}