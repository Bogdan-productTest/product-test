package Assert;


import org.testng.annotations.Test;
import java.io.IOException;

public class Contact extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/contact");
        driver.get("https://product-test.ru" + "/pages" + typePage);
        assertTitle("Контакты | Product-test.ru");

    }
}
