package Assert;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.print.DocFlavor;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Assert.DatabaseConnection.SQLConnect;
import static Assert.MyConfig.getMobiguruApiKeyPT;
import static Assert.MyConfig.getNadaviApiString;
import static Assert.SQLQueries.*;

public class OffersMethods {
    static WebDriver driver;

    public static void switchToSecondHandle(WebDriver driver) {
        String[] myArray = driver.getWindowHandles().toArray(new String[2]);
            driver.switchTo().window(myArray[1]);
    }

    public static void switchToFirstHandle(WebDriver driver) {
        String[] myArray = driver.getWindowHandles().toArray(new String[2]);
        driver.switchTo().window(myArray[0]);
    }

    public static String getOffersFromPT(String domain, String id, String mobiguruGeoId) throws IOException {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(domain + "/product/getnadavioffers?id=" + id + "&tab=3&needFilter=false&filters=[]&mobiguruId=" + mobiguruGeoId)
                .get()
                .build();

        System.out.println(domain + "/product/getnadavioffers?id=" + id + "&tab=3&needFilter=false&filters=[]&ombiguruId=" + mobiguruGeoId);
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String getOffersFromNadavi(String id, String cityId) throws IOException {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(getNadaviApiString() + "&p4g_gid_=" + id + "&p4g_city_id_=" + cityId)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String getOffersFromMobiguru(String id, String ip, int page) throws IOException {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.mobiguru.ru/v1/model/" + id + "/offers.json" + getMobiguruApiKeyPT() + "&geo_id=" + ip + "&count=30&page=" + page)
                .get()
                .build();
        System.out.println("http://api.mobiguru.ru/v1/model/" + id + "/offers.json" + getMobiguruApiKeyPT() + "&geo_id=" + ip + "&count=30&page=" + page);
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
/**
    public static void offersNadaviAreEqualToOffersOnSite(String idProduct, String cityName, String selector) throws IOException, InterruptedException, JSONException, SQLException {

        String nadaviId = getNadaviIdFromId(idProduct);
        System.out.println("Nadavi id = " + nadaviId);
        String cityId = getCityIdFromCityName(cityName);
        System.out.println("CityId = " + cityId);

        //Исправить
        //String jsonoffersSite = getOffersFromPT(idProduct, cityName);
        Document jsonoffersSite = Jsoup.connect("https://product-test.ru/product/getnadavioffers?id=" + idProduct + "&city=" + cityName + "&tab=3&needFilter=false&filters=[]").timeout(10000).get();
        String jsonoffersND = getOffersFromNadavi(nadaviId, cityId);

        //Получаем список оферов отображаемых на РТ
       // JSONObject offersSite = new JSONObject(jsonoffersSite);

        Elements arrayOffersSite = jsonoffersSite.getElementsByClass(selector);
        System.out.println("Количество оферов на РТ: " + arrayOffersSite.size());

        //Получаем список оферов отдаваемых ND
        JSONObject offersND = new JSONObject(jsonoffersND);
       // offersND.remove("Color");
       // offersND.remove("ShopID");

        JSONArray arrayOffersND = offersND.getJSONArray("offers");
        System.out.println("Количество оферов на ND: " + arrayOffersND.length());

        //Проверяем равно ли кол-во оферов на ND и сайте
          Assert.assertEquals(arrayOffersND.length(), arrayOffersSite.size());

        //Проверяем равны ли строки оферов отдаваемых ND и отображаемых на сайте
        for (int i = 0; i<arrayOffersND.length(); i++) {

            JSONObject offerND = arrayOffersND.getJSONObject(i);
            Element offerSite = arrayOffersSite.get(i);
            String g = offerND.getString("BuyUrl");
            if(g.indexOf("http://")!=-1) {
                offerND.put("BuyUrl", "https://" + g.substring(7));
            }

            //Проверка цены
            Verify.verify();
            //Проверка цвета
            //Проверка ссылки на магазин
            //Проверка ссылки на логотип
            //Проверка название магазины
            //Проверка инфо о доставке


            Assert.assertEquals(arrayOffersSite.getString(i), arrayOffersND.getString(i));
            arrayOffersSite.getString(i).equals(arrayOffersND.getString(i));
            /**
            System.out.println("Офер №" + (i + 1) + " соответствует" );

            String cityNameMod = cityName.substring(0, cityName.length() -1);
            String deliveryInfo = offerND.getString("DeliveryInfo");

            System.out.println(deliveryInfo.substring(0,2));
            if (deliveryInfo.substring(0,3).equals("по ")) {
                System.out.println("по городу");
                Assert.assertEquals(deliveryInfo.substring(3, deliveryInfo.length() - 1), cityNameMod);
            } else {
                System.out.println("доставка в город");
                String deliveryInfoMod = deliveryInfo.substring(2, cityName.length() + 1);
                System.out.println(deliveryInfoMod);
                Assert.assertEquals(deliveryInfoMod, cityNameMod);
            }

        }
    }
**/
    public static void offersMobiguruAreEqualToOffersOnSite(String url, String domain) throws IOException, InterruptedException, JSONException, SQLException {

        Pattern p = Pattern.compile("\\/.+?\\/(.+?)\\/kupit");
        Matcher m = p.matcher(url);
        m.find();
        String urlProduct = m.group(1);
        System.out.println("url равен " + urlProduct);

        Pattern p1 = Pattern.compile("city=(.+)");
        Matcher m1 = p1.matcher(url);
        m1.find();
        String cityName;
        try {
            cityName = m1.group(1);
        } catch (IllegalStateException e) {
            cityName = "Казань";
        }

        String mobiguruId = getMobiguruIdFromUrl(urlProduct);
        System.out.println("Mobiguru id = " + mobiguruId);
        String id="";
        if (cityName.equals("Москва")) {id="213";}
        if (cityName.equals("Санкт-Петербург")) {id="2";}
        if (cityName.equals("Казань")) {id="11119";}

        System.out.println("GeoId = " + id);

        String idProduct = getIdFromUrl(urlProduct);

        //Получаем html со списком оферов
        Document jsonoffersSite = Jsoup.connect(domain + "/product/getnadavioffers?id=" + idProduct + "&mobiguruId=" + id + "&tab=3&needFilter=false&filters=[]").timeout(10000).get();
        System.out.println(domain + "/product/getnadavioffers?id=" + idProduct + "&mobiguruId=" + id + "&tab=3&needFilter=false&filters=[]");

        //Получаем ответ Мобигуру
        JSONObject obj = new JSONObject(getOffersFromMobiguru(mobiguruId, id, 1));
        System.out.println(obj.toString());
        obj = (JSONObject) obj.get("models");
        int numOffers = obj.getInt("total");

        String jsonoffersM ="";
        if (numOffers>30) {
            int pageNum = numOffers / 30;
            if (numOffers%30!=0) {
                pageNum++;
            }
            for (int i = 0; i < pageNum; i++) {
                jsonoffersM += getOffersFromMobiguru(mobiguruId, id, i+1);
            }
        } else {
            jsonoffersM = getOffersFromMobiguru(mobiguruId, id, 1);
        }


        //Список оферов, отображаемых на сайте
        Elements offersSite = jsonoffersSite.getElementsByClass("buy__item");
        System.out.println("Количество оферов на РТ: " + offersSite.size());

        //Получаем список оферов отдаваемых M
        JSONObject offersM = new JSONObject(jsonoffersM);

        JSONObject models = offersM.getJSONObject("models");
        JSONArray arrayOffersM = models.getJSONArray("items");
        System.out.println("Количество оферов на M: " + arrayOffersM.length());

        //Проверяем равно ли кол-во оферов на сайте и M
          Assert.assertEquals(arrayOffersM.length(), offersSite.size());

        //Проверяем равны ли строки оферов отдаваемых ND и отображаемых на РТ
        for (int i = 0; i<arrayOffersM.length(); i++) {

            JSONObject offerM = arrayOffersM.getJSONObject(i);
            Element offerSite = null;
            for (int j = 0; j < arrayOffersM.length(); j++) {
                Element e = jsonoffersSite.getElementsByClass("buy__item").get(j);
                String urlShops = e.getElementsByClass("metrik-target").get(0).attr("data-buy-url");
                if ((offerM.getString("url")).equals(urlShops)) {
                    offerSite = e;
                    break;
                } else {
                    if (j==(arrayOffersM.length()-1)) {
                        System.out.println("Ссылка " + offerM.getString("url") + " не найдена среди оферов РТ");
                        Assert.assertTrue(false);
                    }
                }
            }


            //Проверка равные ли названия магазинов
            System.out.println("Название магазина на сайте: " + offerSite.getElementsByClass("metrik-target").get(0).attr("data-shop-name"));
            System.out.println("Название магазина на мобигуру: " + offerM.getJSONObject("shopInfo").getString("name"));
            //Assert.assertEquals(offerM.getJSONObject("shopInfo").getString("name"), offerSite.getElementsByClass("metrik-target").get(0).attr("data-shop-name"));

            //Проверка равны ли ссылки магазинов
            System.out.println("Ссылка на сайте: " + offerSite.getElementsByClass("metrik-target").get(0).attr("data-buy-url"));
            System.out.println("Ссылка на мобигуру: " + offerM.getString("url"));
            //Assert.assertEquals(offerM.getString("url"), offerSite.getElementsByClass("metrik-target").get(0).attr("data-buy-url"));

            //Проверка равны ли цены
            System.out.println("Цена на сайте: " + offerSite.attr("data-price"));
            System.out.println("Цена на мобигуру: " + offerM.getString("price"));
            //Assert.assertEquals(offerM.getString("price"), offerSite.attr("data-price"));

        }

    }

    public static void  offerClickOnKupitDesktop(String url, String domain, String selector, WebDriver driver) {
        try {
            final String selector1 = selector;
            driver.get(domain + url);
            (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    Boolean b;
                    try {
                        b = d.findElement(By.xpath(selector1)).getText().length() != 0;
                    } catch (org.openqa.selenium.NoSuchElementException e) {
                        b = false;
                    }
                    return b;
                }
            });
            int numOffers = driver.findElements(By.xpath(selector1)).size();
            Random r = new Random();
            int randomNumber = r.nextInt(numOffers) + 1;

            //получаем правильный конечный url при переходе в магазин
            String urlNadavi = driver.findElement(By.xpath(selector1 + "[" + randomNumber + "]//a")).getAttribute("data-buy-url");
            ((JavascriptExecutor) driver).executeScript("window.open('','_blank');");
            //driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL + "t");
            switchToSecondHandle(driver);
            driver.get(urlNadavi);
            String expectedUrl = driver.getCurrentUrl();
            driver.close();
            switchToFirstHandle(driver);

            //Клик по лого
            driver.findElements(By.xpath(selector1 + "[" + randomNumber + "]//a[@data-buy-url]")).get(0).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            switchToSecondHandle(driver);
            String l = driver.getCurrentUrl();
            Assert.assertEquals(l,expectedUrl);
            driver.close();
            switchToFirstHandle(driver);

            //Клик по цене
            driver.findElements(By.xpath(selector1 + "[" + randomNumber + "]//a[@data-buy-url]")).get(1).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            switchToSecondHandle(driver);
            String l2 = driver.getCurrentUrl();
            Assert.assertEquals(l2, expectedUrl);
            driver.close();

        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Оферы не загрузились");
        }
    }
    public static void offerClickOnObzorDesktop(String url, String domain, String selector, WebDriver driver) {
        try {
            final String selector1 = selector;
            driver.get(domain + url);
            (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    Boolean b;
                    try {
                        b = d.findElement(By.xpath(selector1)).getText().length() != 0;
                    } catch (org.openqa.selenium.NoSuchElementException e) {
                        b = false;
                    }
                    return b;
                }
            });
            int numOffers = driver.findElements(By.xpath(selector1)).size()/2;
            Random r = new Random();
            int randomNumber = r.nextInt(numOffers) + 1;

            //получаем правильный конечный url при переходе в магазин
            String urlNadavi = driver.findElement(By.xpath(selector1 + "[" + randomNumber + "]//a")).getAttribute("data-buy-url");
            ((JavascriptExecutor) driver).executeScript("window.open('','_blank');");
           // driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL + "t");
            switchToSecondHandle(driver);
            driver.get(urlNadavi);
            String expectedUrl = driver.getCurrentUrl();
            driver.close();
            switchToFirstHandle(driver);

            //Клик по лого
                //Блок №1
                System.out.println("//div[@class='buy buy_content js-buy'][1]//div[@class='buy__list']" + selector1 + "[" + randomNumber + "]//div[@class='buy__name']//a[@data-buy-url]");
                driver.findElement(By.xpath("//div[@class='buy buy_content js-buy'][1]//div[@class='buy__list']" + selector1 + "[" + randomNumber + "]//div[@class='buy__name']//a[@data-buy-url]")).click();
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                switchToSecondHandle(driver);
                String l = driver.getCurrentUrl();
                Assert.assertEquals(l,expectedUrl);
                driver.close();
                switchToFirstHandle(driver);

                //Блок №2
                driver.findElements(By.xpath("//div[@class='buy buy_content js-buy'][2]//div[@class='buy__list']" + selector1 + "[" + randomNumber + "]//div[@class='buy__name']//a[@data-buy-url]")).get(0).click();
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                switchToSecondHandle(driver);
                String l1 = driver.getCurrentUrl();
                Assert.assertEquals(l1,expectedUrl);
                driver.close();
                switchToFirstHandle(driver);

            //Клик по цене
                //Блок №1
                System.out.println("//div[@class='buy buy_content js-buy'][1]//div[@class='buy__list']" + selector1 + "[" + randomNumber + "]//a[@class='buy__button button button_sm metrik-target']");
                driver.findElements(By.xpath("//div[@class='buy buy_content js-buy'][1]//div[@class='buy__list']" + selector1 + "[" + randomNumber + "]//a[@class='buy__button button button_sm metrik-target']")).get(1).click();
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                switchToSecondHandle(driver);
                String l2 = driver.getCurrentUrl();
                Assert.assertEquals(l2, expectedUrl);
                driver.close();
                switchToFirstHandle(driver);

                //Блок №2
                driver.findElements(By.xpath("//div[@class='buy buy_content js-buy'][2]//div[@class='buy__list']" + selector1 + "[" + randomNumber + "]//a[@class='buy__button button button_sm metrik-target']")).get(1).click();
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                switchToSecondHandle(driver);
                String l3 = driver.getCurrentUrl();
                Assert.assertEquals(l3, expectedUrl);
                driver.close();

        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Оферы не загрузились");
        }
    }

    public static void offerClickOnKharakteristikiDesktop(String url, String domain, String selector, WebDriver driver) {
        try {
            final String selector1 = selector;
            driver.get(domain + url);
            (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    Boolean b;
                    try {
                        b = d.findElement(By.xpath(selector1)).getText().length() != 0;
                    } catch (org.openqa.selenium.NoSuchElementException e) {
                        b = false;
                    }
                    return b;
                }
            });

            //получаем правильный конечный url при переходе в магазин
            String urlNadavi = driver.findElement(By.xpath(selector1 + "[1]//a")).getAttribute("data-buy-url");
            ((JavascriptExecutor) driver).executeScript("window.open('','_blank');");
            Thread.sleep(1000);
            switchToSecondHandle(driver);
            driver.get(urlNadavi);
            String expectedUrl = driver.getCurrentUrl();
            driver.close();
            switchToFirstHandle(driver);

            //Клик по лого
            driver.findElements(By.xpath(selector1 + "[1]//a[@data-buy-url]")).get(0).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            switchToSecondHandle(driver);
            String l = driver.getCurrentUrl();
            Assert.assertEquals(l,expectedUrl);
            driver.close();
            switchToFirstHandle(driver);

            //Клик по названию
            driver.findElements(By.xpath(selector1 + "[1]//a[@data-buy-url]")).get(1).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            switchToSecondHandle(driver);
            String l4 = driver.getCurrentUrl();
            Assert.assertEquals(l4,expectedUrl);
            driver.close();
            switchToFirstHandle(driver);

            //Клик по кнопке "В магазин"
            driver.findElements(By.xpath(selector1 + "[1]//a[@data-buy-url]")).get(2).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            switchToSecondHandle(driver);
            String l5 = driver.getCurrentUrl();
            Assert.assertEquals(l5,expectedUrl);
            driver.close();
            switchToFirstHandle(driver);

            //Клик по кнопке "Купить за..."
            driver.findElement(By.xpath("//a[@id='buy_button']")).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            switchToSecondHandle(driver);
            String l6 = driver.getCurrentUrl();
            Assert.assertEquals(l6,expectedUrl);
            driver.close();
            switchToFirstHandle(driver);

        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Оферы не загрузились");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void offerClickOnKharakteristikiMobile(String url) {
        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", "Nexus 5");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        WebDriver driver = new ChromeDriver(chromeOptions);
        try {
            driver.manage().window().setSize(new Dimension(360, 640));
            final String selector1 = "//a[@data-buy-url]";
            driver.get(url);
            (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    Boolean b;
                    try {
                        b = d.findElement(By.xpath(selector1)).getText().length() != 0;
                    } catch (org.openqa.selenium.NoSuchElementException e) {
                        b = false;
                    }
                    return b;
                }
            });

            //получаем правильный конечный url при переходе в магазин
            String urlNadavi = driver.findElement(By.xpath(selector1)).getAttribute("data-buy-url");
            ((JavascriptExecutor) driver).executeScript("window.open('','_blank');");
            Thread.sleep(1000);
            switchToSecondHandle(driver);
            driver.get(urlNadavi);
            String expectedUrl = driver.getCurrentUrl();
            driver.close();
            switchToFirstHandle(driver);

            //Клик по кнопке "Купить за..."
            driver.findElement(By.xpath(selector1)).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            switchToSecondHandle(driver);
            String l7 = driver.getCurrentUrl();
            Assert.assertEquals(l7,expectedUrl);
            driver.close();
            switchToFirstHandle(driver);

        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Оферы не загрузились");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void geoOffers(WebDriver driver,String selector) {
    String delivery = driver.findElement(By.xpath(selector + "//div[@class='buy__delivery buy__delivery__norating']")).getText();
    Assert.assertEquals(delivery, "по Казани");
    }

    public static void getParameterCity(WebDriver driver) {
        String url = driver.getCurrentUrl();
        Pattern p = Pattern.compile("(.*)?\\?city=Казань");
        Matcher m = p.matcher(url);
        Assert.assertTrue(m.matches());
    }

    public static void geoCookie(WebDriver driver) {
        String cookie = driver.manage().getCookieNamed("regionName").getValue();
        Assert.assertEquals(cookie, "Казань");
    }

    public static void offersParentOnKupit(String url, String selector) throws SQLException, IOException, JSONException {
        Pattern p = Pattern.compile("https:\\/\\/.*?\\.ru\\/.*?\\/(.*?)\\/kupit");
        Matcher m = p.matcher(url);
        String url1 = "";
        if (m.find()) {
        url1 = m.group(1);
        }
        System.out.println(url1);
        ResultSet rs = SQLConnect().executeQuery("SELECT NadaviId FROM dbo.Products WHERE ParentId IN (SELECT Id FROM dbo.Products WHERE Url='" + url1 + "')");
        ArrayList <String> l = new ArrayList();
        while (rs.next()) {
            l.add(rs.getString(1));
        }

        driver.get(url);
        ArrayList <String> urls = new ArrayList<String>();
        for (int i = 0; i <l.size(); i++) {
            JSONObject obj = new JSONObject(getOffersFromNadavi(l.get(i), getCityIdFromCityName("Казань")));
            for (int j = 0; j < obj.getJSONArray("offers").length(); j++) {
                JSONObject obj1 = (JSONObject) obj.getJSONArray("offers").get(j);
                urls.add(obj1.getString("BuyUrl"));
            }
        }

        List <WebElement> elements = driver.findElements(By.xpath(selector + "/div[@class='buy__col'][1]//a[@data-buy-url]"));
        ArrayList <String> urlsOnPage = new ArrayList<String>();
        for (int k = 0; k < elements.size(); k++) {
            String g = elements.get(k).getAttribute("data-buy-url");
            if(g.contains("https:")) {
                urlsOnPage.add("http:" + g.substring(6));
            }
            System.out.println("http:" + g.substring(6));
        }
        for (int n = 0; n < urls.size(); n++) {
            if (!urlsOnPage.contains(urls.get(n))) {
                System.out.println("Офера с ссылкой " + urls.get(n) + " нет на странице");
                //System.out.println(urlsOnPage.get(n));
            }
        }
    }

    public static void offersOnKupitForBots(String url, String domain, String selector) {

        List <String> userAgentString = new ArrayList<String>();
        userAgentString.add("Mozilla/5.0 (compatible; YandexBot/3.0)");
        userAgentString.add("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
        userAgentString.add("Googlebot/2.1 (+http://www.google.com/bot.html)");

        driver.get(domain + url + "?city=Москва");
        boolean b = driver.findElements(By.xpath(selector)).size()>0;
        driver.quit();

        if (b) {
            WebDriver driver1;
            for (int i = 0; i < userAgentString.size(); i++) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--user-agent=" + userAgentString.get(i));
                driver1 = new ChromeDriver(options);

                driver1.get(domain + url);
                System.out.println("Кол-во оферов на странице " + driver1.findElements(By.xpath(selector)).size());
                if (driver1.findElements(By.xpath(selector)).size()==0) {
                    System.out.println("На странице " + url + " нет оферов для бота " + userAgentString.get(i));
                    Assert.assertTrue(false);
                }
                driver1.quit();
            }
        } else {
            System.out.println("На странице " + url + " нет оферов для Москвы");
        }
    }

    public static void forBotsIsNoParameterCity(String url, String domain) {
        driver.get(domain + url);
        String endUrl = driver.getCurrentUrl();
        Pattern p = Pattern.compile("\\?city=");
        Matcher m = p.matcher(endUrl);
        if (m.find()) {
            System.out.println("В урле содержится параметр city - " + endUrl );
            Assert.assertTrue(false);
        }
    }

    public static void forBotsNameShopIsTransferred(String url, String domain, String selector) {
        driver.get(domain + url);
        driver.findElements(By.xpath(selector));
        if (driver.findElements(By.xpath(selector)).size()==0) {
            System.out.println("На странице нет оферов");
        } else {
            if (driver.findElements(By.xpath(selector + "//a[@data-shop-url]")).size()==0) {
                System.out.println("На странице " + url + " нет названия магазина");
            }
        }
    }

    public static void showMore(String url, String domain, String selector) throws SQLException, IOException, XPatherException, InterruptedException {
        Pattern p = Pattern.compile("\\/.*?\\/(.*?)\\/kupit");
        Matcher m = p.matcher(url);
        String url1 = "";
        if (m.find()) {
            url1 = m.group(1);
        }

        Pattern p1 = Pattern.compile("\\?city=(.+)");
        Matcher m1 = p1.matcher(url);
        String city = "";
        if (m1.find()) {
            city = m1.group(1);
        } else {
            city = "Казань";
        }
        System.out.println(url1);
        System.out.println(city);
        String geoId = "";
        if (city.equals("Москва")) {
            geoId="213";
        } if (city.equals("Санкт-Петербург")) {
            geoId="2";
        }if (city.equals("Казань")) {
            geoId="11119";
        }

        String idProduct = getIdFromUrl(url1);
        HtmlCleaner cleaner = new HtmlCleaner();
        TagNode html = cleaner.clean(getOffersFromPT(domain, idProduct, geoId));
        Object[] offers =  html.evaluateXPath(selector);
        if (offers.length<11) {
            System.out.println("Оферов для товара меньше 11");
        } else {
            driver.get(domain + url);
            List <WebElement> list = driver.findElements(By.xpath(selector));
            int n=0;
            for (WebElement e : list) {
                if (e.getCssValue("display").equals("block")) {
                    n++;
                }
            }
            System.out.println("Кол-во оферов на странице " + n);
            if (n>10) {
                System.out.println("На странице отображены более 10 оферов");
                Assert.assertTrue(false);
            } else {
                if (driver.findElements(By.xpath("//a[@id='load-more-buy-link']")).size()==0) {
                    System.out.println("Не найдена кнопка 'Показать еще'");
                    Assert.assertTrue(false);
                } else  {
                    WebElement loadMore = driver.findElement(By.id("load-more-buy-link"));
                    loadMore.click();
//                    Actions actions = new Actions(driver);
//                    actions.moveToElement(loadMore).click().build().perform();
                    //driver.findElement(By.id("load-more-buy-link")).click();
                    List <WebElement> list2 = driver.findElements(By.xpath(selector));
                    int n2=0;
                    for (WebElement e : list2) {
                        if (e.getCssValue("display").equals("block")) {
                            n2++;
                        }
                    }
                    System.out.println("Кол-во оферов на странице после нажатия на 'Показать еще '" + n2);
                    if (n2<11||n2>20) {
                        System.out.println("Кол-во оферов после нажатия на 'Показать еще' равно " + n2);
                        Assert.assertTrue(false);
                    }
                }
            }

        }

    }

    @Test
    public static void main1 () throws SQLException, InterruptedException, JSONException, IOException, XPatherException {

        //driver = new ChromeDriver();
        //driver.manage().window().maximize();
        // main("5246", "Москва");
        // offersMobiguruAreEqualToOffersOnSite("2139", "Москва");
        //offersNadaviAreEqualToOffersOnSite("5246", "Москва");
         //offerClickOnObzorDesktop("https://product-test.ru/smartfony/xiaomi-redmi-note-5a/obzor", "//div[@class='buy__item']");
        // offersParentOnKupit("https://product-test.ru/smartfony/apple-iphone-6s/kupit", "//div[@class='buy__item']");
        //System.out.println(getOffersFromPT("3568","Казань"));
        //offersMobiguruAreEqualToOffersOnSite("/smartfony/samsung-galaxy-s8-plus/kupit?city=Москва", "https://product-test.ru");
        //offersOnKupitForBots("/smartfony/samsung-galaxy-s8-plus/kupit", "https://product-test.ru", "//div[@class='buy__item']");
        //offersOnKupitForBots("/smartfony/huawei-p10-plus/kupit", "https://product-test.ru", "//div[@class='buy__item']");
        //offerClickOnKharakteristikiMobile("https://product-test.ru/smartfony/apple-iphone-7-32gb/kharakteristiki?city=Москва");
        //showMore("/smartfony/apple-iphone-7/kupit?city=Москва", "https://product-test.ru", "//div[@class='buy__item']");
       // driver = new ChromeDriver();
       // offerClick("https://product-test.ru/smartfony/samsung-galaxy-s8-plus/kupit");

    }

}
