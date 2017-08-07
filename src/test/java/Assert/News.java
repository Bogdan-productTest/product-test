package Assert;

import org.testng.annotations.Test;

import java.io.IOException;

public class News extends ProductTest {

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/news");
        driver.get("https://product-test.ru" + "/news/kids/2017-8-luchshix-letnix-kolyasok-po-rezultatam-testov");
        assertTitle("8 лучших летних колясок по результатам тестов | Product-test.ru");

    }
}