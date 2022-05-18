package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By projectsLink = By.xpath("/html/body/div[2]/div/div[2]/ul/li[5]/a");
    private By createProjectLink = By.className("btn-primary");
    private By nameTheProject = By.id("name");
    private By createButton = By.name("submit");
    private By alertmessage = By.className("alert-success");
    private By filterButton = By.className("filters-button");
    private By searchInputField = By.id("search");
    private By searchByDropDownMenu = By.id("search_by");
    private By orderByDropDownMenu = By.id("order_by");
    private By orderTypeDropDownMenu = By.id("order_type");
    private By resultsPerPageDropDownMenu = By.id("results_per_page");
    private By submitButton = By.name("submit");
    private By projectName = By.xpath("/html/body/div[2]/section/div[1]/main/div/div[2]/table/tbody/tr[1]/td[1]/a");
    private By kebabDropDownMenu = By.className("text-secondary");
    private By deleteOption = By.linkText("Delete");
    private By deleteProject = By.className("btn-danger");


    public ProjectsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickOnProjectsLink() {
        driver.findElement(projectsLink).click();
    }

    public void clickOnCreateAProjectLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createProjectLink)).click();
    }

    public void enterNameToProject(String name) {
        driver.findElement(nameTheProject).sendKeys(name);
    }

    public void clickOnCreateButton() {
        driver.findElement(createButton).click();
    }

    public String getAlertMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alertmessage)).getText();
    }

    public void clickOnFilterButton() {
        driver.findElement(filterButton).click();
    }

    public void enterSearchInput(String searchInput) {
        driver.findElement(searchInputField).sendKeys(searchInput);
    }

    public void clickOnSearchByDropDownMenu() {
        driver.findElement(searchByDropDownMenu).click();
    }

    public void clickOnOrderByDropDownMenu() {
        driver.findElement(orderByDropDownMenu).click();
    }

    public void clickOnOrderTypeDropDownMenu() {
        driver.findElement(orderTypeDropDownMenu).click();
    }

    public void clickOnResultsPerPageDropDownMenu() {
        driver.findElement(resultsPerPageDropDownMenu).click();
    }

    public void clickOnSubmitButton() {
        driver.findElement(submitButton).click();
    }

    public String getProjectName() {
        return driver.findElement(projectName).getText();
    }

    public void clickOnKebabDropDownMenu() {
        // driver.findElement(kebabDropDownMenu).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(kebabDropDownMenu)).click();
    }

    public void clickOnDeleteOption() {
        driver.findElement(deleteOption).click();
    }

    public void clickOnDeleteProject() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteProject)).click();
    }



    // public void choosingRandomColor() {
    //     JavascriptExecutor jse=(JavascriptExecutor)driver;
    //     jse.executeScript("document.getElementByid('color').value='#FFEEXX'");
    // }
    
}