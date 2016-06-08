package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public class TileComponentImpl extends CustomComponent implements TileComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");
	
	private Label reportName;
	
	private Label reportSummary;
	
	private String id;
	
	// Listeners:
	private List<TileClickListener> listeners = new ArrayList<TileClickListener>();

	private Label timeframe;
	
	/**
	 * Displayes a rectangle with a title and a value. The event when clicked on a tile consist of only the id of the clicked tile.
	 * 
	 * 
	 * @param title - the title to be displayed
	 * @param value - the value to be displayed
	 * @param id - the id returned when the tile is clicked
	 */
	public TileComponentImpl(String title, String value, Date from, Date to, String id, ReportType type){
		this.listeners = new ArrayList<TileClickListener>();
		this.id = id;
		
		// Need to have concrete width/height:
		int width = UI.getCurrent().getPage().getBrowserWindowWidth();
		int height = UI.getCurrent().getPage().getBrowserWindowHeight();
		
		//init all labels:
		reportName = new Label("<b>" + title + "</b>", ContentMode.HTML);	
		
		if(type == ReportType.CASHFLOW || type == ReportType.EFFORT){
			value = value.concat(" .-");
		}
		reportSummary = new Label(value);
		
		timeframe = new Label(sf.format(from) + " - " + sf.format(to));
		
		reportName.setWidth(null);
		reportSummary.setWidth(null);
		timeframe.setWidth(null);
		

		// position and add relevant components:		
		AbsoluteLayout container = new AbsoluteLayout();
		
		//this id will be returned when event is triggered
		container.setId(id);
		container.addComponent(reportName, "left: 5px; top: 7px");
		container.addComponent(reportSummary, "right: 10px; bottom: 20px");
		container.addComponent(timeframe, "right: 10px; top: 7px");
		
		// used later on
		container.addLayoutClickListener(e -> tileClicked(e));
		
		// Set size of the tile:
		container.setWidth(width - 20 + "px");
		container.setHeight(height / 6 - 15 + "px");		
		
		setCompositionRoot(container);		
	}
	
	private void tileClicked(LayoutClickEvent e){
		for(TileClickListener listener: listeners){		
			listener.tileClick(id);
		}
	}

	@Override
	public void setTitle(String title) {
		reportName.setValue("<b>" + title + "</b>");
	}

	@Override
	public void setValue(String value) {
		reportSummary.setValue(value);
	}

	@Override
	public void setTimeframe(Date from, Date to) {
		timeframe.setValue(sf.format(from) + "-" + sf.format(from));
	}
	
	@Override
	public void addListener(TileClickListener listener) {
		listeners.add(listener);		
	}
}
