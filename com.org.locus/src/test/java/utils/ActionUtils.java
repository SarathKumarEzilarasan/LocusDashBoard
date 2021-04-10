package utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import tests.BaseClass;

public class ActionUtils {
    static Actions actions;

    private ActionUtils() {

    }

    public static Actions inst() {
        if (actions == null) {
            actions = new Actions(BaseClass.getDriver());
        }
        return actions;
    }

    public static Actions sendEscape() {
        actions.sendKeys(Keys.ESCAPE);
        return actions;
    }
}
