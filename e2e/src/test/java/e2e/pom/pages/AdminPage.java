package e2e.pom.pages;

import e2e.driver.DriverManager;
import e2e.pom.models.FilmCard;
import e2e.pom.models.FilmTableEntry;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class AdminPage
{
    private final WebDriver driver;

    private final By createPanelBy  = By.xpath("//h3[text()='Create']/parent::div");
    private final By tablePanelBy   = By.xpath("//div[contains(@class, 'admin-panel-table')]");

    private final By createFormBy   = By.xpath("//h3[text()='Create']/parent::div/form");
    private final By updateFormBy   = By.xpath("//h3[text()='Update']/parent::div/form");
    private final By deleteFormBy   = By.xpath("//h3[text()='Delete']/parent::div/form");

    private final By titleInputBy       = By.cssSelector("input[name='title']");
    private final By descriptionInputBy = By.cssSelector("input[name='description']");
    private final By priceInputBy       = By.cssSelector("input[name='price']");
    private final By idInputBy          = By.cssSelector("input[name='id']");

    private final By createdFilmCardBy  = By.xpath("//h3[text()='Create']/parent::div/div[@class='film-card']");
    private final By createResponseBy   = By.xpath("//h3[text()='Create']/parent::div/i");
    private final By updatedFilmCardBy  = By.xpath("//h3[text()='Update']/parent::div/div[@class='film-card']");
    private final By updatedResponseBy  = By.xpath("//h3[text()='Update']/parent::div/i");

    private final By tableEntriesBy     = By.xpath("//div[contains(@class, 'admin-panel-table')]//tbody/tr");
    private final By tableNextButtonBy  = By.xpath("//div[contains(@class, 'admin-panel-table')]/button[text()='Next']");

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

    public void enterUpdateForm(Integer id, String title, String description, Float price)
    {
        final var updateForm = driver.findElement(updateFormBy);
        final var idInput = updateForm.findElement(idInputBy);
        final var titleInput = updateForm.findElement(titleInputBy);
        final var descriptionInput = updateForm.findElement(descriptionInputBy);
        final var priceInput = updateForm.findElement(priceInputBy);

        idInput.sendKeys(id.toString());
        Optional.of(title).ifPresent(titleInput::sendKeys);
        Optional.of(description).ifPresent(descriptionInput::sendKeys);
        Optional.of(price).ifPresent(v -> priceInput.sendKeys(v.toString()));
    }

    public void submitCreateForm()
    {
        final var createForm = driver.findElement(createFormBy);
        createForm.submit();
    }

    public void submitUpdateForm()
    {
        final var updateForm = driver.findElement(updateFormBy);
        updateForm.submit();
    }

    public String getCreatedResponse()
    {
        final var response = driver.findElement(createResponseBy);

        return response.getText();
    }

    public String getUpdatedResponse()
    {
        final var response = driver.findElement(updatedResponseBy);

        return response.getText();
    }

    public FilmCard getCreatedFilmCard()
    {
        FilmCard filmCard = new FilmCard(driver.findElement(createdFilmCardBy));

        return filmCard;
    }

    public FilmCard getUpdatedFilmCard()
    {
        FilmCard filmCard = new FilmCard(driver.findElement(updatedFilmCardBy));

        return filmCard;
    }

    public void waitForCreateResponse()
    {
        new WebDriverWait(driver, Duration.ofMillis(1000)).until(ExpectedConditions.presenceOfElementLocated(createResponseBy));
    }

    public void waitForUpdateResponse()
    {
        new WebDriverWait(driver, Duration.ofMillis(1000)).until(ExpectedConditions.presenceOfElementLocated(updatedResponseBy));
    }

    public void gotoLastTablePage()
    {
        final var wait = new WebDriverWait(driver, Duration.ofMillis(1000));

        while(true)
        {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(tableNextButtonBy)).click();
            } catch (Exception e) {
                break;
            }
        }
    }

    public List<FilmTableEntry> getCurrentFilmEntries()
    {
        final var trElements = driver.findElements(tableEntriesBy);
        final List<FilmTableEntry> filmEntries = trElements.stream().map(FilmTableEntry::new).toList();

        return filmEntries;
    }

}
