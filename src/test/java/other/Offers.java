package other;

        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;
        import org.testng.annotations.*;

        import java.util.ArrayList;
        import java.util.List;

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
    public void testOffers(String URL) throws InterruptedException {
        try {
            driver.get(URL
                     + "?city=Москва"
            );
            driver.findElements(By.className("image-buy"));

            // WebElement offersMessageElement = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("buy_button")));
            WebElement offersMessageElement = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[@class='no-js html-adaptive desktop landscape']/body/div[@class='out']/div[@class='container']/div[@id='m-wrapper']/div[@class='center content-wrapper']/div[2]/div[@class='tabs h-block m-hide']/div[@class='tabs__item active']/span[@class='tabs__link']/span[@class='tabs__counter buy_tabs__counter']")));
            Thread.sleep(3000);
            // driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
            WebElement d = driver.findElement(By.xpath("/html[@class='no-js html-adaptive desktop landscape']/body/div[@class='out']/div[@class='container']/div[@id='m-wrapper']/div[@class='center content-wrapper']/div[2]/div[@class='tabs h-block m-hide']/div[@class='tabs__item active']/span[@class='tabs__link']/span[@class='tabs__counter buy_tabs__counter']"));

            if (d.getText().length() == 0) {
                Thread.sleep(5000);
            }
//        if (d.getText().equals("(0)")) {
//        urlNotOffers.add(URL);
//            Assert.assertTrue(false);
//        } else System.out.println("предложения есть!");
            System.out.println(d.getText());
        } catch (org.openqa.selenium.NoSuchWindowException e) {
            config();
        }
    }
}

