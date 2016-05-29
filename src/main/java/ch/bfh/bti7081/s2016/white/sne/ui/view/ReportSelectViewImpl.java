package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.touchkit.ui.DatePicker;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

/**
 * 
 * @author mcdizzu
 *
 */
public class ReportSelectViewImpl extends NavigationView implements ReportSelectView {

	private FormLayout root;

	private List<ReportSelectViewListener> listeners = new ArrayList<ReportSelectViewListener>();

	public ReportSelectViewImpl() {
		this.root = new FormLayout();
		this.root.setSpacing(true);
		this.root.setMargin(true);

		
		// TODO: make a components out of the report + from&to selection
		// report selection
		NativeSelect reportSelect = new NativeSelect("Report: ");
		ReportType[] types = ReportType.values();
		for (ReportType type : types) {
			reportSelect.addItem(type);
		}
		reportSelect.setNullSelectionAllowed(true);
		reportSelect.setStyleName("configselect");

		this.root.addComponent(reportSelect);

		// from
		DatePicker from = new DatePicker("From: ");
		this.root.addComponent(from);
		
		// to
		DatePicker to = new DatePicker("To: ");
		this.root.addComponent(to);
		
		//go button
		
		// TODO : Errorhandling...
		Button go = new Button("Go");
		go.addClickListener((ClickEvent event) -> {
			for (ReportSelectViewListener listener : listeners)
				listener.go((ReportType) reportSelect.getValue(), from.getValue(), to.getValue());
		});
		
		this.root.addComponent(go);

		this.setContent(root);
	}

	@Override
	public void addListener(ReportSelectViewListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void navigateTo(Component component) {
		getNavigationManager().navigateTo(component);
		
	}

}
