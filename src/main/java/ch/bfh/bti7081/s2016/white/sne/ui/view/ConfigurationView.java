package ch.bfh.bti7081.s2016.white.sne.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TwinColSelect;


@SuppressWarnings("serial")
public class ConfigurationView extends CustomComponent implements View {
	
	public ConfigurationView() {
		TwinColSelect tileSelect = new TwinColSelect();
		
		tileSelect.addItem(1);
		tileSelect.setItemCaption(1, "Personalreport");
		
		
        tileSelect.setRows(6);
        tileSelect.setNullSelectionAllowed(true);
        tileSelect.setMultiSelect(true);
        tileSelect.setImmediate(true);
        tileSelect.setLeftColumnCaption("Verfügbare Reports");
        tileSelect.setRightColumnCaption("Angezeigte Reports");
        
        setCompositionRoot(tileSelect);

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	
    
	
	
	
	
	
	
	
    

	
	

}