package Assert;

import com.google.common.base.Verify;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class SEO {

    static Workbook workbook;
    static Sheet sheet;
    static Row row;
    static String name = "";
    static Object[][] array;
    static WebDriver driver;
    static String domainStage;
    static String domainProd;
    static String domain;

    @BeforeSuite
    public static void initDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(
                  options
        );
    }

    @AfterSuite
    public static void tearDown() {
        driver.quit();
    }

    @BeforeTest
    @Parameters({"domainProd", "domainStage"})
    public static void getDomain(String domainProdString, String domainStageString) {
       domainProd = domainProdString;
       domainStage = domainStageString;

       //если нужно проверить прод
       domain = domainProd;

       //если нужно проверить стейдж
       //  domain = domainStage;
    }



    @BeforeClass
    @Parameters("domainProd")
    public static void parseXLS(String domainProdString) throws IOException {

        if (domainProdString.equals("product-test.ru")) {
            name = "SeoPT.xls";
        } else if (domainProdString.equals("rus-supermarket.ru")) {
            name = "SeoRS.xls";
        } else if (domainProdString.equals("vse-magazini.ru")) {
            name = "SeoVM.xls";
        } else if (domainProdString.equals("pochom.ru")) {
            name = "SeoP.xls";
        }


            FileInputStream inputStream = new FileInputStream(new File(name));

        workbook = new HSSFWorkbook(inputStream);
        sheet = workbook.getSheetAt(0);

        array = new Object[sheet.getLastRowNum() - 1][10];

        Iterator<Row> rowIterator = sheet.iterator();
        row = rowIterator.next();

        for (int i = 0; i < sheet.getLastRowNum()-1; i++) {

            row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();
            Cell c;
            for (int j = 0; j<9; j++) {
                c = cellIterator.next();
                array[i][j] = c.toString();

            }
        }
        System.out.println("количество строк равно " + sheet.getLastRowNum());
        System.out.println("парсинг xls завершен");

    }

    @DataProvider(name = "test")
    public static Object[][] credentials() {
        return array;
    }


    public void assertTitle(String title) {

        System.out.println("Проверка title, ожидается " + title);
        Verify.verify(driver.findElement(By.tagName("title")).getAttribute("innerText").equals(title));
        System.out.println("успешно");

    }

    public void assertH1(String h1) {

        System.out.println("Проверка H1, ожидается " + h1);
        Verify.verify(driver.findElement(By.tagName("h1")).equals(h1));
        System.out.println("успешно");

    }
