package Assert;


import org.testng.annotations.Test;
import java.io.IOException;

public class Helper extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/helper");
        driver.get("https://product-test.ru" + katalog + typePage);
        assertTitle("Какой телефон лучше купить в 2017 году, как выбрать смартфон | Product-test.ru");

    }
}
