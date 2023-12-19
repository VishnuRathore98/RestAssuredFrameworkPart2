package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.http.Header;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExtentReportManager {

    public static ExtentReports extentReports;

    public static ExtentReports createInstance(String fileName, String reportName, String documentTitle){

//      Creating instance of ExtentSparkReporter to set the basic properties related to report.
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(fileName);
        extentSparkReporter.config().setReportName(reportName);
        extentSparkReporter.config().setDocumentTitle(documentTitle);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setEncoding("utf-8");

//      Attaching extentSparkReporter instance to the extentReporter instance and returning it.
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        return extentReports;
    }

//  Creating file name with current timestamp.
    public static String getReportNameWithTimeStamp(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String formattedTime = dateTimeFormatter.format(localDateTime);
        String reportName = "TestReport"+formattedTime+".html";
        return reportName;
    }

//  Implementing test execution result details.
    public static void logPassDetails(String log){
        Setup.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
    }
    public static void logFailDetails(String log){
        Setup.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }
    public static void logInfoDetails(String log){
        Setup.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
    }
    public static void logWarnDetails(String log){
        Setup.extentTest.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
    }

//  To print request and response in json format.
    public static void logJson(String json){
        Setup.extentTest.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
    }

//  To print headers in tabular form
    public static void printHeaders(List<Header> headerList){
        String[][] arrayHeaders = headerList.stream().map(header -> new String[] {header.getName(), header.getValue()})
                                .toArray(String[][] :: new);
        Setup.extentTest.get().info(MarkupHelper.createTable(arrayHeaders));
    }
}










