package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MetroLinePage extends page{
    public MetroLinePage(WebDriver driver) {super(driver); PageFactory.initElements(driver, this);}

    @FindBy(css="button[data-marker='metro-select-dialog/tabs/button(lines)']")
    public WebElement LineStation;

    @FindBy(css="button[data-marker='metro-select-dialog/tabs/button(lines)']")
    public WebElement AcademElement;

    @FindBy(xpath="//span[contains(text(),'Калужско-Рижская')]")
    public WebElement Kalyjsh;

    public By AcademBy() {return By.xpath("//span[contains(text(),'Академическая')] /.. /../../label");}
}
