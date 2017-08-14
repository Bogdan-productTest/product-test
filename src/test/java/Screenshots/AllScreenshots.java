package Screenshots;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AllScreenshots extends Product {

    String url = "https://product-test.ru";

    //О проекте
    @Test
    public void aboutScreenshot () throws IOException {
        setTypePage("about");
        driver.get(url + "/pages" + "/about");
        createActualScreenshot();
    }

    //Пользовательское соглашение
    @Test
    public void agreementScreenshot () throws IOException {
        setTypePage("agreement");
        driver.get(url + "/pages" + "/agreement");
        createActualScreenshot();
    }

    //Контакты
    @Test
    public void contactScreenshot () throws IOException {
        setTypePage("contact");
        driver.get(url + "/pages" + "/contact");
        createActualScreenshot();
    }

    //Гид покупателя
    @Test
    public void helperScreenshot () throws IOException {
        setTypePage("helper");
        driver.get(url + "/mikrovolnovye-pechi" + "/helper");
        createActualScreenshot();
    }

    //Главная страница
    @Test
    public void homeScreenshot () throws IOException {
        setTypePage("homepage");
        driver.get(url);
        createActualScreenshot();
    }

    //Как тестируем
    @Test
    public void kak_testiruemScreenshot () throws IOException {
        setTypePage("kak_testiruem");
        driver.get(url + katalog + "/kak-testiruem" );
        createActualScreenshot();
    }

    //Характеристики
    @Test
    public void kharakteristikiScreenshot () throws IOException {
        setTypePage("kharakteristiki");
        driver.get(url + katalog + product + "/kharakteristiki");
        createActualScreenshot();
    }

    //Где купить
    @Test
    public void kupitScreenshot () throws IOException {
        setTypePage("kupit");
        driver.get(url + katalog + product + "/kupit");
        createActualScreenshot();
    }

    //Новости
    @Test
    public void news_allScreenshot () throws IOException {
        setTypePage("news_all");
        driver.get(url + "/news");
        createActualScreenshot();
    }

    //Обзор
    @Test
    public void obzorScreenshot () throws IOException {
        setTypePage("obzor");
        driver.get(url + "/proghulochnyiekoliaski/pegperegogt3nakedcompleto" + "/obzor");
        createActualScreenshot();
    }

    //Результаты тестов
    @Test
    public void resultScreenshot () throws IOException {
        setTypePage("result");
        driver.get(url + katalog );
        createActualScreenshot();
    }

    //Полезные статьи
    @Test
    public void statiScreenshot () throws IOException {
        setTypePage("stati");
        driver.get(url + "/pages" + "/stati");
        createActualScreenshot();
    }

    //Услуги тестирования
    @Test
    public void uslugitestirovaniiaScreenshot () throws IOException {
        setTypePage("uslughitiestirovaniia");
        driver.get(url + "/pages" + "/uslughitiestirovaniia");
        createActualScreenshot();
    }

    //Каталог
    @Test
    public void katalogScreenshot () throws IOException {
        setTypePage("katalog");
        driver.get(url + katalog + "/katalog");
        createActualScreenshot();
    }

    //Макрокаталог
    @Test
    public void macro_katalogScreenshot () throws IOException {
        setTypePage("macro_katalog");
        driver.get(url + macroKatalog + "/katalog");
        createActualScreenshot();
    }

    //Результаты поиска
    @Test
    public void search_resultScreenshot () throws IOException {
        setTypePage("search_result");
        driver.get(url + "/search?Search=#gsc.tab=0&gsc.q=%D1%80%D0%B5%D0%B9%D1%82%D0%B8%D0%BD%D0%B3%20%D1%81%D0%BC%D0%B0%D1%80%D1%82%D1%84%D0%BE%D0%BD%D0%BE%D0%B2&gsc.sort=");
        createActualScreenshot();
    }

    //404
    @Test
    public void not_foundScreenshot () throws IOException {
        setTypePage("not_found");
        driver.get(url + "/fdgdf");
        createActualScreenshot();
    }

    //Обзоры
    @Test
    public void testsScreenshot () throws IOException {
        setTypePage("tests");
        driver.get(url + "/tests");
        createActualScreenshot();
    }

    //Рейтинг
    @Test
    public void ratingScreenshot () throws IOException {
        setTypePage("rating");
        driver.get(url + katalog + "/rating");
        createActualScreenshot();
    }

    //robots.txt
    @Test
    public void robotsScreenshot () throws IOException {
        setTypePage("robots");
        driver.get(url + "/robots.txt");
        createActualScreenshot();
    }

    //sitemap.xml
    @Test
    public void sitemapScreenshot () throws IOException {
        setTypePage("sitemap");
        driver.get(url + "/sitemap.xml");
        createActualScreenshot();
    }

    //Новости
    @Test
    public void newsScreenshot () throws IOException {
        setTypePage("news");
        driver.get(url + "/news/kids/2017-8-luchshix-letnix-kolyasok-po-rezultatam-testov");
        createActualScreenshot();
    }




}
