package Screenshots;

import org.testng.annotations.Test;
import java.io.IOException;

public class News extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/news");

        driver.get("https://product-test.ru" + "/news");

        createActualScreenshot();

        setExpectedScreenshot();

        createDiffFile();
    }
}
