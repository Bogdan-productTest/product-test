package Assert;

import org.testng.annotations.Test;

import java.io.IOException;

public class Tests extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/test");
        driver.get("https://product-test.ru" + typePage);
        assertTitle("Обзоры и тесты товаров | Product-test.ru");

    }
}