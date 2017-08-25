package Assert;


import org.testng.annotations.Test;
import java.io.IOException;

public class Contact extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/contact");
        driver.get(url + "/pages" + typePage);
        assertTitle("Контакты | Product-test.ru");

    }
}
