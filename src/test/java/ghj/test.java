package ghj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class test {

    @Test
    public void test() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://product-test.ru/pages/about");

        List<WebElement> listOG = driver.findElements(By.tagName("meta"));
        System.out.println(listOG);
        for (WebElement assertOG : listOG) {
            try {
                if (assertOG.getAttribute("property").equals(null))
                    continue;
            } catch (NullPointerException e) {
                System.out.println(assertOG.getAttribute("property"));
                System.out.println(assertOG.getAttribute("content"));

//            String property = assertOG.getAttribute("property");
//            System.out.println(property);
//            if (property.equals("og:url")) {
//                System.out.println(assertOG.getAttribute("content"));
//                assertOG.getAttribute("content").equals("https://product-test.ru/pages/about");
//            }
//            else if (property.equals("og:title"))
//                assertOG.getAttribute("content").equals(" О Product-test.ru: что такое Продакт-тест?");
//            else if (property.equals("og:site_name")) assertOG.getAttribute("content").equals("Product-test.ru");
//            else if (property.equals("og:type"))
//                assertOG.getAttribute("content").equals("https://ptfiles.blob.core.windows.net/files/d6a7a10e-7f36-4a32-9505-35f5f7e44d14_600.jpg");
//            else if (property.equals("og:image"))
//                assertOG.getAttribute("content").equals("https://ptfiles.blob.core.windows.net/files/d6a7a10e-7f36-4a32-9505-35f5f7e44d14_600.jpg");
//            else if (property.equals("og:description"))
//                assertOG.getAttribute("content").equals("Product-test.ru — это первый российский сайт по объективному и независимому тестированию популярных потребительских товаров. На нашем сайте можно найти результаты тестов электроники, товаров для дома, косметики, детских товаров и важнейшие новости о потребительских товарах.");
//        }
            }
        }
    }
}
