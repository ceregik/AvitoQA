package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilterPage extends page {
    public FilterPage(WebDriver driver) {super(driver); PageFactory.initElements(driver, this);}

    public void GoToFilter() {driver.findElement(By.cssSelector("div[data-marker='search-bar/filter']")).click();}

    @FindBy(css="span[data-marker='metro-select/value']")
    public WebElement MetroWithValue;

    @FindBy(css="div[data-marker='location-chooser/value']")
    public WebElement Choice;

    @FindBy(css="input[data-marker='region-search-bar/search']")
    public WebElement InputRegionSearch;

    @FindBy(css="div[data-marker='region-list/list'] div")
    public WebElement FirstInSearch;

    @FindBy(xpath="//span[contains(text(),'Воронеж')]")
    public WebElement FindVoronesh;
}
