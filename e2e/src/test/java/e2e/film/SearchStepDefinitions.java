package e2e.film;

import e2e.pom.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class SearchStepDefinitions
{
    private HomePage page;

    public SearchStepDefinitions()
    {
        page = new HomePage();
    }


    @Given("the user types {string} in the filter")
    public void the_user_types_string_in_the_filter(String filter)
    {
        page.enterFilter(filter);
    }

    @When("the user submits the filter")
    public void the_user_submits_the_filter()
    {
        page.submitFilter();
    }

    @Then("{int} films should display")
    public void int_films_should_display(int count)
    {
        page.waitForPagination();
        final var actualCount = page.getFilmCards().size();

        assertThat(count, equalTo(actualCount));
    }
}
