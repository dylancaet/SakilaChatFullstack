package e2e.admin;

import e2e.pom.models.FilmTableEntry;
import e2e.pom.pages.AdminPage;
import io.cucumber.java.en.When;

public class FilmUpdateStepDefinitions
{
    private final AdminPage page = new AdminPage();

    @When("the user enters the latest id, {string} {string} {double} in the update film form")
    public void the_user_fills_the_update_film_form(String title, String description, Double price)
    {
        FilmTableEntry lastEntry = page.getCurrentFilmEntries().getLast();

        page.enterUpdateForm(lastEntry.getId(), title, description, price.floatValue());
    }

}
