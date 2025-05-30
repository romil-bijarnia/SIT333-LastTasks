package web.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MathGameSeleniumTest {
    private WebDriver driver;

    // Utility method to pause execution for a given number of seconds (to wait for page loads)
    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            // Ignore interruption errors
        }
    }

    @Before
    public void setUp() {
        // Set path to the ChromeDriver executable (update this path for your system)
        System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testFullSuccessPath() {
        // 1. Navigate to the application and log in with valid credentials
        driver.get("http://localhost:8080/");
        driver.get("http://localhost:8080/login");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("passwd")).sendKeys("admin");
        driver.findElement(By.name("dob")).sendKeys("01-01-2000");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        sleep(2);
        // Verify we are on Q1 page
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/q1"));

        // 2. Answer Question 1 correctly (e.g., 2 + 3 = 5)
        driver.findElement(By.name("number1")).sendKeys("2");
        driver.findElement(By.name("number2")).sendKeys("3");
        driver.findElement(By.name("result")).sendKeys("5");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        sleep(2);
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/q2"));

        // 3. Answer Question 2 correctly (e.g., 5 - 2 = 3)
        driver.findElement(By.name("number1")).sendKeys("5");
        driver.findElement(By.name("number2")).sendKeys("2");
        driver.findElement(By.name("result")).sendKeys("3");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        sleep(2);
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/q3"));

        // 4. Answer Question 3 correctly (e.g., 3 * 4 = 12)
        driver.findElement(By.name("number1")).sendKeys("3");
        driver.findElement(By.name("number2")).sendKeys("4");
        driver.findElement(By.name("result")).sendKeys("12");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        sleep(2);
        // After Q3 correct, should redirect to welcome page
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/"));
        // Verify success message is shown on welcome page
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("Congratulations") && pageSource.contains("answered all questions correctly"));
    }

    @Test
    public void testFailurePath() {
        // Repeat login and correct answers for Q1 and Q2
        driver.get("http://localhost:8080/login");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("passwd")).sendKeys("admin");
        driver.findElement(By.name("dob")).sendKeys("01-01-2000");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        sleep(1);
        driver.findElement(By.name("number1")).sendKeys("2");
        driver.findElement(By.name("number2")).sendKeys("3");
        driver.findElement(By.name("result")).sendKeys("5");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        sleep(1);
        driver.findElement(By.name("number1")).sendKeys("5");
        driver.findElement(By.name("number2")).sendKeys("2");
        driver.findElement(By.name("result")).sendKeys("3");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        sleep(1);

        // Provide an incorrect answer on Q3 (e.g., 3 * 4 ≠ 11)
        driver.findElement(By.name("number1")).sendKeys("3");
        driver.findElement(By.name("number2")).sendKeys("4");
        driver.findElement(By.name("result")).sendKeys("11");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        sleep(2);
        // Verify we are still on Q3 page (no redirect to welcome since answer was wrong)
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/q3"));
        // Verify the error flash message is displayed on Q3 page
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("Wrong answer, try again."));
    }
}
