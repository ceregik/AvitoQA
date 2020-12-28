package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Application {

    private WebDriver driver;
    private WebDriverWait wait;


    private MainPage mainPage;
    private FilterPage filterPage;
    private MetroLinePage metrolinePage;
    private MetroSearchPage metrosearchPage;
    private MetroStationPage metrostationPage;

    public Application() {
        Map<String, String> mobileEmulation = new HashMap<>();

        mobileEmulation.put("deviceName", "Nexus 5");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        driver = new ChromeDriver(chromeOptions);

        mainPage = new MainPage(driver);
        filterPage = new FilterPage(driver);
        metrolinePage = new MetroLinePage(driver);
        metrosearchPage = new MetroSearchPage(driver);
        metrostationPage = new MetroStationPage(driver);

        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(600, TimeUnit.MILLISECONDS);
    }

    public void quit(){
        driver.quit();}

    public void GoToMetro() throws InterruptedException {
        mainPage.open();
        filterPage.GoToFilter();
        metrostationPage.GoToMetro();
    }

    public void GoToFilter() throws InterruptedException {
        mainPage.open();
        filterPage.GoToFilter();
    }

    public void AddFirstNStations(int HowManyItems) throws InterruptedException {
        for(int i=1;i<=HowManyItems;i++){
            metrostationPage.FirstNStation(i).click();
        }
    }

    public void CheckNoClick() throws InterruptedException {
        assertFalse(TryToClick(metrostationPage.btnApply()));
    }
    public void ExitAndСomparisonwithAdd(int HowManyStationsWasSelected) throws InterruptedException {
        metrostationPage.DialogApply.click();
        String StrButton = filterPage.MetroWithValue.getText();;
        String[] words = StrButton.split("\\s");
        int N = Integer.parseInt(words[1]);
        assertTrue(N == HowManyStationsWasSelected);
    }

    public void AddNStationsAndСomparisonWithBtn(int HowManyItems) throws InterruptedException {

        for(int i=1 ;i<=HowManyItems;i++){
            metrostationPage.FirstNStation(i).click();
            String StrButton = metrostationPage.DialogApply.getText();
            String[] words = StrButton.split("\\s");
            int N = Integer.parseInt(words[1]);
            assertTrue(N == i);
        }
    }

    public void CheckElement(String DataMarker) throws InterruptedException {
        assertTrue(isElementPresent(metrostationPage.Check(DataMarker)));
    }
    public void CheckNoElement(String DataMarker) throws InterruptedException {
        assertFalse(isElementPresent(metrostationPage.Check(DataMarker)));
    }
    public void NoEnabled(String DataMarker) throws InterruptedException {
        assertFalse(metrostationPage.BtnCheck(DataMarker).isEnabled());
    }
    public void Enabled(String DataMarker) throws InterruptedException {
        assertTrue(metrostationPage.BtnCheck(DataMarker).isEnabled());
    }

    public void ChoiceStation(String Station) throws InterruptedException {
        metrostationPage.ChoiceStation(Station).click();
    }

    public void TabStations() throws InterruptedException {
        metrostationPage.TabStation.click();
    }
    public void TabLines() throws InterruptedException {
       metrolinePage.LineStation.click();
    }

    public void Search() throws InterruptedException {
        metrosearchPage.SearchInput.sendKeys("туш");
        assertTrue(metrosearchPage.FirstInSearch.getText().equals("Тушинская"));
    }

    public void CloseSearch() throws InterruptedException {
        String StrSearch = metrosearchPage.SearchInput.getText();
        assertTrue(StrSearch.equals(""));
    }

    public void ChoiceOnOtherTab() throws InterruptedException {
        assertFalse(TryToClick(metrolinePage.AcademBy()));
        metrolinePage.Kalyjsh.click();
        String ChoiceStation = metrolinePage.AcademElement.getAttribute("aria-checked");
        assertTrue(ChoiceStation.equals("true"));
    }

    public void TabsDontLostStation() throws InterruptedException {
        String Station = metrostationPage.FirstStation.getAttribute("aria-checked");
        TabLines();
        TabStations();
        String StationNew = metrostationPage.FirstStation.getAttribute("aria-checked");
        assertTrue(Station.equals(StationNew));
    }

    public void ChoiceCityWithoutMetro() throws InterruptedException {
        filterPage.Choice.click();
        filterPage.InputRegionSearch.sendKeys("воро");
        wait.until(ExpectedConditions.visibilityOf(filterPage.FindVoronesh));
        filterPage.FirstInSearch.click();
        Thread.sleep(700);
    }


    public boolean TryToClick(By locator){
        try {
            driver.findElement(locator).click();
            return true;
        } catch (ElementClickInterceptedException ex){
            return false;
        }
    }
    public boolean isElementPresent(By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }

}
