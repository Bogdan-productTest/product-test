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
                "https://product-test.ru/shiny/nokian-nordman-5/kupit",
                "https://product-test.ru/shiny/nokian-hakkapeliitta-7/kupit",
                "https://product-test.ru/shiny/pirelli-ice-zero/kupit",
                "https://product-test.ru/shiny/nokian-hakkapeliitta-8/kupit",
                "https://product-test.ru/shiny/goodyear-ultra-grip-ice-arctic/kupit",
                "https://product-test.ru/shiny/nokian-nordman-5-suv/kupit",
                "https://product-test.ru/shiny/yokohama-ice-guard-ig35/kupit",
                "https://product-test.ru/shiny/nokian-hakkapeliitta-8-suv/kupit",
                "https://product-test.ru/shiny/cordiant-snow-cross/kupit",
                "https://product-test.ru/shiny/toyo-observe-g3-ice/kupit",
                "https://product-test.ru/shiny/bridgestone-blizzak-spike-01/kupit",
                "https://product-test.ru/shiny/nokian-hakkapeliitta-r2/kupit",
                "https://product-test.ru/shiny/continental-icecontact-2/kupit",
                "https://product-test.ru/shiny/gislaved-nord-frost-200/kupit",
                "https://product-test.ru/shiny/nokian-nordman-sx/kupit",
                "https://product-test.ru/shiny/bridgestone-blizzak-dm-v2/kupit",
                "https://product-test.ru/shiny/hankook-i-pike-rw11/kupit",
                "https://product-test.ru/shiny/sava-eskimo-stud/kupit",
                "https://product-test.ru/shiny/nokian-hakkapeliitta-r2-suv/kupit",
                "https://product-test.ru/shiny/nokian-nordman-rs2/kupit",
                "https://product-test.ru/shiny/toyo-observe-gsi-5/kupit",
                "https://product-test.ru/shiny/viatti-brina-v-521/kupit",
                "https://product-test.ru/shiny/viatti-brina-nordico-v-522/kupit",
                "https://product-test.ru/shiny/tigar-sigura-stud/kupit",
                "https://product-test.ru/shiny/kumho-wintercraft-ice-wi31/kupit",
                "https://product-test.ru/shiny/nexen-winguard-winspike-wh6/kupit",
                "https://product-test.ru/shiny/yokohama-ice-guard-ig50/kupit",
                "https://product-test.ru/shiny/nokian-nordman-rs2-suv/kupit",
                "https://product-test.ru/shiny/yokohama-ice-guard-ig55/kupit",
                "https://product-test.ru/shiny/hankook-dynapro-i-cept-rw08/kupit",
                "https://product-test.ru/shiny/michelin-primacy-3/kupit",
                "https://product-test.ru/shiny/continental-contipremiumcontact-5/kupit",
                "https://product-test.ru/shiny/michelin-latitude-x-ice-north-2/kupit",
                "https://product-test.ru/shiny/continental-contivikingcontact-6/kupit",
                "https://product-test.ru/shiny/tigar-winter-1/kupit",
                "https://product-test.ru/shiny/continental-icecontact-2-suv/kupit",
                "https://product-test.ru/shiny/matador-mp-50-sibir-ice/kupit",
                "https://product-test.ru/shiny/michelin-energy-xm2/kupit",
                "https://product-test.ru/shiny/nokian-hakka-green-2/kupit",
                "https://product-test.ru/shiny/michelin-pilot-alpin-pa4/kupit"



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
        driver.get(URL + "?city=Санкт-Петербург");
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

