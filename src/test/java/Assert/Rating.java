package Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Rating extends ProductTest{

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/rating");
        driver.get(url + katalog + typePage);
        assertTitle("Рейтинг смартфонов и телефонов 2017: цены, модели, топ-телефоны | Product-test.ru");

        //проверка SEO и микроразметки
        List<WebElement> listOG = driver.findElements(By.tagName("meta"));
        System.out.println(listOG);
        for (WebElement assertOG : listOG) {
            try {
                String property = assertOG.getAttribute("property");
                String name = assertOG.getAttribute("name");
                if (property.equals("og:url")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://product-test.ru/smartfony/rating");
                } else if (property.equals("og:title")) {
                    Assert.assertTrue(assertOG.getAttribute("content").equals("Рейтинг смартфонов (телефонов)")||assertOG.getAttribute("content").equals("Рейтинг смартфонов и телефонов 2017: цены, модели, топ-телефоны | Product-test.ru "));
                } else if (property.equals("og:site_name")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Product-test.ru");
                } else if (property.equals("og:type")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"website");
                } else if (property.equals("og:image")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://ptfiles.blob.core.windows.net/files/8efb71e1-1140-4463-aa83-7022bf1b4d6e_600.jpg");
                } else if (property.equals("og:description")) {
                    Assert.assertTrue(assertOG.getAttribute("content").equals("Сравнивайте и выбирайте смартфон на основе объективной информации.")||assertOG.getAttribute("content").equals("Сравните более 100 популярных смартфонов в едином рейтинге. Объективная информация о мобильных телефонах по результатам тестов экспертов."));
                } else if (name.equals("description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Сравните более 100 популярных смартфонов в едином рейтинге. Объективная информация о мобильных телефонах по результатам тестов экспертов.");
                }  else if (name.equals("Keywords")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "смартфоны обзор отзывы рейтинг лучшие хорошие мобильные телефоны каталог 2017 год цены тесты самые хорошие");
                }
            } catch (NullPointerException e) {
                continue;
            }
        }


        //проверка ссылок товаров
            //по названию
        driver.findElement(By.cssSelector("#raiting-body > tr:nth-child(1) > td:nth-child(1) > a:nth-child(2)")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");
        driver.navigate().back();

            //по цене
        driver.findElement(By.cssSelector("#raiting-body > tr:nth-child(1) > td:nth-child(2) > a:nth-child(1)")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("kupit");
        driver.navigate().back();

        //проверка ссылки тизера
        driver.findElements(By.className("fresh__link")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");


    }
}