package Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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
    protected String url = "https://product-test.ru";

   protected   WebDriver driver;
   protected  String typePage;

   protected String katalog = "/smartfony";
   protected String product = "/xiaomi-redmi-4x";


    @BeforeClass
    protected void before () {
        System.setProperty("webdriver.gecko.driver","/home/luke/product-test/geckodriver-v0.18.0-linux64/geckodriver");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        driver = new FirefoxDriver(capabilities);
        driver.manage().window().maximize();
    }

    @AfterClass
    protected void tearDown() {
            if(driver != null)
                driver.quit();
    }




    protected void setTypePage(String typePage) {
        this.typePage = typePage;
    }

    protected void assertTitle (String title) {
        Assert.assertEquals(driver.getTitle(),title);
    }

    protected void assertLink (String s){
        String st = driver.getCurrentUrl();
        Assert.assertEquals(driver.getCurrentUrl().substring(st.length() - s.length()),s);
    }
}


