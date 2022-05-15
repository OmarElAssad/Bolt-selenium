import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SigninWithInvalidEmail {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\omar_\\Desktop\\Omar\\QA course\\Automation testing\\Workspace\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://staging.boltqr.com/");

        WebElement signinLink = driver.findElement(By.linkText("Sign in"));
        signinLink.click();

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("ergrtgrt");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("admin");

        WebElement loginButton = driver.findElement(By.name("submit"));
        loginButton.click();

        String expectedUrl = "https://staging.boltqr.com/login";
        String actualUrl = driver.getCurrentUrl();

        WebElement alertMessage = driver.findElement(By.className("alert-danger"));
        alertMessage.getText();

        System.out.println(alertMessage.getText());

        if (expectedUrl.equals(actualUrl)) {
            System.out.println("Test passed.");
        } else {
            System.out.println("Test failed.");
        }

        driver.quit();
    }
}
