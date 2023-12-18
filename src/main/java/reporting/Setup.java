package reporting;

import com.aventstack.extentreports.ExtentReports;
import org.testng.ITestContext;
import org.testng.ITestListener;

public class Setup implements ITestListener {

    private static ExtentReports extentReports;

//  Initializing the Report
    @Override
    public void onStart(ITestContext context) {
        String fileName = ExtentReportManager.getReportNameWithTimeStamp();
        String fullReportPath = System.getProperty("user.dir")+"\\reports\\"+fileName;
        extentReports = ExtentReportManager.createInstance(fileName,"Test API Automation Report","Test Execution Report");
    }

//  Generating the report
    @Override
    public void onFinish(ITestContext context) {
        if (extentReports != null)
                extentReports.flush();
    }
}
