package Assert;

import Assert.Pages.CategoryPage;
import Assert.Pages.ProductPage;
import Assert.Pages.StaticPage;
import Assert.Pages.TypePage;
import Screenshots.Product;
import com.google.common.base.Verify;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Assert.Pages.TypePage.textToRegex;
import static Assert.SQLQueries.getSqlResult;

public class SEO {

    static Workbook workbook;
    static Sheet sheet;
    static String name = "Seo/PatternSeo.xls";
    static Object[][] array;
    static String domainStage;
    static String domainProd;
    static String domain;
    static String domainProdStringPT = "product-test.ru";
    static String domainStageStringPT = "staging.product-test.ru";
    static String domainProdStringRS = "rus-supermarket.ru";
    static String domainStageStringRS = "rusupermarket-staging.azurewebsites.net";
    static String domainProdStringVM = "vse-magazini.ru";
    static String domainStageStringVM = "vse-magaziny-staging.azurewebsites.net";
    static String domainProdStringP = "pochom.ru";
    static String domainStageStringP = "pochem-staging.azurewebsites.net";


    @BeforeClass
    public static void parseListUrlForTest() throws IOException {

        FileInputStream inputStream = new FileInputStream(new File("Seo/UrlForSeoPT.xls"));

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

    public static Object getObjectTypePage(String url) throws SQLException {

        Object obj;

        Pattern patternHomePage = Pattern.compile("^\\/$");
        Pattern patternProduct = Pattern.compile("\\/(obzor|kupit|kharakteristiki)");
        Pattern patternStatic = Pattern.compile("^\\/(tests|pages|news)");

        if (patternHomePage.matcher(url).matches()||patternStatic.matcher(url).find()) {
            System.out.println("Статичная страница " + url);
            obj = new StaticPage();
        } else if (patternProduct.matcher(url).find()) {
            System.out.println("Товарная страница " + url);
            obj = new ProductPage();
        } else {
            System.out.println("Категорийная страница " + url);
            obj = new CategoryPage();
        }
        return obj;
    }

    public static int getSheet(String site) {

        int sheet=-1;

        if (site.equals("PT")) {
            sheet = 0;
        } else if (site.equals("RS")) {
            sheet = 1;
        } else if (site.equals("VM")) {
            sheet = 2;
        } else if (site.equals("P")) {
            sheet = 3;
        }
    return sheet;
    }

    public static String getPattern(int numSheet, int numRow, int numCell, String url) throws SQLException {
        String pattern = "";
        if (numRow<18) {
            sheet = workbook.getSheetAt(numSheet);
            pattern = sheet.getRow(numRow).getCell(numCell).toString();
        } else {
            switch (numRow) {
                case 18:
                    switch (numCell) {
                        case 1:
                            pattern = "^" + textToRegex(
                                    getSqlResult("SELECT Title FROM dbo.Seos WHERE TabType=6 AND ProductId='" + getSqlResult("SELECT Id FROM dbo.Products WHERE url='" + url.substring(url.indexOf("/",1)+ 1, (url.indexOf("/", (url.indexOf("/",1) + 1)))) + "'" ) + "'")
                            ) + "$";
                            break;
                        case 2:
                            pattern = "^" + textToRegex(getSqlResult("SELECT MetaDescription FROM dbo.Seos WHERE TabType=6 AND ProductId='" + getSqlResult("SELECT Id FROM dbo.Products WHERE url='" + url.substring(url.indexOf("/",1) + 1, (url.indexOf("/", (url.indexOf("/",1) + 1)))) + "'") + "'")) + "$";
                            break;
                        case 3:
                            pattern = "^" + textToRegex(getSqlResult("SELECT H1 FROM dbo.Seos WHERE TabType=6 AND ProductId='" + getSqlResult("SELECT Id FROM dbo.Products WHERE url='" + url.substring(url.indexOf("/",1) + 1, (url.indexOf("/", (url.indexOf("/",1) + 1)))) + "'") + "'")) + ": Обзор$";
                            break;
                        case 4:
                            pattern = "^" + textToRegex(getSqlResult("SELECT Keywords FROM dbo.Seos WHERE TabType=6 AND ProductId='" + getSqlResult("SELECT Id FROM dbo.Products WHERE url='" + url.substring(url.indexOf("/",1) + 1, (url.indexOf("/", (url.indexOf("/",1) + 1)))) + "'") + "'")) + "$";
                            break;
                    }
                    break;
                case 19:
                    switch (numCell) {
                        case 1:
                            pattern = "^" + textToRegex(getSqlResult("SELECT MetaHelper FROM dbo.Categories WHERE Url='" + url.substring(1, url.indexOf("/", 1)) + "'")) + "$";
                            break;
                        case 2:
                            pattern = "^" + textToRegex(getSqlResult("SELECT MetaDescriptionHelper FROM dbo.Categories WHERE Url='" + url.substring(1, url.indexOf("/", 1)) + "'")) + "$";
                            break;
                        case 3:
                            pattern = "^" + textToRegex(getSqlResult("SELECT H1Helper FROM dbo.Categories WHERE Url='" + url.substring(1, url.indexOf("/", 1)) + "'")) + "$";
                            break;
                        case 4:
                            pattern = "";
                            break;
                    }
                    break;
                case 20:
                    switch (numCell) {
                        case 1:
                            pattern = "^" + textToRegex(getSqlResult("SELECT PageTitle FROM dbo.Categories WHERE Url='" + url.substring(1) + "'")) + "$";
                            break;
                        case 2:
                            pattern = "^" + textToRegex(getSqlResult("SELECT Meta FROM dbo.Categories WHERE Url='" + url.substring(1) + "'")) + "$";
                            break;
                        case 3:
                            pattern = "^" + textToRegex(getSqlResult("SELECT H1Mainer FROM dbo.Categories WHERE Url='" + url.substring(1) + "'")) + "$";
                            break;
                        case 4:
                            pattern = "";
                            break;
                    }
                    break;
                case 21:
                    switch (numCell) {
                        case 1:
                            pattern = "^" + textToRegex(getSqlResult("SELECT MetaRating FROM dbo.Categories WHERE Url='" + url.substring(1, url.indexOf("/", 1)) + "'")) + "$";
                            break;
                        case 2:
                            pattern = "^" + textToRegex(getSqlResult("SELECT MetaDescriptionRating FROM dbo.Categories WHERE Url='" + url.substring(1, url.indexOf("/", 1)) + "'")) + "$";
                            break;
                        case 3:
                            pattern = "^" + textToRegex(getSqlResult("SELECT H1Rating FROM dbo.Categories WHERE Url='" + url.substring(1, url.indexOf("/", 1)) + "'")) + "$";
                            break;
                        case 4:
                            pattern = "";
                            break;
                    }
                    break;
            }
        }
        return pattern;
    }

    public static boolean regexReturnTrue(String pattern, String text) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        return m.matches();
    }

