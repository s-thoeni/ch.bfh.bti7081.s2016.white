package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.ui.model.AlarmConfigurationProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ConfigurationProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.AlarmConfigurationViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationViewImpl;
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
		// currently the only menuview is the dashboard itself
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

		view.getNavigationManager().navigateTo(cv.getView());

	}

	@Override
	public void showReports() {
		menuClosed();
		ReportSelectPresenter rsp = new ReportSelectPresenter(new ReportSelectViewImpl());
		view.getNavigationManager().navigateTo(rsp.getView());

	}

	@Override
	public void showAlarms() {
		// TODO automate with the nav listener
		menuClosed();
		AlarmConfigurationProvider prov = new AlarmConfigurationProvider(new User(USER));
		AlarmConfigurationViewImpl view = new AlarmConfigurationViewImpl();
		AlarmConfigurationPresenter cv = new AlarmConfigurationPresenter(prov, view);
		// Only this button actually does something in the menu. Here we
		// navigate to a dummy view.

		this.view.getNavigationManager().navigateTo(cv.getView());

	}

}