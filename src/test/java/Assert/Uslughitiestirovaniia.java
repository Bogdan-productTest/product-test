package Assert;

import org.testng.annotations.Test;
import java.io.IOException;

public class Uslughitiestirovaniia extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/uslughitiestirovaniia");
        driver.get(url + "/pages" + typePage);
        assertTitle("Оценка и тестирование качества потребительских товаров, организация и проведение фокус групп | Product-test.ru");

    }
}