    public static String modifyPattern(String pattern, TypePage page) throws SQLException {
        String modifyPattern = pattern;
        while (true) {
            if (modifyPattern.indexOf("{Category}")!=-1){
                System.out.println("Вхождение {Category} найдено");
                String firstSubstring = modifyPattern.substring(0, modifyPattern.indexOf("{Category}"));
                String secondSubstring = modifyPattern.substring("{Category}".length() + firstSubstring.length());
                modifyPattern = firstSubstring + textToRegex(page.getCategoryName()) + secondSubstring;
                continue;
            } if (modifyPattern.indexOf("{Name}")!=-1){
                System.out.println("Вхождение {Name} найдено");
                String firstSubstring = modifyPattern.substring(0, modifyPattern.indexOf("{Name}"));
                String secondSubstring = modifyPattern.substring("{Name}".length() + firstSubstring.length());
                modifyPattern = firstSubstring + textToRegex(page.getFullNameEn()) + secondSubstring;
                continue;
            } if (modifyPattern.indexOf("{Имя}")!=-1){
                System.out.println("Вхождение {Имя} найдено");
                String firstSubstring = modifyPattern.substring(0, modifyPattern.indexOf("{Имя}"));
                String secondSubstring = modifyPattern.substring("{Имя}".length() + firstSubstring.length());
                modifyPattern = firstSubstring + textToRegex(page.getFullNameRus()) + secondSubstring;
                continue;
            } if (modifyPattern.indexOf("{Category ед.ч. род.п.}") != -1) {
                String firstSubstring = modifyPattern.substring(0, modifyPattern.indexOf("{Category ед.ч. род.п.}"));
                String secondSubstring = modifyPattern.substring("{Category ед.ч. род.п.}".length() + firstSubstring.length());
                modifyPattern = firstSubstring + textToRegex(page.getAccusativeSingularRusName()) + secondSubstring;
                continue;
            } if (modifyPattern.indexOf("{Category мн.ч. род.п.}") != -1) {
                String firstSubstring = modifyPattern.substring(0, modifyPattern.indexOf("{Category мн.ч. род.п.}"));
                String secondSubstring = modifyPattern.substring("{Category мн.ч. род.п.}".length() + firstSubstring.length());
                modifyPattern = firstSubstring + textToRegex(page.getCategoryGenitiveName()) + secondSubstring;
                continue;
            } if (modifyPattern.indexOf("{Category ед.ч.}") != -1) {
                String firstSubstring = modifyPattern.substring(0, modifyPattern.indexOf("{Category ед.ч.}"));
                String secondSubstring = modifyPattern.substring("{Category ед.ч.}".length() + firstSubstring.length());
                modifyPattern = firstSubstring + textToRegex(page.getCategorySingularName()) + secondSubstring;
                continue;
            } if (modifyPattern.indexOf("{Category}") != -1) {
                String firstSubstring = modifyPattern.substring(0, modifyPattern.indexOf("{Category}"));
                String secondSubstring = modifyPattern.substring("{Category}".length() + firstSubstring.length());
                modifyPattern = firstSubstring + textToRegex(page.getCategoryName()) + secondSubstring;
                continue;
            } if (modifyPattern.indexOf("{Brand}") != -1) {
                String firstSubstring = modifyPattern.substring(0, modifyPattern.indexOf("{Brand}"));
                String secondSubstring = modifyPattern.substring("{Brand}".length() + firstSubstring.length());
                modifyPattern = firstSubstring + textToRegex(page.getBrandEn()) + secondSubstring;
                continue;
            } if (modifyPattern.indexOf("{Бренд}") != -1) {
                String firstSubstring = modifyPattern.substring(0, modifyPattern.indexOf("{Бренд}"));
                String secondSubstring = modifyPattern.substring("{Бренд}".length() + firstSubstring.length());
                modifyPattern = firstSubstring + textToRegex(page.getBrandRus()) + secondSubstring;
                continue;
            }
            break;
        }

        return modifyPattern;
    }

