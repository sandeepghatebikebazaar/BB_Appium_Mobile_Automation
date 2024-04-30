package Pages;



import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import qa.Base_test_1;

public class LoginPage extends Base_test_1 {

@AndroidFindBy (accessibility="user ID")
    MobileElement username;

 @AndroidFindBy(accessibility ="Send OTp")
    MobileElement Button;


 @AndroidFindBy (accessibility = "Enter OTP")
 MobileElement otp;

    @AndroidFindBy(accessibility = "verify otp")
    MobileElement verifyOtp;
    @AndroidFindBy(accessibility = "select")
    MobileElement select;
    @AndroidFindBy(accessibility = "Post Disbursement")
    MobileElement Disbursement;
    @AndroidFindBy(xpath = "//*[@text='Regular']")
    MobileElement drop1;

    @AndroidFindBy(accessibility = "Status & Report")
    MobileElement Status;

    @AndroidFindBy(xpath = "//*[@text='2W']")
    MobileElement drop2;
    @AndroidFindBy(accessibility = "CC Leads")
    MobileElement Leads;

    @AndroidFindBy(xpath = "//*[@text='U2W']")
    MobileElement drop3;

//    @AndroidFindBy(id="android:id/button1")
//    MobileElement alert;



  public void alert()
  {
      click(Button);
  }
    public void enterusername(String text)
{
    sendkey(username,text);
    click(Button);
    //return this;
}

    public void enterotp(String text)
    {

        sendkey(otp,text);
        click(verifyOtp);

        // click(alert);
      //  return this;
    }

}
