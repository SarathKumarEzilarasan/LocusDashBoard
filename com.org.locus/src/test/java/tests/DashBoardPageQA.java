package tests;

import Listerners.TestListerner;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.DashBoardPage;
import utils.ActionUtils;

import static org.testng.Assert.assertEquals;
import static utils.ReadQaProps.properties;
import static utils.WaitUtils.waitTillVisible;

@Listeners(TestListerner.class)
public class DashBoardPageQA extends BaseClass {
    DashBoardPage dashBoardPage;
    String invalidUserName;
    String invalidPwd;
    String validUserName;
    String validPwd;
    String address;
    String slot;
    String taskNumber;

    @BeforeClass
    public void setTest() {
        dashBoardPage = new DashBoardPage(getDriver());
        invalidUserName = properties.getProperty("invalidUserName");
        invalidPwd = properties.getProperty("invalidPwd");
        validUserName = properties.getProperty("validUserName");
        validPwd = properties.getProperty("validPwd");
        address = properties.getProperty("address");
        slot = properties.getProperty("slot");
        taskNumber = properties.getProperty("taskNumber");
    }

    @Test
    public void LoginWithInvalidPwd() throws Exception {
        dashBoardPage.login(invalidUserName, invalidPwd);
        waitTillVisible(dashBoardPage.loginErrorMsg);
        String errorText = dashBoardPage.loginErrorMsg.getText();
        assertEquals(errorText, "Wrong ID or password", "Error text is invalid");
    }

    @Test
    public void LoginWithValidPwd() throws Exception {
        dashBoardPage.login(validUserName, validPwd);
        waitTillVisible(dashBoardPage.userIcon);
        String currentUrl = getDriver().getCurrentUrl();
        assertEquals(currentUrl, "https://test-hiring.locus-dashboard.com/#/client/test-hiring/live_view"
                , "Home Page URL is not as expected");
        dashBoardPage.logOut();
    }

    @Test
    public void verifyProfile() throws Exception {
        dashBoardPage.login(validUserName, validPwd);
        waitTillVisible(dashBoardPage.userIcon);
        ActionUtils.inst().moveToElement(dashBoardPage.userIcon).perform();
        waitTillVisible(dashBoardPage.dropDownValue);
        assertEquals(dashBoardPage.dropDownValue.getText(), "web-test", "Profile name");
        dashBoardPage.logOut();
    }

    @Test
    public void searchTask() throws Exception {
        dashBoardPage.login(validUserName, validPwd);
        dashBoardPage.searchTask(taskNumber, 2);
        getDriver().navigate().refresh();
        dashBoardPage.logOut();
    }

    @Test
    public void addTask() throws Exception {
        long taskNumber = System.nanoTime();
        dashBoardPage.login(validUserName, validPwd)
                ._addTask(taskNumber, address, slot)
                .searchTask("" + taskNumber, 2);
        ActionUtils.inst().sendKeys(Keys.ESCAPE).perform();
        dashBoardPage.logOut();
    }

}
