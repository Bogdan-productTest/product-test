package Assert;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class SEO {

    static Workbook workbook;
    static Sheet sheet;
    static Row row;
    static String name = "Seo.xls";
    static Object[][] array;
    static WebDriver driver;

    @BeforeClass
    public static void parseXLS() throws IOException {

        // Read XSL file
        FileInputStream inputStream = new FileInputStream(new File(name));

        // Get the workbook instance for XLS file
        workbook = new HSSFWorkbook(inputStream);

        // Get first sheet from the workbook
        sheet = workbook.getSheetAt(0);

        array = new Object[sheet.getLastRowNum() + 1][9];
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();
        row = rowIterator.next();

        for (int i = 0; i < sheet.getLastRowNum()-1; i++) {
            //System.out.println(sheet.getLastRowNum());
            row = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell c;
            for (int j = 0; j<9; j++) {
                c = cellIterator.next();
                array[i][j] = c.toString();
                //System.out.println(c);
            }
        }
        System.out.println("количество строк равно " + sheet.getLastRowNum());
        System.out.println("парсинг xls завершен");
        //  ChromeOptions options = new ChromeOptions();
        //  options.addArguments("--headless");
        driver = new ChromeDriver(
                //  options
        );
    }

    @DataProvider(name = "test")
    public static Object[][] credentials() {
        return array;
    }

    @Test(dataProvider = "test")
    public void assertSeo(String url, String title, String ogTitle, String site, String type, String image, String description, String ogDescription, String keywords) {

        System.out.println("url равен " + url);
        System.out.println("title равен " + title);
        System.out.println("ogTitle равен " + ogTitle);
        System.out.println("site равен " + site);
        System.out.println("type равен " + type);
        System.out.println("image равен " + image);
        System.out.println("description равен " + description);
        System.out.println("ogDescription равен " + ogDescription);
        System.out.println("keywords равен " + keywords);

        driver.get(url);

        //проверка title
        Assert.assertEquals(driver.findElement(By.tagName("title")).getAttribute("innerText"), title);

        //проверка SEO и микроразметки
        List<WebElement> listOG = driver.findElements(By.tagName("meta"));

        for (WebElement assertOG : listOG) {
            try {
                String property = assertOG.getAttribute("property");
                String name = assertOG.getAttribute("name");
                if (property.equals("og:url")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), url);
                } else if (property.equals("og:title")) {
                    Assert.assertTrue(
                            assertOG.getAttribute("content").equals(title) ||
                                    assertOG.getAttribute("content").equals(ogTitle));
                } else if (property.equals("og:site_name")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), site);
                } else if (property.equals("og:type")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), type);
                } else if (property.equals("og:description")) {
                    Assert.assertTrue(
                            assertOG.getAttribute("content").equals(ogDescription) ||
                                    assertOG.getAttribute("content").equals(description));
                } else if (name.equals("description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), description);
                } else if (name.equals("Keywords")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), keywords);
                    //} else if (property.equals("og:image")) {
                    //    Assert.assertEquals(assertOG.getAttribute("content"), image);
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
    }
}
