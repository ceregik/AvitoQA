package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class page {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public page(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }
}