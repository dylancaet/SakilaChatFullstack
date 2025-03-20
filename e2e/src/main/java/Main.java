import e2e.pages.HomePage;
import org.openqa.selenium.chrome.ChromeDriver;

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