/**
    public void assertDescription(String description) {

        System.out.println("Проверка description, ожидается " + description);
        Verify.verify(assertOG.getAttribute("content").equals( description));
        System.out.println("успешно");

    }

    public void assertOgTitle(String title, String ogTitle) {

        System.out.println("Проверка og:title, ожидается " + ogTitle + " или " + title);
        Verify.verify(assertOG.getAttribute("content").equals(title) ||
                assertOG.getAttribute("content").equals(ogTitle));
        System.out.println("успешно");
    }

    public void assertOgDescription(String description, String ogDescription) {

        System.out.println("Проверка og:description, ожидается " + ogDescription + " или " + description);
        Verify.verify(assertOG.getAttribute("content").equals(ogDescription) ||
                assertOG.getAttribute("content").equals(description));
        System.out.println("успешно");
    }

    public void assertOgUrl(String url) {

        System.out.println("Проверка og:url, ожидается " + url);
        Verify.verify(assertOG.getAttribute("content").equals( url));
        System.out.println("успешно");
    }

    public void assertOgSite(String site) {

        System.out.println("Проверка og:site_name, ожидается " + site);
        Verify.verify(assertOG.getAttribute("content").equals( site));
        System.out.println("успешно");

    }

    public void assertOgType(String type) {

        System.out.println("Проверка og:type, ожидается " + type);
        Verify.verify(assertOG.getAttribute("content").equals( type));
        System.out.println("успешно");

    }

    public void assertOgImage(String image) {

        System.out.println("Проверка og:image, ожидается " + image);
        Verify.verify(assertOG.getAttribute("content").equals(image));
        System.out.println("успешно");

    }

    public void assertKeywords(String keywords) {

        System.out.println("Проверка keywords, ожидается " + keywords);
        Verify.verify(assertOG.getAttribute("content").equals(keywords));
        System.out.println("успешно");

    }
**/






    @Test(dataProvider = "test")
    public void assertSeo(String url, String title, String ogTitle, String site, String type, String image, String description, String ogDescription, String keywords, String h1) throws IOException {


        Document doc = Jsoup.connect(url).get();

        System.out.println(doc.getElementsByAttributeValue("property","og:title").get(0).text());



        url = "https://" + domain + url;
        System.out.println("Переход на страницу: " + url);

//        System.out.println("url равен " + url);
//        System.out.println("title равен " + title);
//        System.out.println("ogTitle равен " + ogTitle);
//        System.out.println("site равен " + site);
//        System.out.println("type равен " + type);
//        System.out.println("image равен " + image);
//        System.out.println("description равен " + description);
//        System.out.println("ogDescription равен " + ogDescription);
//        System.out.println("keywords равен " + keywords);
//        System.out.println("h1 равен " + h1);

        driver.get(url);

        //проверка title
        System.out.println("Проверка title, ожидается " + title);
        Verify.verify(driver.findElement(By.tagName("title")).getAttribute("innerText").equals(title));
     //   Assert.assertEquals(driver.findElement(By.tagName("title")).getAttribute("innerText"), title);
        System.out.println("успешно");

        //проверка H1
        System.out.println("Проверка H1, ожидается " + h1);
        if (h1!=null) {
            Verify.verify(driver.findElement(By.tagName("h1")).equals(h1) );
       //     Assert.assertEquals(driver.findElement(By.tagName("h1")), h1);
        }
        System.out.println("успешно");

        //проверка SEO
        System.out.println("Проверка seo");
        List<WebElement> listOG = driver.findElements(By.tagName("meta"));

        for (WebElement assertOG : listOG) {

            String property = null;
            String name = null;
            try {
                property = assertOG.getAttribute("property");
                System.out.println("параметр property равен" + property);
                name = assertOG.getAttribute("name");
                System.out.println("параметр name равен" + name);

                if (property.equals("og:url")) {
                    System.out.println("Проверка og:url, ожидается " + url);
                    Verify.verify(assertOG.getAttribute("content").equals( url));
                  //  Assert.assertEquals(assertOG.getAttribute("content"), url);
                    System.out.println("успешно");
                } else if (property.equals("og:site_name")) {
                    System.out.println("Проверка og:site_name, ожидается " + site);
                    Verify.verify(assertOG.getAttribute("content").equals( site));
                    //    Assert.assertEquals(assertOG.getAttribute("content"), site);
                    System.out.println("успешно");
                } else if (property.equals("og:type")) {
                    System.out.println("Проверка og:type, ожидается " + type);
                    Verify.verify(assertOG.getAttribute("content").equals( type));
                    //    Assert.assertEquals(assertOG.getAttribute("content"), type);
                    System.out.println("успешно");
                } else if (name.equals("description")) {
                    System.out.println("Проверка description, ожидается " + description);
                    Verify.verify(assertOG.getAttribute("content").equals( description));
                    //    Assert.assertEquals(assertOG.getAttribute("content"), description);
                    System.out.println("успешно");
                } else if (name.equals("Keywords")) {
                    System.out.println("Проверка keywords, ожидается " + keywords);
                    Verify.verify(assertOG.getAttribute("content").equals( keywords));
                //    Assert.assertEquals(assertOG.getAttribute("content"), keywords);
                    System.out.println("успешно");

                    //} else if (property.equals("og:image")) {
                    //    Assert.assertEquals(assertOG.getAttribute("content"), image);
                } else if (property.equals("og:description")) {
                    System.out.println("Проверка og:description, ожидается " + ogDescription + " или " + description);
                    Verify.verify(assertOG.getAttribute("content").equals(ogDescription) ||
                            assertOG.getAttribute("content").equals(description));
//                    Assert.assertTrue(
//                            assertOG.getAttribute("content").equals(ogDescription) ||
//                                    assertOG.getAttribute("content").equals(description));
                    System.out.println("успешно");
                } else if (property.equals("og:title")) {
                    System.out.println("Проверка og:title, ожидается " + ogTitle + " или " + title);
                    Verify.verify(assertOG.getAttribute("content").equals(title) ||
                            assertOG.getAttribute("content").equals(ogTitle));
//                    Assert.assertTrue(
//                            assertOG.getAttribute("content").equals(title) ||
//                                    assertOG.getAttribute("content").equals(ogTitle));
                    System.out.println("успешно");
                }
            } catch (java.lang.NullPointerException e) {
                System.out.println("");
            }
        }
    }
}
