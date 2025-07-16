package ExtentReporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentReport {
    static ExtentReports extentReports = new ExtentReports();
    static File extentReportFile = new File("ExtentReports/APITesting.html");

    public static ExtentReports generateExtentReport() {
        try {
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(extentReportFile);
            extentSparkReporter.config().setReportName("SwaggerAPITestingReport");
            extentSparkReporter.config().setDocumentTitle("TestingReport");
            extentSparkReporter.config().setTheme(Theme.DARK);
            extentSparkReporter.config().setTimeStampFormat("YYMM");

            extentReports.attachReporter(extentSparkReporter);
        }
        catch(Throwable e){
            throw e;
        }

        return extentReports;
    }
}
