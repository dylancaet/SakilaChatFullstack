import e2e.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main
{
    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        final var driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://ec2-3-84-235-208.compute-1.amazonaws.com/");

        HomePage page = new HomePage(driver);

        page.waitForPagination();
        final var filmcard = page.getFilmCards();

        System.out.println(filmcard.size());

        driver.quit();
    }
}

