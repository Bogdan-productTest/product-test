package Screenshots;

import org.testng.annotations.Test;

import java.io.IOException;

public class News extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/news");
        driver.get("https://product-test.ru" + "/news/kids/2017-8-luchshix-letnix-kolyasok-po-rezultatam-testov");
        assertTitle("8 лучших летних колясок по результатам тестов | Product-test.ru");

    }
}