package Screenshots;



import org.testng.annotations.Test;
import java.io.IOException;

public class About extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/about");

        driver.get("https://product-test.ru" + "/pages" + "/about");

        createActualScreenshot();

        setExpectedScreenshot();

        createDiffFile();


    }
}
