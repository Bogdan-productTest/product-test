package Screenshots;

import org.testng.annotations.Test;
import java.io.IOException;

public class Kupit extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/kupit");

        driver.get("https://product-test.ru" + katalog + product + "/kupit");

        createActualScreenshot();

        setExpectedScreenshot();

        createDiffFile();
    }


}
