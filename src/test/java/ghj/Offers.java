package ghj;

        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;
        import org.testng.Assert;
        import org.testng.annotations.*;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.concurrent.TimeUnit;

public class Offers {
    WebDriver driver;
    List<String> urlNotOffers = new ArrayList<String>();

    @DataProvider(name = "url")
    public Object[] createData1() {
        return new Object[]{

        };
    }

    @BeforeClass
    public void config() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown() {
        System.out.println(urlNotOffers);
        driver.quit();
    }

    @Test(dataProvider = "url")
    public void testOffers(String URL) {
        driver.get(URL);
        driver.findElements(By.className("image-buy"));

        WebElement offersMessageElement = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("buy_button")));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebElement d = driver.findElement(By.xpath("/html[@class='no-js html-adaptive desktop landscape']/body/div[@class='out']/div[@class='container']/div[@id='m-wrapper']/div[@class='center content-wrapper']/div[2]/div[@class='tabs h-block m-hide']/div[@class='tabs__item active']/span[@class='tabs__link']/span[@class='tabs__counter buy_tabs__counter']"));
//        if (d.getText().equals("(0)")) {
//        urlNotOffers.add(URL);
//            Assert.assertTrue(false);
//        } else System.out.println("предложения есть!");
        System.out.println(d.getText());
        }
    }

