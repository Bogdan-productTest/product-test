package Screenshots;

import org.testng.annotations.Test;

import java.io.IOException;

public class Macro_katalog extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/electronics/katalog");
        driver.get("https://product-test.ru" + typePage );
        assertTitle("Электроника | Product-test.ru");

    }
}