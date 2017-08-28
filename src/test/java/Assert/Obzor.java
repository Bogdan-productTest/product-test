package Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Obzor extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/obzor");
        driver.get(url + katalog + product + typePage);
        assertTitle("Xiaomi Redmi 4X - обзор, отзывы о Сяоми Редми 4X | Product-test.ru");

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
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://product-test.ru/smartfony/xiaomi-redmi-4x/obzor");
                } else if (property.equals("og:title")) {
                    Assert.assertTrue(assertOG.getAttribute("content").equals("Смартфон Xiaomi Redmi 4X: Обзор")||assertOG.getAttribute("content").equals("Xiaomi Redmi 4X - обзор, отзывы о Сяоми Редми 4X | Product-test.ru"));
                } else if (property.equals("og:site_name")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Product-test.ru");
                } else if (property.equals("og:type")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"website");
                } else if (property.equals("og:image")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://ptfiles.blob.core.windows.net/files/92a6dab9-fd82-4454-a9cc-013d12501dde.jpg");
                } else if (property.equals("og:description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Обзор Xiaomi Redmi 4X: тесты производительности, экрана, камер, батареи смартфона по научной методике, а также отзывы владельцев и экспертов.");
                } else if (name.equals("description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Обзор Xiaomi Redmi 4X: тесты производительности, экрана, камер, батареи смартфона по научной методике, а также отзывы владельцев и экспертов.");
                }  else if (name.equals("Keywords")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Смартфон Xiaomi Redmi 4X 16gb 32gb 3gb black 5.0 обзор отзывы цена характеристики где купить Сяоми Редми 4X");
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
    }
}
