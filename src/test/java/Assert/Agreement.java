package Assert;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class Agreement extends ProductTest {

    @Test
    protected void main() throws IOException {

        //провека title
        setTypePage("/agreement");
        driver.get(url + "/pages" + typePage);
        assertTitle("Пользовательское соглашение | Product-test.ru");

        //проверка SEO и микроразметки
        List<WebElement> listOG = driver.findElements(By.tagName("meta"));
        System.out.println(listOG);
        for (WebElement assertOG : listOG) {
            try {
                String property = assertOG.getAttribute("property");
                String name = assertOG.getAttribute("name");
                if (property.equals("og:url")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://product-test.ru/pages/agreement");
                } else if (property.equals("og:title")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Пользовательское соглашение | Product-test.ru");
                } else if (property.equals("og:site_name")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Product-test.ru");
                } else if (property.equals("og:type")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"website");
                } else if (property.equals("og:image")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"https://ptfiles.blob.core.windows.net/files/d6a7a10e-7f36-4a32-9505-35f5f7e44d14_600.jpg");
                } else if (property.equals("og:description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"),"Правила использования представленных на сайте результатов тестов товаров.");
                } else if (name.equals("description")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Правила использования представленных на сайте результатов тестов товаров.");
                }  else if (name.equals("Keywords")) {
                    Assert.assertEquals(assertOG.getAttribute("content"), "Product-test.ru");
                }
            } catch (NullPointerException e) {
                continue;
            }


        }
    }
}
