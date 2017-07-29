package Screenshots;

import org.testng.annotations.Test;
import java.io.IOException;

public class Uslughitiestirovaniia extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/uslughitiestirovaniia");

        driver.get("https://product-test.ru" + "/pages" + "/uslughitiestirovaniia");

        createActualScreenshot();

        setExpectedScreenshot();

        createDiffFile();
    }
}
