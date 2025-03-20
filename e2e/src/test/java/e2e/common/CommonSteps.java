package e2e.common;

import e2e.driver.DriverManager;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

public class CommonSteps
{
    @Given("the user is on {string}")
    public void the_user_is_on_string(String url)
    {
        WebDriver driver = DriverManager.getDriver();
        driver.get(url);
    }
}
