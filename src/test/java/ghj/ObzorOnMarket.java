package ghj;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static jdk.internal.org.objectweb.asm.commons.GeneratorAdapter.OR;


public class ObzorOnMarket {

    WebDriver driver;
    boolean s;
    String urlObzor;
    String[] myArray;
    String urlMarket1;

   public void setS (boolean a) {
       this.s = a;
   }

   @Test
   protected void blackList () {
       List <String> blackListProd = new ArrayList<String>();
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/pegperegosicompleto/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/jetemcastle/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/capellas-709/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/capellas-803/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/pegperegogt3nakedcompleto/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/inglesinaespresso/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/pegperegoplikop3/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/inglesinazippy/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/camportofino/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/chiccosimplicity/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/maclarentechnoxlr/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/brevigrillo/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/capellas-901/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/maclarentechnoxt/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/peg-perego-book-completo/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/chiccoliteway/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/maclarenquestsport/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/capellas-102/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/inglesinatrip/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/pegperegoaria/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/maclarenvolo/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/britax-b-agile-4/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/quinnyzapp/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/happy-baby-cindy/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/inglesinaswift/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/jetem-paris/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/jetem-london/obzor");
       blackListProd.add("https://product-test.ru/proghulochnyiekoliaski/jetem-elegant/obzor");

       driver = new ChromeDriver();
       driver.get("https://product-test.ru/product/fulllist");
       List <WebElement> listObzor = driver.findElements(By.xpath("//div[@class='clmnA']/ul[26]/li/a"));
       for (WebElement iter : listObzor) {
           for (String oter : blackListProd) {
              if (iter.equals(oter) == true) {
                  System.out.println(iter + " находится в черном списке!");
                 break;
              }
           }

       }
   }

    @Test
    protected void main() {
        List <String> obzoraNet = new ArrayList<String>();
        List <String> oferaNet = new ArrayList<String>();
        List <String> idNet = new ArrayList<String>();
        List <String> idNevernyi = new ArrayList<String>();
        driver = new ChromeDriver();
        driver.get("https://product-test.ru/product/fulllist");
//        List<WebElement> listObzor = driver.findElements(By.xpath("//ul[5]/li/a"));
        List<WebElement> listObzor = driver.findElements(By.xpath("//div[@class='clmnA']//a"));
        System.out.println(listObzor);
        System.out.println(listObzor.size());

            for (WebElement iter : listObzor) {

            urlObzor = iter.getAttribute("href");

            String x = Keys.chord(Keys.CONTROL, Keys.RETURN);
            iter.sendKeys(x);
            myArray = driver.getWindowHandles().toArray(new String[2]);
            driver.switchTo().window(myArray[1]);

             List <WebElement> urlMarket = driver.findElements(By.className("load-more__link"));
               String h1 = driver.findElement(By.xpath("//h1[@class='head__title m-hide']")).getText();
               h1 = h1.substring(0, h1.length() - 7);
                if (urlMarket.size() == 0) {
                    oferaNet.add(urlObzor + " " + h1);
//                    System.out.println(urlObzor + " отсутствуют офферы у товара");
                    driver.close();
                    driver.switchTo().window(myArray[0]);
                    continue;
                }
             urlMarket1 = urlMarket.get(0).getAttribute("href");
//                System.out.println("urlMArket1 = " + urlMarket1);
             if (urlMarket1.equals("https://market.yandex.ru/")) {
                 idNet.add(urlObzor + " " + h1);
//                 System.out.println(urlObzor + " отсутствует ЯМ id у товара");
                 driver.close();
                 driver.switchTo().window(myArray[0]);
                 continue;
             }
             driver.get(urlMarket1 + "/articles");

                List<WebElement> link = driver.findElements(By.xpath("//a[@class='product-articles__item-info link link_theme_outer']"));
//                System.out.println(link);
                for (WebElement listLink : link) {
                    setS(false);
                    String href = listLink.getAttribute("href");

                    if (link.size() == 0) {
                        break;
                    }

                    if (href.equals(urlObzor)) {
                        setS(true);
                        break;
                    } else if (href.equals(urlObzor.substring(0, urlObzor.length() - 6))) {
                        setS(true);
                        break;
                    }

                }
                if (driver.getTitle().equals("404") == true) {
                    idNevernyi.add(urlObzor + " " + h1);
                } else if (s == false) {
                    obzoraNet.add(urlObzor +", "+ urlMarket1);
//                    System.out.println(urlObzor +", "+ urlMarket1  + " отсутствует на ЯМ");
                }
                driver.close();
                driver.switchTo().window(myArray[0]);
            }
        if (obzoraNet.size() != 0) { System.out.println("Нет обзоров на ЯМ:" + obzoraNet + "(" + urlMarket1 + ")"); }
        if (oferaNet.size() != 0) {
            System.out.println("<!DOCTYPE HTML>\n" + "<html>\n" + "<ul>");
            for (int i=0; i<=oferaNet.size(); i++){ System.out.println("<li><a href=" + oferaNet.get(i) + ">" +  "</a></li>"); }
            System.out.println("</ul>\n" + "</html>");}
        if (idNet.size() != 0) { System.out.println("У товаров отсутствует id:" + idNet); }
        if (idNevernyi.size() != 0) { System.out.println("Неверный id у товаров:" + idNevernyi); }
    }
}