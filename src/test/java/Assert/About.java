package Assert;



import org.testng.annotations.Test;
import java.io.IOException;

public class About extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/about");
        driver.get("https://product-test.ru" + "/pages" + typePage);
        assertTitle("О Product-test.ru: что такое Продакт-тест?");

    }
}
