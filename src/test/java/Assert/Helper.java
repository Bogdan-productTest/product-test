package Assert;


import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Helper extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/helper");
        driver.get(url + katalog + typePage);
        assertTitle("Какой телефон лучше купить в 2017 году, как выбрать смартфон | Product-test.ru");

        //проверка ссылки тизера
        driver.findElements(By.className("fresh__link")).get(0).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertLink("obzor");
    }
}
