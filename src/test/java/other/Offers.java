package other;

        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.chrome.ChromeOptions;
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
                "https://product-test.ru/tablets/ipad4/kupit",
                "https://product-test.ru/tablets/ipadmini/kupit",
                "https://product-test.ru/tablets/galaxynote101/kupit",
                "https://product-test.ru/tablets/galaxytab2101/kupit",
                "https://product-test.ru/tablets/appleipadair/kupit",
                "https://product-test.ru/tablets/appleipadminiretina/kupit",
                "https://product-test.ru/tablets/apple-ipad-air-2/kupit",
                "https://product-test.ru/tablets/apple-ipad-mini-3/kupit",
                "https://product-test.ru/tablets/apple-ipad-mini-4/kupit",
                "https://product-test.ru/tablets/apple-ipad-pro/kupit",
                "https://product-test.ru/tablets/asus-zenpad-80-z380kl/kupit",
                "https://product-test.ru/tablets/asus-zenpad-c-70-z170cg/kupit",
                "https://product-test.ru/tablets/samsung-galaxy-tab-e-96-sm-t561n/kupit",
                "https://product-test.ru/tablets/asus-zenpad-80-z380c/kupit",
                "https://product-test.ru/tablets/asus-zenpad-10-z300cg/kupit",
                "https://product-test.ru/tablets/asus-zenpad-c-70-z170c/kupit",
                "https://product-test.ru/tablets/samsung-galaxy-tab-s2-97-sm-t810/kupit",
                "https://product-test.ru/tablets/lenovo-tab-2-a7-30dc/kupit",
                "https://product-test.ru/tablets/lenovo-yoga-tab-3-pro/kupit",
                "https://product-test.ru/tablets/apple-ipad-pro-9-7/kupit",
                "https://product-test.ru/tablets/lenovo-ideapad-miix-310/kupit",
                "https://product-test.ru/tablets/huawei-mediapad-m2-100/kupit",
                "https://product-test.ru/tablets/xiaomi-mipad-2/kupit",
                "https://product-test.ru/tablets/apple-ipad-pro-97-128gb-wi-fi/kupit",
                "https://product-test.ru/tablets/apple-ipad-pro-97-256gb-wi-fi/kupit",
                "https://product-test.ru/tablets/samsung-galaxy-tab-s3-97/kupit",
                "https://product-test.ru/tablets/asus-zenpad-10-z300cg-1gb-8gb/kupit",
                "https://product-test.ru/tablets/hp-pavilion-x2-z8300-64gb/kupit",
                "https://product-test.ru/tablets/turbopad-712/kupit",
                "https://product-test.ru/tablets/huawei-mediapad-t1-80-3g-8gb/kupit",
                "https://product-test.ru/tablets/huawei-mediapad-t1-10-wi-fi-16gb/kupit",
                "https://product-test.ru/tablets/torex-pad-4g/kupit",
                "https://product-test.ru/tablets/fujitsu-stylistic-q584-128gb-lte/kupit",
                "https://product-test.ru/tablets/panasonic-toughpad-fz-g1-128gb/kupit",
                "https://product-test.ru/tablets/getac-z710-basic/kupit",
                "https://product-test.ru/tablets/getac-t800-128gb/kupit",
                "https://product-test.ru/tablets/lenovo-yoga-tablet-10-3-16gb/kupit",
                "https://product-test.ru/tablets/panasonic-toughpad-fz-m1-128gb-4gb/kupit",
                "https://product-test.ru/tablets/turbopad-802/kupit",
                "https://product-test.ru/tablets/chuwi-vx8/kupit",
                "https://product-test.ru/tablets/prestigio-multipad-pmt3787-3g/kupit",
                "https://product-test.ru/tablets/turbokids-20/kupit",
                "https://product-test.ru/tablets/jeka-jk-103/kupit",
                "https://product-test.ru/tablets/lexand-sb7-hd/kupit",
                "https://product-test.ru/tablets/turbokids-star/kupit",
                "https://product-test.ru/tablets/turbopad-1000/kupit",
                "https://product-test.ru/tablets/twinscom-g10/kupit",
                "https://product-test.ru/tablets/pipo-u1/kupit",
                "https://product-test.ru/tablets/newsmy-t3/kupit",
                "https://product-test.ru/tablets/roadmax-evolution-3/kupit",
                "https://product-test.ru/tablets/oysters-t8/kupit",
                "https://product-test.ru/tablets/eplutus-g17/kupit",
                "https://product-test.ru/tablets/htc-jetstream/kupit",
                "https://product-test.ru/tablets/hyundai-s900/kupit",
                "https://product-test.ru/tablets/samsung-galaxy-tab-4-80-sm-t330-16gb/kupit",
                "https://product-test.ru/tablets/luckystar-r11/kupit",
                "https://product-test.ru/tablets/nextbook-next3/kupit",
                "https://product-test.ru/tablets/samsung-galaxy-tab-4-70-sm-t230-16gb/kupit",
                "https://product-test.ru/tablets/pocket-rocket-ag-100/kupit",
                "https://product-test.ru/tablets/pocket-rocket-ag-30/kupit",
                "https://product-test.ru/tablets/prestigio-multipad-pmp812f-3g-pro/kupit",
                "https://product-test.ru/tablets/zte-v66/kupit",
                "https://product-test.ru/tablets/eken-a90/kupit",
                "https://product-test.ru/tablets/nextpad-family/kupit",
                "https://product-test.ru/tablets/nextpad-city/kupit",
                "https://product-test.ru/tablets/digma-hit-3g-4gb/kupit",
                "https://product-test.ru/tablets/luckystar-m14/kupit",
                "https://product-test.ru/tablets/qumo-2go!/kupit",
                "https://product-test.ru/tablets/orro-n920/kupit",
                "https://product-test.ru/tablets/orro-a960/kupit",
                "https://product-test.ru/tablets/orro-a990/kupit",
                "https://product-test.ru/tablets/luckystar-m7/kupit",
                "https://product-test.ru/tablets/luckystar-m73/kupit",
                "https://product-test.ru/tablets/tracer-neo/kupit",
                "https://product-test.ru/tablets/tracer-ovo/kupit",
                "https://product-test.ru/tablets/cube-t6/kupit",
                "https://product-test.ru/tablets/ampe-a10/kupit",
                "https://product-test.ru/tablets/ferguson-regent-10/kupit",
                "https://product-test.ru/tablets/ibox-eos/kupit",
                "https://product-test.ru/tablets/ibox-hermes/kupit",
                "https://product-test.ru/tablets/ibox-zeus/kupit",
                "https://product-test.ru/tablets/eplutus-g29/kupit",
                "https://product-test.ru/tablets/keener-k-10c/kupit",
                "https://product-test.ru/tablets/keener-k-10s/kupit",
                "https://product-test.ru/tablets/getac-e100/kupit",
                "https://product-test.ru/tablets/apache-q88/kupit",
                "https://product-test.ru/tablets/odys-cosmo/kupit",
                "https://product-test.ru/tablets/bmorn-k12/kupit",
                "https://product-test.ru/tablets/maylong-m-150/kupit",
                "https://product-test.ru/tablets/pipo-u3/kupit",
                "https://product-test.ru/tablets/eplutus-m72/kupit",
                "https://product-test.ru/tablets/eken-m001/kupit",
                "https://product-test.ru/tablets/eken-m003/kupit",
                "https://product-test.ru/tablets/eken-m005/kupit",
                "https://product-test.ru/tablets/smart-devices-r10/kupit",
                "https://product-test.ru/tablets/mebol-1003/kupit",
                "https://product-test.ru/tablets/mebol-1001/kupit",
                "https://product-test.ru/tablets/gpad-g10/kupit",
                "https://product-test.ru/tablets/gpad-g11/kupit",
                "https://product-test.ru/tablets/acer-iconia-tab-w500/kupit",
                "https://product-test.ru/tablets/megafon-v9-plus/kupit",
                "https://product-test.ru/tablets/roverpad-1024/kupit",
                "https://product-test.ru/tablets/nokia-n800/kupit",
                "https://product-test.ru/tablets/explay-ar-701/kupit",
                "https://product-test.ru/tablets/kobo-vox/kupit",
                "https://product-test.ru/tablets/samsung-galaxy-tab-2-70-p3110-8gb/kupit",
                "https://product-test.ru/tablets/samsung-galaxy-tab-2-101-p5110-16gb/kupit",
                "https://product-test.ru/tablets/samsung-galaxy-note-101-n8000-64gb/kupit",
                "https://product-test.ru/tablets/samsung-galaxy-tab-2-70-p3110-16gb/kupit",
                "https://product-test.ru/tablets/dixon-a710/kupit",
                "https://product-test.ru/tablets/apple-ipad-4-16gb-wi-fi/kupit",
                "https://product-test.ru/tablets/aoson-m92/kupit",
                "https://product-test.ru/tablets/icoo-d50/kupit",
                "https://product-test.ru/tablets/bmorn-v25/kupit",
                "https://product-test.ru/tablets/ergo-legend/kupit",
                "https://product-test.ru/tablets/plark-p21/kupit",
                "https://product-test.ru/tablets/plark-p24/kupit",
                "https://product-test.ru/tablets/ampe-a83/kupit",
                "https://product-test.ru/tablets/plark-p22/kupit",
                "https://product-test.ru/tablets/hugerock-s71/kupit",
                "https://product-test.ru/tablets/plark-p23/kupit",
                "https://product-test.ru/tablets/hugerock-t80/kupit",
                "https://product-test.ru/tablets/hugerock-s70/kupit",
                "https://product-test.ru/tablets/lenovo-miix3-10-64gb/kupit",
                "https://product-test.ru/tablets/hp-x2-210-z8350-4gb-64gb/kupit",
                "https://product-test.ru/tablets/acer-switch-one-10-z8300-32gb/kupit",
                "https://product-test.ru/tablets/samsung-galaxy-tab-s2-97-sm-t810-wi-fi-64gb/kupit",
                "https://product-test.ru/tablets/prestigio-multipad-pmt3777c-3g/kupit",
                "https://product-test.ru/tablets/apache-a9/kupit",
                "https://product-test.ru/tablets/eplutus-m78/kupit",
                "https://product-test.ru/tablets/huawei-mediapad-m3-84-lte/kupit",
                "https://product-test.ru/tablets/lenovo-yoga-book-yb1-x91l-64gb/kupit",
                "https://product-test.ru/tablets/acer-switch-one-10-z8300-532gb/kupit",
                "https://product-test.ru/tablets/lenovo-yoga-book-yb1-x91f-64gb/kupit",
                "https://product-test.ru/tablets/asus-transformer-3-t305ca-8gb-256gb/kupit",
                "https://product-test.ru/tablets/hp-x2-10-z8350-2gb-32gb/kupit",
                "https://product-test.ru/tablets/irbis-tz737w/kupit",
                "https://product-test.ru/tablets/lenovo-miix-510-12-i5-8gb-256gb-lte/kupit",
                "https://product-test.ru/tablets/huawei-mediapad-t1-7-3g-8gb/kupit",
                "https://product-test.ru/tablets/lenovo-miix-510-12-i5-8gb-256gb-wifi/kupit",
                "https://product-test.ru/tablets/lenovo-yoga-book-yb1-x90f-64gb/kupit",
                "https://product-test.ru/tablets/lenovo-yoga-book-yb1-x90l-64gb/kupit",
                "https://product-test.ru/tablets/matrix-758/kupit",
                "https://product-test.ru/tablets/asus-transformer-3-t305ca-2gb-256gb/kupit",
                "https://product-test.ru/tablets/onda-v80-se/kupit",
                "https://product-test.ru/tablets/teclast-x5-pro/kupit",
                "https://product-test.ru/tablets/cube-t12/kupit",
                "https://product-test.ru/tablets/runbo-p12/kupit",
                "https://product-test.ru/tablets/ginzzu-gt-8010-rev2/kupit",
                "https://product-test.ru/tablets/lenovo-yoga-tablet-3-pro-lte-4gb-64gb/kupit",
                "https://product-test.ru/tablets/msi-enjoy-7-plus/kupit",
                "https://product-test.ru/tablets/kurio-10s/kupit",
                "https://product-test.ru/tablets/mts-1078/kupit",
                "https://product-test.ru/tablets/eplutus-g47/kupit",
                "https://product-test.ru/tablets/lenovo-yoga-tablet-3-pro-lte-2gb-64gb/kupit",
                "https://product-test.ru/tablets/vastking-q88/kupit",
                "https://product-test.ru/tablets/apple-ipad-32gb-wi-fi-2/kupit",
                "https://product-test.ru/tablets/ferguson-s8/kupit",
                "https://product-test.ru/tablets/forensis-t808/kupit",
                "https://product-test.ru/tablets/voyo-x6/kupit",
                "https://product-test.ru/tablets/twinmos-t714a/kupit",
                "https://product-test.ru/tablets/twinscom-g7/kupit",
                "https://product-test.ru/tablets/buytek-k103/kupit",
                "https://product-test.ru/tablets/ntt-611/kupit",
                "https://product-test.ru/tablets/samsung-galaxy-note-101-p6050-16gb/kupit",
                "https://product-test.ru/tablets/lenovo-miix-510-12-i3-4gb-256gb-lte/kupit",
                "https://product-test.ru/tablets/pipo-m7/kupit",
                "https://product-test.ru/tablets/etuline-t850/kupit",
                "https://product-test.ru/tablets/eken-c70-plus/kupit",
                "https://product-test.ru/tablets/energy-sistem-x7/kupit",
                "https://product-test.ru/tablets/hugerock-t70/kupit",
                "https://product-test.ru/tablets/turbopad-1012/kupit",
                "https://product-test.ru/tablets/crown-b850/kupit",
                "https://product-test.ru/tablets/jxd-s7300/kupit",
                "https://product-test.ru/tablets/turbopad-duo/kupit",
                "https://product-test.ru/tablets/msi-s100/kupit",
                "https://product-test.ru/tablets/manta-mid781/kupit",
                "https://product-test.ru/tablets/denout-drive/kupit",
                "https://product-test.ru/tablets/etuline-t750/kupit",
                "https://product-test.ru/tablets/izooma-g17/kupit",
                "https://product-test.ru/tablets/pipo-t2/kupit",
                "https://product-test.ru/tablets/apache-m72/kupit",
                "https://product-test.ru/tablets/apple-ipad/kupit",
                "https://product-test.ru/tablets/lenovo-miix-320-10-4gb-64gb-lte/kupit",
                "https://product-test.ru/tablets/lenovo-miix-320-10-4gb-64gb-wifi/kupit",
                "https://product-test.ru/tablets/huawei-mediapad-t3-80-16gb/kupit",
                "https://product-test.ru/tablets/matrix-748/kupit",
                "https://product-test.ru/tablets/qumo-pulse/kupit",
                "https://product-test.ru/tablets/ramos-i9-16gb/kupit",
                "https://product-test.ru/tablets/chuwi-vx1/kupit",
                "https://product-test.ru/tablets/digma-platina-80-3g/kupit",
                "https://product-test.ru/tablets/nextway-f10x/kupit",
                "https://product-test.ru/tablets/huawei-mediapad-m3-84-64gb-lte/kupit",
                "https://product-test.ru/tablets/microsoft-surface-pro-5-i5-4gb-128gb/kupit",
                "https://product-test.ru/tablets/microsoft-surface-pro-5-i5-8gb-256gb/kupit",
                "https://product-test.ru/tablets/prestigio-multipad-pmt3618-4g/kupit",
                "https://product-test.ru/tablets/microsoft-surface-pro-5-m3-4gb-128gb/kupit",
                "https://product-test.ru/tablets/prestigio-multipad-pmt3318d/kupit",
                "https://product-test.ru/tablets/bdf-107-002/kupit",
                "https://product-test.ru/tablets/supra-m74a/kupit",
                "https://product-test.ru/tablets/voyo-a18/kupit",
                "https://product-test.ru/tablets/turbopad-703/kupit",
                "https://product-test.ru/tablets/bdf-107-003/kupit",
                "https://product-test.ru/tablets/huawei-mediapad-m3-lite-10/kupit"
        };
    }

    @BeforeClass
    public void config() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(
                options
        );
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
                     + "?city=Казань"
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

