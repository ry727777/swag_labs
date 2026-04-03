package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {
    
    WebDriver driver;

    By right_button = By.id("react-burger-menu-btn");
    By logoutbtn = By.id("logout_sidebar_link");

    public LogoutPage(WebDriver driver){
        this.driver = driver;
    }

    public void logout(){
        driver.findElement(right_button).click();
        driver.findElement(logoutbtn).click();
    }
   
}
