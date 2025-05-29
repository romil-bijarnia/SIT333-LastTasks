package web.service;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginServiceTest {

    // Utility method to pause for a few seconds (to allow page load in UI tests)
    private void sleep(long seconds) {
        try { Thread.sleep(seconds * 1000); } 
        catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Test
    public void testLoginSuccess() {
        // **NOTE**: Set the path to your Chrome WebDriver executable:
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        // Navigate to the local login page (file path may need to be adjusted)
        driver.navigate().to("file:///C:/path/to/login.html");
        sleep(2);
        // Enter valid username
        WebElement field = driver.findElement(By.id("username"));
        field.clear();
        field.sendKeys("ahsan");
        // Enter valid password
        field = driver.findElement(By.id("passwd"));
        field.clear();
        field.sendKeys("ahsan_pass");
        // Enter valid date of birth
        field = driver.findElement(By.id("dob"));
        field.sendKeys("1990-01-01");
        // Submit the form
        field = driver.findElement(By.cssSelector("input[type=submit]"));
        field.submit();
        sleep(3);
        // Verify the page title indicates success
        String title = driver.getTitle();
        System.out.println("Page title after login: " + title);
        Assert.assertEquals("success", title);
        driver.close();
    }

    @Test
    public void testLoginFailure() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("file:///C:/path/to/login.html");
        sleep(2);
        // Enter valid username but wrong password
        WebElement field = driver.findElement(By.id("username"));
        field.sendKeys("ahsan");
        field = driver.findElement(By.id("passwd"));
        field.sendKeys("wrong_pass");
        field = driver.findElement(By.id("dob"));
        field.sendKeys("1990-01-01");
        // Submit the form
        driver.findElement(By.cssSelector("input[type=submit]")).submit();
        sleep(3);
        // Verify the page title indicates failure
        String title = driver.getTitle();
        System.out.println("Page title after failed login: " + title);
        Assert.assertEquals("fail", title);
        driver.close();
    }
}
