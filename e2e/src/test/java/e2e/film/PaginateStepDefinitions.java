package e2e.film;

import e2e.base.TestBase;
import e2e.models.FilmCard;
import e2e.pages.HomePage;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class PaginateStepDefinitions
{
    private TestBase base;
    private HomePage page;

    private List<FilmCard> preResults;

    @Before /* =BeforeScenario */
    public void beforeScenario(Scenario scenario)
    {
        this.base = new TestBase(scenario.getName());
        this.page = new HomePage(base.getDriver());

        base.startSession();
    }

    @After /* =AfterScenario */
    public void afterScenario(Scenario scenario)
    {
        base.endSession(scenario);
    }

    @Given("the user is on {string}")
    public void the_user_is_on_string(String url)
    {
        base.gotoURL(url);
    }

    @When("the user clicks the load more button")
    public void the_user_clicks_the_load_more_button()
    {
        page.waitForPagination();
        preResults = page.getFilmCards();

        page.clickLoadMore();
    }

    @Then("{int} film cards are displayed")
    public void int_string_are_displayed(int count)
    {
        page.waitForPagination();
        final var initialResults = page.getFilmCards();

        assertThat(initialResults.size(), equalTo(count));
    }

    @Then("more or the same film cards are displayed")
    public void more_or_the_same_string_are_displayed()
    {
        page.waitForPagination();

        final var postResults = page.getFilmCards();

        assertThat(postResults.size(), greaterThanOrEqualTo(preResults.size()));
    }

}

/* Bugs found
    1. After filtering, the Load more button will display when there are no more results
    2. Chat box will hide the load more button on vertical screens, not rendering the load more button
    3. After filtering, load more will increment paginated page index, UNINTENTIONAL
        3.1. Searching empty string possible, and sends a request for ?title=
    4. At the final pagination the load more button is present, pressing it makes a request, 0 posters are added, and then disappears
*/