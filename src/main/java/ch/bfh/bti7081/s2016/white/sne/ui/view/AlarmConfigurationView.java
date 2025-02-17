package ch.bfh.bti7081.s2016.white.sne.ui.view;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.AlarmSetImpl;

public interface AlarmConfigurationView {

	public interface AlarmConfigurationViewListener {
		void addClick();
		
		void saveClick() throws SneException;
		
		void cancelClick();
		
		void deleteClick(String id);

	}
	
	public void addListener(AlarmConfigurationViewListener listener);
	
	void addAlarmSet(AlarmSetImpl configSet);
	
	void deleteAlarmSet(AlarmSetImpl configSet);

}