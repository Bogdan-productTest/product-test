package Assert;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Tests extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/tests");
        driver.get(url + typePage);
        assertTitle("Обзоры и тесты товаров | Product-test.ru");

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