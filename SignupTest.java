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

public class SignupTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get("https://app-staging.nokodr.com/");
        driver.findElement(By.xpath("//*[contains(text(),'Sign up')]")).click();
    }

    //    @Test(priority = 5)
    public void validateMandatoryFields() throws InterruptedException {

        driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-sign-up/div/div/div[2]/span/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[1]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input")).sendKeys("anuragbagul11@gmail.com");

        driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-sign-up/div/div/div[2]/span/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[2]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-checkbox/div/label/span")).click();
        driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-sign-up/div/div/div[2]/span/div[2]/abx-button/button/abx-mergetext/div")).click();

        Thread.sleep(10000);

        WebElement verifyCodeElement = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("/html/body/abx-modal/section/div/div/verification-code/div/div[2]/div/abx-button/button/abx-mergetext/div"))));
        Thread.sleep(15000);
        Assert.assertTrue((verifyCodeElement.isEnabled()));
        verifyCodeElement.click();
        Thread.sleep(200);
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/abx-modal/section/div/div/user-details/abx-form/abx-field[1]/div/div/div[1]/div[1]/abx-text/input")));
        firstName.sendKeys("Anurag");
        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/abx-modal/section/div/div/user-details/abx-form/abx-field[2]/div/div/div/div[1]/abx-text/input")));
        lastName.sendKeys("Bagul");

        Thread.sleep(200);
        WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/abx-modal[1]/section[1]/div[1]/div[1]/user-details[1]/abx-form[1]/abx-field[3]/div[1]/div[1]/div[1]/div[1]/abx-password[1]/div[1]/div[2]/input[1]")));
        confirmPasswordField.sendKeys("Pass@1234");
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/abx-modal/section/div/div/user-details/abx-form/abx-field[3]/div/div/div/div[1]/abx-password/div/div[1]/input")));

        Thread.sleep(200);
        passwordField.sendKeys("Pass@1234");



        WebElement resisterButton = driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/user-details/div/div[2]/div/abx-button[2]/button/abx-mergetext/div"));
        resisterButton.click();



    }

    @Test(priority = 0)
    public void validateEmailFormat() {



        WebElement emailField =         driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-sign-up/div/div/div[2]/span/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[1]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input"));
        WebElement checkBoxAgreeTerm = driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-sign-up/div/div/div[2]/span/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[2]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-checkbox/div/label/span"));

        WebElement submitButton = driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-sign-up/div/div/div[2]/span/div[2]/abx-button/button/abx-mergetext/div"));

        // Test invalid email format
        emailField.sendKeys("invalid$#@gmail.com");
        checkBoxAgreeTerm.click();
        submitButton.click();

        WebElement errorMessageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.slds-notify__content h2"))
        );
        Assert.assertTrue(errorMessageElement.getText().contains("Username seems to be incorrect based on format"), "Error message should appear for non-registered email");


        // Clear the email field
        emailField.clear();
    }

    @Test(priority = 1)
    public void validatePasswordAndConfirmPassword() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-sign-up/div/div/div[2]/span/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[1]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input")).sendKeys("anuragbagul11@gmail.com");

        driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-sign-up/div/div/div[2]/span/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[2]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-checkbox/div/label/span")).click();
        driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-sign-up/div/div/div[2]/span/div[2]/abx-button/button/abx-mergetext/div")).click();

        Thread.sleep(10000);

        WebElement verifyCodeElement = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("/html/body/abx-modal/section/div/div/verification-code/div/div[2]/div/abx-button/button/abx-mergetext/div"))));
        Thread.sleep(15000);
        Assert.assertTrue((verifyCodeElement.isEnabled()));
        verifyCodeElement.click();
        Thread.sleep(200);
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/abx-modal/section/div/div/user-details/abx-form/abx-field[1]/div/div/div[1]/div[1]/abx-text/input")));
        firstName.sendKeys("Anurag");
        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/abx-modal/section/div/div/user-details/abx-form/abx-field[2]/div/div/div/div[1]/abx-text/input")));
        lastName.sendKeys("Bagul");

        Thread.sleep(200);
        WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/abx-modal[1]/section[1]/div[1]/div[1]/user-details[1]/abx-form[1]/abx-field[3]/div[1]/div[1]/div[1]/div[1]/abx-password[1]/div[1]/div[2]/input[1]")));
        confirmPasswordField.sendKeys("Invalde@Pass23");
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/abx-modal/section/div/div/user-details/abx-form/abx-field[3]/div/div/div/div[1]/abx-password/div/div[1]/input")));

        Thread.sleep(200);
        passwordField.sendKeys("Valde@Pass23");

        WebElement errorMsg = driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/user-details/abx-form/abx-field[3]/div/div/div/div[1]/abx-password/div/div[3]/div"));
        Assert.assertTrue(errorMsg.getText().contains("The password and confirmation password do not match"));

        WebElement resisterButton = driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/user-details/div/div[2]/div/abx-button[2]/button/abx-mergetext/div"));
        Assert.assertTrue(resisterButton.isEnabled());


        passwordField.clear();
        confirmPasswordField.clear();
    }

    @Test(priority = 3)
    public void testValidSignup() throws InterruptedException {

        driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-sign-up/div/div/div[2]/span/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[1]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input")).sendKeys("anuragbagul11@gmail.com");

        driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-sign-up/div/div/div[2]/span/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[2]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-checkbox/div/label/span")).click();
        driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-sign-up/div/div/div[2]/span/div[2]/abx-button/button/abx-mergetext/div")).click();

        Thread.sleep(10000);

        WebElement verifyCodeElement = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("/html/body/abx-modal/section/div/div/verification-code/div/div[2]/div/abx-button/button/abx-mergetext/div"))));
        Thread.sleep(15000);
        Assert.assertTrue((verifyCodeElement.isEnabled()));
        verifyCodeElement.click();
        Thread.sleep(200);
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/abx-modal/section/div/div/user-details/abx-form/abx-field[1]/div/div/div[1]/div[1]/abx-text/input")));
        firstName.sendKeys("Anurag");
        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/abx-modal/section/div/div/user-details/abx-form/abx-field[2]/div/div/div/div[1]/abx-text/input")));
        lastName.sendKeys("Bagul");

        Thread.sleep(200);
        WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/abx-modal[1]/section[1]/div[1]/div[1]/user-details[1]/abx-form[1]/abx-field[3]/div[1]/div[1]/div[1]/div[1]/abx-password[1]/div[1]/div[2]/input[1]")));
        confirmPasswordField.sendKeys("Pass@1234");
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/abx-modal/section/div/div/user-details/abx-form/abx-field[3]/div/div/div/div[1]/abx-password/div/div[1]/input")));

        Thread.sleep(200);
        passwordField.sendKeys("Pass@1234");



        WebElement resisterButton = driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/user-details/div/div[2]/div/abx-button[2]/button/abx-mergetext/div"));
        resisterButton.click();
        // Assert that the success message is displayed
        WebElement sideBar = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-home/div/div[2]/setup/div/div[1]"))
        );
        Assert.assertTrue(sideBar.isDisplayed(), "User should be redirected to the user-profile page");
    }

    //    @Test
    public void testBlankFields() {
        WebElement submitButton = driver.findElement(By.id("submitButton"));

        // Test blank fields
        submitButton.click();

        // Wait for the error message to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("generic_error_message")));

        // Assert that the error message for blank fields is shown
        Assert.assertTrue(errorMessage.getText().contains("All fields are required"), "Error message should appear for blank fields");
    }

        @AfterMethod
    public void tearDown() {
        // Close the browser after each test
        driver.quit();
    }
}