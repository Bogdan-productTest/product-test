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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Assert.Methods.*;

import static Assert.DatabaseConnection.SQLConnect;

public class SEO {

    static Workbook workbook;
    static Sheet sheet;
    static Row row;
    static String name = "Seo/PatternSeo.xls";
    static Object[][] array;
    static WebDriver driver;
    static String domainStage;
    static String domainProd;
    static String domain;


    @BeforeTest
    @Parameters({"domainProd", "domainStage"})
    public static void getDomain(String domainProdString, String domainStageString) {
       domainProd = domainProdString;
       domainStage = domainStageString;

       //если нужно проверить прод
     //  domain = domainProd;

       //если нужно проверить стейдж
         domain = domainStage;
    }


    @BeforeClass
    public static void parseListUrlForTest() throws IOException {

        FileInputStream inputStream = new FileInputStream(new File("Seo/UrlForSeo.xls"));

        Workbook workbook1 = new HSSFWorkbook(inputStream);
        Sheet sheet1 = workbook1.getSheetAt(0);

        //инициализируем итератор
        Iterator<Row> rowIterator = sheet1.iterator();

        //определяем размер массива
        array = new Object[sheet1.getLastRowNum()+1][2];

        //проходим по всем строкам файла
        for (int i = 0; i < sheet1.getLastRowNum()+1; i++) {

            Row row1 = rowIterator.next();

            Iterator<Cell> cellIterator = row1.cellIterator();
            Cell c;
            for (int j = 0; j<2; j++) {
                c = cellIterator.next();
                array[i][j] = c.toString();
            }
        }
    }



    @BeforeClass
    public static void initPatternFile() throws IOException {

        FileInputStream inputStream = new FileInputStream(new File(name));
        workbook = new HSSFWorkbook(inputStream);

    }

    @DataProvider(name = "test")
    public static Object[][] getListUrl() {
        return array;
    }

    public static void getCategory(String url) throws SQLException {

        Pattern p = Pattern.compile("pages|news|tests");
        Matcher m = p.matcher(url);

        if (!m.find()) {
            String urlCategory ="";
            System.out.println(url.length());
            if (url.length()>1) {
                if (url.indexOf("/", 1)<0) {
                    urlCategory = url.substring(1);
                } else {
                    urlCategory = url.substring(1, url.indexOf("/", 1));
                    //  SQLConnect().executeQuery();
                }
            } else {
                System.out.println("Это главная страница");
            }
            System.out.println(urlCategory);
        }
    }

    public static String getPattern(int numSheet, int numRow, int numCell) {
        sheet = workbook.getSheetAt(numSheet);
        return sheet.getRow(numRow).getCell(numCell).toString();
    }

    public static boolean regexReturnTrue(String pattern, String text) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        return m.matches();
    }


    @Test(dataProvider = "test")
    public static void assertSeo(String url, String site) throws IOException, SQLException {

        url = "https://" + domain + url;
        System.out.println("Проверка страницы: " + url);

        Document doc = Jsoup.connect(url).timeout(10000).get();
//        String ogTitleActual = doc.getElementsByAttributeValue("property","og:title").get(0).attr("content");
//        System.out.println(ogTitleActual);

        getCategory("/");
        getCategory("/smartfony/test");
        getCategory("/smartfony");
        getCategory("/pages");
        getCategory("/tests");
        getCategory("/news");
        getCategory("/pages/test");

        //Проверка title
        String titleActual = doc.getElementsByTag("title").get(0).text();
        System.out.println("Проверка title страницы");
        Verify.verify(regexReturnTrue(getPattern(0,1,1), titleActual));
/**
        //Проверка description
        String descriptionActual = doc.getElementsByAttributeValue("name","description").get(0).attr("content");
        System.out.println("Проверка description страницы");
        Verify.verify(regexReturnTrue(description, descriptionActual));
**/

        }
    }

