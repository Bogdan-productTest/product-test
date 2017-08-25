package Everyday;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


public class Products {

    String path = "C:/Product_test/Screenshots/Release/";
    WebDriver driver;
    String browser = "chrome";

    String typePage;
    String date;

    String katalog = "/smartfony";
    String macroKatalog = "/electronics";
    String product = "/xiaomi-redmi-4x";

    @BeforeClass
    protected void getDriver () {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        date = getDate();
    }

    @AfterClass
    protected void tearDown() {
        if(driver != null)
            driver.quit();
    }




    protected void setTypePage(String typePage) {
        this.typePage = typePage;
    }

    protected void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    protected void setBrowser(String browser) {
        this.browser = browser;
    }




    protected void createFolders(File file) {
        if (file.exists() == false) {
            file.mkdirs();
            return;
        }
    }


    protected String setDir (String pageName, String browser, String typeScreenshoot){
        File file = new File("C:/Product_test/Screenshots" + pageName + browser + typeScreenshoot);
        String pathToString = file.getAbsolutePath();
        createFolders(file);
        return pathToString;
    }

    protected String getDate () {
        Locale local = new Locale("ru","RU");
        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, local);
        Date currentDate = new Date();
        return df.format(currentDate);
    }


    protected void createActualScreenshot () throws IOException {
        Screenshot actualScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        File fileActual = new File( path + "actual/" + date + "_" + typePage + "_" + browser  + ".png");
        if (fileActual.exists() == false)
            fileActual.mkdirs();
        ImageIO.write(actualScreenshot.getImage(), "png", fileActual);


        File fileExpected = new File (path + "expected/" + typePage + "_" + browser  + ".png");
        if (fileExpected.exists() == false) {
            fileExpected.mkdirs();
            ImageIO.write(actualScreenshot.getImage(), "png", fileExpected);
        }
        Screenshot expectedScreenshot = new Screenshot(ImageIO.read(fileExpected));

        ImageDiff diff = new ImageDiffer().makeDiff(
                expectedScreenshot, actualScreenshot);

        File diffFile = new File("C:/Product_test/Screenshots/Everyday/"  + date + "_" + typePage + "_" + browser + ".png");
        if (diffFile.exists() == false)
            diffFile.mkdirs();
        ImageIO.write(diff.getMarkedImage(), "png", diffFile);


    }
}
