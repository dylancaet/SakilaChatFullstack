package e2e.admin.common;

import e2e.pom.models.FilmCard;
import e2e.pom.pages.AdminPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CommonAdminStepDefinitions
{
    private final AdminPage page = new AdminPage();

    @Given("the user can see the latest film id")
    public void the_user_can_see_the_latest_film_id() throws InterruptedException
    {
        page.gotoLastTablePage();
    }

    @When("the user submits the {string} film form")
    public void the_user_submits_the_create_film_form(String opType)
    {

        switch(opType)
        {
            case "UPDATE":
                page.submitUpdateForm();
                break;
            case "CREATE":
                page.submitCreateForm();
                break;
            case "DELETE":
                break;
            default:
                throw new IllegalArgumentException();
        }

    }

    @Then("the {string} film operation response displays {string}")
    public void the_film_operation_response_displays(String opType, String response)
    {
        String actualResponse = "";

        switch(opType)
        {
            case "UPDATE":
                page.waitForUpdateResponse();
                actualResponse = page.getUpdatedResponse();
                break;
            case "CREATE":
                page.waitForCreateResponse();
                actualResponse = page.getCreatedResponse();
                break;
            case "DELETE":
                break;
            default:
                throw new IllegalArgumentException();
        }

        assertThat(actualResponse, equalTo(response));
    }

    @Then("the {string} film card appears")
    public void the_film_card_appears(String opType)
    {
        FilmCard filmCard = null;

        switch(opType){
            case "UPDATE":
                filmCard = page.getUpdatedFilmCard();
                break;
            case "CREATE":
                filmCard = page.getCreatedFilmCard();
                break;
            default:
                throw new IllegalArgumentException();
        }

        assertThat(filmCard, notNullValue());
    }

}
