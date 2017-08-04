package Screenshots;

import org.testng.annotations.Test;

import java.io.IOException;

public class Rating extends ProductTest{

    @Test
    public void main() throws IOException {

        setTypePage("/rating");
        driver.get("https://product-test.ru" + katalog + typePage);
        assertTitle("Рейтинг смартфонов и телефонов 2017: цены, модели, топ-телефоны | Product-test.ru ");

    }
}
