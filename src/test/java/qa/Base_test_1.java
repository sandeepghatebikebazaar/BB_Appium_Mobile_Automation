package qa;

import com.google.common.collect.Table;
import com.sun.jdi.connect.Transport;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import util.Testutil;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;

public class Base_test_1 {
    protected static ThreadLocal <AppiumDriver> driver=new ThreadLocal<AppiumDriver>();
    protected static ThreadLocal<String> datetime=new ThreadLocal<>();
    protected static ThreadLocal<Properties> prop=new ThreadLocal<>();

    private static AppiumDriverLocalService server;
    private AppiumServiceBuilder builder;

    public static AppiumDriver getDriver() {
        return driver.get();
    }
    public void setDriver(AppiumDriver driver2) {
        driver.set(driver2);
    }

    public String datetime() {
        return datetime.get();
    }
    public void setdatetime(String datetime2) {
        datetime.set(datetime2);
    }
    public Properties prop() {
        return prop.get();
    }
    public void setprop(Properties prop2) {
        prop.set(prop2);
    }


    protected AppiumFieldDecorator appium = new AppiumFieldDecorator(getDriver());
    Testutil util;
    // static Logger log=Logger.getLogger(Base_test_1.class);

    public Base_test_1() {
        PageFactory.initElements(appium, this);
    }

    /*To start recording we need add below code to base test
    start recording before method and stop after method execution
    * */
   @BeforeMethod
   public synchronized void beforemethod()
   {
       System.out.println("Started recording");
       ((CanRecordScreen)getDriver()).startRecordingScreen();
   }

   @AfterMethod
   public synchronized void aftertest(ITestResult result) throws IOException {
       System.out.println("Stop recording");
       String media = ((CanRecordScreen) getDriver()).stopRecordingScreen();
        /*getStatus() there given in build status:

        Created=1;
        Success=1;
        Failure=2;
        skip=3;
        success_percentage_failure=4;
        started=16;
result.getStatus()==2: will process screen record only for Failure tests
        * */


       if (result.getStatus() == 1 || result.getStatus()==2) {
           Map<String, String> param = result.getTestContext().getCurrentXmlTest().getAllParameters();
           String dir = "Videos" + File.separator + param.get("platformName") + "_" + param.get("deviceName") + "_" + param.get("udid") + File.separator + datetime()+ File.separator + result.getTestClass().getRealClass().getSimpleName();
           File videodir = new File(dir);
           if (!videodir.exists()) {
               videodir.mkdirs(); // to create multipal directories
           }
           FileOutputStream output = new FileOutputStream(videodir + File.separator + result.getName() + ".mp4");
           output.write(Base64.decodeBase64(media));
       }
          }


