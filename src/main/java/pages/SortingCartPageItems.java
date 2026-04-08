package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class SortingCartPageItems {

    WebDriver driver;

    By sortingEle = By.className("product_sort_container");
    By itemsPrice = By.className("inventory_item_price");

    public SortingCartPageItems(WebDriver driver) {
        this.driver = driver;
    }

    boolean isSorted(List<Double> items, boolean asc) {
        for (int i = 0; i < items.size() - 1; i++) {
            if (asc) {
                // Ascending order
                if (items.get(i) > items.get(i + 1))
                    return false;
            } else {
                // Descending order
                if (items.get(i) < items.get(i + 1))
                    return false;
            }
        }
        return true;
    }

    List<Double> fetchPriceValue(List<WebElement> itemsList) {
        List<Double> ele = new ArrayList<>();
        for (int i = 0; i < itemsList.size(); i++) {
            String s = itemsList.get(i).getText();
            Double val = Double.parseDouble(s.replace("$", "").trim());
            ele.add(val);
        }
        return ele;
    }

    public boolean lowToHigh() {
        WebElement dropdown = driver.findElement(sortingEle);
        Select select = new Select(dropdown);
        select.selectByVisibleText("Price (low to high)");

        // verify items are in sorted order low to high
        List<WebElement> itemsList = driver.findElements(itemsPrice);
        // System.out.println("Items value " + itemsList.get(0).getText());
        List<Double> ele = fetchPriceValue(itemsList);
        boolean ans = isSorted(ele, true);
        return ans;

    }

    public boolean highTolow() {
        WebElement dropdown = driver.findElement(sortingEle);
        Select select = new Select(dropdown);
        select.selectByVisibleText("Price (high to low)");

        // verify items are in sorted order low to high
        List<WebElement> itemsList = driver.findElements(itemsPrice);

        List<Double> ele = fetchPriceValue(itemsList);
        boolean ans = isSorted(ele, false);
        return ans;
    }
}
