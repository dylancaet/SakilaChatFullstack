package e2e.pages;

import e2e.models.FilmCard;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
public class HomePage
{
    private final WebDriver driver;

    private final By filmCardBy     = By.className("film-card");
    private final By loadButtonBy   = By.className("load-button");

//    private ChatBox chatBox;
//    private SearchFilter searchFilter;
//    private String heading1;
//    private String heading2;

    private List<FilmCard> filmCards;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
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

    public void scrollDown(int y)
    {
        WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromViewport(10, 10);
        new Actions(driver)
                .scrollFromOrigin(scrollOrigin, 0, y)
                .perform();
    }
}
