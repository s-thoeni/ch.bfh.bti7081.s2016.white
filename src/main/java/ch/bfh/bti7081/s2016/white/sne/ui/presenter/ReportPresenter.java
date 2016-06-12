package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.ui.model.ReportProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportViewImpl;

public class ReportPresenter implements ReportView.ReportViewListener {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ReportPresenter.class);

	private ReportProvider model;
	private ReportView view;

	public ReportPresenter(ReportProvider model, ReportView view) {
		this.model = model;
		this.view = view;
		
		this.view.addListener(this);
	}
	
	public Component getView() {
		logger.debug("->");
		logger.debug("<-");
		return (ReportViewImpl)this.view;
	}

}
