package Screenshots;


import org.testng.annotations.Test;
import java.io.IOException;

public class Stati extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/stati");
        driver.get("https://product-test.ru" + "/pages" + "/stati");
        assertTitle("Полезные статьи для потребителей | Product-test.ru");

    }
}
