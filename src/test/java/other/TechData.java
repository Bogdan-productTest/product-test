package other;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class TechData {

    static HSSFSheet sheet1;
    static Object [][] o;
    static Row row1;
    static HSSFWorkbook workbook1;
    static String name = "проверка всех категорий";

    static String path = "C:/Users/Mirror/Desktop/TechData/";
    static WebDriver driver;

    @BeforeClass
    public static void parseXML() throws IOException {

        // Read XSL file
        FileInputStream inputStream = new FileInputStream(new File(path + name + ".xls"));

        // Get the workbook instance for XLS file
        workbook1 = new HSSFWorkbook(inputStream);

        // Get first sheet from the workbook
        sheet1 = workbook1.getSheetAt(0);

        o = new Object[sheet1.getLastRowNum()+1][2];
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet1.iterator();


        for (int i = 0; i<sheet1.getLastRowNum()+1; i++) {
            row1 = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row1.cellIterator();
            Cell c = cellIterator.next();
            o[i][0] = c.toString();
            o[i][1] = c.toString().substring(c.toString().indexOf("/", 1)+1, c.toString().length()-16);
            //    o[i][1] = i+1;
        }
        driver = new ChromeDriver();
    }


    @DataProvider(name = "url")
    public static Object[][] credentials() {
        return o;
    }

    protected void createActualScreenshot (String s, String n) throws IOException {
        driver.get("https://staging.product-test.ru" + s);
        Screenshot actualScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        File fileActual = new File( path + "actual/" + n + ".png");
        if (fileActual.exists() == false)
            fileActual.mkdirs();
        ImageIO.write(actualScreenshot.getImage(), "png", fileActual);


        File fileExpected = new File (path + "expected/" + n + ".png");
        if (fileExpected.exists() == false) {
            fileExpected.mkdirs();
            ImageIO.write(actualScreenshot.getImage(), "png", fileExpected);
        }
        Screenshot expectedScreenshot = new Screenshot(ImageIO.read(fileExpected));

        ImageDiff diff = new ImageDiffer().makeDiff(
                expectedScreenshot, actualScreenshot);

        File diffFile = new File(path + "diff/"  + n + ".png");
        if (diffFile.exists() == false)
            diffFile.mkdirs();
        ImageIO.write(diff.getMarkedImage(), "png", diffFile);

    }

    protected void createExpectedScreenshot (String s, String n) throws IOException {
        driver.get("https://product-test.ru" + s);
        Screenshot actualScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        File fileActual = new File( path + "expected/" + n + ".png");
        if (fileActual.exists() == false)
            fileActual.mkdirs();
        ImageIO.write(actualScreenshot.getImage(), "png", fileActual);
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test(dataProvider = "url")
    public void testOffers(String URL, String t) throws IOException {
        try {
            createExpectedScreenshot(URL, t);
            createActualScreenshot(URL, t);
        } catch (org.openqa.selenium.WebDriverException e) {
            tearDown();
            driver = new ChromeDriver();
        }

    }

}
