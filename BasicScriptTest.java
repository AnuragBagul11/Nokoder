package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
public class BasicScriptTest {

    @Test
    public void openLoinPage(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://app-staging.nokodr.com/");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        driver.close();
    }
}
