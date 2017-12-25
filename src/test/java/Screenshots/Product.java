
package Screenshots;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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
    public class Product {

        WebDriver driver;
        String browser;

        String typePage;
        String date;

        String katalog = "/smartfony";
        String macroKatalog = "/electronics";
        String product = "/xiaomi-redmi-4x";
        String path = "C:/Product_test/Screenshots/Release/";

        @BeforeClass
        @Parameters("browser")
        protected WebDriver getDriver( String browserName) {
            if(browserName.equals("chrome")) {
                driver = new ChromeDriver();
                browser = "chrome";
            }
            else if(browserName.equals("firefox")) {
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);
                driver = new FirefoxDriver(capabilities);
                browser = "firefox";
            }
            else if(browserName.equals("opera")) {
                driver = new OperaDriver();
                browser = "opera";
            }
            else if(browserName.equals("edge")) {
                driver = new EdgeDriver();
                browser = "edge";
            }
            else if(browserName.equals("ie")) {
                driver = new InternetExplorerDriver();
                browser = "ie";
            }
            date = getDate();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            setBrowser(browser);
            return driver;
        }

        @AfterTest
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




        protected boolean createFolders(File file) {
            boolean i = false;
            if (file.exists() == false) {
                file.mkdirs();
                i = true;
            }
            return i;
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

            //актуальный скриншот
            Screenshot actualScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
            File fileActual = new File( path + "actual/" + date + "_" + typePage + "_" + browser +  ".png");
            createFolders(fileActual);
            ImageIO.write(actualScreenshot.getImage(), "png", fileActual);

            //эталонный скриншот
            File fileExpected = new File (path + "expected/" + typePage + "_" + browser  + ".png");
            if (createFolders(fileExpected) == true ) {
                ImageIO.write(actualScreenshot.getImage(), "png", fileExpected);
            }
            Screenshot expectedScreenshot = new Screenshot(ImageIO.read(fileExpected));

            //маркированный скриншот
            ImageDiff diff = new ImageDiffer().makeDiff(
                    expectedScreenshot, actualScreenshot);

            File diffFile = new File(path + "diff/" + date + "_" + typePage + "_" + browser + ".png");
            createFolders(diffFile);
            ImageIO.write(diff.getMarkedImage(), "png", diffFile);


        }

//        protected void setExpectedScreenshot () throws IOException {
//            File fileExpected = new File ("C:/Product_test/Screenshots" + typePage + browser + "/expected"+ "/expectedScreenshot.png");
//            if (fileExpected.exists() == false) {
//                fileExpected.mkdirs();
//                ImageIO.write(actualScreenshot.getImage(), "png", fileExpected);
//            }
//            expectedScreenshot = new Screenshot(ImageIO.read(fileExpected));
//        }
//
//        protected void createDiffFile () throws IOException {
//            ImageDiff diff = new ImageDiffer().makeDiff(
//                    expectedScreenshot, actualScreenshot);
//            expectedScreenshot = null;
//            actualScreenshot = null;
//
//            File diffFile = new File("C:/Product_test/Screenshots" + typePage + browser + "/diff"+ "/" + date + ".png");
//            ImageIO.write(diff.getMarkedImage(), "png", diffFile);
        }

// protected void createGif () {
//        File[] filesArray = {expectedScreenshot, actualScreenshot, diffFile};
//        gifFile = GifSequenceWriter.createGIF(filesArray, resultGifs+name);
//    }


