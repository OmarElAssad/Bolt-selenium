package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By alertMessage = By.className("alert-info");
    private By pageHeading = By.tagName("h1");
    private By sidebarFooter = By.className("app-sidebar-footer");
    private By LogoutLink = By.linkText("Logout");
    private By projectsLink = By.xpath("/html/body/div[2]/div/div[2]/ul/li[5]/a");

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getAlertMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage)).getText();
    }
    
    public String getPageHeadingText() {
        return driver.findElement(pageHeading).getText();
    }

    public void clickOnSidebarFooter() {
        driver.findElement(sidebarFooter).click();
    }

    public void clickOnLogoutLink() {
        driver.findElement(LogoutLink).click();
    }

    public void logout() {
        clickOnSidebarFooter();
        clickOnLogoutLink();
    }

    public void clickOnProjectsLink() {
        driver.findElement(projectsLink).click();
    }
}
