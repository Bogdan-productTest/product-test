package Assert.Rusupermarket;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static Assert.MyConfig.getMobiguruApiKeyPT;
import static Assert.MyConfig.getNadaviApiString;
import static Assert.Rusupermarket.Methods.*;

public class CheckOffers {

    private static WebDriver driver;
   // private static long start = 0;
    private static String idKirill = "35658953";
    protected static String idBogdan = "297837315";
    private static String idChatPT = "-226718542";
    private static String idChatDev = "-209426772";
    private static int lastChange = 0;
    private static long timeCheck = 0;
    private static Queue<Url> queue;
    private static Queue<UrlForCheckCode> queueForCheckCode;
    static long time1;
    static int minuteLastReportToXLS = -1;

    private static Logger log = Logger.getLogger(CheckOffers.class.getName());

    @BeforeClass
    public static void browserHeadless() throws FileNotFoundException, URISyntaxException {
        /**
        ChromeOptions options = new ChromeOptions();
      //  options.addArguments("--headless");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability("pageLoadStrategy", "none");
        driver = new ChromeDriver(
                options
        );
**/
        FirefoxProfile profile = new FirefoxProfile(new File("C:\\Users\\Mirror\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\a7rxrtg8.Bot"));

        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
       // firefoxBinary.addCommandLineOptions("--log debug");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
       // firefoxOptions.setLogLevel(Level.OFF);
        firefoxOptions.setBinary(firefoxBinary);

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        capabilities.setCapability("pageLoadStrategy", "eager");
       // capabilities.setCapability("browser.cache.offline.enable", "false");
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        driver = new FirefoxDriver(firefoxBinary ,profile, capabilities);
      //  driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.MILLISECONDS);
      //  driver.manage().window().setSize(new Dimension(300,1000));
    }

