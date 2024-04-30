package util;



import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import qa.Base_test_1;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class Testutil extends Base_test_1 {
    public static final long wait = 30 ;

    public String datetime() {
        DateFormat dateformate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        System.out.println(dateformate.format(date));
        return  dateformate.format(date);
    }
    public static String getscreenshots(ITestResult result) throws IOException {

        File source=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        DateFormat date=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
//        String path=System.getProperty("user.dir")+File.separator+"src"+File.separator+"Screenshots"+File.separator+result.getTestClass().getName()+File.separator+date.format(new Date())+".png";
        Map<String, String> param = result.getTestContext().getCurrentXmlTest().getAllParameters();
        String dir = "Screenshots" + File.separator + param.get("platformName") + "_" + param.get("deviceName") + "_" + param.get("udid") + File.separator + File.separator+result.getTestClass().getName()+File.separator+date.format(new Date())+".png";

//        String path =System.getProperty("user.dir")+"\\screenshots\\"+File.separator+result.getTestClass().getName()+File.separator+date.format(new Date())+".png";;
        File finaldestination=new File(dir);
        FileUtils.copyFile(source,finaldestination);
        return dir;

    }
}


