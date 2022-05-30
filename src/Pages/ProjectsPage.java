package pages;

import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By projectsLink = By.xpath("/html/body/div[2]/div/div[2]/ul/li[5]/a");

    private By createProjectLink = By.className("btn-primary");
    private By nameTheProject = By.id("name");
    private By createButton = By.name("submit");
    private By alertmessage = By.className("alert-success");
    private By projectName = By.xpath("/html/body/div[2]/section/div[1]/main/div/div[3]/table/tbody/tr[1]/td[1]/a");

    private By filterButton = By.className("filters-button");

    private By searchInputField = By.id("search");

    private By orderByDropDownMenu = By.id("order_by");

    private By orderTypeDropDownMenu = By.id("order_type");

    private By resultsPerPageDropDownMenu = By.id("results_per_page");

    private By filterSubmitButton = By.name("submit");

    private By kebabDropDownMenu = By.className("text-secondary");
    private By editOption = By.linkText("Edit");
    private By deleteOption = By.linkText("Delete");
    private By deleteProject = By.className("btn-danger");


    public ProjectsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickOnProjectsLink() {
        driver.findElement(projectsLink).click();
    }

    // Methods for creating a project, and validating the changes

    public void clickOnCreateAProjectLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createProjectLink)).click();
    }

    public void enterNameToProject(String name) {
        driver.findElement(nameTheProject).sendKeys(name);
    }

    public void changeColorForProject() {
        String sampleJS = "document.getElementById('color').value='#ffffff'";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(sampleJS);
    }
    
    public void clickOnCreateButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createButton)).click();
    }

    public String getProjectNameValidationMessag() {
        return driver.findElement(nameTheProject).getAttribute("validationMessage");
    }

    public String getAlertMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alertmessage)).getText();
    }

    // Methods for testing the Kebab dropdown menu for the created project (Edit & Delete options)

    public void clickOnKebabDropDownMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(kebabDropDownMenu)).click();
    }

    public void clickOnDeleteOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteOption)).click();
    }

    public void clickOnDeleteProject() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteProject)).click();
    }

    public void clickOnEditOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(editOption)).click();
    }

    public void changeProjectName(String name) {
        driver.findElement(By.id("name")).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE), (name));
    }

    public String getProjectName() {
        return driver.findElement(projectName).getText();
    }

    public void deleteProject() {
        clickOnKebabDropDownMenu();
        clickOnDeleteOption();
        clickOnDeleteProject();
    }

    public void deleteAllProjects() {
        do {
            deleteProject();
        } while (getProjectName() != null);
            
    }


    // Methods for filtering the projects table, and their validation methods

    public void clickOnFilterButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterButton)).click();
    }

    public void clickOnFilterSubmitButton() {
        driver.findElement(filterSubmitButton).click();
    }


    // Search input field

    public void enterSearchInput(String searchInput) {
        driver.findElement(searchInputField).sendKeys(searchInput);
    }

    public List<String> getNamesOfProjects() {
        List<String> projectNames = new ArrayList<>();
        WebElement table = driver.findElement(By.tagName("tbody"));
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));
        for (WebElement row : tableRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            projectNames.add(cells.get(0).getText());
        }
        return projectNames;
    }


    // Order By

    public void clickOnOrderByDropDownMenu() {
        driver.findElement(orderByDropDownMenu).click();
    }

    public void enterOrderByIndexValue(int index) {
        WebElement selectElement = driver.findElement(By.id("order_by"));
        Select selectObject = new Select(selectElement);
        selectObject.selectByIndex(index);
    }

    public String getOrderByAttributeValue() {
        return driver.findElement(By.xpath("//*[@id='order_by']")).getAttribute("value");
    }


    // Order Type

    public void clickOnOrderTypeDropDownMenu() {
        driver.findElement(orderTypeDropDownMenu).click();
    }

    public void enterOrderTypeIndexValue(int index) {
        WebElement selectElement = driver.findElement(By.id("order_type"));
        Select selectObject = new Select(selectElement);
        selectObject.selectByIndex(index);
    }

    public String getOrderTypeAttributeValue() {
        return driver.findElement(By.xpath("//*[@id='order_type']")).getAttribute("value");
    }


    // Results per page

    public void clickOnResultsPerPageDropDownMenu() {
        driver.findElement(resultsPerPageDropDownMenu).click();
    }

    public void enterResultsPerPageIndexValue(int index) {
        WebElement selectElement = driver.findElement(By.id("results_per_page"));
        Select selectObject = new Select(selectElement);
        selectObject.selectByIndex(index);
    }

    public String getResultsPerPageAttributeValue() {
        return driver.findElement(By.xpath("//*[@id='results_per_page']")).getAttribute("value");
    }
        
}
