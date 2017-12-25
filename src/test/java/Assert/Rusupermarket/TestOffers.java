package Assert.Rusupermarket;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static Assert.DatabaseConnection.SQLConnect;
import static Assert.Rusupermarket.CheckOffers.browser;


public class TestOffers {
//    long idCheck;
//    boolean messageIsSent = false;
    static WebDriver driver;

    public static String getOffers (String id, String city) throws IOException {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://product-test.ru/product/getnadavioffers?id=" + id + "&city=" + city)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String getNadaviOffers (String id, String cityId) throws IOException {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://info.price.nadavi.ru/p4g3.php?p4g_api_type_=json&p4g_partner_=77800&p4g_gid_=" + id + "&p4g_city_id_=" + cityId)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String getCityId (String city) throws SQLException {
        ResultSet rs = SQLConnect().executeQuery("SELECT CityId FROM dbo.Locations WHERE City='" + city + "'");
        rs.next();
        return rs.getString(1);
    }

    public static String getNadaviId (String id) throws SQLException {
        ResultSet rs = SQLConnect().executeQuery("SELECT NadaviId FROM dbo.Products WHERE Id='" + id + "'");
        rs.next();
        return rs.getString(1);
    }

  //  @Test
    public static void main(String idProduct, String cityName) throws IOException, InterruptedException, JSONException, SQLException {

        String nadaviId = getNadaviId(idProduct);
        System.out.println("Nadavi id = " + nadaviId);
        String cityId = getCityId(cityName);
        System.out.println("city id = " + cityId);

        //Получаем список оферов отображаемых на РТ
            JSONObject offersPT = new JSONObject(getOffers(idProduct, cityName));
            JSONArray arrayOffersPT = offersPT.getJSONArray("Offers");
            System.out.println("Количество оферов на РТ: " + arrayOffersPT.length());

        //Получаем список оферов отдаваемых ND
        JSONObject offersND = new JSONObject(getNadaviOffers(nadaviId, cityId ));
        JSONArray arrayOffersND = offersND.getJSONArray("offers");
        System.out.println("Количество оферов на ND: " + arrayOffersND.length());

        //Проверяем равно ли кол-во оферов на ND и PT
      //  Assert.assertEquals(arrayOffersND.length(), arrayOffersPT.length());

        //Проверяем равны ли строки оферов отдаваемых ND и отображаемых на РТ
            for (int i = 0; i<arrayOffersND.length(); i++) {

                JSONObject offer = arrayOffersND.getJSONObject(i);

                //Удаляем параметр Color из ответа ND
                offer.remove("Color");

                System.out.println("PT #" + (i+1) + " : " + arrayOffersPT.getString(i));
                System.out.println("ND #" + (i+1) + " : " + arrayOffersND.getString(i));

                Assert.assertEquals(arrayOffersPT.getString(i), arrayOffersND.getString(i));
                arrayOffersPT.getString(i).equals(arrayOffersND.getString(i));
                System.out.println("Офер №" + (i + 1) + " соответствует" );

                String cityNameMod = cityName.substring(0, cityName.length() -1);
                String deliveryInfo = offer.getString("DeliveryInfo");

                System.out.println(deliveryInfo.substring(0,2));
                if (deliveryInfo.substring(0,3).equals("по ")) {
                    System.out.println("по городу");
                    Assert.assertEquals(deliveryInfo.substring(3, deliveryInfo.length() - 1), cityNameMod);
                } else {
                    System.out.println("доставка в город");
                    String deliveryInfoMod = deliveryInfo.substring(2, cityName.length() + 1);
                    System.out.println(deliveryInfoMod);
                    Assert.assertEquals(deliveryInfoMod, cityNameMod);
                }
            }
   }

   //проверка корректности перехода в магазин
   public static void offerClick () {
        driver = new ChromeDriver();
        driver.get("https://product-test.ru/koliaski/adamex-barletta-2-v-1-3-v-1/kupit");
        //клик по логотипу
       driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
       driver.findElement(By.cssSelector("#shops-block > div:nth-child(1) > div:nth-child(1) > div > a > img")).click();

        //клик по кнопке цены
       driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
       driver.findElement(By.cssSelector("#shops-block > div:nth-child(1) > div:nth-child(2) > a")).click();

       //получить ссылку на магазин из логотипа
       driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
       driver.findElement(By.cssSelector("#shops-block > div:nth-child(1) > div:nth-child(1) > div > a > img")).getAttribute("data-buy-url");

       //получить ссылку на магазин из цены
       driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
       driver.findElement(By.cssSelector("#shops-block > div:nth-child(1) > div:nth-child(2) > a")).getAttribute("data-buy-url");
   }

   @Test
    public static void main1 () throws SQLException, InterruptedException, JSONException, IOException {

  // main("5246", "Москва");
   // main("52464", "Казань");
offerClick();

    }
}
