package e2e.pom.pages;

import e2e.driver.DriverManager;
import e2e.pom.models.FilmCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage
{
    private final WebDriver driver;

    private final By filmCardBy     = By.className("film-card");
    private final By loadButtonBy   = By.className("load-button");
    private final By filterFormBy   = By.xpath("//form[@class='film-search-container']");
    private final By filterInputBy  = By.xpath("//form[@class='film-search-container']//input");

//    private ChatBox chatBox;
//    private SearchFilter searchFilter;
//    private String heading1;
//    private String heading2;

    private List<FilmCard> filmCards;

    public HomePage()
    {
        this.driver = DriverManager.getDriver();
        this.filmCards = new ArrayList<FilmCard>();
    }

    public List<FilmCard> getFilmCards()
    {
        return driver.findElements(By.className("film-card"))
                    .stream()
                    .map(FilmCard::new)
                    .toList();
    }

    public void clickLoadMore()
    {
        filmCards = getFilmCards();

        final var loadButton = driver.findElement(loadButtonBy);

        new WebDriverWait(driver, Duration.ofMillis(1000)).until(ExpectedConditions.elementToBeClickable(loadButton));

        loadButton.click();
    }

    public void waitForPagination()
    {
        new WebDriverWait(driver, Duration.ofMillis(1000)).until(ExpectedConditions.numberOfElementsToBeMoreThan(filmCardBy, filmCards.size()));
    }

    public void enterFilter(String text)
    {
        final var filterInput = driver.findElement(filterInputBy);
        filterInput.sendKeys(text);
    }

    public void submitFilter()
    {
        final var filterForm = driver.findElement(filterFormBy);
        filterForm.submit();
    }

}
