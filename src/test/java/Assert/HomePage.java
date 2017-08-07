package Assert;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

/**
 * Created by admin on 17.07.2017.
 */
 public class HomePage extends ProductTest {

    public String getCurrentFrameUrl(WebDriver driver) {
        return ((JavascriptExecutor) driver).executeScript("return window.location.href").toString();
    }

    @Test
    protected void main() throws IOException {

        //проверка title
        setTypePage("/homepage");
        driver.get("https://product-test.ru");
        assertTitle("Product-test.ru - тесты, обзоры и отзывы о товарах от экспертов и потребителей");

//        driver.findElements(By.className("blockGoods-row-pic")).get(0).click();
//        System.out.println(driver.getCurrentUrl());

//       WebElement element = driver.findElement(By.linkText("Пальмовое масло: вред или польза?"));
//       Actions actions = new Actions(driver);
//       actions.moveToElement(element);
//       driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        System.out.println(getCurrentFrameUrl(driver));
//        element.click();
    }


}
