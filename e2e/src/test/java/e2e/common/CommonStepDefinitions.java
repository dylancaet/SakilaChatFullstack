package e2e.common;

import e2e.driver.DriverManager;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class CommonStepDefinitions
{
    @Given("the user is on {string}")
    public void the_user_is_on_string(String url)
    {
        WebDriver driver = DriverManager.getDriver();
        driver.get(url);
    }
}
