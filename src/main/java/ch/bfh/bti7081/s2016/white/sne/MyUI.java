package ch.bfh.bti7081.s2016.white.sne;

import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2016.white.sne.ui.presenter.UiPresenter;
import ch.bfh.bti7081.s2016.white.sne.ui.view.UiLayoutImpl;



@SuppressWarnings("serial")
@Theme("slidemenutheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	final NavigationManager layout = new NavigationManager();
		setContent(layout);

		
		final UiLayoutImpl ul = new UiLayoutImpl();
		UiPresenter up = new UiPresenter(ul);
		
		layout.navigateTo(ul);

    }

    public static class MyUIServlet extends TouchKitServlet {
    }
}
