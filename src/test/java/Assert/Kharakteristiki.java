package Assert;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Kharakteristiki extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/kharakteristiki");
        driver.get(url + katalog + product + typePage);
        assertTitle("Характеристики Xiaomi Redmi 4X 32Gb (Сяоми Редми 4Кс 32Гб) | Product-test.ru");

        //проверка ссылки тизера
        driver.findElements(By.className("fresh__link")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("kupit");
        driver.navigate().back();

    }
}
