package ch.bfh.bti7081.s2016.white.sne.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class MenuView extends Panel implements View{
	public static final String NAME = "";

    private static int count = 1;

    public MenuView() {
    	final VerticalLayout layout = new VerticalLayout();
        
        layout.addComponent(new Link("COUNT", new ExternalResource("#!"
                + MenuView.NAME)));
        
        layout.addComponent(new Link("Config", new ExternalResource("#!"
                + "ConfigurationView")));
        
        //layout.addComponent(new Link("Dashboard", new ExternalResource("#!"
        //        + "DashboardViewImpl"))); 
        
        layout.addComponent(new Label("Created: " + count++));
        
        setContent(layout);
    }

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		System.out.println("menu view");
	}
}
