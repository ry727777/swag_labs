package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ConfigReader;
import pages.LoginPage;
import pages.CartPage;

public class CartItemPresentTest extends BaseTest {
    
    @Test(groups = "cart_page")
    void checkItemsPresentTest(){
        LoginPage loginpage = new LoginPage(driver);

        String user_name = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        loginpage.login(user_name, password);

        // check the element present or not
        CartPage cart = new CartPage(driver);
        boolean isPresent = cart.checkItemsPresent(driver);
        Assert.assertTrue(isPresent, "No Element present on the cart page");
    }
}
