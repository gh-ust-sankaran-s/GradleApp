package com.test.selenium.refactoring;

import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderSteps
{
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    @Then("the order total is correct")
    public void verifyOrderTotal() {
        driver.findElement(By.id("checkout")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("total")));
        int priceInPaise = 1299;
        int quantity = 3;
        int expectedTotalInPaise = priceInPaise * quantity;
        String shown = driver.findElement(By.id("total")).getText();
        assertEquals("38.97", shown);
    }
}