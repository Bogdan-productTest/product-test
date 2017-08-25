package Assert;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Result extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/result");
        driver.get(url + katalog );
        assertTitle("Лучшие смартфоны 2017: обзоры, отзывы о телефонах, тесты | Product-test.ru");

        //проверка ссылки тизера
        driver.findElements(By.className("fresh__link")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");
    }
}
