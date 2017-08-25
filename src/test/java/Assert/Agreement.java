package Assert;



import org.testng.annotations.Test;
import java.io.IOException;

public class Agreement extends ProductTest {

    @Test
    protected void main() throws IOException {

        //провека title
        setTypePage("/agreement");
        driver.get(url + "/pages" + typePage);
        assertTitle("Пользовательское соглашение | Product-test.ru");

    }
}
