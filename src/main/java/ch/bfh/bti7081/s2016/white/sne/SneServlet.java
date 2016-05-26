package ch.bfh.bti7081.s2016.white.sne;

import javax.servlet.ServletException;

import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.addon.touchkit.settings.TouchKitSettings;

public class SneServlet extends TouchKitServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();

        TouchKitSettings s = getTouchKitSettings();
      
    }
}