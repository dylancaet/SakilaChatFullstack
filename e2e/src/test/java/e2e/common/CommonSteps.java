package e2e.common;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

/*
    https://github.com/SeleniumHQ/selenium/wiki/PageFactory
    https://github.com/eliasnogueira/selenium-java-lean-test-architecture/blob/main/src/main/java/com/eliasnogueira/driver/DriverManager.java
 */
public class CommonSteps
{
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public CommonSteps()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    }

    @Given("the user is on {string}")
    public void the_user_is_on_string(String url)
    {
        WebDriver driver = getDriver();
        driver.get(url);
    }

    public static WebDriver getDriver()
    {
        if (driver.get() == null) {
            var options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            WebDriver _driver = new ChromeDriver(options);

            _driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
            _driver.manage().window().maximize();

            System.out.println("Driver::Created");
            driver.set(_driver);
        }
        return driver.get();
    }

    public static void quitDriver(Scenario scenario)
    {
        if (driver.get() != null) {
            driver.get().quit();
            System.out.println("Driver::Exit "+scenario.getName());
            driver.remove();
        }
    }
}
