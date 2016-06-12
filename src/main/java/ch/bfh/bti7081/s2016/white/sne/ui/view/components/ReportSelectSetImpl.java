package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.addon.touchkit.ui.DatePicker;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;


/**
 * 
 * @author mcdizzu
 *
 */
public class ReportSelectSetImpl extends CustomComponent implements ReportSelectSet {

	private List<ReportSelectSetListener> listeners = new ArrayList<ReportSelectSetListener>();
	
	private FormLayout root;
	private NativeSelect reportSelect;
	private DatePicker fromDate;
	private DatePicker toDate;
	
	public ReportSelectSetImpl(boolean first) {
		
		this.root = new FormLayout();
		this.root.setSpacing(true);
		this.root.setMargin(true);

		// report selection
		this.reportSelect = new NativeSelect("Report: ");
		ReportType[] types = ReportType.values();
		for (ReportType type : types) {
			this.reportSelect.addItem(type);
		}
		this.reportSelect.setNullSelectionAllowed(true);
		this.reportSelect.setStyleName("configselect");

		this.root.addComponent(this.reportSelect);

		// from
		//DateField from = new DateField("From: ");
		this.fromDate = new DatePicker("From: ");
		this.root.addComponent(this.fromDate);
		
		// to
		this.toDate = new DatePicker("To: ");
		this.root.addComponent(this.toDate);
		
		// remove button
		if(!first) {
			Button removeBtn = new Button("-");
			removeBtn.setHeight("50px");
			removeBtn.setWidth("50px");

			removeBtn.addClickListener(e -> handleDeleteClick());
			this.root.addComponent(removeBtn);
		}		
		
		setCompositionRoot(this.root);
	}
	
	@Override
	public void addListener(ReportSelectSetListener listener) {
		this.listeners.add(listener);
	}

	public void handleDeleteClick() {
		for(ReportSelectSetListener listener : listeners)
			listener.handleDeleteClick(this);
	}
	
	public ReportType getReportType(){
		if(this.reportSelect.getValue() instanceof ReportType){
			return (ReportType) this.reportSelect.getValue();
		} else{
			return null;
		}
	}
	
	public Date getFromDate(){
		if(this.fromDate.getValue() instanceof Date){
			return (Date) this.fromDate.getValue();
		} else{
			return null;
		}
	}
	
	public Date getToDate(){
		if(this.toDate.getValue() instanceof Date){
			return (Date) this.toDate.getValue();
		} else{
			return null;
		}
	}

}
