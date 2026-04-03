
package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ConfigReader;
import pages.LoginPage;
import pages.CartPage;

public class AddToCartTest  extends BaseTest {
    
    @Test(groups = "add_to_cart")
    public void addToCartTest(){
        LoginPage loginpage = new LoginPage(driver);

        String user_name = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        loginpage.login(user_name, password);

        // Add element to the cart page
        CartPage cart_page = new CartPage(driver);
        boolean result = cart_page.addToCart(driver);
        Assert.assertTrue(result, "Add to cart failed");

    }
}
