package Everyday;

import org.testng.annotations.Test;

import java.io.IOException;

public class ScreenshotsImportant extends Products {

    String url = "https://product-test.ru";

    //Где купить
    @Test
    public void kupitScreenshot () throws IOException {
        setTypePage("kupit");
        driver.get( url + katalog + product + "/kupit");
        createActualScreenshot();

    }

    //Обзор
    @Test
    public void obzorScreenshot () throws IOException {
        setTypePage("obzor");
        driver.get(url + "/proghulochnyiekoliaski/pegperegogt3nakedcompleto" + "/obzor");
        createActualScreenshot();
    }

    //Характеристики
    @Test
    public void kharakteristikiScreenshot () throws IOException {
        setTypePage("kharakteristiki");
        driver.get(url + katalog + product + "/kharakteristiki");
        createActualScreenshot();
    }

    //Главная
    @Test
    public void homePageScreenshot () throws IOException {
        setTypePage("homepage");
        driver.get(url);
        createActualScreenshot();
    }

    //404
    @Test
    public void not_foundScreenshot () throws IOException {
        setTypePage("not_found");
        driver.get(url + "/fdgdf");
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

    //products-0.xml
    @Test
    public void productsXMLScreenshot () throws IOException {
        setTypePage("products");
        driver.get(url + "/products-0.xml");
    }
}