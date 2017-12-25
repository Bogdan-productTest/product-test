package Assert.Rusupermarket;

import okhttp3.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import static Assert.MyConfig.getTelegramApiString;

public class CheckOffers {

        static WebDriver driver;
        static long start = 0;
        static String idKirill = "35658953";
        static String idBogdan = "297837315";
        static String idChatPT = "-226718542";
        static String idChatDev = "-209426772";
        static int lastChange = 0;
        static  long timeCheck = 0;
        static Queue<Url> queue;
        static Url urlPT;
        static Url urlRS;
        static String telegramApiString;

        private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");



    @BeforeClass
    public static void browserHeadless () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(
                options
        );
    }

    @BeforeClass
    public static void createPoolUrl () {

        urlPT = new Url();
            urlPT.setTimeMessage(300000);
            urlPT.setUrl("https://product-test.ru/smartfony/apple-iphone-8-64gb/kupit");
        urlRS = new Url();
            urlRS.setTimeMessage(300000);
            urlRS.setUrl("https://rus-supermarket.ru/smartfony/apple-iphone-8-64gb/kupit");
         //   urlRS.setUrl("https://rus-supermarket.ru/dietskiiesoki/malyshamiabloko/kupit");

        queue = new LinkedList<Url>();

        queue.add(urlPT);
        queue.add(urlRS);

    }

    @BeforeClass
    public static void getTelegramConfig (){
        telegramApiString = getTelegramApiString();
    }

    public static Url getUrlFromPool (Queue <Url> queue) {
        Url url = queue.peek();
        queue.poll();
        queue.add(url);
        return url;
    }

    public static void sendMessage (String chat_id, String text) throws IOException {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(telegramApiString + "/sendMessage?chat_id=" + chat_id + "&text=" + text)
                .get()
                .build();

        Response response = client.newCall(request).execute();
    }

    public static void sendScreenshot (long name, String chat_id) throws IOException {
        final OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("photo", name + ".png",
                        RequestBody.create(MEDIA_TYPE_PNG, new File("C:/Users/Mirror/Desktop/product-test/screenshots/" + name + ".png")))
                .build();

        Request request = new Request.Builder()
                .url(telegramApiString + "/sendPhoto?chat_id=" + chat_id
                        //+ "&caption=test"
                        )
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
    }

 //  @BeforeClass
    public static WebDriver browser () {
      return new ChromeDriver();
    }

    public void setTimeCheck(long timeCheck) {
        this.timeCheck = timeCheck;
    }

    //скриншот экрана
    public static void createScreenshot (String chat_id) throws IOException {
        Screenshot actualScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        File fileActual = new File("C:/Users/Mirror/Desktop/product-test/screenshots/" + timeCheck  + ".png");
        if (!fileActual.exists())
            fileActual.mkdirs();
        ImageIO.write(actualScreenshot.getImage(), "png", fileActual);
        sendScreenshot(timeCheck, chat_id);
    }


    //время с последнего изменения статуса оферов
    static public long timeSinceLastChange() {
        return System.currentTimeMillis() - start;
    }

    //ежедневный отчет
    public static void  report (Url url) throws IOException {

        if (url.getStatus()) {
            url.setTimeUp(url.timeUp + timeSinceLastChange());
            start = System.currentTimeMillis();
        }
        else {
            url.setTimeDown(url.timeDown + timeSinceLastChange());
            start = System.currentTimeMillis();
        }
       // sendMessage(idChatPT, "Статистика отображения/не отображения оферов за последние сутки (в минутах): " + (url.timeUp)/60000 + "/" + (url.timeDown)/60000);
        sendMessage(idBogdan, "Статистика отображения/не отображения оферов для страницы " + url.getUrl() + " за последние сутки (в минутах): " + (url.timeUp)/60000 + "/" + (url.timeDown)/60000);
    }

    //проверка доступности оферов
        public static void checkOffers (Url url) throws InterruptedException, IOException {
            try {
                driver.get(url.getUrl());
                //  System.out.println("Переход на страницу: " + url.getUrl());
                Thread.sleep(5000);
                timeCheck = System.currentTimeMillis();
               // System.out.println(timeCheck);
                driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[4]/div/div[2]/div[5]/div[4]"));

              //  System.out.println(System.currentTimeMillis());

                if (!url.getStatus()) {

                    url.setStatus(true);
                    url.setTimeDown(url.timeDown + timeSinceLastChange());

                    sendMessage(idBogdan, "[Test] На странице " + url.getUrl() + " появились оферы. Не отображались: " + timeSinceLastChange()/1000 + " секунд");

                    // ?
                    if (url.messageIsSent) {

                        //Отправка сообщения о появлении оферов в чаты
                        System.out.println("Оферы появились, недоступны были: " + timeSinceLastChange() / 1000 + "секунд");
                        sendMessage(idChatPT, "На странице " + url.getUrl() + " появились оферы. Не отображались: " + timeSinceLastChange()/60000 + " минут");
                        sendMessage(idChatDev, "На странице " + url.getUrl() + " появились оферы. Не отображались: " + timeSinceLastChange()/60000 + " минут");

                    }
                    start = System.currentTimeMillis();
                    url.setMessageIsSent(false);
                }

            } catch (org.openqa.selenium.NoSuchElementException e) {
                if (url.getStatus()) {
                    url.setMessageIsSent(false);
                    url.setStatus(false);


                    createScreenshot(idBogdan);
                    sendMessage(idBogdan, "[Test] На странице " + url.getUrl() + " пропали оферы");


                    url.setTimeUp(url.timeUp + timeSinceLastChange());
                    start = System.currentTimeMillis();

                    System.out.println("Оферы пропали");

                } else if (timeSinceLastChange()>=url.getTimeMessage() && !url.messageIsSent) {

                    //отправка сообщения в чат РТ
                    createScreenshot(idChatPT);
                    sendMessage(idChatPT, "На странице " + url.getUrl() + " пропали оферы");

                    //отправка сообщения в чат с разработчиками
                    createScreenshot(idChatDev);
                    sendMessage(idChatDev, "На странице " + url.getUrl() + " пропали оферы");


                    url.setMessageIsSent(true);

                    System.out.println("Сообщение отправлено в чат: " + url.messageIsSent);
                    System.out.println("Статус оферов: " + url.getStatus());

                }


            }
        }


        @Test
        public static void main() throws IOException, InterruptedException {
            Url url1;
            //= new Url();
          //  url1.setUrl(
                  // "https://rus-supermarket.ru/smartfony/apple-iphone-8-64gb/kupit"
                   // "https://rus-supermarket.ru/dietskiiesoki/malyshamiabloko/kupit"
         //   );
         //   url1.setTimeMessage(300000);

            if (start == 0) {
                start = System.currentTimeMillis();
               // System.out.println(start);
            }

            while (true) {
                try {
                    url1 = getUrlFromPool(queue);
                   // url1.setUrl(url1.getUrlFromPool(queue));
                    checkOffers(url1);
                    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

                    GregorianCalendar gcalendar = new GregorianCalendar();
                    if (gcalendar.get(Calendar.DATE)!= url1.dateLastReport) {
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
                }

                }
            }
        }
