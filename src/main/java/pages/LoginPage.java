package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    LoginPage(){}

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By  loggingBtn = By.id("login-button");

    private By app_logo = By.className("app_logo");
    private By product_text = By.xpath("//span[@class=\"title\"]");

    private By error_mess = By.xpath("//h3[@data-test=\"error\"]");

    public void enterUserName(String user_name){
        driver.findElement(username).sendKeys(user_name);
    }

    public void enterPassword(String pass){
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLogin(){
        driver.findElement(loggingBtn).click();
    }

    public void login(String user_name, String password){
        enterUserName(user_name);
        enterPassword(password);
        clickLogin();
    }

    public boolean dashboardPage(){
        String logo = driver.findElement(app_logo).getText();
        String product = driver.findElement(product_text).getText();
        return logo.equals("Swag Labs") && product.equals("Products");
    }

    public boolean loginPageValidation(){
        boolean t = driver.findElement(username).isDisplayed();
        boolean m = driver.findElement(password).isDisplayed();
        boolean n = driver.findElement(loggingBtn).isDisplayed();

        return t && m && n;
    }

    public String errorMessage(){
        return driver.findElement(error_mess).getText();
    }
}
