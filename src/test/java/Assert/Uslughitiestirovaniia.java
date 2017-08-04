package Assert;

import org.testng.annotations.Test;
import java.io.IOException;

public class Uslughitiestirovaniia extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/uslughitiestirovaniia");
        driver.get("https://product-test.ru" + "/pages" + typePage);
        assertTitle("Оценка и тестирование качества потребительских товаров, организация и проведение фокус групп | Product-test.ru");

    }
}
