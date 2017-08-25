package Assert;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class About extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/about");
        driver.get(url + "/pages" + typePage);
        assertTitle("О Product-test.ru: что такое Продакт-тест?");

        List<WebElement> listOG = driver.findElements(By.tagName("meta"));
        for (WebElement assertOG: listOG) {
            String property = assertOG.getAttribute("property");
            if (property = "og:url")
            switch (property) {
                case "og:url" :
                    assertOG.getAttribute("content").equals("https://product-test.ru/pages/about");
                    break;
                case "og:title" :
                    assertOG.getAttribute("content").equals(" О Product-test.ru: что такое Продакт-тест?");
                    break;
                case "og:site_name" :
                    assertOG.getAttribute("content").equals("Product-test.ru");
                    break;
                case "og:type" :
                    assertOG.getAttribute("content").equals("website");
                    break;
                case "og:image" :
                    assertOG.getAttribute("content").equals("https://ptfiles.blob.core.windows.net/files/d6a7a10e-7f36-4a32-9505-35f5f7e44d14_600.jpg");
                    break;
                case "og:description" :
                    assertOG.getAttribute("content").equals("Product-test.ru — это первый российский сайт по объективному и независимому тестированию популярных потребительских товаров. На нашем сайте можно найти результаты тестов электроники, товаров для дома, косметики, детских товаров и важнейшие новости о потребительских товарах.");
                    break;
            }
        }

    }
}
