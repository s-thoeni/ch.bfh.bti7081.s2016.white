package ch.bfh.bti7081.s2016.white.sne.ui.view;

import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSetImpl;

public interface ConfigurationView {

	public interface ConfigurationViewListener {
		void saveClick();
		
		void addClick();
		
		void deleteClick(String id);
	}
	
	public void addListener(ConfigurationViewListener listener);
	
	void addConfigSet(ConfigSetImpl configSet);
	
	void deleteConfigSet(ConfigSetImpl configSet);
	
	void clickOk();
	
	void clickCancel();

}