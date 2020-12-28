package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MetroStationPage extends page{
    public MetroStationPage(WebDriver driver) {super(driver); PageFactory.initElements(driver, this);}

    public void GoToMetro() {driver.findElement(By.cssSelector("div[data-marker='metro-select/withoutValue']")).click();}


    public WebElement FirstNStation(int i) {return driver.findElement(By.cssSelector("label[data-marker='metro-select-dialog/stations/item']:nth-child(" + i + ")"));}

    public By btnApply() {return By.cssSelector("button[data-marker='metro-select-dialog/apply']");}

    public By Check(String DataMarker) {return By.cssSelector("div[data-marker='"+DataMarker+"']");}

    public WebElement BtnCheck(String DataMarker) {return driver.findElement(By.cssSelector("button[data-marker='"+DataMarker+"']"));}

    public WebElement ChoiceStation(String Station) {return driver.findElement(By.xpath("//span[contains(text(),'"+Station+"')]"));}

    @FindBy(css="button[data-marker='metro-select-dialog/apply']")
    public WebElement DialogApply;

    @FindBy(css="button[data-marker='metro-select-dialog/tabs/button(stations)']")
    public WebElement TabStation;

    @FindBy(css="label[data-marker='metro-select-dialog/stations/item']")
    public WebElement FirstStation;

}
