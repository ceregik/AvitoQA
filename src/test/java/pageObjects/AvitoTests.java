package pageObjects;

import org.junit.Test;
import org.openqa.selenium.*;

import org.openqa.selenium.support.ui.WebDriverWait;


public class AvitoTests extends TestBase {
    private WebDriver driver;
    private WebDriverWait wait;

    @Test
    public void Tabstest()  throws InterruptedException {
        app.GoToMetro();

        app.AddFirstNStations(1);

        app.TabsDontLostStation();

    }



    @Test
    public void ButtonTest() throws InterruptedException {

        app.GoToMetro();

        app.CheckNoClick();

        app.AddNStationsAndСomparisonWithBtn(4);
    }

    @Test
    public void LinesTest() throws InterruptedException {
        app.GoToMetro();

        app.ChoiceStation("Академическая");

        app.TabLines();

        app.ChoiceOnOtherTab();

    }


    @Test
    public void ThrowOffTest() throws InterruptedException {
        app.GoToMetro();

        app.NoEnabled("metro-select-dialog/reset");

        app.AddFirstNStations(1);

        app.Enabled("metro-select-dialog/reset");
    }

    @Test
    public void SearchTest() throws InterruptedException {
        app.GoToMetro();

        app.Search();

        app.AddFirstNStations(1);

        app.CloseSearch();

    }

    @Test
    public void beginTest() throws InterruptedException {
        app.GoToMetro();

        int i=3;
        app.AddFirstNStations(i);

        app.ExitAndСomparisonwithAdd(i);
    }

    @Test
    public void CityTest() throws InterruptedException {
        app.GoToFilter();

        app.CheckElement("metro-select/withoutValue");

        app.ChoiceCityWithoutMetro();

        app.CheckNoElement("metro-select/withoutValue");
    }


}

