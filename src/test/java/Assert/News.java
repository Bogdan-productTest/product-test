package Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class News extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/news");
        driver.get(url + "/news/kids/2017-8-luchshix-letnix-kolyasok-po-rezultatam-testov");
        assertTitle("8 лучших летних колясок по результатам тестов | Product-test.ru");

        //проверка SEO и микроразметки
        List<WebElement> listOG = driver.findElements(By.tagName("meta"));
        System.out.println(listOG);
        for (WebElement assertOG : listOG) {
            try {
                String property = assertOG.getAttribute("property");
                String name = assertOG.getAttribute("name");
                if (property.equals("og:url")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://product-test.ru/news/kids/2017-8-luchshix-letnix-kolyasok-po-rezultatam-testov");
                } else if (property.equals("og:title")) {
                    Assert.assertTrue(assertOG.getAttribute("content").equals("8 лучших колясок на лето")||assertOG.getAttribute("content").equals("8 лучших летних колясок по результатам тестов | Product-test.ru"));
                } else if (property.equals("og:site_name")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Product-test.ru");
                } else if (property.equals("og:type")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"website");
                } else if (property.equals("og:image")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://ptfiles.blob.core.windows.net/files/9a172ed2-dd06-418f-b2d3-c7ce3afdd37c.jpg");
                } else if (property.equals("og:description")) {
                    Assert.assertTrue(assertOG.getAttribute("content").equals("Найдите свою модель.")||assertOG.getAttribute("content").equals("Эксперты Product-test.ru выбрали 8 лучших летних прогулочных колясок на основе тестов. Узнайте, какие летние коляски самые легкие и удобные."));
                } else if (name.equals("description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Эксперты Product-test.ru выбрали 8 лучших летних прогулочных колясок на основе тестов. Узнайте, какие летние коляски самые легкие и удобные.");
                }  else if (name.equals("Keywords")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "лучшие летние коляски детские прогулочные трость на лето");
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
    }
}