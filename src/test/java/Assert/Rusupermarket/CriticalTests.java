package Assert.Rusupermarket;

import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static Assert.OffersMethods.*;
import static Assert.OffersMethods.geoCookie;

public class CriticalTests {

    private static String selectorOffersOnKupit = "//div[@class='detail-shops-item']";
    private static WebDriver driver;
    private static String domain = "https://rus-supermarket.ru";

    @Test
    private static void offersNadaviAreEqualToOffersOnRS(String id, String city) throws InterruptedException, SQLException, JSONException, IOException {
   //     offersNadaviAreEqualToOffersOnSite(id,city);
    }

    @Test
    private static void clickToOffersOnKupit(String url) {
        offerClickOnKupitDesktop(url, domain,  selectorOffersOnKupit, driver);
    }

    @Test
    private static void geoOnKupit(String url) {
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        geoOffers(driver, selectorOffersOnKupit);
        getParameterCity(driver);
        geoCookie(driver);
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
