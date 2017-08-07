package Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class News_all extends ProductTest {

    @Test
    protected void main() throws IOException {

        setTypePage("/news_all");
        driver.get("https://product-test.ru" + "/news");
        assertTitle("Новости | Product-test.ru");

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
