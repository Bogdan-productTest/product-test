package Screenshots;

import org.testng.annotations.Test;
import java.io.IOException;

public class Result extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/result");

        driver.get("https://product-test.ru" + katalog );

        createActualScreenshot();

        setExpectedScreenshot();

        createDiffFile();
    }
}
