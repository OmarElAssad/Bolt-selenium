package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    

    private By logo = By.className("navbar-logo");
    private By signInLink = By.linkText("Sign in");
    private By signUpLink = By.xpath("//*[@id='main_navbar']/ul/li[3]/a");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnSignInLink() {
        driver.findElement(signInLink).click();
    }

    public void clickOnLogo() {
        driver.findElement(logo).click();
    }

    public void clickOnSignUpLink() {
        driver.findElement(signUpLink).click();
    }
    
}
