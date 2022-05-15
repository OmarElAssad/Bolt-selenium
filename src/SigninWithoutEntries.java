import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SigninWithoutEntries {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\omar_\\Desktop\\Omar\\QA course\\Automation testing\\Workspace\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        driver.get("https://staging.boltqr.com/");

        WebElement signInLink = driver.findElement(By.linkText("Sign in"));
        signInLink.click();

        WebElement emailField = driver.findElement(By.id("email"));
        
        WebElement loginButton = driver.findElement(By.name("submit"));
        loginButton.click();

        String expectedUrl = "https://staging.boltqr.com/login";
        String actualUrl = driver.getCurrentUrl();

        String expectedValidationMessage = "Please fill out this field.";
        String actualValidationMessage = emailField.getAttribute("validationMessage");

        if (expectedUrl.equals(actualUrl) && expectedValidationMessage.equals(actualValidationMessage)) {
            System.out.println("Test passed.");
        } else {
            System.out.println("Test failed.");
        }

        driver.quit();
    }
}
