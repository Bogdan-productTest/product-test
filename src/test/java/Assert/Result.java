package Assert;

import org.testng.annotations.Test;
import java.io.IOException;

public class Result extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/result");
        driver.get("https://product-test.ru" + katalog );
        assertTitle("Лучшие смартфоны 2017: обзоры, отзывы о телефонах, тесты | Product-test.ru ");

    }
}
