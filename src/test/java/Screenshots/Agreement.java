package Screenshots;



import org.testng.annotations.Test;
import java.io.IOException;

public class Agreement extends ProductTest {

    @Test
    public void main() throws IOException {

        setTypePage("/agreement");
        driver.get("https://product-test.ru" + "/pages" + typePage);
        assertTitle("Пользовательское соглашение | Product-test.ru");

    }
}
