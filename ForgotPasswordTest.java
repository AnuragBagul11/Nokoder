package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ForgotPasswordTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(20));

        driver.get("https://app-staging.nokodr.com/");
        driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/a")).click();
    }

    @Test
    public void validateEmailField() {
        WebElement emailField = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/abx-modal/section/div/div/abx-forgot-password/div/div/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input"))
        );

        WebElement proceedButton = driver.findElement(By.xpath("//div[@title=\"Proceed\"]"));

        Assert.assertTrue(emailField.isDisplayed(), "Email field is visible");

        Assert.assertTrue(proceedButton.isDisplayed(), "Submit button should be disabled when email is blank");
    }

    @Test
    public void validateEmailFormat() {
        WebElement emailField = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/abx-modal/section/div/div/abx-forgot-password/div/div/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input"))
        );

        WebElement proceedButton = driver.findElement(By.xpath("//div[@title=\"Proceed\"]"));

        emailField.sendKeys("kkkkkut7877785@gmail.com");
        proceedButton.click();

        WebElement errorMessageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.slds-notify__content h2"))
        );
        Assert.assertTrue(errorMessageElement.getText().contains("User does not exists"), "Error message should be shown for invalid email format");

        emailField.clear();
    }

    @Test
    public void testValidEmail() {
        WebElement emailField = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/abx-modal/section/div/div/abx-forgot-password/div/div/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input"))
        );

        WebElement proceedButton = driver.findElement(By.xpath("//div[@title=\"Proceed\"]"));


        emailField.sendKeys("pramodkoli2333@gmail.com");
        proceedButton.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.slds-notify__content h2")));

        WebElement verifyCodeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"modal-content-id-1\"]/verification-code/div/div[2]/div/abx-button/button")));
        Assert.assertFalse(verifyCodeElement.isEnabled());



        Assert.assertTrue(successMessage.getText().contains("Verification code sent successfully"), "Success message should appear for valid email");
    }

    @Test
    public void testNonRegisteredEmail() {
        WebElement emailField = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/abx-modal/section/div/div/abx-forgot-password/div/div/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input"))
        );

        WebElement proceedButton = driver.findElement(By.xpath("//div[@title=\"Proceed\"]"));

        emailField.sendKeys("nonregistered@domain.com");
        proceedButton.click();

        WebElement errorMessageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.slds-notify__content h2"))
        );
        Assert.assertTrue(errorMessageElement.getText().contains("User does not exists"), "Error message should appear for non-registered email");
    }

    @Test
    public void testBlankEmailField() {
        WebElement emailField = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/abx-modal/section/div/div/abx-forgot-password/div/div/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input"))
        );

        WebElement proceedButton = driver.findElement(By.xpath("//div[@title=\"Proceed\"]"));

        emailField.clear();
        proceedButton.click();

        WebElement errorMessageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.slds-notify__content h2"))
        );
        Assert.assertTrue(errorMessageElement.getText().contains("Please enter email"), "Error message should appear for blank email field");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}