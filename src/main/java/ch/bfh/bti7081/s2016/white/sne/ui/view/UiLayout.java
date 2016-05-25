package ch.bfh.bti7081.s2016.white.sne.ui.view;

import org.vaadin.thomas.slidemenu.SlideMenu;
import org.vaadin.thomas.slidemenu.SlideMenu.SlideMenuListener;
import org.vaadin.thomas.slidemenu.SlideMenuView;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


@SuppressWarnings("serial")
public class UiLayout extends SlideMenuView {
	
	public UiLayout() {
		final VerticalLayout root = new VerticalLayout();
		root.setMargin(true);
		root.setSpacing(true);
		setContent(root);

		getNavigationBar().setCaption("header");

		// add menu items
		buildMenu();

		// We can have listeners
		getMenu().addSlideMenuListener(new SlideMenuListener() {

			@Override
			public void menuOpened() {

				root.addComponent(new Label("opened"));
			}

			@Override
			public void menuClosed() {

				root.addComponent(new Label("closed"));
			}
		});

		// We can also set the width of the popup, default is 80%
		getMenu().setWidth("70%");
	}
	
	private void buildMenu() {

		// Just a normal Vaadin button
		final Button close = new Button("close menu");
		close.setWidth(null);
		close.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// Programmatic closing of the menu
				getMenu().close();
			}
		});
		getMenu().addComponent(close);

		

		// Buttons with styling (slightly smaller with left-aligned text)
		Button b = new Button("Dashboard");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);

		b.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				// TODO automate with the nav listener
				getMenu().close();

				// Only this button actually does something in the menu. Here we
				// navigate to a dummy view.
				getNavigationManager().navigateTo(new NavigationView() {
					private static final long serialVersionUID = 7226460754270812124L;

					{
						setContent(new Label("another view"));
						setCaption("DashBoard");
					}
				});
			}
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
		
	}
}
