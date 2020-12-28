import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class test {
    private WebDriver driver;
    private WebDriverWait wait;

    public boolean isElementPresent(By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }
    public boolean TryToClick(By locator){
        try {
            driver.findElement(locator).click();
            return true;
        } catch (ElementClickInterceptedException ex){
            return false;
        }
    }

    @Before
    public void start() {
        Map<String, String> mobileEmulation = new HashMap<>();

        mobileEmulation.put("deviceName", "Nexus 5");


        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(800, TimeUnit.MILLISECONDS);
    }


    @Test
    public void Tabstest() throws InterruptedException {
        driver.get("https://m.avito.ru/moskva/kommercheskaya_nedvizhimost");
        driver.findElement(By.cssSelector("div[data-marker='search-bar/filter']")).click();
        driver.findElement(By.cssSelector("div[data-marker='metro-select/withoutValue']")).click();
        driver.findElement(By.cssSelector("label[data-marker='metro-select-dialog/stations/item']")).click();

        String Station = driver.findElement(By.cssSelector("label[data-marker='metro-select-dialog/stations/item']")).getAttribute("aria-checked");
        driver.findElement(By.cssSelector("button[data-marker='metro-select-dialog/tabs/button(lines)']")).click();
        driver.findElement(By.cssSelector("button[data-marker='metro-select-dialog/tabs/button(stations)']")).click();
        String StationNew = driver.findElement(By.cssSelector("label[data-marker='metro-select-dialog/stations/item']")).getAttribute("aria-checked");
        assertTrue(Station.equals(StationNew));

    }



    @Test
    public void ButtonTest() throws InterruptedException {
        driver.get("https://m.avito.ru/moskva/kommercheskaya_nedvizhimost");
        driver.findElement(By.cssSelector("div[data-marker='search-bar/filter']")).click();
        driver.findElement(By.cssSelector("div[data-marker='metro-select/withoutValue']")).click();
        assertFalse(TryToClick(By.cssSelector("button[data-marker='metro-select-dialog/apply']")));

        for(int i=1;i<5;i++){
        driver.findElement(By.cssSelector("label[data-marker='metro-select-dialog/stations/item']:nth-child(" + i + ")")).click();
        String StrButton = driver.findElement(By.cssSelector("button[data-marker='metro-select-dialog/apply']")).getText();
        String[] words = StrButton.split("\\s");
        int N = Integer.parseInt(words[1]);
        assertTrue(N == i);
        }
    }

    @Test
    public void LinesTest() throws InterruptedException {
        driver.get("https://m.avito.ru/moskva/kommercheskaya_nedvizhimost");
        driver.findElement(By.cssSelector("div[data-marker='search-bar/filter']")).click();
        driver.findElement(By.cssSelector("div[data-marker='metro-select/withoutValue']")).click();

        driver.findElement(By.xpath("//span[contains(text(),'Академическая')]")).click();

        driver.findElement(By.cssSelector("button[data-marker='metro-select-dialog/tabs/button(lines)']")).click();

        assertFalse(TryToClick(By.xpath("//span[contains(text(),'Академическая')] /.. /../../label")));
        driver.findElement(By.xpath("//span[contains(text(),'Калужско-Рижская')]")).click();
        String ChoiceStation = driver.findElement(By.xpath("//span[contains(text(),'Академическая')] /.. /../../label")).getAttribute("aria-checked");
        assertTrue(ChoiceStation.equals("true"));
    }


    @Test
    public void ThrowOffTest() throws InterruptedException {
        driver.get("https://m.avito.ru/moskva/kommercheskaya_nedvizhimost");
        driver.findElement(By.cssSelector("div[data-marker='search-bar/filter']")).click();
        driver.findElement(By.cssSelector("div[data-marker='metro-select/withoutValue']")).click();

        assertFalse(driver.findElement(By.cssSelector("button[data-marker='metro-select-dialog/reset']")).isEnabled());
        driver.findElement(By.cssSelector("label[data-marker='metro-select-dialog/stations/item']")).click();
        assertTrue(driver.findElement(By.cssSelector("button[data-marker='metro-select-dialog/reset']")).isEnabled());
    }

    @Test
    public void SearchTest() throws InterruptedException {
        driver.get("https://m.avito.ru/moskva/kommercheskaya_nedvizhimost");
        driver.findElement(By.cssSelector("div[data-marker='search-bar/filter']")).click();
        driver.findElement(By.cssSelector("div[data-marker='metro-select/withoutValue']")).click();

        driver.findElement(By.cssSelector("input[data-marker='metro-select-dialog/search']")).sendKeys("туш");
        assertTrue(driver.findElement(By.cssSelector("label[data-marker='metro-select-dialog/stations/item']")).getText().equals("Тушинская"));
        driver.findElement(By.cssSelector("label[data-marker='metro-select-dialog/stations/item']")).click();

        String StrSearch = (driver.findElement(By.cssSelector("input[data-marker='metro-select-dialog/search']")).getText());
        assertTrue(StrSearch.equals(""));
    }

    @Test
    public void beginTest() throws InterruptedException {
        driver.get("https://m.avito.ru/moskva/kommercheskaya_nedvizhimost");
        driver.findElement(By.cssSelector("div[data-marker='search-bar/filter']")).click();
        driver.findElement(By.cssSelector("div[data-marker='metro-select/withoutValue']")).click();

        int i=0;
        for(i=1;i<3;i++){
            driver.findElement(By.cssSelector("label[data-marker='metro-select-dialog/stations/item']:nth-child(" + i + ")")).click();
        }
        i--;

        driver.findElement(By.cssSelector("button[data-marker='metro-select-dialog/apply']")).click();

        String StrButton = driver.findElement(By.cssSelector("span[data-marker='metro-select/value']")).getText();;
        String[] words = StrButton.split("\\s");
        int N = Integer.parseInt(words[1]);
        assertTrue(N == i);
    }

    @Test
    public void CityTest() throws InterruptedException {
        driver.get("https://m.avito.ru/moskva/kommercheskaya_nedvizhimost");
        driver.findElement(By.cssSelector("div[data-marker='search-bar/filter']")).click();

        assertTrue(isElementPresent(By.cssSelector("div[data-marker='metro-select/withoutValue']")));

        driver.findElement(By.cssSelector("div[data-marker='location-chooser/value']")).click();

        driver.findElement(By.cssSelector("input[data-marker='region-search-bar/search']")).sendKeys("воро");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'Воронеж')]"))));
        driver.findElement(By.cssSelector("div[data-marker='region-list/list'] div")).click();
       Thread.sleep(500);

        assertFalse(isElementPresent(By.cssSelector("div[data-marker='metro-select/withoutValue']")));
    }


    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
