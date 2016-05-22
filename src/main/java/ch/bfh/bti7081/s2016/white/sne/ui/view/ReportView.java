package ch.bfh.bti7081.s2016.white.sne.ui.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2016.white.sne.data.PatientRecord;
import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;

import java.util.ArrayList;
import java.util.List;

public class ReportView extends CustomComponent implements ClickListener {

	// TODO(jan): Verify that this serialVersionUID makes sense
	private static final long serialVersionUID = 1437015328529272368L;

	private List<ReportViewListener> listeners = new ArrayList<ReportViewListener>();
	private Label titleLabel = new Label("Report View");
	
	public ReportView() {
		GridLayout layout = new GridLayout(4, 5);
		layout.addComponent(titleLabel, 0, 0, 3, 0);
		layout.addComponent(new Button("Apply filters", this));
		
		this.setCompositionRoot(layout);
	}

	public void setReport(Report report) {
		// TODO(jan): implement this method
		String title = report.getReportName();
		for (Record record : report.getRecords()) {
			title += ", " + record.toString();
		}
		titleLabel.setValue(title);
	}

	public void addListener(ReportViewListener listener) {
		listeners.add(listener);
	}
	
	public void buttonClick(ClickEvent event) {
		for (ReportViewListener listener: this.listeners) {
			listener.filterClicked();
		}
	}

}
