package Assert;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Macro_katalog extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/electronics/katalog");
        driver.get(url + typePage );
        assertTitle("Электроника | Product-test.ru");

        //проверка ссылки "Все характеристики"
        driver.findElement(By.linkText("Все характеристики")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("kharakteristiki");

    }
}