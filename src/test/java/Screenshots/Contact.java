package Screenshots;


import org.testng.annotations.Test;
import java.io.IOException;

public class Contact extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/contact");

        driver.get("https://product-test.ru" + "/pages" + "/contact");

        createActualScreenshot();

        setExpectedScreenshot();

        createDiffFile();
    }
}
