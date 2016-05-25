package ch.bfh.bti7081.s2016.white.sne;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.ui.model.DashboardProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ReportProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.presenter.DashboardPresenter;
import ch.bfh.bti7081.s2016.white.sne.ui.presenter.ReportPresenter;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportViewImpl;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("ch.bfh.bti7081.s2016.white.sne.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        Label lbl = new Label("Menu");
        lbl.setWidth("100%");
        
        // is 100% default
        lbl.setWidth(null);
        layout.addComponent(lbl);
        layout.setComponentAlignment(lbl,Alignment.TOP_CENTER);
        
        
        //FIXME: DUMMY CONFIGURATION
        /*
        Configuration config = new Configuration();
        config.setReportTypes(new ReportType[]{ReportType.AVAILABLE_EMPLOYEES, ReportType.CASHFLOW, ReportType.EFFORT, ReportType.ENTRIES_EXITS, ReportType.INCIDENTS, ReportType.PATIENS});
        
        DashboardPresenter db = new DashboardPresenter(new DashboardProvider(config), new DashboardViewImpl(), layout);
        
        layout.addComponent(db.getView());                
        layout.setComponentAlignment(db.getView(), Alignment.MIDDLE_CENTER);
        */

		ReportProvider reportModel = new ReportProvider();
		ReportView reportView = new ReportViewImpl(reportModel.getReportByTypeAndTimeFrame(ReportType.INCIDENTS, ReportTimeframe.LAST_WEEK));
		ReportPresenter reportPresenter = new ReportPresenter(reportModel, reportView);
		
		layout.addComponent(reportPresenter.getView());
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
