package ch.bfh.bti7081.s2016.white.sne.ui.view;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSetImpl;

public interface ConfigurationView {

	public interface ConfigurationViewListener {
		void addClick();
		
		void saveClick() throws SneException;
		
		void cancelClick();
		
		void deleteClick(String id);

	}
	
	public void addListener(ConfigurationViewListener listener);
	
	void addConfigSet(ConfigSetImpl configSet);
	
	void deleteConfigSet(ConfigSetImpl configSet);
	
}