package Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Kupit extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/kupit");
        driver.get(url + katalog + product + typePage);
        assertTitle("Купить Xiaomi Redmi 4X 16Gb, цены Сяоми Редми 4Кс 16Гб | Product-test.ru");

        //проверка SEO и микроразметки
        List<WebElement> listOG = driver.findElements(By.tagName("meta"));
        System.out.println(listOG);
        for (WebElement assertOG : listOG) {
            try {
                String property = assertOG.getAttribute("property");
                String name = assertOG.getAttribute("name");
                if (property.equals("og:url")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "https://product-test.ru/smartfony/xiaomi-redmi-4x-16gb/kupit");
                } else if (property.equals("og:title")) {
                    Assert.assertTrue(assertOG.getAttribute("content").equals("Смартфон Xiaomi Redmi 4X 16Gb: Где купить") || assertOG.getAttribute("content").equals("Купить Xiaomi Redmi 4X 16Gb, цены Сяоми Редми 4Кс 16Гб | Product-test.ru"));
                } else if (property.equals("og:site_name")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Product-test.ru");
                } else if (property.equals("og:type")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "website");
                } else if (property.equals("og:image")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "https://images.product-test.ru/sourceimages/3/492/302/e10caec8-4bc1-42af-a35c-45779013e1a6.jpg?preset=popup");
                } else if (property.equals("og:description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Сэкономьте! Купите Xiaomi Redmi 4X 16Gb по лучшим ценам в удобном месте. Большой выбор предложений обычных и интернет-магазинов.");
                } else if (name.equals("description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Сэкономьте! Купите Xiaomi Redmi 4X 16Gb по лучшим ценам в удобном месте. Большой выбор предложений обычных и интернет-магазинов.");
                } else if (name.equals("Keywords")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Купить Xiaomi Redmi 4X 16Gb цены Сяоми Редми 4Кс 16Гб Product-test.ru");
                }
            } catch (NullPointerException e) {
                continue;
            }
        }



        //проверка ссылки тизера
        driver.findElements(By.className("fresh__link")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("kupit");

    }
}
