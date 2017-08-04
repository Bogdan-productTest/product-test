package Screenshots;

import java.io.IOException;
import org.testng.annotations.Test;

/**
 * Created by admin on 17.07.2017.
 */
 public class HomePage extends ProductTest {


    @Test
    public void main() throws IOException {

        setTypePage("/homepage");
        driver.get("https://product-test.ru");
        assertTitle("Product-test.ru - тесты, обзоры и отзывы о товарах от экспертов и потребителей");

    }
}
