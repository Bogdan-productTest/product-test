package Assert;

import org.testng.annotations.Test;

import java.io.IOException;

public class Katalog extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/katalog");
        driver.get("https://product-test.ru" + katalog + typePage );
        assertTitle("Купить смартфон недорого: цены, характеристики и каталог смартфонов | Product-test.ru");

    }
}
