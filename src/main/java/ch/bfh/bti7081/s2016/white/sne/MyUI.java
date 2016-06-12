package ch.bfh.bti7081.s2016.white.sne;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2016.white.sne.bl.AlarmFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.AlarmFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.bl.ConfigurationFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ConfigurationFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
import ch.bfh.bti7081.s2016.white.sne.ui.model.DashboardProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.presenter.DashboardPresenter;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardViewImpl;

@SuppressWarnings("serial")
@Theme("mytheme")
public class MyUI extends UI {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(MyUI.class);

	private DashboardPresenter db;

	private NavigationManager navigationManager;

	static {
		SLF4JBridgeHandler.install();
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		logger.debug("->");
		logger.info("Initializing UI");

		this.navigationManager = new NavigationManager();

		setContent(getNavigationManager());
		User user = new User("lucas.wirtz");
		ConfigurationFacade configFac;
		try {
			configFac = new ConfigurationFacadeImpl();

			Configuration config = configFac.getConfig(user);

			AlarmFacade alarmFac = new AlarmFacadeImpl(user);
			List<Alarm> alarms = alarmFac.getAlarms();

			DashboardProvider provider = new DashboardProvider(config, user);
			DashboardViewImpl view = new DashboardViewImpl(config, alarms);

			db = new DashboardPresenter(provider, view);

			logger.info("Navigating to dashbord");
			
			getNavigationManager().navigateTo(db.getView());
		} catch (SneException e) {
			logger.error(e.getMessage(), e);
			Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}

		logger.debug("<-");
	}

	public DashboardPresenter getDashboard() {
		logger.debug("->");
		logger.debug("<-");
		return db;
	}

	public NavigationManager getNavigationManager() {
		logger.debug("->");
		
		System.out.println(this.navigationManager.getCurrentComponent());
		logger.debug("<-");
		return this.navigationManager;
	}

	public void setNavigationManager(NavigationManager navigationManager) {
		logger.debug("->");
		
		this.navigationManager = navigationManager;
		logger.debug("<-");
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