    //создание пула урлов для проверки
    public static void createPoolUrl() {

        Url urlPTNadaviChild = getUrlObject("https://product-test.ru/smartfony/apple-iphone-8-64gb/kupit", "//div[@class='buy__item']", 300000);
        // urlPTNadaviChild.setNadaviIdChild("1204676");
       // Url urlPTNadaviChild = getUrlObject("https://product-test.ru/elektrosushilki-dlya-ovoshhej--fruktov--gribov/endever-skyline-fd-55/kupit?city=Москва", "//div[@class='buy__item']", 300000);
        Url urlPTNadaviParent = getUrlObject("https://product-test.ru/shiny/nokian-nordman-7/kupit", "//div[@class='buy__item']", 300000);
        // urlPTNadaviParent.setNadaviIdParent("1082406");
        Url urlPTMobiguru = getUrlObject("https://product-test.ru/tablets/ipad4/kupit?city=Москва", "//div[@class='buy__item']", 300000);
        // urlPTMobiguru.setMobiguruId("81761258");
        Url urlRSChild = getUrlObject("https://rus-supermarket.ru/smartfony/apple-iphone-8-64gb/kupit", "//div[@class='detail-shops-item ']", 300000);
        // urlRSChild.setNadaviIdChild("1204676");
        Url urlRSParent = getUrlObject("https://rus-supermarket.ru/shiny/nokian-nordman-7/kupit", "//div[@class='detail-shops-item ']", 300000);
        // urlRSParent.setNadaviIdParent("1082406");
        Url urlVMNadaviParent = getUrlObject("https://vse-magazini.ru/shiny/nokian-nordman-rs2-suv/kupit", "//div[@class='shops-list-item ']", 300000);
        // urlVMNadaviParent.setNadaviIdParent("643852");
        Url urlVMNadaviChild = getUrlObject("https://vse-magazini.ru/smartfony/samsung-galaxy-s8/kupit", "//div[@class='shops-list-item ']", 300000);
        // urlVMNadaviChild.setNadaviIdChild("1078250");
        Url urlVMMobiguru = getUrlObject("https://vse-magazini.ru/tablets/ipad4/kupit?city=Москва", "//div[@class='shops-list-item ']", 300000);
        // urlVMMobiguru.setMobiguruId("81761258");
        Url urlPMobiguru = getUrlObject("https://pochom.ru/smartfony/samsung-galaxy-s8-plus/kupit", "//div[@class='shops-list-item ']", 300000);
        // urlPMobiguru.setMobiguruId("121060466");
        Url urlPMobiguruTest1 = getUrlObject("https://pochom.ru/smartfony/apple-iphone-7/kupit?city=Москва", "//div[@class='shops-list-item ']", 300000);
        // urlPMobiguruTest1.setMobiguruId("115932246");
        Url urlPMobiguruTest2 = getUrlObject("https://pochom.ru/tablets/ipad4/kupit?city=Москва", "//div[@class='shops-list-item ']", 300000);
        // urlPMobiguruTest2.setMobiguruId("81761258");

        Url urlPT1 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-redmi-4x-32gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT2 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-redmi-4x-16gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT3 =  getUrlObject("https://product-test.ru/smartfony/apple-iphone-7-32gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT4 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-s7-32gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT5 =  getUrlObject("https://product-test.ru/smartfony/huawei-p10-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT6 =  getUrlObject("https://product-test.ru/smartfony/apple-iphone-6-16gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT7 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-mi-max-2/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT8 =  getUrlObject("https://product-test.ru/smartfony/apple-iphone-6s-16gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT9 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-mi6-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT10 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-s7-edge-32gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT11 =  getUrlObject("https://product-test.ru/smartfony/huawei-honor-9-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT12 =  getUrlObject("https://product-test.ru/smartfony/apple-iphone-7-plus-32gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT13 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-mi-max-2-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT14 =  getUrlObject("https://product-test.ru/smartfony/apple-iphone-5s-16gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT15 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-s6-32gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT16 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-redmi-note-4x-32gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT17 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-mi5-32gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT18 =  getUrlObject("https://product-test.ru/smartfony/apple-iphone-x-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT19 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-s6-edge-32gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT20 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-redmi-4-pro-32gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT21 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-s8/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT22 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-redmi-note-4-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT23 =  getUrlObject("https://product-test.ru/smartfony/oneplus-5t-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT24 =  getUrlObject("https://product-test.ru/smartfony/apple-iphone-se-16gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT25 =  getUrlObject("https://product-test.ru/smartfony/huawei-honor-9-64gb6gb-dual-sim/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT26 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-redmi-4x-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT27 =  getUrlObject("https://product-test.ru/smartfony/apple-iphone-4s-8gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT28 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-redmi-note-4x-16gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT29 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-redmi-4-pro/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT30 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-note8-64gb/kupit?city=Москва?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT31 =  getUrlObject("https://product-test.ru/smartfony/nokia-8800/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT32 =  getUrlObject("https://product-test.ru/smartfony/apple-iphone-8-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT33 =  getUrlObject("https://product-test.ru/smartfony/apple-iphone-se-32gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT34 =  getUrlObject("https://product-test.ru/smartfony/apple-iphone-6s-plus-16gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT35 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-redmi-note-5a-16gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT36 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-redmi-4a-32gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT37 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-a5-2017/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT38 =  getUrlObject("https://product-test.ru/smartfony/sony-xperia-z5/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT39 =  getUrlObject("https://product-test.ru/smartfony/zte-axon-7-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT40 =  getUrlObject("https://product-test.ru/smartfony/apple-iphone-8-plus-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT41 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-j7-20172/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT42 =  getUrlObject("https://product-test.ru/smartfony/apple-iphone-6-plus-16gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT43 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-s8-plus-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT44 =  getUrlObject("https://product-test.ru/smartfony/nokia-8-dual-sim/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT45 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-redmi-5a-16gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT46 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-mi-note-3-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT47 =  getUrlObject("https://product-test.ru/smartfony/huawei-p10-64gb-dual-sim/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT48 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-a5-2016/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT49 =  getUrlObject("https://product-test.ru/smartfony/nokia-5-dual-sim/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT50 =  getUrlObject("https://product-test.ru/smartfony/huawei-honor-9-128gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT51 =  getUrlObject("https://product-test.ru/igrovye-pristavki/nintendo-switch/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT52 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-redmi-note-4-16gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT53 =  getUrlObject("https://product-test.ru/smartfony/apple-iphone-4-8gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT54 =  getUrlObject("https://product-test.ru/smartfony/nokia-6-dual-sim/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT55 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-note-4/kupit?city=Москва?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT56 =  getUrlObject("https://product-test.ru/smartfony/huawei-honor-8-pro-64gb4gb-dual-sim/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT57 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-mi-a1-32gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT58 =  getUrlObject("https://product-test.ru/smartfony/oppo-f5/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT59 =  getUrlObject("https://product-test.ru/smartfony/oneplus-5t-128gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT60 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-mi-a1-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT61 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-s4-gt-i9500-16gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT62 =  getUrlObject("https://product-test.ru/smartfony/huawei-p10-lite-32gb3gb-dual-sim/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT63 =  getUrlObject("https://product-test.ru/fotoapparaty/canon-eos-5d-mark-ii-body/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT64 =  getUrlObject("https://product-test.ru/smartfony/oukitel-k10000-pro/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT65 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-j3-2016/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT66 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-a3-20172/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT67 =  getUrlObject("https://product-test.ru/smartfony/htc-u11-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT68 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-note-5-32gb/kupit?city=Москва?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT69 =  getUrlObject("https://product-test.ru/smartfony/nokia-6700-classic/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT70 =  getUrlObject("https://product-test.ru/smartfony/sony-xperia-z5-compact/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT71 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-mi-max-32gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT72 =  getUrlObject("https://product-test.ru/fotoapparaty/canon-eos-5d-mark-iii-body/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT73 =  getUrlObject("https://product-test.ru/smartfony/oneplus-5-128gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT74 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-redmi-3s-32gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT75 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-mi-max-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT76 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-redmi-4-16gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT77 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-mi-mix-2-64gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT78 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-mi6-128gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT79 =  getUrlObject("https://product-test.ru/smartfony/xiaomi-mi-max-2-128gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlPT80 =  getUrlObject("https://product-test.ru/smartfony/samsung-galaxy-s5-16gb/kupit?city=Москва","//div[@class='buy__item']",600000);
        Url urlVM81 =  getUrlObject("https://vse-magazini.ru/smartfony/xiaomi-redmi-4x-16gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM82 =  getUrlObject("https://vse-magazini.ru/smartfony/xiaomi-mi6-64gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM83 =  getUrlObject("https://vse-magazini.ru/smartfony/samsung-galaxy-s7-32gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM84 =  getUrlObject("https://vse-magazini.ru/smartfony/apple-iphone-7-32gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM85 =  getUrlObject("https://vse-magazini.ru/smartfony/xiaomi-redmi-4x-32gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM86 =  getUrlObject("https://vse-magazini.ru/smartfony/huawei-honor-9-64gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM87 =  getUrlObject("https://vse-magazini.ru/smartfony/samsung-galaxy-s8/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM88 =  getUrlObject("https://vse-magazini.ru/smartfony/apple-iphone-4s-8gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM89 =  getUrlObject("https://vse-magazini.ru/smartfony/xiaomi-mi-max-2/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM90 =  getUrlObject("https://vse-magazini.ru/smartfony/huawei-p10-32gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM91 =  getUrlObject("https://vse-magazini.ru/smartfony/xiaomi-redmi-4x-64gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM92 =  getUrlObject("https://vse-magazini.ru/smartfony/samsung-galaxy-s6-32gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM93 =  getUrlObject("https://vse-magazini.ru/smartfony/apple-iphone-5s-16gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM94 =  getUrlObject("https://vse-magazini.ru/smartfony/samsung-galaxy-s7-edge-32gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM95 =  getUrlObject("https://vse-magazini.ru/smartfony/xiaomi-mi5-32gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM96 =  getUrlObject("https://vse-magazini.ru/smartfony/xiaomi-redmi-note-4-64gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM97 =  getUrlObject("https://vse-magazini.ru/smartfony/apple-iphone-7-plus-32gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM98 =  getUrlObject("https://vse-magazini.ru/smartfony/xiaomi-redmi-4-pro/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM99 =  getUrlObject("https://vse-magazini.ru/smartfony/xiaomi-redmi-note-4x-16gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM100 =  getUrlObject("https://vse-magazini.ru/smartfony/sony-xperia-z5/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM101 =  getUrlObject("https://vse-magazini.ru/smartfony/xiaomi-redmi-5a-16gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM102 =  getUrlObject("https://vse-magazini.ru/igrovye-pristavki/nintendo-switch/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM103 =  getUrlObject("https://vse-magazini.ru/smartfony/apple-iphone-6s-16gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM104 =  getUrlObject("https://vse-magazini.ru/smartfony/samsung-galaxy-s6-edge-32gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM105 =  getUrlObject("https://vse-magazini.ru/smartfony/lenovo-vibe-p2/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM106 =  getUrlObject("https://vse-magazini.ru/palatki/stek-kub-3/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM107 =  getUrlObject("https://vse-magazini.ru/smartfony/xiaomi-redmi-note-4x-32gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM108 =  getUrlObject("https://vse-magazini.ru/smartfony/samsung-galaxy-a3-2016/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM109 =  getUrlObject("https://vse-magazini.ru/smartfony/xiaomi-mi-max-2-64gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM110 =  getUrlObject("https://vse-magazini.ru/smartfony/apple-iphone-x-64gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM111 =  getUrlObject("https://vse-magazini.ru/smartfony/oukitel-k10000-pro/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM112 =  getUrlObject("https://vse-magazini.ru/smartfony/apple-iphone-6-16gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM113 =  getUrlObject("https://vse-magazini.ru/smartfony/nokia-8800/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM114 =  getUrlObject("https://vse-magazini.ru/smartfony/samsung-gt-c3050/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM115 =  getUrlObject("https://vse-magazini.ru/fotoapparaty/canon-eos-80d-body/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM116 =  getUrlObject("https://vse-magazini.ru/smartfony/sony-xperia-z1/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM117 =  getUrlObject("https://vse-magazini.ru/smartfony/xiaomi-mi-note-3-64gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM118 =  getUrlObject("https://vse-magazini.ru/smartfony/xiaomi-redmi-note-4-16gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM119 =  getUrlObject("https://vse-magazini.ru/smartfony/sony-xperia-z3-compact/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlVM120 =  getUrlObject("https://vse-magazini.ru/fotoapparaty/nikon-d610-body/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlRS121 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-redmi-4x-16gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS122 =  getUrlObject("https://rus-supermarket.ru/smartfony/huawei-p10-32gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS123 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-redmi-4x-32gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS124 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-redmi-4x-64gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS125 =  getUrlObject("https://rus-supermarket.ru/smartfony/samsung-galaxy-s7-32gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS126 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-mi-max-2-64gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS127 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-redmi-4-pro-32gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS128 =  getUrlObject("https://rus-supermarket.ru/smartfony/apple-iphone-4s-8gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS129 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-redmi-note-4x-16gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS130 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-mi6-64gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS131 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-redmi-note-4x-32gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS132 =  getUrlObject("https://rus-supermarket.ru/smartfony/apple-iphone-5s-16gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS133 =  getUrlObject("https://rus-supermarket.ru/smartfony/samsung-galaxy-s7-edge-32gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS134 =  getUrlObject("https://rus-supermarket.ru/smartfony/huawei-p10-64gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS135 =  getUrlObject("https://rus-supermarket.ru/smartfony/oukitel-k10000-pro/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS136 =  getUrlObject("https://rus-supermarket.ru/smartfony/samsung-galaxy-a3-2016/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS137 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-redmi-note-4-64gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS138 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-redmi-note-5a-16gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS139 =  getUrlObject("https://rus-supermarket.ru/smartfony/oukitel-k10000/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS140 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-redmi-4-pro/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS141 =  getUrlObject("https://rus-supermarket.ru/smartfony/huawei-honor-9-64gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS142 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-mi5-32gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS143 =  getUrlObject("https://rus-supermarket.ru/smartfony/samsung-galaxy-s8/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS144 =  getUrlObject("https://rus-supermarket.ru/igrovye-pristavki/nintendo-switch/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS145 =  getUrlObject("https://rus-supermarket.ru/smartfony/samsung-galaxy-s6-32gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS146 =  getUrlObject("https://rus-supermarket.ru/smartfony/samsung-galaxy-s6-edge-32gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS147 =  getUrlObject("https://rus-supermarket.ru/smartfony/huawei-mate-10-dual-sim/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS148 =  getUrlObject("https://rus-supermarket.ru/televizory/lg-49sj810v/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS149 =  getUrlObject("https://rus-supermarket.ru/paroochistiteli-i-otparivateli/mie-allegro/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS150 =  getUrlObject("https://rus-supermarket.ru/smartfony/apple-iphone-4-8gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS151 =  getUrlObject("https://rus-supermarket.ru/smartfony/samsung-galaxy-note-4/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS152 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-redmi-4-16gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS153 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-mi-note-3-64gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS154 =  getUrlObject("https://rus-supermarket.ru/smartfony/samsung-galaxy-note-5-32gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS155 =  getUrlObject("https://rus-supermarket.ru/smartfony/apple-iphone-se-16gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS156 =  getUrlObject("https://rus-supermarket.ru/palatki/stek-kub-3/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS157 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-mi-a1-32gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS158 =  getUrlObject("https://rus-supermarket.ru/smartfony/motorola-e398/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS159 =  getUrlObject("https://rus-supermarket.ru/smartfony/xiaomi-mi6-128gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlRS160 =  getUrlObject("https://rus-supermarket.ru/smartfony/apple-iphone-5-16gb/kupit?city=Москва","//div[@class='detail-shops-item ']",600000);
        Url urlP161 =  getUrlObject("https://pochom.ru/smartfony/xiaomi-redmi-4x/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP162 =  getUrlObject("https://pochom.ru/smartfony/appleiphone5/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP163 =  getUrlObject("https://pochom.ru/smartfony/huawei-honor-8/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP164 =  getUrlObject("https://pochom.ru/smartfony/samsung-galaxy-s6-sm-g920f-128gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP165 =  getUrlObject("https://pochom.ru/smartfony/xiaomi-mi-max-2/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP166 =  getUrlObject("https://pochom.ru/smartfony/apple-iphone-6/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP167 =  getUrlObject("https://pochom.ru/smartfony/xiaomi-redmi-note-4x/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP168 =  getUrlObject("https://pochom.ru/smartfony/xiaomi-redmi-note-5a/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP169 =  getUrlObject("https://pochom.ru/smartfony/xiaomi-mi-a1/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP170 =  getUrlObject("https://pochom.ru/smartfony/samsung-galaxy-note-4/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP171 =  getUrlObject("https://pochom.ru/smartfony/xiaomi-redmi-3s/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP172 =  getUrlObject("https://pochom.ru/smartfony/samsung-galaxy-s8/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP173 =  getUrlObject("https://pochom.ru/smartfony/apple-iphone-5s/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP174 =  getUrlObject("https://pochom.ru/smartfony/samsung-galaxy-note-5-32gb/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP175 =  getUrlObject("https://pochom.ru/smartfony/oukitel-k10000/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP176 =  getUrlObject("https://pochom.ru/smartfony/apple-iphone-se/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP177 =  getUrlObject("https://pochom.ru/smartfony/xiaomi-redmi-note-4/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP178 =  getUrlObject("https://pochom.ru/smartfony/samsung-galaxy-j7-2017/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP179 =  getUrlObject("https://pochom.ru/smartfony/sony-xperia-z1/kupit?city=Москва","//div[@class='shops-list-item ']",600000);
        Url urlP180 =  getUrlObject("https://pochom.ru/smartfony/htc-one-m8/kupit?city=Москва","//div[@class='shops-list-item ']",600000);


        queue = new LinkedList<Url>();

        queue.add(urlPTNadaviChild);
        queue.add(urlPTNadaviParent);
        queue.add(urlPTMobiguru);
        queue.add(urlRSParent);
        queue.add(urlRSChild);
        queue.add(urlVMNadaviParent);
        queue.add(urlVMNadaviChild);
        queue.add(urlVMMobiguru);
        queue.add(urlPMobiguru);
        queue.add(urlPMobiguruTest1);
        queue.add(urlPMobiguruTest2);

        queue.add(urlPT1);
        queue.add(urlPT2);
        queue.add(urlPT3);
        queue.add(urlPT4);
        queue.add(urlPT5);
        queue.add(urlPT6);
        queue.add(urlPT7);
        queue.add(urlPT8);
        queue.add(urlPT9);
        queue.add(urlPT10);
        queue.add(urlPT11);
        queue.add(urlPT12);
        queue.add(urlPT13);
        queue.add(urlPT14);
        queue.add(urlPT15);
        queue.add(urlPT16);
        queue.add(urlPT17);
        queue.add(urlPT18);
        queue.add(urlPT19);
        queue.add(urlPT20);
        queue.add(urlPT21);
        queue.add(urlPT22);
        queue.add(urlPT23);
        queue.add(urlPT24);
        queue.add(urlPT25);
        queue.add(urlPT26);
        queue.add(urlPT27);
        queue.add(urlPT28);
        queue.add(urlPT29);
        queue.add(urlPT30);
        queue.add(urlPT31);
        queue.add(urlPT32);
        queue.add(urlPT33);
        queue.add(urlPT34);
        queue.add(urlPT35);
        queue.add(urlPT36);
        queue.add(urlPT37);
        queue.add(urlPT38);
        queue.add(urlPT39);
        queue.add(urlPT40);
        queue.add(urlPT41);
        queue.add(urlPT42);
        queue.add(urlPT43);
        queue.add(urlPT44);
        queue.add(urlPT45);
        queue.add(urlPT46);
        queue.add(urlPT47);
        queue.add(urlPT48);
        queue.add(urlPT49);
        queue.add(urlPT50);
        queue.add(urlPT51);
        queue.add(urlPT52);
        queue.add(urlPT53);
        queue.add(urlPT54);
        queue.add(urlPT55);
        queue.add(urlPT56);
        queue.add(urlPT57);
        queue.add(urlPT58);
        queue.add(urlPT59);
        queue.add(urlPT60);
        queue.add(urlPT61);
        queue.add(urlPT62);
        queue.add(urlPT63);
        queue.add(urlPT64);
        queue.add(urlPT65);
        queue.add(urlPT66);
        queue.add(urlPT67);
        queue.add(urlPT68);
        queue.add(urlPT69);
        queue.add(urlPT70);
        queue.add(urlPT71);
        queue.add(urlPT72);
        queue.add(urlPT73);
        queue.add(urlPT74);
        queue.add(urlPT75);
        queue.add(urlPT76);
        queue.add(urlPT77);
        queue.add(urlPT78);
        queue.add(urlPT79);
        queue.add(urlPT80);
        queue.add(urlVM81);
        queue.add(urlVM82);
        queue.add(urlVM83);
        queue.add(urlVM84);
        queue.add(urlVM85);
        queue.add(urlVM86);
        queue.add(urlVM87);
        queue.add(urlVM88);
        queue.add(urlVM89);
        queue.add(urlVM90);
        queue.add(urlVM91);
        queue.add(urlVM92);
        queue.add(urlVM93);
        queue.add(urlVM94);
        queue.add(urlVM95);
        queue.add(urlVM96);
        queue.add(urlVM97);
        queue.add(urlVM98);
        queue.add(urlVM99);
        queue.add(urlVM100);
        queue.add(urlVM101);
        queue.add(urlVM102);
        queue.add(urlVM103);
        queue.add(urlVM104);
        queue.add(urlVM105);
        queue.add(urlVM106);
        queue.add(urlVM107);
        queue.add(urlVM108);
        queue.add(urlVM109);
        queue.add(urlVM110);
        queue.add(urlVM111);
        queue.add(urlVM112);
        queue.add(urlVM113);
        queue.add(urlVM114);
        queue.add(urlVM115);
        queue.add(urlVM116);
        queue.add(urlVM117);
        queue.add(urlVM118);
        queue.add(urlVM119);
        queue.add(urlVM120);
        queue.add(urlRS121);
        queue.add(urlRS122);
        queue.add(urlRS123);
        queue.add(urlRS124);
        queue.add(urlRS125);
        queue.add(urlRS126);
        queue.add(urlRS127);
        queue.add(urlRS128);
        queue.add(urlRS129);
        queue.add(urlRS130);
        queue.add(urlRS131);
        queue.add(urlRS132);
        queue.add(urlRS133);
        queue.add(urlRS134);
        queue.add(urlRS135);
        queue.add(urlRS136);
        queue.add(urlRS137);
        queue.add(urlRS138);
        queue.add(urlRS139);
        queue.add(urlRS140);
        queue.add(urlRS141);
        queue.add(urlRS142);
        queue.add(urlRS143);
        queue.add(urlRS144);
        queue.add(urlRS145);
        queue.add(urlRS146);
        queue.add(urlRS147);
        queue.add(urlRS148);
        queue.add(urlRS149);
        queue.add(urlRS150);
        queue.add(urlRS151);
        queue.add(urlRS152);
        queue.add(urlRS153);
        queue.add(urlRS154);
        queue.add(urlRS155);
        queue.add(urlRS156);
        queue.add(urlRS157);
        queue.add(urlRS158);
        queue.add(urlRS159);
        queue.add(urlRS160);
        queue.add(urlP161);
        queue.add(urlP162);
        queue.add(urlP163);
        queue.add(urlP164);
        queue.add(urlP165);
        queue.add(urlP166);
        queue.add(urlP167);
        queue.add(urlP168);
        queue.add(urlP169);
        queue.add(urlP170);
        queue.add(urlP171);
        queue.add(urlP172);
        queue.add(urlP173);
        queue.add(urlP174);
        queue.add(urlP175);
        queue.add(urlP176);
        queue.add(urlP177);
        queue.add(urlP178);
        queue.add(urlP179);
        queue.add(urlP180);

    }

    //создание пула урлов для проверки Api
    @BeforeClass
    public static void createPoolUrlForCheckCode() {

        UrlForCheckCode urlNadavi = new UrlForCheckCode();
        urlNadavi.setUrl(getNadaviApiString() + "&p4g_gid_=30011&p4g_city_id_=178.219.186.12");
        urlNadavi.setProvider("Nadavi");

        UrlForCheckCode urlMobiguru = new UrlForCheckCode();
        urlMobiguru.setUrl("http://api.mobiguru.ru/v1/model/120022092/offers.json" + getMobiguruApiKeyPT() + "&remote_ip=178.219.186.12");
        urlMobiguru.setProvider("Mobiguru");

        UrlForCheckCode urlPTApiNadavi = new UrlForCheckCode();
        urlPTApiNadavi.setUrl("https://product-test.ru/product/getnadavioffers?id=761667&city=%D0%9A%D0%B0%D0%B7%D0%B0%D0%BD%D1%8C");
        urlPTApiNadavi.setProvider("PTApiNadavi");

        UrlForCheckCode urlPTApiMobiguru = new UrlForCheckCode();
        urlPTApiMobiguru.setUrl("https://product-test.ru/product/getnadavioffers?id=2157&city=%D0%9A%D0%B0%D0%B7%D0%B0%D0%BD%D1%8C");
        urlPTApiMobiguru.setProvider("PTApiMobiguru");

        queueForCheckCode = new LinkedList<UrlForCheckCode>();

        queueForCheckCode.add(urlNadavi);
        queueForCheckCode.add(urlMobiguru);
        queueForCheckCode.add(urlPTApiNadavi);
        queueForCheckCode.add(urlPTApiMobiguru);

    }


    //скриншот экрана
    public static void createScreenshot(long time, String chat_id, String text) throws IOException {
        Screenshot actualScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        File fileActual = new File("C:/Users/Mirror/Desktop/product-test/screenshots/" + time + ".png");
        if (!fileActual.exists())
            fileActual.mkdirs();
        ImageIO.write(actualScreenshot.getImage(), "png", fileActual);
        sendScreenshot(time, chat_id, text);
    }

//    //ежедневный отчет
//    public static void report(Url url) throws IOException {
//
//        if (url.getStatus()) {
//            url.setTimeUp(url.timeUp + timeSinceLastChange());
//            start = System.currentTimeMillis();
//        } else {
//            url.setTimeDown(url.timeDown + timeSinceLastChange());
//            start = System.currentTimeMillis();
//        }
//        // sendMessage(idChatPT, "Статистика отображения/не отображения оферов за последние сутки (в минутах): " + (url.timeUp)/60000 + "/" + (url.timeDown)/60000);
//        sendMessage(idBogdan, "Статистика отображения/не отображения оферов для страницы " + url.getUrl() + " за последние сутки (в минутах): " + (url.timeUp) / 60000 + "/" + (url.timeDown) / 60000);
//    }

    public static void reportForTimeLoadOffers(GregorianCalendar calendar, Queue <Url> queue) throws IOException {
        Workbook workbook = null;
        Sheet sheet;

        Date date = calendar.getTime();
        SimpleDateFormat f = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat d = new SimpleDateFormat("HH:mm");

        if (d.format(date).equals("00:00")) {
            calendar.add(Calendar.DATE, -1);
            date = calendar.getTime();
        }
        String s = "C:/Users/Mirror/Desktop/product-test/reports/" + f.format(date)+ ".xls";
        File file = new File(s);
        file.getParentFile().mkdirs();

        if(!file.exists()) {
            FileOutputStream outFile = new FileOutputStream(file);
            workbook = new HSSFWorkbook();
            Sheet sheet1 = workbook.createSheet();
            Row row1 = sheet1.createRow(0);
            sheet1 = workbook.getSheetAt(0);
            sheet1.getRow(0).createCell(0).setCellValue("Url");
            sheet1.getRow(0).createCell(1).setCellValue("Время");
            sheet1.getRow(0).createCell(2).setCellValue("Кол-во успешных проверок");
            sheet1.getRow(0).createCell(3).setCellValue("Кол-во неуспешных проверок (время загрузки оферов больше 1 мин.)");
            sheet1.getRow(0).createCell(4).setCellValue("Среднее время загрузки оферов");
            sheet1.getRow(0).createCell(5).setCellValue("Среднее время прорисовки страницы");
            workbook.write(outFile);

        }
        FileInputStream inputStream = new FileInputStream(new File(s));
        workbook = new HSSFWorkbook(inputStream);
        sheet = workbook.getSheetAt(0);


        int rows = (calendar.get(Calendar.HOUR) + calendar.get(Calendar.AM_PM)*12)*6*queue.size() + (calendar.get(Calendar.MINUTE)/10)*queue.size() - 10;
       // System.out.println("строка равна " + rows);
        System.out.println("запись в отчет для " + queue.size() + " урлов");
        for (int i = 0; i < queue.size(); i++) {
            Url url = getUrlFromPool(queue);
        //    System.out.println("Запись для " + url.getUrl());
            if (rows==-10) {
                rows = 143*queue.size();
            }
            Row row = sheet.createRow(rows + i);
            row.createCell(0).setCellValue(url.getUrl());
            row.createCell(1).setCellValue(d.format(date));
            row.createCell(2).setCellValue(url.getNumCheckPass());
            row.createCell(3).setCellValue(url.getNumCheckFail());
            row.createCell(4).setCellValue(url.averageOffers);
            row.createCell(5).setCellValue(url.averagePage);

            url.setNumCheckPass(0);
            url.setNumCheckFail(0);
            url.averageOffers =0;
            url.averagePage =0;
            url.amountOfNumbers =0;
        }

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);

    }

    public static void exceptionHandling(Url url) throws IOException {

        //если оферы пропали
        if (url.getStatus()) {
            url.setMessageIsSent(false);
            url.setStatus(false);
            url.setTimeMissOffers(timeCheck);

            createScreenshot( url.getTimeMissOffers() ,idBogdan, "[Test] На странице " + url.getUrl() + " пропали оферы");
            //checkCode();

            url.setTimeUp(url.timeUp + (timeCheck - url.getTimeShowUpOffers()));
            // start = System.currentTimeMillis();

            url.setTimeShowUpOffers(0);
          //  System.out.println("Оферы пропали");

        }
        /**
        //если оферы не отображались в течение url.getTimeMessage
        if (!url.getStatus() && (timeCheck - url.getTimeMissOffers()) >= url.getTimeMessage() && !url.messageIsSent) {

            createScreenshot( url.getTimeMissOffers() ,idChatPT, "На странице " + url.getUrl() + " пропали оферы");
            // sendMessage(idChatPT, "На странице " + url.getUrl() + " пропали оферы");

            createScreenshot( url.getTimeMissOffers() ,idChatDev, "На странице " + url.getUrl() + " пропали оферы");
            // sendMessage(idChatDev, "На странице " + url.getUrl() + " пропали оферы");

            url.setMessageIsSent(true);

            System.out.println("Сообщение отправлено в чат: " + url.messageIsSent);
            System.out.println("Статус оферов: " + url.getStatus());

        }
**/
        List <WebElement> element = driver.findElements(By.className("offers-message"));
        if (element.size()==0) {
         //   System.out.println("Оферы не загрузились");
            url.toAverage((double) (System.currentTimeMillis() - time1)/1000, (double) (time1 - timeCheck)/1000);
        //    System.out.println("Время загрузки оферов равно " + (double) (System.currentTimeMillis() - time1)/1000);
        }

        url.setNumCheckFail(url.getNumCheckFail()+1);
      //  System.out.println("Кол-во неуспешных проверок " + url.getNumCheckFail());
    }

    //проверка доступности оферов
    public static void checkOffers(Url url) throws InterruptedException, IOException {
        try {
            timeCheck = System.currentTimeMillis();
           //  System.out.println("Время текущей проверки " + timeCheck);
            try {
                driver.get(url.getUrl());
            } catch (java.lang.RuntimeException ignore) {
            }

            /**
          //  (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.tagName("header")));
            (new WebDriverWait(driver, 60)).until(new ExpectedCondition <Boolean>() {
                                                      public Boolean apply(WebDriver d) {
                                                          Boolean b;
                                                          try {
                                                              System.out.println("Ищем хедер");
                                                              b = d.findElement(By.tagName("//div[@class='header-container']")).getText().length() != 0;
                                                          } catch (org.openqa.selenium.NoSuchElementException e) {
                                                              b = false;
                                                          }
                                                          return b;
                                                      }
                                                  });
             **/
            time1 = System.currentTimeMillis();
            System.out.println("Время до отрисовки страницы " + (double) (time1 - timeCheck)/1000);
            final String s = url.getSelector();
            (new WebDriverWait(driver, 60)).until(new ExpectedCondition <Boolean>() {
                public Boolean apply (WebDriver d) {
                    Boolean b;
                    try {
                        b = d.findElement(By.xpath(s)).getText().length() != 0;
                    } catch (org.openqa.selenium.NoSuchElementException e) {
                        b = false;
                    }
                    return b;
                }
            });
            long timeLoadOffers =  System.currentTimeMillis();
            url.setNumCheckPass(url.getNumCheckPass()+1);

            System.out.println("Время загрузки оферов после отрисовки страницы " + (double) (timeLoadOffers - time1)/1000);
           // System.out.println("Время загрузки оферов " + (double) (timeCheck - timeLoadOffers)/1000);
            url.toAverage((double) (timeLoadOffers - time1)/1000, (double) (time1 - timeCheck)/1000);
            System.out.println("Среднее время загрузки оферов " + url.averageOffers);
          //  System.out.println("Кол-во успешных проверок равно " + url.getNumCheckPass());
          //  System.out.println("Время загрузки оферов относительно хедера " + (double) (System.currentTimeMillis() - time1)/1000 + " секунд");
          //  System.out.println("Среднее время загрузки оферов для страницы " + url.getUrl() + " равно " + url.averageOffers);

            // System.out.println(timeCheck);
          //  System.out.println("Ищем оферы");
            driver.findElement(By.xpath(url.getSelector()));
            //  System.out.println("Для страницы " + url.getUrl() + " статус оферов " + url.getStatus());
            //  System.out.println(System.currentTimeMillis());

            if (!url.getStatus()) {

                url.setStatus(true);
                url.setTimeDown(url.timeDown + (timeCheck - url.getTimeMissOffers()));
                url.setTimeShowUpOffers(timeCheck);

                sendMessage(idBogdan, "[Test] На странице " + url.getUrl() + " появились оферы. Не отображались: " + (timeCheck - url.getTimeMissOffers()) / 1000 + " секунд");

                if (url.messageIsSent) {

                    //Отправка сообщения о появлении оферов в чаты
                    System.out.println("Оферы появились, недоступны были: " + (timeCheck - url.getTimeMissOffers()) / 1000 + "секунд");
                    sendMessage(idChatPT, "На странице " + url.getUrl() + " появились оферы. Не отображались: " + (timeCheck - url.getTimeMissOffers()) / 60000 + " минут");
                    sendMessage(idChatDev, "На странице " + url.getUrl() + " появились оферы. Не отображались: " + (timeCheck - url.getTimeMissOffers()) / 60000 + " минут");

                }
               // start = System.currentTimeMillis();
                url.setTimeMissOffers(0);
                url.setMessageIsSent(false);
            }

        } catch (org.openqa.selenium.NoSuchElementException e) {
            exceptionHandling(url);
        } catch (org.openqa.selenium.TimeoutException e) {
            exceptionHandling(url);
        }
    }


    @Test(enabled = true)
    public static void main() throws IOException, InterruptedException, URISyntaxException {
    createPoolUrl();

//        InputStream input = null;
//
//        String pathOfAbsolute = "C:\\Users\\Mirror\\Desktop\\product-test";
//        String propertiesFilePath = pathOfAbsolute + "/logging.properties";
//        propertiesFilePath = propertiesFilePath.replace("file:/", "").replace("/", "\\");
//        // System.out.println(pathOfAbsolute);
//        // System.out.println(propertiesFilePath);
//        // Paths.get(new URI(pathOfAbsolute));
//        input = ClassLoader.getSystemResourceAsStream(propertiesFilePath);
//        input = new FileInputStream(propertiesFilePath);
//
//
//        //  Properties properties = new Properties();
//        //  Thread currentThread = Thread.currentThread();
//        //  ClassLoader contextClassLoader = currentThread.getContextClassLoader();
//        InputStream propertiesStream = input;
//
//        LogManager
//                .getLogManager()
//                .readConfiguration(propertiesStream);

//        if (start == 0) {
//            start = System.currentTimeMillis();
//        }

        Url url1;
        while (true) {
            try {
                url1 = getUrlFromPool(queue);
                System.out.println("Получаем страницу для проверки " + url1.getUrl());
                GregorianCalendar gcalendar = new GregorianCalendar();

                checkOffers(url1);
              //  System.out.println("Время пропажи оферов " + url1.getTimeMissOffers());
               // System.out.println("Время появления оферов " + url1.getTimeShowUpOffers());
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

//                if (gcalendar.get(Calendar.DATE) != url1.dateLastReport) {
//                    if (gcalendar.get(Calendar.AM_PM) == 0 && gcalendar.get(Calendar.HOUR) == 9) {
//                        report(url1);
//                        url1.dateLastReport = gcalendar.get(Calendar.DATE);
//                    }
//                }
                int minute = gcalendar.get(Calendar.MINUTE);
                if (minute%10==0&&minuteLastReportToXLS!=minute) {
                    reportForTimeLoadOffers(gcalendar, queue);
                    minuteLastReportToXLS = minute;
                }


            } catch (org.openqa.selenium.WebDriverException e) {
                System.out.println("Перезапускаем браузер");
                System.err.println(e);
                driver.quit();
                browserHeadless();
            } catch (java.net.UnknownHostException e) {
                System.out.println("Ошибка java.net.UnknownHostException" + e);
                Thread.sleep(30000);
                sendMessage(idBogdan, "Ошибка java.net.UnknownHostException" + e);
            } catch (java.net.NoRouteToHostException e) {
                System.out.println("Ошибка java.net.NoRouteToHostException" + e);
                Thread.sleep(10000);

            }
        }
    }


    @Test(enabled = false)
    public static void checkApi() throws IOException, InterruptedException {

        UrlForCheckCode url;

        while (true) {
            try {
                url = getUrlFromPoolForCheckCode(queueForCheckCode);
                //  System.out.println("Проверяем метод: " + url.getUrl());
                checkCode(url);
                Thread.sleep(1000);
            } catch (java.lang.NullPointerException e) {
                System.out.println(e);
            }
        }
    }

    @Test(enabled = false)
    public static void tests() throws IOException, InterruptedException {


    }
}
