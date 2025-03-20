package e2e.driver;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/*
    https://github.com/SeleniumHQ/selenium/wiki/PageFactory
    https://github.com/eliasnogueira/selenium-java-lean-test-architecture/blob/main/src/main/java/com/eliasnogueira/driver/DriverManager.java
 */
public class DriverManager
{
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public DriverManager()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    }
    public static WebDriver getDriver()
    {
        if (driver.get() != null)
            return driver.get();

        var options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver _driver = new ChromeDriver(options);

        _driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        _driver.manage().window().maximize();

        System.out.println("Driver::Created");
        driver.set(_driver);

        return driver.get();
    }

    public static void quitDriver(Scenario scenario)
    {
        if (driver.get() == null)
            return;

        driver.get().quit();
        System.out.println("Driver::Exit "+scenario.getName());
        driver.remove();
    }
}
