package OtherScreenshots;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;




public class OtherScreenshots {
    WebDriver driver;
    String url = "https://staging.product-test.ru";

    private void createActualScreenshot (String name, String type) throws IOException {
        Screenshot actualScreenshot1 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        File filename = new File( "C:/Users/Тестер/Desktop/screenshotsStaging2508/" + type + name + ".png");
        System.out.println(filename.exists());
        if (filename.exists() == false) {
            filename.mkdirs();
        }
        ImageIO.write(actualScreenshot1.getImage(), "png", filename);
    }

    private void createScreenshotElement (WebElement element, String name) throws IOException {
        Screenshot screenshot2 = new AShot().takeScreenshot(driver, element);
        File filename = new File( "C:/Users/Тестер/Desktop/screenshotsStaging2508/" + name + ".png");
        System.out.println(filename.exists());
        if (filename.exists() == false) {
            filename.mkdirs();
        }
        ImageIO.write(screenshot2.getImage(), "png", filename);
    }

    private void adaptive () {
        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", "Nexus 5");

        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        driver = new ChromeDriver(capabilities);
    }

    private void desktop () {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @AfterTest
    private void tearDown () {driver.quit();}

    @Test
    public void screenshotsDesktop () throws IOException {

        desktop();

        //главная страница
        driver.get(url);
        createActualScreenshot ("Главная страница" , "desktop/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //гид покупателя
            driver.get(url + "/smartfony/helper");
            createActualScreenshot ("Гид покупателя" , "desktop/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //как мы тестируем
        driver.get(url + "/smartfony/kak-testiruem");
        createActualScreenshot ("Как мы тестируем" , "desktop/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //результаты тестов
            driver.get(url + "/smartfony");
            createActualScreenshot ("Результаты тестов" , "desktop/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //где купить
        driver.get(url + "/smartfony/xiaomi-redmi-4x/kupit");
        createActualScreenshot ("Где купить" , "desktop/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //обзор
            driver.get(url + "/smartfony/xiaomi-redmi-4x/obzor");
            createActualScreenshot ("Обзор" , "desktop/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //характеристики
        driver.get(url + "/smartfony/xiaomi-redmi-4x/kharakteristiki");
        createActualScreenshot ("Характеристики" , "desktop/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //новости
            driver.get(url + "/news");
            createActualScreenshot ("Новости" , "desktop/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //контакты
        driver.get(url + "/pages/contact");
        createActualScreenshot ("Контакты" , "desktop/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //услуги тестирования
            driver.get(url + "/pages/uslughitiestirovaniia");
            createActualScreenshot ("Услуги тестирования" , "desktop/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //полезные статьи
        driver.get(url + "/pages/stati");
        createActualScreenshot ("Полезные статьи" , "desktop/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //пользовательское соглашение
            driver.get(url + "/pages/agreement");
            createActualScreenshot ("Пользовательское соглашение" , "desktop/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //о проекте
        driver.get(url + "/pages/about");
        createActualScreenshot ("О проекте" , "desktop/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

//        //основное меню
//            driver.get(url);
//            createActualScreenshot ("menu" , "desktop/");
//            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //каталог
        driver.get(url + "/smartfony/katalog");
        createActualScreenshot ("Каталог" , "desktop/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //макрокатегория
            driver.get(url + "/electronics/katalog");
            createActualScreenshot ("Макрокатегория", "desktop/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //результаты поиска
        driver.get(url + "/search?Search=#gsc.tab=0&gsc.q=%D1%80%D0%B5%D0%B9%D1%82%D0%B8%D0%BD%D0%B3%20%D1%81%D0%BC%D0%B0%D1%80%D1%82%D1%84%D0%BE%D0%BD%D0%BE%D0%B2&gsc.sort=");
        createActualScreenshot ("Результаты поиска", "desktop/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //404
            driver.get(url + "/sf");
            createActualScreenshot ("404", "desktop/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //обзоры
        driver.get(url + "/tests");
        createActualScreenshot ("Обзоры", "desktop/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //рейтинг
            driver.get(url + "/smartfony/rating");
//            driver.findElement(By.cssSelector("table.rating__table:nth-child(1)"));
            createActualScreenshot ("Рейтинг", "desktop/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //форма входа
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".header__enter-btn")).click();
        createScreenshotElement (driver.findElement(By.className("newOverlay-block")), "Форма входа");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //форма регистрации
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.findElement(By.cssSelector(".header__enter-btn")).click();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.findElement(By.id("lnk-reg")).click();
            createScreenshotElement (driver.findElement(By.cssSelector("#overlay-registration > div:nth-child(1)")),"Форма регистрации");

        //форма восстановления пароля
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".header__enter-btn")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id("lnk-reminder")).click();
        createScreenshotElement (driver.findElement(By.cssSelector("#overlay-reminder > div:nth-child(1)")),"Форма восстановления пароля");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //личный кабинет
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.findElement(By.cssSelector(".header__enter-btn")).click();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.findElement(By.id("Email")).sendKeys("ruslan@product-test.ru");
            driver.findElement(By.id("Password")).sendKeys("Ot1i7ob2pE");
            driver.findElement(By.className("btn-login")).click();
            driver.get(url + "/account");
            createActualScreenshot ("Личный кабинет", "desktop/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //robot.txt
        driver.get(url + "/robots.txt");
        createActualScreenshot ("robot.txt", "desktop/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //sitemap.xml
            driver.get(url + "/sitemap.xml");
            createActualScreenshot ("sitemap.xml", "desktop/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //новость
        driver.get(url + "/news/kids/2017-8-luchshix-letnix-kolyasok-po-rezultatam-testov");
        createActualScreenshot ("Новость", "desktop/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void screenshotsMobile () throws IOException {

        adaptive();

        //главная страница
        driver.get(url);
        createActualScreenshot ("Главная страница" , "mobile/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


        //каталог
        driver.get(url + "/smartfony/katalog");
        createActualScreenshot ("Каталог", "mobile/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //макрокатегория
            driver.get(url + "/electronics/katalog");
            createActualScreenshot ("Макрокатегория", "mobile/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //обзоры
        driver.get(url + "/tests");
        createActualScreenshot ("Обзоры", "mobile/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //рейтинг
            driver.get(url + "/smartfony/rating");
            createActualScreenshot ("Рейтинг", "mobile/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //где купить
        driver.get(url + "/smartfony/xiaomi-redmi-4x/kupit");
        createActualScreenshot ("Где купить", "mobile/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //обзор
            driver.get(url + "/smartfony/xiaomi-redmi-4x/obzor");
            createActualScreenshot ("Обзор", "mobile/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //характеристики
        driver.get(url + "/smartfony/xiaomi-redmi-4x/kharakteristiki");
        createActualScreenshot ("Характеристики", "mobile/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //гид покупателя
            driver.get(url + "/smartfony/helper");
            createActualScreenshot ("Гид покупателя", "mobile/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //как мы тестируем
        driver.get(url + "/smartfony/kak-testiruem");
        createActualScreenshot ("Как мы тестируем", "mobile/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //результаты тестов
            driver.get(url + "/smartfony");
//            System.out.println(driver.findElement(By.cssSelector(".top-page-button")).getAttribute("position"));
      //  System.out.println(driver.findElement(By.cssSelector("html.no-js.html-adaptive.desktop.portrait body.firefox div.top-page-button")).getAttribute("top"));
            createActualScreenshot ("Результаты тестов", "mobile/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //новости
        driver.get(url + "/news");
        createActualScreenshot ("Новости", "mobile/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //новость
            driver.get(url + "/news/kids/2017-8-luchshix-letnix-kolyasok-po-rezultatam-testov");
            createActualScreenshot ("Новость", "mobile/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //фильтр каталога
        driver.get(url + "/smartfony/katalog");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("div.m-sort__select:nth-child(2)")).click();
        createActualScreenshot ("Фильтр каталога", "mobile/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //сортировка каталога
            driver.get(url + "/smartfony/katalog");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.findElement(By.cssSelector("div.m-sort__select:nth-child(1)")).click();
            createActualScreenshot ("Сортировка каталога", "mobile/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //сортировка макрокатегории
        driver.get(url + "/electronics/katalog");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("div.m-sort__select:nth-child(1)")).click();
        createActualScreenshot ("Сортировка макрокатегории", "mobile/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //меню каталога
            driver.get(url + "/smartfony/katalog");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.findElement(By.cssSelector(".head__menu")).click();
            createActualScreenshot ("Меню каталога", "mobile/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //основное меню
        driver.get(url + "/smartfony/katalog");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id("js-m-show-menu")).click();
        createActualScreenshot ("Основное меню", "mobile/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //поле поиска
            driver.get(url + "/smartfony/katalog");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//input[@class='header__search-input']")).click();
            createActualScreenshot ("Поле поиска", "mobile/");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //дропдаун выбора модификаций
        driver.get(url + "/smartfony/xiaomi-redmi-4x/kupit");
        driver.findElement(By.cssSelector("div.m-select:nth-child(1)")).click();
        createActualScreenshot ("Дропдаун выбора модификаций", "mobile/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //дропдаун все тесты
        driver.get(url + "/tests");
        driver.findElement(By.cssSelector(".m-select")).click();
        createActualScreenshot ("Дропдаун все тесты", "mobile/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //дропдаун все новости
        driver.get(url + "/news");
        driver.findElement(By.cssSelector(".m-select")).click();
        createActualScreenshot ("Дропдаун все новости", "mobile/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //404
        driver.get(url + "/sf");
        createActualScreenshot ("404", "mobile/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //результаты тестов
        driver.get(url + "/search?Search=рейтинг+смартфонов#gsc.tab=0&gsc.q=%D1%80%D0%B5%D0%B9%D1%82%D0%B8%D0%BD%D0%B3%20%D1%81%D0%BC%D0%B0%D1%80%D1%82%D1%84%D0%BE%D0%BD%D0%BE%D0%B2&gsc.sort=");
        createActualScreenshot ("Результаты тестов", "mobile/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    }
}
