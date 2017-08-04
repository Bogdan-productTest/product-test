package Assert;

import org.testng.annotations.Test;
import java.io.IOException;

public class News_all extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/news_all");
        driver.get("https://product-test.ru" + "/news");
        assertTitle("Новости | Product-test.ru");

    }
}
