package tests;


import Pages.LoginPage;
import Pages.LoginPage_page;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.*;
import qa.Base_test_1;

import java.io.InputStream;
import java.lang.reflect.Method;

public class LoginTests extends Base_test_1 {

    LoginPage loginpage;
    LoginPage_page resetotp;
    JSONObject object;
    @BeforeClass
    public void Beforeclass()
    {
        InputStream stream=null;
    String path="Data/loginUsers.json";
    stream=getClass().getClassLoader().getResourceAsStream(path);
    JSONTokener tokener=new JSONTokener(stream);
    object=new JSONObject(tokener); // to call objects from jason file


    }
    @AfterClass
    public void Afterclass()
    {
        closeapp();
        launchapp();
    }
    @BeforeMethod
    public void BeforeMethod(Method m)
    {
        System.out.println("logintest before method");
System.out.println("\n"+"***Starting test:"+m.getName()+"****"+"\n");

    }
    @AfterMethod
    public void AfterMethod()
    {
        System.out.println("logintest after method");
    }

    @Test
public void loginOTP() throws InterruptedException {
loginpage=new LoginPage();
loginpage.enterusername(object.getJSONObject("invaliduser").getString("enterusername"));
loginpage.enterotp(object.getJSONObject("invaliduser").getString("enterpasswrod"));

    }
//
//    @Test
//    public void resendOtp() throws InterruptedException {
//
//        loginpage.enterusername(object.getJSONObject("invaliduser").getString("enterusername"));
//        loginpage.enterotp(object.getJSONObject("invaliduser").getString("enterpasswrod"));
//
//
//    }





}
