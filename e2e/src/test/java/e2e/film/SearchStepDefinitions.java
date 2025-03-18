package e2e.film;

import e2e.base.TestBase;
import e2e.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class SearchStepDefinitions
{
    private TestBase base;
    private HomePage page;
//
//    @Before /* =BeforeScenario */
//    public void beforeScenario(Scenario scenario)
//    {
//        this.base = new TestBase(scenario.getName());
//        this.page = new HomePage(base.getDriver());
//
//        base.startSession();
//    }
//
//    @After /* =AfterScenario */
//    public void afterScenario(Scenario scenario)
//    {
//        base.endSession(scenario);
//    }

    @Given("the user is on {string} b")
    public void the_user_is_on_string_b(String url)
    {
        base.gotoURL(url);
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
        final var actualCount = page.getFilmCards();

        assertThat(count, equalTo(actualCount));
    }
}
