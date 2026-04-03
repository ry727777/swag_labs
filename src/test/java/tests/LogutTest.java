package tests;
import org.testng.Assert;

import base.BaseTest;

import pages.LogoutPage;
import pages.LoginPage;
import utils.ConfigReader;
import org.testng.annotations.*;

public class LogutTest extends BaseTest {
    
    @Test
    public void logouttest(){
        // First Login
        LoginPage loginpage = new LoginPage(driver);
        LogoutPage logoutpage = new LogoutPage(driver);

        String user_name = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        loginpage.login(user_name, password);

        // logut
        logoutpage.logout();

        // verify if logout and comback to login page ?
        boolean login_page_validation = loginpage.loginPageValidation();
        Assert.assertTrue(login_page_validation, "Logout Failed");
    }
}
