package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.ActionUtils;
import utils.ReadQaProps;
import utils.WaitUtils;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static utils.WaitUtils.waitTillVisible;

public class DashBoardPage {
    WebDriver driver;

    public DashBoardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        // TODO Auto-generated constructor stub
    }

    @FindBy(css = "[data-test-id=\"button-redirect-to-sso\"]")
    public WebElement homePageLogin;

    @FindBy(css = "[data-test-id=\"input-userId\"]")
    public WebElement userName;

    @FindBy(css = "[data-test-id=\"input-password\"]")
    public WebElement password;

    @FindBy(css = "[data-test-id=\"button-login-with-password\"]")
    public WebElement buttonWithPwd;

    @FindBy(css = "[data-test-id=\"error-auth0\"]")
    public WebElement loginErrorMsg;

    @FindBy(css = "[class=\"UserMenuIcon__accountIconWrap___GEakb\"]")
    public WebElement userIcon;

    @FindBy(css = "[title=\"test +916383964124\"]")
    public WebElement record;

    @FindBy(css = "[data-test-id=\"button-personnel-continue-to-login\"]")
    public WebElement continueLogin;

    @FindBy(css = "[class=\"UserMenuItem__title___3q4j7\"]")
    public WebElement dropDownValue;

    @FindBy(css = "[class$=\"SearchTheme__iconBtn___3eJY9\"]")
    public WebElement searchButton;

    @FindBy(css = "[placeholder=\"Search Tasks by ID, address, name...\"]")
    public WebElement searchInput;

    @FindBy(css = "[class=\"fixedDataTableRowLayout_rowWrapper\"]")
    public List<WebElement> tableRows;

    @FindBy(css = "[class^=\"TaskSearchResults__taskId\"]")
    public WebElement column;

    @FindBy(css = "[data-test-id=\"personnelMenu-signOut\"]")
    public WebElement logOff;

    @FindBy(css = "[testid=\"addVisit\"]")
    public WebElement addTask;

    @FindBy(css = "[testid=\"enterVistId\"]")
    public WebElement inputTask;

    @FindBy(css = "[class=\"ListCard__cardTitle___jOgnk TeamRenderer__cardTitle___1P7-r\"]")
    public WebElement selectTeamDropDown;

    @FindBy(css = "[testid=\"option-0\"]")
    public WebElement team;

    @FindBy(css = "[testid=\"proceedTaskCreation\"]")
    public WebElement proceedCreation;

    @FindBy(css = "[class=\"TaskDetails__titleWrap___11il6\"]")
    public WebElement createdTask;

    @FindBy(css = "[testid=\"option-DROP\"]")
    public WebElement visitTypeDropDown;

    @FindBy(css = "[testid=\"option-2\"]")
    public WebElement service;

    @FindBy(css = "[testid=\"option-PICKUP\"]")
    public WebElement editVisitType;

    @FindBy(css = "[testid=\"inputbox\"]")
    public WebElement address;

    @FindBy(css = "[testid=\"option-0\"]")
    public WebElement selectAddress;

    @FindBy(css = "[class^=\"theme__button___3elfs Button__button___3Y4nz SlotCard__button\"]")
    public WebElement choiceSlot;

    @FindBy(css = "[placeholder=\"Enter SLA (min)\"]")
    public WebElement enterSlot;

    @FindBy(css = "[testid=\"save\"]")
    public WebElement saveSlot;

    @FindBy(css = "[placeholder=\"Enter Total Volume\"]")
    public WebElement volume;

    @FindBy(css = "[testid=\"createTask\"]")
    public WebElement createTask;

    @FindBy(css = "[class$=\"theme__inverse___3uKC8\"]")
    public WebElement closeWindow;


    public DashBoardPage login(String _userName, String _password) throws Exception {
        driver.get(ReadQaProps.properties.getProperty("loginUrl"));
        WaitUtils.waitTillVisible(homePageLogin);
        homePageLogin.click();
        userName.sendKeys(_userName);
        continueLogin.click();
        WaitUtils.waitTillVisible(password);
        password.sendKeys(_password);
        buttonWithPwd.click();
        return this;
    }

    public DashBoardPage logOut() throws Exception {
        WaitUtils.waitTillVisible(userIcon);
        Actions actions = new Actions(driver);
        actions.moveToElement(userIcon).perform();
        WaitUtils.waitTillVisible(dropDownValue);
        logOff.click();
        WaitUtils.waitTillVisible(homePageLogin);
        return this;
    }

    public DashBoardPage _addTask(long taskNumber, String _address, String _slot) throws Exception {
        waitTillVisible(addTask);
        Assert.assertTrue(addTask.isEnabled());
        addTask.click();
        inputTask.sendKeys("" + taskNumber);
        selectTeamDropDown.click();
        team.click();
        waitTillVisible(proceedCreation);
        Assert.assertTrue(proceedCreation.isEnabled());
        proceedCreation.click();
        waitTillVisible(editVisitType);
        Assert.assertTrue(editVisitType.isDisplayed());
        editVisitType.click();
        service.click();
        visitTypeDropDown.click();
        service.click();
        ActionUtils.inst().moveToElement(address).click().sendKeys(_address).perform();
        waitTillVisible(selectAddress);
        selectAddress.click();
        choiceSlot.click();
        waitTillVisible(enterSlot);
        enterSlot.sendKeys(_slot);
        saveSlot.click();
        waitTillVisible(createTask);
        createTask.click();
        waitTillVisible(closeWindow);
        closeWindow.click();
        return this;
    }

    public void searchTask(String taskNumber,int rowCount) throws Exception {
        waitTillVisible(searchButton);
        Assert.assertTrue(searchButton.isEnabled());
        searchButton.click();
        searchInput.sendKeys(taskNumber);
        waitTillVisible(column);
        assertEquals(tableRows.size(), rowCount,"Row Count");
    }

}
