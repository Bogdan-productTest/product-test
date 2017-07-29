package Screenshots;

import org.testng.annotations.Test;
import java.io.IOException;

public class Obzor extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/obzor");

        driver.get("https://product-test.ru" + katalog + product + "/obzor");

        createActualScreenshot();

        setExpectedScreenshot();

        createDiffFile();
    }
}
