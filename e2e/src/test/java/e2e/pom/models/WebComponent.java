package e2e.pom.models;

import org.openqa.selenium.WebElement;

public abstract class WebComponent
{
    protected final WebElement element;

    public WebComponent(WebElement element)
    {
        this.element = element;

        initialise();
    }

    abstract void initialise();
}
