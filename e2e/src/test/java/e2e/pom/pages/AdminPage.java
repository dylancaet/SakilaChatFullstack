package e2e.pom.pages;

import e2e.driver.DriverManager;
import e2e.pom.models.FilmCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPage
{
    private final WebDriver driver;

    private final By createPanelBy  = By.xpath("//h3[text()='Create']/parent::div");

    private final By createFormBy   = By.xpath("//h3[text()='Create']/parent::div/form");
    private final By updateFormBy   = By.xpath("//h3[text()='Update']/parent::div/form");
    private final By deleteFormBy   = By.xpath("//h3[text()='Delete']/parent::div/form");

    private final By titleInputBy       = By.cssSelector("input[name='title']");
    private final By descriptionInputBy = By.cssSelector("input[name='description']");
    private final By priceInputBy       = By.cssSelector("input[name='price']");
    private final By idInputBy          = By.cssSelector("input[name='id']");

    private final By createdFilmCardBy = By.xpath("//h3[text()='Create']/parent::div//div[@class='film-card']");
    private final By createResponseBy = By.xpath("//h3[text()='Create']/parent::div/i");

    public AdminPage()
    {
        this.driver = DriverManager.getDriver();
    }

    public void enterCreateForm(String title, String description, Float price)
    {
        final var createForm = driver.findElement(createFormBy);
        final var titleInput = createForm.findElement(titleInputBy);
        final var descriptionInput = createForm.findElement(descriptionInputBy);
        final var priceInput = createForm.findElement(priceInputBy);

        titleInput.sendKeys(title);
        descriptionInput.sendKeys(description);
        priceInput.sendKeys(price.toString());
    }

    public void submitCreateForm()
    {
        final var createForm = driver.findElement(createFormBy);
        createForm.submit();
    }

    public String getCreatedResponse()
    {
        final var response = driver.findElement(createResponseBy);

        return response.getText();
    }

    public FilmCard getCreatedFilmCard()
    {
        FilmCard filmCard = new FilmCard(driver.findElement(createdFilmCardBy));

        return filmCard;
    }

    public void waitForCreateResponse()
    {
        new WebDriverWait(driver, Duration.ofMillis(1000)).until(ExpectedConditions.presenceOfElementLocated(createResponseBy));
    }

}
