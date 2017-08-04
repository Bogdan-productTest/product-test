package Assert;

import org.testng.annotations.Test;
import java.io.IOException;

public class Kupit extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/kupit");
        driver.get("https://product-test.ru" + katalog + product + typePage);
        assertTitle("Купить Xiaomi Redmi 4X 16Gb, цены Сяоми Редми 4Кс 16Гб | Product-test.ru");

    }


}
