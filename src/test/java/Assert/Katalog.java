package Assert;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Katalog extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/katalog");
        driver.get(url + katalog + typePage );
        assertTitle("Купить смартфон недорого: цены, характеристики и каталог смартфонов | Product-test.ru");

        //проверка ссылок товаров
            //по иконке
        driver.findElements(By.className("prod__preview")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("kupit");
        driver.navigate().back();

            //по названию
        driver.findElements(By.className("prod__title-link")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("kupit");
        driver.navigate().back();

            //по цене
        driver.findElements(By.className("prod__price-value")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("kupit");
        driver.navigate().back();

            //оценка product-test
        driver.findElement(By.linkText("Оценка Product-test:")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");
    }
}
