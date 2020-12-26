import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class test {
    private WebDriver driver;
    private WebDriverWait wait;

    public boolean isElementPresent(By locator){
        try {
            driver.findElement(locator);
            System.out.println("Элемент найден");
            return true;
        } catch (NoSuchElementException ex){
            System.out.println("Элемент не найден");
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
        wait = new WebDriverWait(driver, 1);
        driver.manage().timeouts().implicitlyWait(600, TimeUnit.MILLISECONDS);
    }

/*
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
*/

    @Test
    public void ButtonTest() throws InterruptedException {
        driver.get("https://m.avito.ru/moskva/kommercheskaya_nedvizhimost");
        driver.findElement(By.cssSelector("div[data-marker='search-bar/filter']")).click();
        driver.findElement(By.cssSelector("div[data-marker='metro-select/withoutValue']")).click();
        for(int i=1;i<5;i++){
        driver.findElement(By.cssSelector("label[data-marker='metro-select-dialog/stations/item']:nth-child(" + i + ")")).click();
        String StrButton = driver.findElement(By.cssSelector("button[data-marker='metro-select-dialog/apply']")).getText();
        String[] words = StrButton.split("\\s");
        int N = Integer.parseInt(words[1]);
        assertTrue(N == i);
    }
       // Thread.sleep(500000);
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
