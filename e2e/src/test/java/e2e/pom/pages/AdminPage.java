package e2e.pom.pages;

import e2e.driver.DriverManager;
import e2e.pom.models.FilmCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

public class AdminPage
{
    private final WebDriver driver;

    private final By createFormBy   = By.xpath("//h3[text()='Create']/following-sibling::form");
    private final By updateFormBy   = By.xpath("//h3[text()='Update']/following-sibling::form");
    private final By deleteFormBy   = By.xpath("//h3[text()='Delete']/following-sibling::form");

    private final By titleInputBy       = By.cssSelector("input[name='title']");
    private final By descriptionInputBy = By.cssSelector("input[name='description']");
    private final By priceInputBy       = By.cssSelector("input[name='price']");
    private final By idInputBy          = By.cssSelector("input[name='id']");

    private final By filmCardBy = By.cssSelector("film-card");
    private final By responseBy = By.cssSelector("i");

    public AdminPage()
    {
        this.driver = DriverManager.getDriver();
    }

    public void enterCreateForm(Optional<String> title, Optional<String> description,Optional<Float> price)
    {
        final var createForm = driver.findElement(createFormBy);
        final var titleInput = createForm.findElement(titleInputBy);
        final var descriptionInput = createForm.findElement(descriptionInputBy);
        final var priceInput = createForm.findElement(priceInputBy);

        title.ifPresent(titleInput::sendKeys);
        description.ifPresent(descriptionInput::sendKeys);
        price.ifPresent(p -> priceInput.sendKeys(p.toString()));
    }

    public void submitCreateForm()
    {
        final var createForm = driver.findElement(createFormBy);
        createForm.submit();
    }

    public String getCreatedResponse()
    {
        final var createForm = driver.findElement(createFormBy);
        final var response = createForm.findElement(responseBy);

        return response.getText();
    }

    public FilmCard getCreatedFilmCard()
    {
        final var createForm = driver.findElement(createFormBy);
        FilmCard filmCard = new FilmCard(createForm.findElement(filmCardBy));

        return filmCard;
    }
}
