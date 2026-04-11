package base;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import io.qameta.allure.testng.AllureTestNg;

import utils.ConfigReader;

@Listeners({ AllureTestNg.class })
public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        String browser = ConfigReader.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            // ✅ Your existing options
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            // 🔥 CI / GitHub Actions FIX
            String headless = System.getProperty("headless");

            if ("true".equalsIgnoreCase(headless)) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }
            // chrome option 
            driver = new ChromeDriver(options);
        }

        // 🔥 Avoid maximize issue in headless
        if ("true".equalsIgnoreCase(System.getProperty("headless"))) {
            driver.manage().window().setSize(new Dimension(1920, 1080));
        } else {
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void endUp() {
        if (driver != null) {
            driver.quit();
        }
    }
}
