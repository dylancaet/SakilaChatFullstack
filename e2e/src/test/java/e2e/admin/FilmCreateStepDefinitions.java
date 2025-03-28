package e2e.admin;

import e2e.pom.pages.AdminPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class FilmCreateStepDefinitions
{
    private final AdminPage page = new AdminPage();

    @When("the user enters {string} {string} {double} in the create film form")
    public void the_user_fills_the_create_film_form(String title, String description, Double price)
    {
        page.enterCreateForm(title, description, price.floatValue());
    }

}
