package e2e.pom.models;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import java.util.List;

public class FilmTableEntry extends WebComponent
{
    private Integer id;
    private String title;
    private String description;
    private String price;

    public FilmTableEntry(WebElement element)
    {
        super(element);
    }

    @Override
    void initialise()
    {
        final List<WebElement> rowEntries = element.findElements(By.tagName("td"));

        /* id | title | description | price */
        id = Integer.parseInt(rowEntries.get(0).getText());
        title = rowEntries.get(1).getText();
        description = rowEntries.get(2).getText();
        price = rowEntries.get(3).getText();
    }

    public Integer getId()
    {
        return id;
    }
}
