package Assert.Rusupermarket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static Assert.MyConfig.getMobiguruApiKeyPT;
import static Assert.MyConfig.getNadaviApiString;
import static Assert.Rusupermarket.Methods.*;

public class CheckOffers {

    private static WebDriver driver;
    private static long start = 0;
    private static String idKirill = "35658953";
    protected static String idBogdan = "297837315";
    private static String idChatPT = "-226718542";
    private static String idChatDev = "-209426772";
    private static int lastChange = 0;
    private static long timeCheck = 0;
    private static Queue<Url> queue;
    private static Queue<UrlForCheckCode> queueForCheckCode;
    private static Url urlPTNadaviChild;
    private static Url urlPTNadaviParent;
    private static Url urlPTMobiguruChild;
    private static Url urlRSChild;
    private static Url urlRSParent;
    private static UrlForCheckCode urlNadavi;
    private static UrlForCheckCode urlMobiguru;
    private static UrlForCheckCode urlPTApiNadavi;
    private static UrlForCheckCode urlPTApiMobiguru;

    private static Logger log = Logger.getLogger(CheckOffers.class.getName());

    @BeforeClass
    public static void browserHeadless() throws FileNotFoundException, URISyntaxException {
        ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
        driver = new ChromeDriver(
                           options
        );
    }

    //создание пула урлов для проверки
    @BeforeClass
    public static void createPoolUrl() {

        urlPTNadaviChild = new Url();
        urlPTNadaviChild.setTimeMessage(300000);
        urlPTNadaviChild.setUrl("https://product-test.ru/smartfony/apple-iphone-8-64gb/kupit");
        //    urlPTNadaviChild.setUrl("https://product-test.ru/smartfony/huawei-nexus-6p-32gb/kupit");
        urlPTNadaviChild.setSelector("//div[@class='buy__item']");

        urlPTNadaviParent = new Url();
        urlPTNadaviParent.setTimeMessage(300000);
        urlPTNadaviParent.setUrl("https://product-test.ru/shiny/nokian-nordman-7/kupit");
        urlPTNadaviParent.setSelector("//div[@class='buy__item']");

        urlPTMobiguruChild = new Url();
        urlPTMobiguruChild.setTimeMessage(300000);
        urlPTMobiguruChild.setUrl("https://product-test.ru/notebooks/xps13/kupit");
        urlPTMobiguruChild.setSelector("//div[@class='buy__item']");

        urlRSChild = new Url();
        urlRSChild.setTimeMessage(300000);
        urlRSChild.setUrl("https://rus-supermarket.ru/smartfony/apple-iphone-8-64gb/kupit");
        //    urlRSChild.setUrl("https://rus-supermarket.ru/dietskiiesoki/malyshamiabloko/kupit");
        urlRSChild.setSelector("//div[@class='detail-shops-item ']");

        urlRSParent = new Url();
        urlRSParent.setTimeMessage(300000);
        urlRSParent.setUrl("https://rus-supermarket.ru/shiny/nokian-nordman-7/kupit");
        urlRSParent.setSelector("//div[@class='detail-shops-item ']");

        queue = new LinkedList<Url>();

        queue.add(urlPTNadaviChild);
        queue.add(urlPTNadaviParent);
        queue.add(urlPTMobiguruChild);
        queue.add(urlRSParent);
        queue.add(urlRSChild);

    }

    //создание пула урлов для проверки Api
    @BeforeClass
    public static void createPoolUrlForCheckCode() {

        urlNadavi = new UrlForCheckCode();
        urlNadavi.setUrl(getNadaviApiString() + "&p4g_gid_=30011&p4g_city_id_=178.219.186.12");
        urlNadavi.setProvider("Nadavi");

        urlMobiguru = new UrlForCheckCode();
        urlMobiguru.setUrl("http://api.mobiguru.ru/v1/model/120022092/offers.json" + getMobiguruApiKeyPT() + "&remote_ip=178.219.186.12");
        urlMobiguru.setProvider("Mobiguru");

        urlPTApiNadavi = new UrlForCheckCode();
        urlPTApiNadavi.setUrl("https://product-test.ru/product/getnadavioffers?id=761667&city=%D0%9A%D0%B0%D0%B7%D0%B0%D0%BD%D1%8C");
        urlPTApiNadavi.setProvider("PTApiNadavi");

        urlPTApiMobiguru = new UrlForCheckCode();
        urlPTApiMobiguru.setUrl("https://product-test.ru/product/getnadavioffers?id=2157&city=%D0%9A%D0%B0%D0%B7%D0%B0%D0%BD%D1%8C");
        urlPTApiMobiguru.setProvider("PTApiMobiguru");

        queueForCheckCode = new LinkedList<UrlForCheckCode>();

        queueForCheckCode.add(urlNadavi);
        queueForCheckCode.add(urlMobiguru);
        queueForCheckCode.add(urlPTApiNadavi);
        queueForCheckCode.add(urlPTApiMobiguru);

    }


    //скриншот экрана
    public static void createScreenshot(String chat_id, String text) throws IOException {
        Screenshot actualScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        File fileActual = new File("C:/Users/Mirror/Desktop/product-test/screenshots/" + timeCheck + ".png");
        if (!fileActual.exists())
            fileActual.mkdirs();
        ImageIO.write(actualScreenshot.getImage(), "png", fileActual);
        sendScreenshot(timeCheck, chat_id, text);
    }

    //время с последнего изменения статуса оферов
    static public long timeSinceLastChange() {
        return System.currentTimeMillis() - start;
    }

