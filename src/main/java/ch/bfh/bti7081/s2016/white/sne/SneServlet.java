package ch.bfh.bti7081.s2016.white.sne;

import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.ui.Notification;

public class SneServlet extends TouchKitServlet {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(SneServlet.class);

	/**
	 * Class serial ID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void servletInitialized() throws ServletException {
		logger.debug("->");
		
		try {
			super.servletInitialized();
		} catch (ServletException e) {
			// log error
			logger.error("Servlet initlalization failed \n" + e.getMessage(), e);
			Notification.show("Error occured during initializing process! Sorry.", Notification.Type.ERROR_MESSAGE);
			throw e;
		}

		// If we want to be a bit more mobile we should add some of those:
		// TouchKitSettings s = getTouchKitSettings();

		logger.debug("<-");
	}
}