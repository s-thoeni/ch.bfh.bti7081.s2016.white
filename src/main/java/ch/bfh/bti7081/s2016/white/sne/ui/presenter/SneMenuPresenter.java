package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.addon.touchkit.ui.NavigationManager;
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

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(SneMenuPresenter.class);

	private static final String USER = "lucas.wirtz";
	private SneMenuViewImpl view;

	public SneMenuPresenter(SneMenuViewImpl view) {
		this.view = view;
		this.view.addListener(this);

	}

	public Component getView() {
		logger.debug("->");
		logger.debug("<-");
		return (SneMenuViewImpl) this.view;
	}

	@Override
	public void menuOpened() {
		logger.debug("->");
		
		this.view.getMenu().open();
		logger.debug("<-");
	}

	@Override
	public void menuClosed() {
		logger.debug("->");
		this.view.getMenu().close();
		logger.debug("<-");
	}

	@Override
	public void showDashboard(Configuration config) {
		logger.debug("->");
		
		menuClosed();
		// currently the only menuview is the dashboard itself
		
		logger.debug("<-");
	}

	@Override
	public void showConfiguration() {
		logger.debug("->");
		
		menuClosed();
		ConfigurationProvider prov = new ConfigurationProvider(new User(USER));
		ConfigurationViewImpl view = new ConfigurationViewImpl();
		ConfigurationPresenter cv = new ConfigurationPresenter(prov, view);

		this.view.getNavigationManager().navigateTo(cv.getView());
		logger.debug("<-");
	}

	@Override
	public void showReports() {
		logger.debug("->");
		
		menuClosed();
		ReportSelectPresenter rsp = new ReportSelectPresenter(new ReportSelectViewImpl());
		view.getNavigationManager().navigateTo(rsp.getView());
		logger.debug("<-");
	}

	@Override
	public void showAlarms() {
		logger.debug("->");
		
		menuClosed();
		AlarmConfigurationProvider prov = new AlarmConfigurationProvider(new User(USER));
		AlarmConfigurationViewImpl view = new AlarmConfigurationViewImpl();
		AlarmConfigurationPresenter cv = new AlarmConfigurationPresenter(prov, view);

		this.view.getNavigationManager().navigateTo(cv.getView());
		logger.debug("<-");
	}

}