    //ежедневный отчет
    public static void report(Url url) throws IOException {

        if (url.getStatus()) {
            url.setTimeUp(url.timeUp + timeSinceLastChange());
            start = System.currentTimeMillis();
        } else {
            url.setTimeDown(url.timeDown + timeSinceLastChange());
            start = System.currentTimeMillis();
        }
        // sendMessage(idChatPT, "Статистика отображения/не отображения оферов за последние сутки (в минутах): " + (url.timeUp)/60000 + "/" + (url.timeDown)/60000);
        sendMessage(idBogdan, "Статистика отображения/не отображения оферов для страницы " + url.getUrl() + " за последние сутки (в минутах): " + (url.timeUp) / 60000 + "/" + (url.timeDown) / 60000);
    }

    public static void exceptionHandling (Url url) throws IOException {

        if (url.getStatus()) {
            url.setMessageIsSent(false);
            url.setStatus(false);


            createScreenshot(idBogdan, "[Test] На странице " + url.getUrl() + " пропали оферы");

            url.setTimeUp(url.timeUp + timeSinceLastChange());
            start = System.currentTimeMillis();

            System.out.println("Оферы пропали");

        } else if (timeSinceLastChange() >= url.getTimeMessage() && !url.messageIsSent) {

            createScreenshot(idChatPT, "На странице " + url.getUrl() + " пропали оферы");
            // sendMessage(idChatPT, "На странице " + url.getUrl() + " пропали оферы");

            createScreenshot(idChatDev, "На странице "  + url.getUrl() + " пропали оферы");
            // sendMessage(idChatDev, "На странице " + url.getUrl() + " пропали оферы");

            url.setMessageIsSent(true);

            System.out.println("Сообщение отправлено в чат: " + url.messageIsSent);
            System.out.println("Статус оферов: " + url.getStatus());

        }

    }

    //проверка доступности оферов
    public static void checkOffers(Url url) throws InterruptedException, IOException {
        try {
            driver.get(url.getUrl());

            try {
                WebElement explicitWait = (new WebDriverWait(driver, 10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(url.getSelector())));
            } catch (org.openqa.selenium.TimeoutException e) {
                WebElement explicitWait1 = (new WebDriverWait(driver, 10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("offers-message")));
            }

            //        Thread.sleep(5000);
            timeCheck = System.currentTimeMillis();
            // System.out.println(timeCheck);
            driver.findElement(By.xpath(url.getSelector()));
            System.out.println("Для страницы " + url.getUrl() + " статус оферов " + url.getStatus());
            //  System.out.println(System.currentTimeMillis());

            if (!url.getStatus()) {

                url.setStatus(true);
                url.setTimeDown(url.timeDown + timeSinceLastChange());

                sendMessage(idBogdan, "[Test] На странице " + url.getUrl() + " появились оферы. Не отображались: " + timeSinceLastChange() / 1000 + " секунд");


                if (url.messageIsSent) {

                    //Отправка сообщения о появлении оферов в чаты
                    System.out.println("Оферы появились, недоступны были: " + timeSinceLastChange() / 1000 + "секунд");
                    sendMessage(idChatPT, "На странице " + url.getUrl() + " появились оферы. Не отображались: " + timeSinceLastChange() / 60000 + " минут");
                    sendMessage(idChatDev, "На странице " + url.getUrl() + " появились оферы. Не отображались: " + timeSinceLastChange() / 60000 + " минут");

                }
                start = System.currentTimeMillis();
                url.setMessageIsSent(false);
            }

        } catch (org.openqa.selenium.NoSuchElementException e) {

            exceptionHandling(url);

        } catch (org.openqa.selenium.TimeoutException e) {

            exceptionHandling(url);

        }
    }


    @Test
    public static void main() throws IOException, InterruptedException, URISyntaxException {

        InputStream input = null;

        String pathOfAbsolute = "C:\\Users\\Mirror\\Desktop\\product-test";
        String propertiesFilePath = pathOfAbsolute + "/logging.properties";
        propertiesFilePath = propertiesFilePath.replace("file:/", "").replace("/", "\\");
        // System.out.println(pathOfAbsolute);
        // System.out.println(propertiesFilePath);
        // Paths.get(new URI(pathOfAbsolute));
        input = ClassLoader.getSystemResourceAsStream(propertiesFilePath);
        input = new FileInputStream(propertiesFilePath);


        //  Properties properties = new Properties();
        //  Thread currentThread = Thread.currentThread();
        //  ClassLoader contextClassLoader = currentThread.getContextClassLoader();
        InputStream propertiesStream = input;

        LogManager
                .getLogManager()
                .readConfiguration(propertiesStream);

        Url url1;

        if (start == 0) {
            start = System.currentTimeMillis();
        }

            while (true) {
                try {
                    url1 = getUrlFromPool(queue);

                    // url1.setUrl(url1.getUrlFromPool(queue));
                    checkOffers(url1);
                    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

                    GregorianCalendar gcalendar = new GregorianCalendar();
                    if (gcalendar.get(Calendar.DATE) != url1.dateLastReport) {
                        if (gcalendar.get(Calendar.AM_PM) == 0 && gcalendar.get(Calendar.HOUR) == 9) {
                            report(url1);
                            url1.dateLastReport = gcalendar.get(Calendar.DATE);
                        }
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


        @Test
        public static void checkApi () throws IOException, InterruptedException {

            UrlForCheckCode url;

            while (true) {

                try {
                    url = getUrlFromPoolForCheckCode(queueForCheckCode);
                    System.out.println("Проверяем метод: " + url.getUrl());
                    checkCode(url);
                    Thread.sleep(1000);

                } catch (java.lang.NullPointerException e) {
                    System.out.println(e);
                }

            }
        }
    }
