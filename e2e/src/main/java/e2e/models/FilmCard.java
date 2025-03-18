package e2e.models;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class FilmCard extends WebComponent
{
    private String title;
    private String priceText;
    private String imageUrl;

    public FilmCard(WebElement element) {
        super(element);
    }

    @Override
    void initialise()
    {
        final var filmCardInfo = element.findElement(By.className("film-card-info"));

        title = filmCardInfo.findElement(By.tagName("h5")).getText();
        priceText = filmCardInfo.findElement(By.tagName("p")).getText();
        imageUrl = element.findElement(By.tagName("img")).getAttribute("src");
    }
}
