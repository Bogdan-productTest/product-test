package Screenshots;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.*;
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
import java.util.concurrent.TimeUnit;


/**
 * Created by admin on 17.07.2017.
 */
public class ProductTest {

    WebDriver driver;
    String browser;
    Screenshot actualScreenshot;
    Screenshot expectedScreenshot;

    String typePage;

    String katalog = "/smartfony";
    String macroKatalog = "/electronics";
    String product = "/xiaomi-redmi-4x";


    String screenshotActual = "/actual";
    String screenshotExpected = "/expected";
    String screenshotDiff = "/diff";
    String screenshotGif = "/gif";



        @BeforeClass
        @Parameters("browser")
        protected WebDriver getDriver( String browserName) {
            if(browserName.equals("chrome")) {
                driver = new ChromeDriver();
                browser = "/chrome";
            }
            else if(browserName.equals("firefox")) {
                driver = new FirefoxDriver();
                browser = "/firefox";
            }
            else if(browserName.equals("opera")) {
                driver = new OperaDriver();
                browser = "/opera";
            }
            else if(browserName.equals("edge")) {
                driver = new EdgeDriver();
                browser = "/edge";
            }
            else if(browserName.equals("ie")) {
                driver = new InternetExplorerDriver();
                browser = "/ie";
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            setBrowser(browser);
            return driver;
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

    protected void assertTitle (String title) {
        Assert.assertEquals(driver.getTitle(),title);
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
        actualScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        File filename = new File( setDir(typePage, browser, screenshotActual) + "/" + getDate() + ".png");
        ImageIO.write(actualScreenshot.getImage(), "png", filename);
    }

    protected void setExpectedScreenshot () throws IOException {
        File fileExpected = new File (setDir(typePage, browser, screenshotExpected)+ "/expectedScreenshot.png");
        if (fileExpected.exists() == false) {
            fileExpected.mkdirs();
            ImageIO.write(actualScreenshot.getImage(), "png", fileExpected);
        }
        expectedScreenshot = new Screenshot(ImageIO.read(fileExpected));
    }

    protected void createDiffFile () throws IOException {
        ImageDiff diff = new ImageDiffer().makeDiff(
                expectedScreenshot, actualScreenshot);

        File diffFile = new File(setDir(typePage, browser, screenshotDiff)+ "/" + getDate() + ".png");
        ImageIO.write(diff.getMarkedImage(), "png", diffFile);
    }

// protected void createGif () {
//        File[] filesArray = {expectedScreenshot, actualScreenshot, diffFile};
//        gifFile = GifSequenceWriter.createGIF(filesArray, resultGifs+name);
//    }

}


