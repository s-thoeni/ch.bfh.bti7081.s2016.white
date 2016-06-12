package ch.bfh.bti7081.s2016.white.sne.ui;

import org.vaadin.thomas.slidemenu.SlideMenu;

import com.vaadin.addon.touchkit.ui.NavigationManager.NavigationEvent;
import com.vaadin.addon.touchkit.ui.NavigationManager.NavigationListener;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * An enhanced {@link NavigationView} with button to open the {@link SlideMenu}.
 * <p>
 * Button will automatically be hidden on navigation to sub-views (displaying
 * the normal back-link instead).
 *
 * @author thomas
 *
 */
public class SlideMenuView extends NavigationView implements NavigationListener {

	private static final long serialVersionUID = 3952898936850021537L;

	protected SlideMenu menu = new SlideMenu();
	protected Button menuButton;

	public SlideMenuView() {

		menuButton = new Button();
		menuButton.setIcon(FontAwesome.BARS);
		menuButton.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 6014031942527721065L;

			@Override
			public void buttonClick(ClickEvent event) {
				menu.open();
			}
		});

		getNavigationBar().setLeftComponent(menuButton);

	}

	@Override
	public void attach() {
		super.attach();
		if (getNavigationManager() != null) {
			getNavigationManager().addNavigationListener(this);
		}
	}

	public SlideMenu getMenu() {
		return menu;
	}

	/**
	 * Change the icon for the menu button. Default is {@link FontAwesome#BARS}
	 *
	 * @param icon
	 */
	public void setMenuIcon(Resource icon) {
		menuButton.setIcon(icon);
	}

	@Override
	public void navigate(NavigationEvent event) {
		// Dont throw a damn nullpointer in my face, Thomas! Kind regards, T!
		if(getNavigationManager() == null || getNavigationManager().getCurrentComponent() == null) return;
		
		// When navigating, do one of two things:
		
		if (getNavigationManager().getCurrentComponent().equals(this)) {
			// 1. if navigating back to this view, re-add the menu button to top
			// left corner
			setLeftComponent(menuButton);
		} else {
			// 2. if navigating away, make sure the menu is closed
			menu.close();
		}

	}
}