package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.thomas.slidemenu.SlideMenu;
import org.vaadin.thomas.slidemenu.SlideMenu.SlideMenuListener;
import org.vaadin.thomas.slidemenu.SlideMenuView;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.bl.ConfigurationFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ConfigurationFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.User;

@SuppressWarnings("serial")
public class UiLayoutImpl extends SlideMenuView implements UiLayout {
	//TODO get User via User Provider... ?
	private static final String USER = "T-Boy!";
	private Configuration config;

	private List<UiLayoutListener> listeners = new ArrayList<UiLayoutListener>();
	private final VerticalLayout root;

	public UiLayoutImpl() {
		root = new VerticalLayout();
		root.setSizeFull();

		root.setMargin(true);
		root.setSpacing(true);
		root.setHeight("100%");
		root.setWidth("100%");
		setContent(root);

		getNavigationBar().setCaption("SNE");

		// Get the user-config:
		ConfigurationFacade configFacade = new ConfigurationFacadeImpl();
		this.config = configFacade.getConfig(new User(USER));

		// add menu items
		buildMenu();

		// We can have listeners
		/*getMenu().addSlideMenuListener(new SlideMenuListener() {
			@Override
			public void menuOpened() {
				for (UiLayoutListener listener : listeners)
					//listener.menuOpened();
			}

			@Override
			public void menuClosed() {
				for (UiLayoutListener listener : listeners)
					//listener.menuClosed();
			}
		});*/

		// We can also set the width of the popup, default is 80%
		getMenu().setWidth("50%");
	}

	private void buildMenu() {

		// close button
		final Button close = new Button("close menu");
		close.setWidth(null);

		close.addClickListener((ClickEvent event) -> {
			for (UiLayoutListener listener : listeners)
				listener.menuClosed();
		});
		getMenu().addComponent(close);

		//User
		Label user = new Label("Hi! " + USER);
		user.addStyleName(SlideMenu.STYLENAME_SECTIONLABEL);
		getMenu().addComponent(user);

		// Buttons with styling (slightly smaller with left-aligned text)
		Button b = new Button("Dashboard");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);

		b.addClickListener((ClickEvent event) -> {
			for (UiLayoutListener listener : listeners)
				listener.showDashboard(config);
		});
				

		// Section labels have a bolded style
		Label l = new Label("Human Resources");
		l.addStyleName(SlideMenu.STYLENAME_SECTIONLABEL);
		getMenu().addComponent(l);

		// add more buttons for a more realistic look.
		b = new Button("Report HR");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);

		l = new Label("Patients / Health care");
		l.addStyleName(SlideMenu.STYLENAME_SECTIONLABEL);
		getMenu().addComponent(l);

		b = new Button("Report P1");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);

		b = new Button("Report P2");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);

		l = new Label("Finance");
		l.addStyleName(SlideMenu.STYLENAME_SECTIONLABEL);
		getMenu().addComponent(l);

		b = new Button("Report F1");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);

		b = new Button("Report F2");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);

		l = new Label("Settings:");
		l.addStyleName(SlideMenu.STYLENAME_SECTIONLABEL);
		getMenu().addComponent(l);

		b = new Button("Options");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);

		b.addClickListener((ClickEvent event) -> {
			for (UiLayoutListener listener : listeners)
				listener.showConfiguration();
		});
	}

	public static String getUser() {
		return USER;
	}
	
	@Override
	public void addListener(UiLayoutListener listener) {
		this.listeners.add(listener);

	}
}