    @Test(dataProvider = "test")
    public static void assertSeo(String url, String site) throws IOException, SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        System.out.println("Проверяем для страницы " + url + " и сайта " + site);

        if (site.equals("PT")){
            domainProd = domainProdStringPT;
            domainStage = domainStageStringPT;
        } else if (site.equals("RS")){
            domainProd = domainProdStringRS;
            domainStage = domainStageStringRS;
        } else if (site.equals("VM")){
            domainProd = domainProdStringVM;
            domainStage = domainStageStringVM;
        } else if (site.equals("P")){
            domainProd = domainProdStringP;
            domainStage = domainStageStringP;
        }

        //если нужно проверить прод
       // domain = domainProd;

        //если нужно проверить стейдж
          domain = domainStage;

        String urlFull = "https://" + domain + url;
        System.out.println("Проверка страницы: " + urlFull);

        Document doc = Jsoup.connect(urlFull).timeout(10000).get();

            TypePage page = (TypePage) getObjectTypePage(url);
            page.setUrl(url);
            page.getProduct();
            page.getCategory();

            //Проверка title
            System.out.println("Проверка title");
            System.out.println(page.getRow());
           String pattern =  getPattern(getSheet(site), page.getRow(), 1, url);
            System.out.println("Изменяем следующий паттерн " + pattern);
           pattern = modifyPattern(pattern, page);
            System.out.println("Паттерн изменен на " + pattern);

            String titleActual = doc.getElementsByTag("title").get(0).text();
            System.out.println("Актуальное значение равно " + titleActual);

            //Verify.verify(Pattern.compile(pattern.toLowerCase()).matcher(titleActual.toLowerCase()).matches());

        //Проверка description
        System.out.println("Проверка description");
        pattern =  getPattern(getSheet(site), page.getRow(), 2, url);
        System.out.println("Изменяем следующий паттерн " + pattern);
        pattern = modifyPattern(pattern, page);
        System.out.println("Паттерн изменен на " + pattern);

        String descriptionActual = doc.getElementsByAttributeValue("name","description").get(0).attr("content");
        System.out.println("Актуальное значение равно " + descriptionActual);
        //Verify.verify(Pattern.compile(pattern.toLowerCase()).matcher(descriptionActual.toLowerCase()).matches());


        //Проверка H1
        System.out.println("Проверка h1");
        pattern =  getPattern(getSheet(site), page.getRow(), 3, url);
        System.out.println("Изменяем следующий паттерн " + pattern);
        pattern = modifyPattern(pattern, page);
        System.out.println("Паттерн изменен на " + pattern);

        String h1Actual = doc.getElementsByTag("h1").get(0).text();
        System.out.println("Актуальное значение равно " + h1Actual);
        //Verify.verify(Pattern.compile(pattern.toLowerCase()).matcher(h1Actual.toLowerCase()).matches());

        //Проверка keywords
        System.out.println("Проверка keywords");
        pattern =  getPattern(getSheet(site), page.getRow(), 4, url);
        System.out.println("Изменяем следующий паттерн " + pattern);
        pattern = modifyPattern(pattern, page);
        System.out.println("Паттерн изменен на " + pattern);

        String keywordsActual = doc.getElementsByAttributeValue("name","Keywords").get(0).attr("content");
        System.out.println("Актуальное значение равно " + keywordsActual);

/**
        //Проверка title
        String titleActual = doc.getElementsByTag("title").get(0).text();
        System.out.println("Проверка title страницы");
        Verify.verify(regexReturnTrue(getPattern(0,1,1), titleActual));

        //Проверка description
        String descriptionActual = doc.getElementsByAttributeValue("name","description").get(0).attr("content");
        System.out.println("Проверка description страницы");
        Verify.verify(regexReturnTrue(description, descriptionActual));
**/

        }
    }

