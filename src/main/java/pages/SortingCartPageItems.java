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

    boolean isSorted(List<Double> priceValues, boolean asc){
        if(asc){
            for(int i=0; i<priceValues.size()-1; i++){
                if(priceValues.get(i) > priceValues.get(i+1)) return false;
            }
        }else{
             for(int i=0; i<priceValues.size()-1; i++){
                if(priceValues.get(i) < priceValues.get(i+1)) return false;
            }
        }
        return true;
    }

    public List<Double> fethPrice(List<WebElement> itemsList){
        List<Double> ele = new ArrayList<>();
        for(int i=0; i<itemsList.size(); i++){
            WebElement t = itemsList.get(i);
            String s = t.getText();
            Double num = Double.parseDouble(s.replace("$", "").trim());
            ele.add(num);
        }
        return ele;
    }


    public boolean lowToHigh() {
        WebElement dropdown = driver.findElement(sortingEle);
        Select select = new Select(dropdown);
        select.selectByVisibleText("Price (low to high)");

        // verify items are in sorted order low to high
        List<WebElement> itemsList = driver.findElements(itemsPrice);

        List<Double> ele = fethPrice(itemsList);
        boolean result = isSorted(ele, true);
        return result;

    }

    public boolean highTolow() {
        WebElement dropdown = driver.findElement(sortingEle);
        Select select = new Select(dropdown);
        select.selectByVisibleText("Price (high to low)");

        // verify items are in sorted order low to high
        List<WebElement> itemsList = driver.findElements(itemsPrice);

        List<Double> ele = fethPrice(itemsList);
        boolean result = isSorted(ele, false);
        return result;
    }
}