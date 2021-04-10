package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.ReadQaProps;

public class BaseClass {
    private static WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        ReadQaProps.inst().loadFile();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
