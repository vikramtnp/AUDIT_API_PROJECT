package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
    
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    
    String repName;
    
    public void onStart(ITestContext context) 
    {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());
        repName = "Test-Report" + timestamp + ".html";
        
        sparkReporter = new ExtentSparkReporter(".//reports//" + repName);
        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject_Audi_Alpha");
        sparkReporter.config().setReportName("Audit Alpha API");
        sparkReporter.config().setTheme(Theme.DARK);
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Audit Alpha Project");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Vikram Kumar Mehta");
        
    }
    
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
    }
    
    public void onTestSuccess(ITestResult result) 
    {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS, "Test Passed");
    }
    
    public void onTestFailure(ITestResult result) 
    {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.FAIL, "Test failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }
    
    public void onTestSkipped(ITestResult result) 
    {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.SKIP, "Test skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }
    
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
    {
        // Implement if needed
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }
    
    public void onFinish(ITestContext context) 
    {
        extent.flush();
    }
}
