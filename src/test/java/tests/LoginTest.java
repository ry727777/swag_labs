package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ConfigReader;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    
    @Test(groups = "login")
    public void validLoginTest(){
        LoginPage loginpage = new LoginPage(driver);

        String user_name = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        loginpage.login(user_name, password);

        String curr_url = driver.getCurrentUrl();

        Assert.assertTrue(curr_url.contains("inventory"), "Login failed");

        Assert.assertTrue(loginpage.dashboardPage(), "Elements are present");
    }

    @Test
    public void invalidLoginTest(){
        LoginPage loginpage = new LoginPage(driver);

        String user_name = ConfigReader.getProperty("username");
        String password = "wrong_password";

        loginpage.login(user_name, password);

        String curr_url = driver.getCurrentUrl();

        Assert.assertFalse(curr_url.contains("inventory"), "Should not Login");

        String error_msg = loginpage.errorMessage();
        Assert.assertTrue(error_msg.contains("Username and password do not match"));

    }
}
