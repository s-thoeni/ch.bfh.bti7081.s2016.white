package ch.bfh.bti7081.s2016.white.sne;

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

import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
import ch.bfh.bti7081.s2016.white.sne.ui.model.LoginProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.presenter.DashboardPresenter;
import ch.bfh.bti7081.s2016.white.sne.ui.presenter.LoginPresenter;
import ch.bfh.bti7081.s2016.white.sne.ui.view.LoginViewImpl;

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
    	final NavigationManager layout = new NavigationManager();
		setContent(layout);
		this.navigationManager = layout;
		
		LoginProvider loginProvider;
		try {
			loginProvider = new LoginProvider();
			LoginViewImpl loginView =  new LoginViewImpl();
			LoginPresenter lp = new LoginPresenter(loginProvider, loginView);
			layout.navigateTo(lp.getView());
		} catch (SneException e) {
			// log error
			logger.error("Initialize login failed \n" + e.getMessage(), e);
			Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		
		logger.debug("<-");
    }

	public DashboardPresenter getDashboard() {
		logger.debug("->");
		logger.debug("<-");
		return this.db;
	}

	public NavigationManager getNavigationManager() {
		logger.debug("->");
		logger.debug("<-");
		return this.navigationManager;
	}

	public void setNavigationManager(NavigationManager navigationManager) {
		logger.debug("->");
		
		this.navigationManager = navigationManager;
		logger.debug("<-");
	}

	public void setDashboard(DashboardPresenter db) {
		this.db = db;
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
