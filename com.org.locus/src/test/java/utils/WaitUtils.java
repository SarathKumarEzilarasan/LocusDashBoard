package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import tests.BaseClass;

public class WaitUtils {

    public static void waitTillVisible(WebElement element) throws Exception {
        By by = getByFromElement(element);
        long totalWait = 0;
        boolean flag = false;
        while (totalWait < 15000 && !flag) {
            try {
                WebElement el = BaseClass.getDriver().findElement(by);
                Thread.sleep(500);
                flag = true;
                waitTillDisplayed(el, totalWait);
            } catch (NoSuchElementException e) {
                Thread.sleep(500);
                totalWait += 1000;
                continue;
            }
        }
    }

    public static void waitTillDisplayed(WebElement el, long totalWait) throws InterruptedException {
        while (!el.isEnabled()) {
            Thread.sleep(500);
            totalWait += 500;
            if (totalWait >= 10000) {
                break;
            }
        }
    }

    public static By getByFromElement(WebElement element) throws Exception {
        By by = null;
        //[[ChromeDriver: chrome on XP (d85e7e220b2ec51b7faf42210816285e)] -> xpath: //input[@title='Search']]
        boolean flag = true;
        String[] pathVariables = null;
        int totalTime = 0;
        String selector = "";
        String value = "";
        while (totalTime < 15000 && flag) {
            try {
                pathVariables = (element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "")).split(":");
                flag = false;
            } catch (ArrayIndexOutOfBoundsException e) {
                Thread.sleep(500);
                totalTime += 500;
                continue;
            }
        }
        try {
            selector = pathVariables[0].trim();
            value = pathVariables[1].trim();
        } catch (NullPointerException exception) {
            throw new Exception("Timeout: Element Not Found");
        }
        switch (selector) {
            case "id":
                by = By.id(value);
                break;
            case "className":
                by = By.className(value);
                break;
            case "tagName":
                by = By.tagName(value);
                break;
            case "xpath":
                by = By.xpath(value);
                break;
            case "css selector":
                by = By.cssSelector(value);
                break;
            case "linkText":
                by = By.linkText(value);
                break;
            case "name":
                by = By.name(value);
                break;
            case "partialLinkText":
                by = By.partialLinkText(value);
                break;
            default:
                throw new IllegalStateException("locator : " + selector + " not found!!!");
        }
        return by;
    }

}
