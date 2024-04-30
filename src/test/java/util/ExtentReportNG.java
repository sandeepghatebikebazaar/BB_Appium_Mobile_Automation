package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;

public class ExtentReportNG {
    static ExtentReports extent;

    public static ExtentReports getreportobject() throws IOException {
        String path =System.getProperty("user.dir")+"\\reports\\reports.html";

        ExtentSparkReporter reporter=new ExtentSparkReporter(path);

        reporter.config().setReportName("Bike Bazaar QA");
        reporter.config().setReportName("Bike Bazaar Automation");
        reporter.config().setDocumentTitle("Bike Bazaar");
//        reporter.config().enableOfflineMode(true);
//        reporter.config().setTimelineEnabled(true);
//        reporter.config().setCss("css-string");

        reporter.config().setTheme(Theme.STANDARD);
        extent=new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Automation Bike bazaar","Bike Bazaar QA");
        return extent;

    }

}
