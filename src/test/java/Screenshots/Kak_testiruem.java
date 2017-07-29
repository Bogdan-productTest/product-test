package Screenshots;

import org.testng.annotations.Test;
import java.io.IOException;

public class Kak_testiruem extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/kak-testiruem");

        driver.get("https://product-test.ru" + katalog + "/kak-testiruem" );

        createActualScreenshot();

        setExpectedScreenshot();

        createDiffFile();
    }
}
