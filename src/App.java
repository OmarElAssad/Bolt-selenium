import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\omar_\\Desktop\\Omar\\QA course\\Automation testing\\Workspace\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        driver.get("https://staging.boltqr.com/");

        WebElement signInLink = driver.findElement(By.linkText("Sign in"));
        signInLink.click();

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("admin");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("admin");

        WebElement loginButton = driver.findElement(By.name("submit"));
        loginButton.click();

        String expectedUrl = "https://staging.boltqr.com/dashboard";
        String actualUrl = driver.getCurrentUrl();

        String expectedAlertMessage = "Welcome back, Milomir! You've successfully logged in.";
        WebElement alertElement = driver.findElement(By.className("alert-info"));
        String alertMessage = alertElement.getText();

        String expectedPageHeading = "Dashboard";
        String pageHeading = driver.findElement(By.tagName("h1")).getText();

        if (expectedUrl.equals(actualUrl) && expectedAlertMessage.equals(alertMessage) && expectedPageHeading.equals(pageHeading)) {
            System.out.println("Test passed.");
        } else {
            System.out.println("Test failed.");
        }

        driver.quit();
    }
}
