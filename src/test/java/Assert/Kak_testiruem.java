package Assert;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Kak_testiruem extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/kak-testiruem");
        driver.get(url + katalog + typePage );
        assertTitle("Как мы тестируем смартфоны | Product-test.ru");

        //проверка ссылки тизера
        driver.findElements(By.className("fresh__link")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");
    }
}
