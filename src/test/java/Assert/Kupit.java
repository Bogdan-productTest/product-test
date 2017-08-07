package Assert;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Kupit extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/kupit");
        driver.get("https://product-test.ru" + katalog + product + typePage);
        assertTitle("Купить Xiaomi Redmi 4X 16Gb, цены Сяоми Редми 4Кс 16Гб | Product-test.ru");

        //проверка ссылки тизера
        driver.findElements(By.className("fresh__link")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("kupit");
    }


}
