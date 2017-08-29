package Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Macro_katalog extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/electronics/katalog");
        driver.get(url + typePage );
        assertTitle("Электроника | Product-test.ru");

        //проверка SEO и микроразметки
        List<WebElement> listOG = driver.findElements(By.tagName("meta"));
        System.out.println(listOG);
        for (WebElement assertOG : listOG) {
            try {
                String property = assertOG.getAttribute("property");
                String name = assertOG.getAttribute("name");
                if (property.equals("og:url")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://product-test.ru/electronics/katalog");
                } else if (property.equals("og:title")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Электроника");
                } else if (property.equals("og:site_name")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Product-test.ru");
                } else if (property.equals("og:type")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"website");
                }
            } catch (NullPointerException e) {
                continue;
            }
        }

        //проверка ссылки "Все характеристики"
        driver.findElement(By.linkText("Все характеристики")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("kharakteristiki");

    }
}