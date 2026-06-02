package StepDefinition;

import io.cucumber.java.en.*;

public class LoginSteps {

    @Given("login page should be open in default browser")
    public void open() {
        System.out.println("Login page opened");
    }

    @When("click on username field and add valid user username")
    public void username() {
        System.out.println("Username entered");
    }

    @And("then click on password button and enter valid password")
    public void password() {
        System.out.println("Password entered");
    }

    @And("now click on submit button")
    public void submit() {
        System.out.println("Submit clicked");
    }

    @Then("login successfully and redirect to home page")
    public void success() {
        System.out.println("Login successful");
    }
}
