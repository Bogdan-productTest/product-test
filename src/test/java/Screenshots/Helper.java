package Screenshots;


import org.testng.annotations.Test;
import java.io.IOException;

public class Helper extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/helper");

        driver.get("https://product-test.ru" + katalog + "/helper");

        createActualScreenshot();

        setExpectedScreenshot();

        createDiffFile();
    }
}
