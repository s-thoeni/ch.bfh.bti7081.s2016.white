package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2016.white.sne.MyUI;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
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

	private SneMenuViewImpl view;

	private User user;
	
	public SneMenuPresenter(SneMenuViewImpl view, User user) {
		this.view = view;
		this.view.addListener(this);
		this.user = user;
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

		MyUI ui = (MyUI) UI.getCurrent();
		ui.getNavigationManager().navigateTo(this.getView());

		
		logger.debug("<-");
	}

	@Override
	public void showConfiguration() throws SneException {
		logger.debug("->");
		
		menuClosed();
		ConfigurationProvider prov = new ConfigurationProvider(user);
		ConfigurationViewImpl view = new ConfigurationViewImpl();
		ConfigurationPresenter cv = new ConfigurationPresenter(prov, view);

		MyUI ui = (MyUI) UI.getCurrent();
		ui.getNavigationManager().navigateTo(cv.getView());

		logger.debug("<-");
	}

	@Override
	public void showReports() {
		logger.debug("->");
		
		menuClosed();
		ReportSelectPresenter rsp = new ReportSelectPresenter(new ReportSelectViewImpl());

		MyUI ui = (MyUI) UI.getCurrent();
		ui.getNavigationManager().navigateTo(rsp.getView());

		logger.debug("<-");
	}

	@Override
	public void showAlarms() throws SneException {
		logger.debug("->");
		
		menuClosed();
		AlarmConfigurationProvider prov = new AlarmConfigurationProvider(user);
		AlarmConfigurationViewImpl view = new AlarmConfigurationViewImpl();
		AlarmConfigurationPresenter cv = new AlarmConfigurationPresenter(prov, view);

		MyUI ui = (MyUI) UI.getCurrent();
		ui.getNavigationManager().navigateTo(cv.getView());

		logger.debug("<-");
	}

}