package Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Tests extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/tests");
        driver.get(url + typePage);
        assertTitle("Обзоры и тесты товаров | Product-test.ru");

        //проверка SEO и микроразметки
        List<WebElement> listOG = driver.findElements(By.tagName("meta"));
        System.out.println(listOG);
        for (WebElement assertOG : listOG) {
            try {
                String property = assertOG.getAttribute("property");
                String name = assertOG.getAttribute("name");
                if (property.equals("og:url")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://product-test.ru/tests");
                } else if (property.equals("og:title")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Обзоры и тесты товаров | Product-test.ru");
                } else if (property.equals("og:site_name")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Product-test.ru");
                } else if (property.equals("og:type")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"website");
                } else if (property.equals("og:image")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://ptfiles.blob.core.windows.net/files/d6a7a10e-7f36-4a32-9505-35f5f7e44d14_600.jpg");
                } else if (property.equals("og:description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Смотрите самые свежие обзоры и тесты популярных товаров от экспертов Product-test.ru, проведенные по научной методике.");
                } else if (name.equals("description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Смотрите самые свежие обзоры и тесты популярных товаров от экспертов Product-test.ru, проведенные по научной методике.");
                }  else if (name.equals("Keywords")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Обзоры тесты товаров");
                }
            } catch (NullPointerException e) {
                continue;
            }
        }


        //проверка ссылки тизера
            //по названию
        driver.findElements(By.className("post__title")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");
        driver.navigate().back();

            //по иконке
        driver.findElements(By.className("post__pic")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");
        driver.navigate().back();

        //проверка ссылки тизера новости
        driver.findElements(By.className("teasers-title")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String t = driver.getCurrentUrl();
        Assert.assertEquals(driver.getCurrentUrl().substring(0,27), "https://product-test.ru/news" );
        driver.navigate().back();

        //все новости
        driver.findElements(By.className("link-arrow")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("news");

    }
}