package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dev.failsafe.internal.util.Assert;

import java.util.List;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    private List<WebElement> itemList;

    // Locators
    private By cartLink = By.className("shopping_cart_link");
    private By invetoryList = By.className("inventory_item");
    private By cartPageItems = By.xpath("//span[@class=\"shopping_cart_badge\"]");
    private By addBtn = By.xpath(".//button[text()=\"Add to cart\"]");
    private By removebtn = By.xpath(".//button[text()=\"Remove\"]");

    public boolean checkItemsPresent(WebDriver driver){
        itemList = driver.findElements(invetoryList);
        System.out.println("Number of items: "+ itemList.size());
        return itemList.size() != 0;
    }

    public int  numberOfElementsCart(WebDriver driver){
        return Integer.parseInt(driver.findElement(cartPageItems).getText());
    }

    public boolean addToCart(WebDriver driver){
        boolean items = checkItemsPresent(driver);
        if(!items) return false;
        // add one item to the cart

        for(int i=0; i<3; i++){
            WebElement addToCartBtn = itemList.get(i).findElement(addBtn);
             addToCartBtn.click();
        }

        int total_items = numberOfElementsCart(driver);

        return total_items == 3;
        
    }

    public boolean removeItems(WebDriver driver){

        int beforeRemoved = numberOfElementsCart(driver);

        // remove one
        itemList.get(0).findElement(removebtn).click();

        int afterRemovedOne = numberOfElementsCart(driver);

        itemList.get(1).findElement(removebtn).click();

        int afterRemovedSecond = numberOfElementsCart(driver);
        
        return beforeRemoved == 3 && afterRemovedOne == 2 && afterRemovedSecond == 1;
    }
}