    /*using default service to start the appium server programmatically we use AppiumDriverLocalService*/
//    @BeforeSuite
//    public void beforesuite_startappiumserver()
//    {
//        getappiumservice();
//        server.start();
//        server.clearOutPutStreams();    //to hide the server  logs
//        System.out.println("server started");
//    }
//    @AfterSuite
//    public void aftersuite_stopappiumserver()
//    {
//        server.stop();
//        System.out.println("server stop");
//    }
//    /*buildDefaultService is not working to start the server so we use buildService method to add custom paths*/
////    public AppiumDriverLocalService appiumserverdefault()
////    {
////        return AppiumDriverLocalService.buildDefaultService();
////    }
//    public void getappiumservice()
//    {
//        builder=new AppiumServiceBuilder();
////        String node_js_path="C:\\Program Files\\nodejs\\node.exe";
////        String appium_js_path="C:\\Users\\User\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
//
//        String node_js_path="C:\\Program Files\\nodejs\\node.exe";
//        String appium_js_path="C:\\Users\\sandeep.ghate\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\main.js";
//
//        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
//                .usingDriverExecutable(new File(node_js_path))
//                .withAppiumJS(new File(appium_js_path))
//                .withIPAddress("127.0.0.1")
//                        .withArgument(BASEPATH, "/wd/hub")
//                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//                .withArgument (GeneralServerFlag.LOG_LEVEL, "debug")
//                .usingPort(4723));
//
//
//
////        builder.usingDriverExecutable(new File(node_js_path));
////        builder.withAppiumJS(new File(appium_js_path));
////        builder.usingPort(4723);
////        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
//        server=AppiumDriverLocalService.buildService(builder);
//
//
//    }
    @Parameters({"platformName", "deviceName", "udid", "systemPort","chromeDriverPort"})
    @BeforeTest
    public void BeforeTest(String platformName, String deviceName, String udid, String chromeDriverPort, String systemPort) throws IOException {
//log.info("this is info message");
//log.debug("this is debug");
//log.error("this is error");
//log.warn("this is warning");
        util = new Testutil();
        FileInputStream inputStream;
        setdatetime(util.datetime());
AppiumDriver driver;
        String location = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Data/config.properties";
        inputStream = new FileInputStream(location);
        Properties prop=new Properties();
        prop = new Properties();
        prop.load(inputStream);
        setprop(prop);
        DesiredCapabilities des = new DesiredCapabilities();
        des.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        des.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        des.setCapability(MobileCapabilityType.UDID, udid);
          des.setCapability(MobileCapabilityType.AUTOMATION_NAME,prop.getProperty("androidAutomatioName"));
          des.setCapability("appPackage",prop.getProperty("androidAppPackage"));
          des.setCapability("appActivity",prop.getProperty("androidAppActivity"));
des.setCapability("systemPort",systemPort);
        des.setCapability("chromeDriverPort",chromeDriverPort);

       //   des.setCapability("adbExecTimeout",20000);
         // des.setCapability("uiautomator2ServerLaunchTimeout",20000);
      //    URL appurl=getClass().getClassLoader().getResource(prop.getProperty("androidAppLocation"));
        String appurl=System.getProperty("user.dir")+ File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"App"+File.separator+"FinalDMAAppiumTest.apk";
          des.setCapability(MobileCapabilityType.APP,appurl);
        URL url=new URL("http://0.0.0.0:4723/wd/hub");
        /*
        If we run the appium through command prompt use below URL
         */
//        URL url=new URL("http://0.0.0.0:4723/");
     //   URL url=new URL(server);

        driver=new AndroidDriver(url,des);

setDriver(driver);
    }

    public AppiumDriver driverrepo() {
        return getDriver();
    }

//    public static ThreadLocal<String> gettime() {
//        return gettime();
//    }
public ThreadLocal<String> gettime() {
    return datetime;
}
    public void waitforvisibility(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Testutil.wait);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void clear(MobileElement e) {
        waitforvisibility(e);
        e.clear();      // to clear the text box for user and password
    }

    public void sendkey(MobileElement e, String txt) {
        waitforvisibility(e);
        e.sendKeys(txt);
    }

    public void click(MobileElement e) {
        waitforvisibility(e);
        e.click();
    }

    public String getattribute(MobileElement e, String attribute) {
        waitforvisibility(e);
        String title = e.getAttribute(attribute);
        return title;
    }

    public void closeapp() {
        getDriver().closeApp();
    }

    public void launchapp() {
        getDriver().launchApp();
    }

    /*
     * Importent Note: we can use xpath for below scrollintoview. we can use classname,id,text, contain-desc attribute
     *
     *   return (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("+"new UiSelector().description(\"test-Price\"));"));
     * */
    public MobileElement srolltoelement() {
        return (MobileElement) getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(" + "new UiSelector().description(\"test-Price\"));"));

//          ((FindsByAndroidUIAutomator)driver).findElementByAndroidUIAutomator("new Uiscrollable(new UiSelector()"+".description(\"test-Inventory item page\")).scrollIntoView("+"new UiSelector().description(\"test-Price\"));");
    }




    @AfterTest
    public void AfterTest() {
        getDriver().quit();
    }
}

