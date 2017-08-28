package Assert;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Helper extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/helper");
        driver.get(url + katalog + typePage);
        assertTitle("Какой телефон лучше купить в 2017 году, как выбрать смартфон | Product-test.ru");

        //проверка ссылки тизера
        driver.findElements(By.className("fresh__link")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");

        //проверка SEO и микроразметки
        List<WebElement> listOG = driver.findElements(By.tagName("meta"));
        System.out.println(listOG);
        for (WebElement assertOG : listOG) {
            try {
                String property = assertOG.getAttribute("property");
                String name = assertOG.getAttribute("name");
                if (property.equals("og:url")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://product-test.ru/smartfony/helper");
                } else if (property.equals("og:title")) {
                    Assert.assertTrue(assertOG.getAttribute("content").equals("Смартфон — как выбрать?")||assertOG.getAttribute("content").equals("Какой телефон лучше купить в 2017 году, как выбрать смартфон | Product-test.ru "));
                } else if (property.equals("og:site_name")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Product-test.ru");
                } else if (property.equals("og:type")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"website");
                } else if (property.equals("og:image")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://ptfiles.blob.core.windows.net/files/8efb71e1-1140-4463-aa83-7022bf1b4d6e_600.jpg");
                } else if (property.equals("og:description")) {
                    Assert.assertTrue(assertOG.getAttribute("content").equals("Узнайте всё самое важное в выборе смартфона еще до его покупки.")||assertOG.getAttribute("content").equals("Гид покупателя смартфона — простые советы экспертов: какой телефон лучше купить, как выбрать смартфон и на что стоит обратить внимание."));
                } else if (name.equals("description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Гид покупателя смартфона — простые советы экспертов: какой телефон лучше купить, как выбрать смартфон и на что стоит обратить внимание.");
                }  else if (name.equals("Keywords")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "смартфоны обзор отзывы рейтинг лучшие хорошие мобильные телефоны каталог 2017 год цены тесты самые хорошие");
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
    }
}
