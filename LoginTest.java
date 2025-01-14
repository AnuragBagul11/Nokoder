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
    import java.util.ResourceBundle;
    import java.util.concurrent.TimeUnit;

    public class LoginTest {
        WebDriver driver;
        public ResourceBundle resourceBundle = ResourceBundle.getBundle("constants");
        WebDriverWait wait;


        @BeforeMethod
        public void setup() {
            driver = new ChromeDriver();
            driver.get("https://app-staging.nokodr.com/");
            wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        }

        @Test
        public void validateMandatoryFields() {
            WebElement usernameField = driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[1]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input"));
            WebElement passwordField = driver.findElement(By.xpath("//abx-field/div/div/div/div[1]/abx-password/div/div/input"));
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/div[2]/abx-button/button/abx-mergetext/div"));

            // Check that username and password fields are mandatory
            Assert.assertTrue(usernameField.isDisplayed(), "Username field is visible");
            Assert.assertTrue(passwordField.isDisplayed(), "Password field is visible");

            // Check if login button is enabled when fields are blank
            Assert.assertTrue(loginButton.isEnabled(), "Login button should be disabled when fields are empty");
        }

        @Test
        public void validatePasswordFormat() {
            WebElement usernameField = driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[1]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input"));
            WebElement passwordField = driver.findElement(By.xpath("//abx-field/div/div/div/div[1]/abx-password/div/div/input"));
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/div[2]/abx-button/button/abx-mergetext/div"));

            // Test password with less than required length
            usernameField.sendKeys(resourceBundle.getString("emailId"));
            passwordField.sendKeys("gdg8");
            loginButton.click();



            WebElement errorMessageElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.slds-notify__content h2"))
            );


            Assert.assertTrue(errorMessageElement.getText().contains("Invalid Email or Password"), "Error message did not contain the expected text.");


            // Test valid password format
            passwordField.clear();
            passwordField.sendKeys(resourceBundle.getString("password"));
            loginButton.click();

            WebElement sideBar = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-home/div/div[2]/setup/div/div[1]"))
            );
            Assert.assertTrue(sideBar.isDisplayed(), "User should be redirected to the user-profile page");
        }

        @Test
        public void testValidCredentials() {
            WebElement usernameField = driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[1]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input"));
            WebElement passwordField = driver.findElement(By.xpath("//abx-field/div/div/div/div[1]/abx-password/div/div/input"));
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/div[2]/abx-button/button/abx-mergetext/div"));

            usernameField.sendKeys(resourceBundle.getString("emailId"));
            passwordField.sendKeys(resourceBundle.getString("password"));
            loginButton.click();

            // Assuming successful login redirects to dashboard
            WebElement sideBar = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-home/div/div[2]/setup/div/div[1]"))
            );
            Assert.assertTrue(sideBar.isDisplayed(), "User should be redirected to the user-profile page");
        }

        @Test
        public void testInvalidCredentials() {
            WebElement usernameField = driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[1]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input"));
            WebElement passwordField = driver.findElement(By.xpath("//abx-field/div/div/div/div[1]/abx-password/div/div/input"));
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/div[2]/abx-button/button/abx-mergetext/div"));

            usernameField.sendKeys("xjggg@gmail.com");
            passwordField.sendKeys("kkkkg");
            loginButton.click();

            // Verify error message for invalid credentials

            WebElement errorMessageElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.slds-notify__content h2"))
            );


            Assert.assertTrue(errorMessageElement.getText().contains("Invalid Email or Password"), "Error message did not contain the expected text.");

        }

        @Test
        public void testBlankFields() {
            WebElement usernameField = driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[1]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input"));
            WebElement passwordField = driver.findElement(By.xpath("//abx-field/div/div/div/div[1]/abx-password/div/div/input"));
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/div[2]/abx-button/button/abx-mergetext/div"));

            // Test blank fields
            usernameField.clear();
            passwordField.clear();
            loginButton.click();

            // Verify error message for blank fields
            WebElement errorMessageElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.slds-notify__content h2"))
            );


            Assert.assertTrue(errorMessageElement.getText().contains("Please enter email"), "Error message did not contain the expected text.");

        }

        @Test
        public void testSpecialCharactersInCredentials() {
            WebElement usernameField = driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[1]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input"));
            WebElement passwordField = driver.findElement(By.xpath("//abx-field/div/div/div/div[1]/abx-password/div/div/input"));
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/div[2]/abx-button/button/abx-mergetext/div"));
            // Test special characters in username and password
            usernameField.sendKeys("jgjgd*7!@gmail.com");
            passwordField.sendKeys("gjgjkgkd@75753j");
            loginButton.click();


            WebElement errorMessageElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.slds-notify__content h2"))
            );


            Assert.assertTrue(errorMessageElement.getText().contains("Invalid username format"), "Error message did not contain the expected text.");



        }

        @Test
        public void testRememberMeFunctionality() {
            WebElement rememberMeCheckbox = driver.findElement(By.id("rememberMe"));
            WebElement usernameField = driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[1]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input"));
            WebElement passwordField = driver.findElement(By.xpath("//abx-field/div/div/div/div[1]/abx-password/div/div/input"));
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/div[2]/abx-button/button/abx-mergetext/div"));

            // Test Remember Me checkbox functionality
            usernameField.sendKeys(resourceBundle.getString("emailId"));
            passwordField.sendKeys(resourceBundle.getString("password"));
            rememberMeCheckbox.click();
            loginButton.click();

            // After successful login, check if the "Remember Me" setting was saved (this may depend on your implementation)
            // Here we assume it's stored in cookies/session storage
            // Verification step can vary depending on platform

            WebElement sideBar = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-home/div/div[2]/setup/div/div[1]"))
            );
            Assert.assertTrue(sideBar.isDisplayed(), "User should be redirected to the user-profile page");

        }

        @AfterMethod
        public void tearDown() {
            driver.quit();
        }
    }
