package Assert;


import org.testng.annotations.Test;
import java.io.IOException;

public class Stati extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/stati");
        driver.get("https://product-test.ru" + "/pages" + "/stati");
        assertTitle("Полезные статьи для потребителей | Product-test.ru");

    }
}
