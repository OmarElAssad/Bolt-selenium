import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SigninRememberMeCheckBox {
    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\omar_\\Desktop\\Omar\\QA course\\Automation testing\\Workspace\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://staging.boltqr.com/");

        WebElement signinLink = driver.findElement(By.linkText("Sign in"));
        signinLink.click();

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("admin");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("admin");

        WebElement rememberMeCheckBox = driver.findElement(By.className("custom-control-label"));
        rememberMeCheckBox.click();

        WebElement loginButton = driver.findElement(By.name("submit"));
        loginButton.click();

        String expectedURL = "https://staging.boltqr.com/dashboard";
        String actualURL = driver.getCurrentUrl();

        if (expectedURL.equals(actualURL)) {
            System.out.println("Test passed.");
        } else {
            System.out.println("Test failed.");
        }

        driver.quit();
    }
}
