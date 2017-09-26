package Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Result extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/result");
        driver.get(url + katalog );
        assertTitle("Лучшие смартфоны 2017: обзоры, отзывы о телефонах, тесты | Product-test.ru");

        //проверка SEO и микроразметки
        List<WebElement> listOG = driver.findElements(By.tagName("meta"));
        System.out.println(listOG);
        for (WebElement assertOG : listOG) {
            try {
                String property = assertOG.getAttribute("property");
                String name = assertOG.getAttribute("name");
                if (property.equals("og:url")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://product-test.ru/smartfony");
                } else if (property.equals("og:title")) {
                    Assert.assertTrue(assertOG.getAttribute("content").equals("Смартфоны (мобильные телефоны)")||assertOG.getAttribute("content").equals("Лучшие смартфоны 2017: обзоры, отзывы о телефонах, тесты | Product-test.ru "));
                } else if (property.equals("og:site_name")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Product-test.ru");
                } else if (property.equals("og:type")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"website");
                } else if (property.equals("og:image")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://ptfiles.blob.core.windows.net/files/8efb71e1-1140-4463-aa83-7022bf1b4d6e_600.jpg");
                } else if (property.equals("og:description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Интересные результаты тестов телефонов независимыми экспертами — узнайте, какие смартфоны оказались самыми лучшими, обзоры и отзывы о телефонах.");
                } else if (name.equals("description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Интересные результаты тестов телефонов независимыми экспертами — узнайте, какие смартфоны оказались самыми лучшими, обзоры и отзывы о телефонах.");
                }  else if (name.equals("Keywords")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "смартфоны обзор отзывы рейтинг лучшие хорошие мобильные телефоны каталог 2017 год цены тесты самые хорошие");
                }
            } catch (NullPointerException e) {
                continue;
            }
        }


        //проверка ссылки тизера
        driver.findElements(By.className("fresh__link")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");

    }
}
