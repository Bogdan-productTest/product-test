package Assert.ProductTest;

import org.htmlcleaner.XPatherException;
import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.sql.SQLException;

import static Assert.OffersMethods.*;
import static Assert.OffersMethods.offerClickOnKharakteristikiDesktop;

public class CriticalTests {

    private static String selectorOffersOnKupit = "//div[@class='buy__item']";
    private static String domain = "https://product-test.ru";
    private static WebDriver driver;

    @DataProvider (name = "urlForOffersOnKupit")
    private Object[] listKupit() {
       return new Object[]{
              "/smartfony/apple-iphone-7/kupit"
       };
    }

    @DataProvider (name = "urlForOffersOnKharakteristiki")
    private Object[] listKharakteristiki() {
        return new Object[]{
                "/smartfony/apple-iphone-7-32gb/kharakteristiki"
        };
    }

    @DataProvider (name = "urlForOffersOnObzor")
    private Object[] listObzor() {
        return new Object[]{
                "/smartfony/apple-iphone-7/obzor"
        };
    }

//    @Test
//    private static void offersNadaviAreEqualToOffersOnPT(String id, String city) throws InterruptedException, SQLException, JSONException, IOException {
//        offersNadaviAreEqualToOffersOnSite(id,city);
//    }

    @BeforeTest
    private static void setUp() {
        driver = new ChromeDriver();
    }

    @AfterTest
    private static void tearDown() {
        driver.quit();
    }

    @Test (dataProvider="urlForOffersOnKupit")
    private static void offersMobiguruAreEqualToOffersOnPT(String url) throws InterruptedException, SQLException, JSONException, IOException {
        offersMobiguruAreEqualToOffersOnSite(url, domain);
    }

    @Test(dataProvider="urlForOffersOnKupit")
    private static void clickToOffersOnKupit(String url) {
        offerClickOnKupitDesktop(url, domain, selectorOffersOnKupit, driver);
    }

    @Test(dataProvider="urlForOffersOnObzor")
    private static void clickToOffersOnObzor(String url) {
        offerClickOnObzorDesktop(url, domain, selectorOffersOnKupit, driver);
    }

    @Test(dataProvider="urlForOffersOnKharakteristiki")
    private static void clickToOffersOnKharakteristiki(String url) {
        offerClickOnKharakteristikiDesktop(url, domain, selectorOffersOnKupit, driver);
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

    @Test
    private static void offersForBots(String url, String domain) {
        offersOnKupitForBots(url, domain, selectorOffersOnKupit);
        forBotsIsNoParameterCity(url, domain);
        forBotsNameShopIsTransferred(url, domain, selectorOffersOnKupit);
    }

    @Test
    private static void buttonShowMore(String url, String domain) throws InterruptedException, SQLException, XPatherException, IOException {
        showMore(url, domain, selectorOffersOnKupit);
    }

    public static void allPageReturnStatusCode200(String url) {

    }
}
