package Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class News_all extends ProductTest {

    @Test
    protected void main() throws IOException {

        setTypePage("/news_all");
        driver.get(url + "/news");
        assertTitle("Новости | Product-test.ru");

        //проверка SEO и микроразметки
        List<WebElement> listOG = driver.findElements(By.tagName("meta"));
        System.out.println(listOG);
        for (WebElement assertOG : listOG) {
            try {
                String property = assertOG.getAttribute("property");
                String name = assertOG.getAttribute("name");
                if (property.equals("og:url")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://product-test.ru/news");
                } else if (property.equals("og:title")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Новости | Product-test.ru");
                } else if (property.equals("og:site_name")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Product-test.ru");
                } else if (property.equals("og:type")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"website");
                } else if (property.equals("og:image")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://ptfiles.blob.core.windows.net/files/d6a7a10e-7f36-4a32-9505-35f5f7e44d14_600.jpg");
                } else if (property.equals("og:description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Смотрите самые свежие новости для потребителей от экспертов Product-test.ru.");
                } else if (name.equals("description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Смотрите самые свежие новости для потребителей от экспертов Product-test.ru.");
                }  else if (name.equals("Keywords")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Новости");
                }
            } catch (NullPointerException e) {
                continue;
            }
        }

        //провека ссылок тизеров
        driver.findElement(By.cssSelector("div.fresh__item:nth-child(1) > div:nth-child(2) > h3:nth-child(1) > a:nth-child(1)")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");
        driver.navigate().back();

        driver.findElement(By.cssSelector("div.post__wrap:nth-child(3) > h3:nth-child(1)")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");

        //проверка ссылки "Все тесты"
        driver.navigate().back();
        driver.findElement(By.cssSelector(".post__more")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("tests");



    }
}
