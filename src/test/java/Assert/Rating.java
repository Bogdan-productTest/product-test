package Assert;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Rating extends ProductTest{

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/rating");
        driver.get("https://product-test.ru" + katalog + typePage);
        assertTitle("Рейтинг смартфонов и телефонов 2017: цены, модели, топ-телефоны | Product-test.ru");

        //проверка ссылок товаров
            //по названию
        driver.findElement(By.cssSelector("#raiting-body > tr:nth-child(1) > td:nth-child(1) > a:nth-child(2)")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");
        driver.navigate().back();

            //по цене
        driver.findElement(By.cssSelector("#raiting-body > tr:nth-child(1) > td:nth-child(2) > a:nth-child(1)")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("kupit");
        driver.navigate().back();

        //проверка ссылки тизера
        driver.findElements(By.className("fresh__link")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");

    }
}
