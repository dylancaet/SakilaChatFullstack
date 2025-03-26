package e2e.admin.common;

import e2e.pom.pages.AdminPage;
import io.cucumber.java.en.Given;

public class CommonAdminStepDefinitions
{
    private final AdminPage page = new AdminPage();

    @Given("the user can see the latest film id")
    public void the_user_can_see_the_latest_film_id() throws InterruptedException
    {
        page.gotoLastTablePage();
    }
}
