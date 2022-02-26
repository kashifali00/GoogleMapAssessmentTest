package stepDefinations;

import com.qa.BaseTest;
import com.qa.Pages.GoogleMapPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class GoogleMapStepDefinition {
    BaseTest baseTest;
    GoogleMapPage googleMapPage;


    public GoogleMapStepDefinition() {
        System.out.println("I'm in constructor of DarazloginpageTest");
        baseTest = new BaseTest();
        googleMapPage = new GoogleMapPage();

    }

    @Given("User is on google map")
    public void user_is_on_google_map() {
        baseTest.softAssert.assertEquals(googleMapPage.getPageTitle(),"Google Maps");
        baseTest.softAssert.assertAll();
    }
    @When("User search city {string}")
    public void user_search_city(String string) {
       googleMapPage.searchCity(string);
    }
    @Then("User should see the results")
    public void user_should_see_the_results() {

    }


} // end of class


