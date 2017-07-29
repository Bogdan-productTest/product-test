package Screenshots;

import org.testng.annotations.Test;
import java.io.IOException;

public class Kharakteristiki extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/kharakteristiki");

        driver.get("https://product-test.ru" + katalog + product + "/kharakteristiki");

        createActualScreenshot();

        setExpectedScreenshot();

        createDiffFile();
    }
}
