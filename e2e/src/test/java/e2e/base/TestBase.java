package e2e.base;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;

import java.time.Duration;

public class TestBase
{
    private WebDriver driver;

    public TestBase(String called)
    {
        System.out.println("Driver::Create "+called);

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        var options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver();
    }

    public void startSession()
    {
        launchBrowser();
    }

    public void endSession(Scenario scenario)
    {
        closeBrowser();
        System.out.println("Driver::Exit " + scenario.getName());
    }

    public void gotoURL(String url)
    {
        driver.get(url);
    }

    public WebDriver getDriver() {
        return driver;
    }


    private void launchBrowser()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.manage().window().maximize();
    }

    private void closeBrowser()
    {
        driver.quit();
    }
}
