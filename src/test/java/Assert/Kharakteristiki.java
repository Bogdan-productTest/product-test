package Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Kharakteristiki extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/kharakteristiki");
        driver.get(url + katalog + product + typePage);
        assertTitle("Характеристики Xiaomi Redmi 4X 32Gb (Сяоми Редми 4Кс 32Гб) | Product-test.ru");

        //проверка ссылки тизера
        driver.findElements(By.className("fresh__link")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("kupit");
        driver.navigate().back();

        //проверка SEO и микроразметки
        List<WebElement> listOG = driver.findElements(By.tagName("meta"));
        System.out.println(listOG);
        for (WebElement assertOG : listOG) {
            try {
                String property = assertOG.getAttribute("property");
                String name = assertOG.getAttribute("name");
                if (property.equals("og:url")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://product-test.ru/smartfony/xiaomi-redmi-4x-32gb/kharakteristiki");
                } else if (property.equals("og:title")) {
                    Assert.assertTrue(assertOG.getAttribute("content").equals("Смартфон Xiaomi Redmi 4X 32Gb: Характеристики")||assertOG.getAttribute("content").equals("Характеристики Xiaomi Redmi 4X 32Gb (Сяоми Редми 4Кс 32Гб) | Product-test.ru"));
                } else if (property.equals("og:site_name")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Product-test.ru");
                } else if (property.equals("og:type")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"website");
                } else if (property.equals("og:image")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://images.product-test.ru/sourceimages/60/452/217/12c7a926-2952-49f3-b63e-6ebe2053965f.jpg?preset=popup");
                } else if (property.equals("og:description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Полные характеристики Смартфон Xiaomi Redmi 4X 32Gb с описанием всех особенностей. Сравните Xiaomi Redmi 4X 32Gb с основными конкурентами.");
                } else if (name.equals("description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Полные характеристики Смартфон Xiaomi Redmi 4X 32Gb с описанием всех особенностей. Сравните Xiaomi Redmi 4X 32Gb с основными конкурентами.");
                }  else if (name.equals("Keywords")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Характеристики Xiaomi Redmi 4X 32Gb Сяоми Редми 4Кс 32Гб Product-test.ru");
                }
            } catch (NullPointerException e) {
                continue;
            }
        }

    }
}
