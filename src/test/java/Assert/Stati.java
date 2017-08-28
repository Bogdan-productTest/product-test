package Assert;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class Stati extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/stati");
        driver.get(url + "/pages" + "/stati");
        assertTitle("Полезные статьи для потребителей | Product-test.ru");

        //проверка SEO и микроразметки
        List<WebElement> listOG = driver.findElements(By.tagName("meta"));
        System.out.println(listOG);
        for (WebElement assertOG : listOG) {
            try {
                String property = assertOG.getAttribute("property");
                String name = assertOG.getAttribute("name");
                if (property.equals("og:url")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://product-test.ru/pages/stati");
                } else if (property.equals("og:title")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Полезные статьи для потребителей | Product-test.ru");
                } else if (property.equals("og:site_name")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Product-test.ru");
                } else if (property.equals("og:type")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"website");
                } else if (property.equals("og:description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Независимые тесты потребительских товаров в лабораториях, отзывы реальных пользователей, обзоры экспертов. Узнайте о товарах все!");
                } else if (name.equals("description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Независимые тесты потребительских товаров в лабораториях, отзывы реальных пользователей, обзоры экспертов. Узнайте о товарах все!");
                } else if (name.equals("Keywords")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Полезные статьи Product-test.ru");
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
    }
}
