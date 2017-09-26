package Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Katalog extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/katalog");
        driver.get(url + katalog + typePage );
        assertTitle("Купить смартфон недорого: цены, характеристики и каталог смартфонов | Product-test.ru");

        //проверка SEO и микроразметки
        List<WebElement> listOG = driver.findElements(By.tagName("meta"));
        System.out.println(listOG);
        for (WebElement assertOG : listOG) {
            try {
                String property = assertOG.getAttribute("property");
                String name = assertOG.getAttribute("name");
                if (property.equals("og:url")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://product-test.ru/smartfony/katalog");
                } else if (property.equals("og:title")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Купить смартфон недорого: цены, характеристики и каталог смартфонов | Product-test.ru");
                } else if (property.equals("og:site_name")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Product-test.ru");
                } else if (property.equals("og:type")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"website");
                } else if (property.equals("og:image")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://ptfiles.blob.core.windows.net/files/d6a7a10e-7f36-4a32-9505-35f5f7e44d14_600.jpg");
                } else if (property.equals("og:description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Здесь можно выбрать и купить смартфон по параметрам, отзывам и цене. Большой выбор моделей от проверенных магазинов.");
                } else if (name.equals("description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Здесь можно выбрать и купить смартфон по параметрам, отзывам и цене. Большой выбор моделей от проверенных магазинов.");
                }  else if (name.equals("Keywords")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "смартфоны обзор отзывы рейтинг лучшие хорошие мобильные телефоны каталог 2017 год цены тесты самые хорошие");
                }
            } catch (NullPointerException e) {
                continue;
            }
        }


        //проверка ссылок товаров
            //по иконке
        driver.findElements(By.className("prod__preview")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("kupit");
        driver.navigate().back();

            //по названию
        driver.findElements(By.className("prod__title-link")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("kupit");
        driver.navigate().back();

            //по цене
        driver.findElements(By.className("prod__price-value")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("kupit");
        driver.navigate().back();

            //оценка product-test
        driver.findElement(By.linkText("Оценка Product-test:")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");


    }
}