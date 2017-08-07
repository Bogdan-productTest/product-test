package Assert;



import org.testng.annotations.Test;
import java.io.IOException;

public class Agreement extends ProductTest {

    @Test
    protected void main() throws IOException {

        //провека title
        setTypePage("/agreement");
        driver.get("https://product-test.ru" + "/pages" + typePage);
        assertTitle("Пользовательское соглашение | Product-test.ru");

    }
}
