package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public boolean checkItemsPresent(WebDriver driver){
        itemList = driver.findElements(invetoryList);
        System.out.println("Number of items: "+ itemList.size());
        return itemList.size() != 0;
    }

    public boolean addToCart(WebDriver driver){
        boolean items = checkItemsPresent(driver);
        // add one item to the cart

        for(int i=0; i<3; i++){
            WebElement addToCartBtn = itemList.get(i).findElement(By.tagName("button"));
             addToCartBtn.click();
        }

        int total_items = Integer.parseInt(driver.findElement(cartPageItems).getText());

        return total_items == 3;
        
    }
}
