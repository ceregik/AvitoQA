package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends page {
    public MainPage(WebDriver driver) {super(driver); PageFactory.initElements(driver, this);}

    public void open() {driver.get("https://m.avito.ru/moskva/kommercheskaya_nedvizhimost");}

}
