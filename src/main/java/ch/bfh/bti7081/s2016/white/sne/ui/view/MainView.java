package ch.bfh.bti7081.s2016.white.sne.ui.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.ui.presenter.DashboardPresenter;

@Theme("mytheme")
@Widgetset("ch.bfh.bti7081.s2016.white.sne.MyAppWidgetset")
public class MainView extends CustomComponent {

	public MainView() {
		VerticalLayout layout = new VerticalLayout();
		// Create Navigator, use the UI content layout to display the views
		Navigator navigator = new Navigator(UI.getCurrent(), UI.getCurrent());

		// Add some Views
		// NOTE: First View should have empty "" name
		//navigator.addView(MenuView.NAME, new MenuView());
		//navigator.addView("ConfigurationView", ConfigurationViewImpl.class);

		//navigator.addView("DashboardViewImpl", );
	}
}