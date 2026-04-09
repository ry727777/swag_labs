package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ConfigReader;
import pages.*;

public class CartPageSortingTest extends BaseTest {

    @Test(groups = "low_to_high")
    public void increasingOrderTest() {
        LoginPage loginpage = new LoginPage(driver);

        String user_name = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");
        loginpage.login(user_name, password);

        CartPage cart_page = new CartPage(driver);
        boolean items_pre = cart_page.checkItemsPresent(driver);
        Assert.assertTrue(items_pre, "Items not present on cart page");

        // check items should come in increasing order
        SortingCartPageItems sortCart = new SortingCartPageItems(driver);
        boolean isSorted = sortCart.lowToHigh();
        Assert.assertTrue(isSorted, "Items are not sorted based on price low to high");
      
    }

    @Test(groups = "high_to_low")
    public void decreasingOrderTest() {
        LoginPage loginpage = new LoginPage(driver);

        String user_name = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        loginpage.login(user_name, password);
        CartPage cart_page = new CartPage(driver);
        boolean items_pre = cart_page.checkItemsPresent(driver);
        Assert.assertTrue(items_pre, "Items not present on cart page");

        // check items should come in decreasing order
        SortingCartPageItems sortCart = new SortingCartPageItems(driver);
        boolean isSorted = sortCart.highTolow();
        Assert.assertTrue(isSorted, "Items are not sorted based on price high to low");
    }

}