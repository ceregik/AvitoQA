package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MetroSearchPage extends page{
    public MetroSearchPage(WebDriver driver) {super(driver); PageFactory.initElements(driver, this);}

    @FindBy(css="input[data-marker='metro-select-dialog/search']")
    public WebElement SearchInput;

    @FindBy(css="label[data-marker='metro-select-dialog/stations/item']")
    public WebElement FirstInSearch;
}
