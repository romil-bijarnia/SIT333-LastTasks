package web;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MathGameSeleniumTest {

    private void sleep(int sec) {
        try { Thread.sleep(sec * 1000); } catch (InterruptedException e) { }
    }

    @Test
    public void testCompleteQuizSuccessFlow() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        // Start at the login page (server must be running at 127.0.0.1:8082)
        driver.get("http://127.0.0.1:8082/login");
        sleep(1);
        // Perform login with valid credentials
        driver.findElement(By.id("username")).sendKeys("ahsan");
        driver.findElement(By.id("passwd")).sendKeys("ahsan_pass");
        driver.findElement(By.id("dob")).sendKeys("1990-01-01");
        driver.findElement(By.cssSelector("input[type=submit]")).click();
        sleep(1);
        // Now on Q1 page – fill correct answer for addition
        driver.findElement(By.name("number1")).sendKeys("2");
        driver.findElement(By.name("number2")).sendKeys("3");
        driver.findElement(By.name("result")).sendKeys("5");  // 2+3 = 5 correct
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        sleep(1);
        // On Q2 page – fill correct answer for subtraction
        driver.findElement(By.name("number1")).sendKeys("10");
        driver.findElement(By.name("number2")).sendKeys("4");
        driver.findElement(By.name("result")).sendKeys("6");  // 10-4 = 6 correct
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        sleep(1);
        // On Q3 page – fill correct answer for multiplication
        driver.findElement(By.name("number1")).sendKeys("3");
        driver.findElement(By.name("number2")).sendKeys("4");
        driver.findElement(By.name("result")).sendKeys("12"); // 3*4 = 12 correct
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        sleep(1);
        // Now we should be on the welcome page with a congratulations message
        WebElement msg = driver.findElement(By.id("message"));
        String finalMessage = msg.getText();
        System.out.println("Final page message: " + finalMessage);
        Assert.assertTrue(finalMessage.contains("Congratulations"));
        driver.close();
    }

    @Test
    public void testWrongAnswerOnQ1() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://127.0.0.1:8082/login");
        sleep(1);
        // Login with valid credentials first
        driver.findElement(By.id("username")).sendKeys("ahsan");
        driver.findElement(By.id("passwd")).sendKeys("ahsan_pass");
        driver.findElement(By.id("dob")).sendKeys("1990-01-01");
        driver.findElement(By.cssSelector("input[type=submit]")).click();
        sleep(1);
        // On Q1 page – intentionally give a wrong answer
        driver.findElement(By.name("number1")).sendKeys("2");
        driver.findElement(By.name("number2")).sendKeys("2");
        driver.findElement(By.name("result")).sendKeys("5");  // 2+2 is 4, we give 5 (wrong)
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        sleep(1);
        // Should still be on Q1 with an error message displayed
        String currentUrl = driver.getCurrentUrl();
        WebElement msg = driver.findElement(By.id("message"));
        String errorText = msg.getText();
        System.out.println("After wrong answer, URL: " + currentUrl + ", message: " + errorText);
        Assert.assertTrue("User should still be on Q1 page", currentUrl.endsWith("/q1"));
        Assert.assertTrue(errorText.contains("Wrong answer"));
        driver.close();
    }
}
