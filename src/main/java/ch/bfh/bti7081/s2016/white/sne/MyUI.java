package ch.bfh.bti7081.s2016.white.sne;

import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2016.white.sne.bl.ConfigurationFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ConfigurationFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.ui.model.DashboardProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.presenter.DashboardPresenter;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardViewImpl;


@SuppressWarnings("serial")
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	final NavigationManager layout = new NavigationManager();
		setContent(layout);
		ConfigurationFacade fac = new ConfigurationFacadeImpl();
		Configuration config = fac.getConfig(new User("T-Boy"));
		DashboardPresenter db = new DashboardPresenter(new DashboardProvider(config), new DashboardViewImpl(config));		
		
		layout.navigateTo(db.getView());

    }

    public static class MyUIServlet extends TouchKitServlet {
    }
}
