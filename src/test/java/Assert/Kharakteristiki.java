package Assert;

import org.testng.annotations.Test;
import java.io.IOException;

public class Kharakteristiki extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/kharakteristiki");
        driver.get("https://product-test.ru" + katalog + product + typePage);
        assertTitle("Xiaomi Redmi 4X - обзор, отзывы о Сяоми Редми 4X | Product-test.ru");

    }
}
