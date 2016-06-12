package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.vaadin.thomas.slidemenu.SlideMenu;
import org.vaadin.thomas.slidemenu.SlideMenuView;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
import ch.bfh.bti7081.s2016.white.sne.ui.presenter.SneMenuPresenter;

@SuppressWarnings("serial")
public class SneMenuViewImpl extends SlideMenuView implements SneMenuView {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(SneMenuViewImpl.class);

	private static final String USER = "T_Boy!";

	// Get the user-config:
	Configuration config;
	List<Alarm> alarms;

	private List<SneMenuListener> listeners;

	public SneMenuViewImpl(Configuration config, List<Alarm> alarms) {
		this.config = config;
		this.alarms = alarms;
		listeners = new ArrayList<SneMenuListener>();
		// add menu items
		buildMenu();

		// add alarming popover
		buildAlarming();

		new SneMenuPresenter(this);

		// We can also set the width of the popup, default is 80%
		getMenu().setWidth("70%");
	}

	private void buildMenu() {
		logger.debug("->");

		// close button
		final Button close = new Button("close menu");
		close.setWidth(null);

		close.addClickListener((ClickEvent event) -> {
			for (SneMenuListener listener : listeners)
				listener.menuClosed();
		});
		getMenu().addComponent(close);

		// User
		Label user = new Label("Hi! " + USER);
		user.addStyleName(SlideMenu.STYLENAME_SECTIONLABEL);
		getMenu().addComponent(user);

		// Buttons with styling (slightly smaller with left-aligned text)
		Button b = new Button("Dashboard");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);

		b.addClickListener((ClickEvent event) -> {
			for (SneMenuListener listener : listeners)
				listener.showDashboard(config);
		});

		// Section labels have a bolded style
		b = new Button("Reports");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);
		b.addClickListener((ClickEvent event) -> {
			for (SneMenuListener listener : listeners)
				listener.showReports();
		});

		Label l = new Label("Settings:");
		l.addStyleName(SlideMenu.STYLENAME_SECTIONLABEL);
		getMenu().addComponent(l);

		b = new Button("Dashboard Configuration");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);

		b.addClickListener((ClickEvent event) -> {
			for (SneMenuListener listener : listeners)
				listener.showConfiguration();
		});

		b = new Button("Alarm Configuration");
		b.addStyleName(SlideMenu.STYLENAME_BUTTON);
		getMenu().addComponent(b);

		b.addClickListener((ClickEvent event) -> {
			for (SneMenuListener listener : listeners)
				listener.showAlarms();
		});
		logger.debug("<-");
	}

	private void buildAlarming() {
		logger.debug("->");

		boolean hasWarning = false;
		boolean hasError = false;
		Table table = new Table("Alarms");
		table.addContainerProperty("State", Label.class, null);
		table.addContainerProperty("Report", String.class, null);
		table.addContainerProperty("Check", String.class, null);
		int index = 1;
		for (Alarm alarm : alarms) {
			Object[] alarmVisualization = null;
			try {
				alarmVisualization = alarm.visualizeAlarm();
			} catch (SneException e) {
				Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
			}
			if (alarmVisualization != null) {
				table.addItem(alarmVisualization, index);
				switch (alarm.getAlarmStateString()) {
				case "Error":
					hasError = true;
					break;
				case "Warning":
					hasWarning = true;
					break;
				}
				index++;
			}
		}
		table.setPageLength(table.size());
		if (hasError || hasWarning) {
			Popover popover = new Popover();
			popover.setContent(table);
			Button alarmButton = new Button();
			alarmButton.setIcon(FontAwesome.EXCLAMATION_TRIANGLE);
			if (hasError) {
				alarmButton.setStyleName("Error");
			} else if (hasWarning) {
				alarmButton.setStyleName("Warning");
			}
			alarmButton.addClickListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					popover.showRelativeTo(getRightComponent());
				}
			});

			getNavigationBar().setRightComponent(alarmButton);
		}
		logger.debug("<-");
	}

	public static String getUser() {
		logger.debug("->");
		logger.debug("<-");
		return USER;
	}

	@Override
	public void addListener(SneMenuListener listener) {
		logger.debug("->");

		this.listeners.add(listener);
		logger.debug("<-");
	}

	public NavigationManager getNavigationManager() {
		logger.debug("->");
		logger.debug("<-");
		return super.getNavigationManager();
	}
}
