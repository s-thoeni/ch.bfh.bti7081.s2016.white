package ch.bfh.bti7081.s2016.white.sne.ui.view;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSetImpl;

/**
 * View interface for configuration view.
 * 
 * @author shepd1
 *
 */
public interface ConfigurationView {

	/**
	 * Inner interface for Listeners of the configuration view.
	 * 
	 * @author shepd1
	 *
	 */
	public interface ConfigurationViewListener {
		
		/**
		 * Adds a configset component to the configuration.
		 * 
		 */
		void addClick();
		
		/**
		 * Saves the configuration to the DB.
		 * 
		 * @throws SneException
		 */
		void saveClick() throws SneException;
		
		/**
		 * Aborts the editing of the configuration and navigates back.
		 */
		void cancelClick();
		
		/**
		 * Deletes a configset from the configuration.
		 * @param id The ID of the component which should be deleted.
		 */
		void deleteClick(String id);

	}
	
	/**
	 * Add a new listener to be used to evaluate Click events.
	 * 
	 * @param listener The listener to add.
	 */
	public void addListener(ConfigurationViewListener listener);
	
	/**
	 * Add the a ConfigSetImpl object to the configuration view.
	 * 
	 * @param configSet The configset to add.
	 */
	void addConfigSet(ConfigSetImpl configSet);
	
	/**
	 * Delete a given ConfigSetImpl object from the configuration view.
	 * 
	 * @param configSet the configset to delete.
	 */
	void deleteConfigSet(ConfigSetImpl configSet);
	
}