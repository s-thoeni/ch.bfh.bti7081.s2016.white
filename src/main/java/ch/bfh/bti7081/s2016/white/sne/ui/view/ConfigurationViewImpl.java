package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSetImpl;

/**
 * Implemntation of the configuration view. Shows configuration sets 
 * through which the dashboard tiles could be configured.
 * Shows 
 * 
 * @author shepd1
 *
 */
public class ConfigurationViewImpl extends NavigationView implements ConfigurationView {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ConfigurationViewImpl.class);

	/**
	 * UI Grid Layout
	 */
	private VerticalLayout grid;
	
	/**
	 * List of Configsets displayed
	 */
	private List<ConfigSetImpl> configSets;
	
	/**
	 * List of Listeners for the configurationview.
	 */
	private List<ConfigurationViewListener> listeners;

	/**
	 * The title to be displayed in the header of the menu
	 */
	private static final String CONFIGURATION_TITLE = "Dashboard Configuration";

	/**
	 * Class serial ID
	 */
	private static final long serialVersionUID = 1L;

	public ConfigurationViewImpl() {
		// Set the caption
		getNavigationBar().setCaption(CONFIGURATION_TITLE);

		this.configSets = new ArrayList<ConfigSetImpl>();
		this.listeners = new ArrayList<ConfigurationViewListener>();

		this.grid = new VerticalLayout();

		grid.setWidth("100%");
		grid.setHeight("100%");
		grid.setSpacing(true);

		grid.setStyleName("dashboard");

		this.setWidth(null);

		Button addBtn = new Button("+");
		Button saveBtn = new Button("Speichern");
		Button cancelBtn = new Button("Abbrechen");
		addBtn.setHeight("50px");
		addBtn.setWidth("50px");
		saveBtn.setHeight("50px");
		saveBtn.setWidth("200px");
		cancelBtn.setHeight("50px");
		cancelBtn.setWidth("200px");

		addBtn.addClickListener(e -> handleAddClick());
		saveBtn.addClickListener(e -> handleClickSave());
		cancelBtn.addClickListener(e -> handleClickCancel());

		VerticalLayout vertical = new VerticalLayout();
		vertical.setSpacing(true);
		HorizontalLayout h1 = new HorizontalLayout();
		HorizontalLayout h2 = new HorizontalLayout();
		h1.setWidth(100, Unit.PERCENTAGE);
		h2.setWidth(100, Unit.PERCENTAGE);
		h1.addComponent(addBtn);
		h2.addComponent(saveBtn);
		h2.addComponent(cancelBtn);
		vertical.addComponent(grid);
		vertical.addComponent(h1);
		vertical.addComponent(h2);

		this.setContent(vertical);
	}

	@Override
	public void addListener(ConfigurationViewListener listener) {
		logger.debug("->");

		listeners.add(listener);
		logger.debug("<-");
	}
	
	/**
	 * Notifier for click on the "+" Button to add a configset. 
	 */
	public void handleAddClick() {
		logger.debug("->");

		for (ConfigurationViewListener listener : listeners)
			listener.addClick();
		logger.debug("<-");
	}

	/**
	 * Notifier for click on the "-" Button to delete a configset.
	 */
	public void handleClickSave() {
		logger.debug("->");

		for (ConfigurationViewListener listener : listeners) {
			try {
				listener.saveClick();
			} catch (SneException e) {
				Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
			}
		}
		logger.debug("<-");
	}

	/**
	 * Notifier for click on the "Cancle" button to abort editing of configuration.
	 */
	public void handleClickCancel() {
		logger.debug("->");

		for (ConfigurationViewListener listener : listeners)
			listener.cancelClick();
		logger.debug("<-");
	}

	@Override
	public void addConfigSet(ConfigSetImpl configSet) {
		logger.debug("->");

		configSets.add(configSet);
		grid.addComponent(configSet);
		logger.debug("<-");
	}

	@Override
	public void deleteConfigSet(ConfigSetImpl configSet) {
		logger.debug("->");

		configSets.remove(configSet);
		grid.removeComponent(configSet);
		logger.debug("<-");
	}

	public List<ConfigSetImpl> getConfigSets() {
		logger.debug("->");
		logger.debug("<-");
		return this.configSets;
	}
}
