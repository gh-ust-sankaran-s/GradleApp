package com.test.selenium.bbd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private final World world;

    public LoginSteps(World world) {
        this.world = world;
    }

    @Given("the login page is open")
    public void theLoginPageIsOpen() {
        world.login = new com.test.selenium.pages.LoginPage(world.driver).open();
    }

    @When("I login with {string} and {string}")
    public void iLoginWithAnd(String email, String password) {
        world.catalog = world.login.login(email, password);
    }

    @Then("I should be redirected to the home page")
    public void iShouldBeRedirectedToTheHomePage() {
        assertTrue(world.driver.getCurrentUrl().contains("/home"));
    }

}
