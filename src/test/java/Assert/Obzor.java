package Assert;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Obzor extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/obzor");
        driver.get("https://product-test.ru" + katalog + product + typePage);
        assertTitle("Xiaomi Redmi 4X - обзор, отзывы о Сяоми Редми 4X | Product-test.ru");

        //проверка ссылки тизера
        driver.findElements(By.className("fresh__link")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");
    }
}
