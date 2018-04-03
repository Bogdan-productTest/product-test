package Assert.CheckOffersBot;

import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.Queue;

import static Assert.MyConfig.getTelegramApiString;
import static Assert.CheckOffersBot.CheckOffers.idBogdan;

public class Methods {

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static String telegramApiString = getTelegramApiString();

    //метод для создания Объектов урлов для проверки оферов
    public static Url getUrlObject (String url, String selector, int time) {
        Url url1 = new Url();
        url1.setUrl(url);
        url1.setSelector(selector);
        url1.setTimeMessage(time);
        return url1;
    }

    //метод для создания Объектов урлов для проверки Апи
    public static UrlForCheckCode getUrlObject (String url, String provider) {
        UrlForCheckCode url1 = new UrlForCheckCode();
        url1.setUrl(url);
        url1.setProvider(provider);
        return url1;
    }

    //метод получения урла из пула для проверки оферов
    public static Url getUrlFromPool (Queue<Url> queue) {
        Url url = queue.peek();
        queue.poll();
        queue.add(url);
        return url;
    }

    //метод получения урла из пула для проверки Api
    public static UrlForCheckCode getUrlFromPoolForCheckCode (Queue <UrlForCheckCode> queueForCheckCode) {
        UrlForCheckCode urlForCheckCode = queueForCheckCode.peek();
        queueForCheckCode.poll();
        queueForCheckCode.add(urlForCheckCode);
        return urlForCheckCode;
    }

    //метод для отправки сообщения в телеграм
    public static void sendMessage (String chat_id, String text) throws IOException {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(telegramApiString + "/sendMessage?chat_id=" + chat_id + "&text=" + text)
                .get()
                .build();

        Response response = client.newCall(request).execute();
    }

    //метод для отправки скриншота в телеграм
    public static void sendScreenshot (long name, String chat_id, String text) throws IOException {
        final OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("photo", name + ".png",
                        RequestBody.create(MEDIA_TYPE_PNG, new File("C:/Users/Mirror/Desktop/product-test/screenshots/" + name + ".png")))
                .build();

        Request request = new Request.Builder()
                .url(telegramApiString + "/sendPhoto?chat_id=" + chat_id
                        + "&caption=" + text)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
    }

    //проверка кода ответа Api
    public static void checkCode(UrlForCheckCode urlForCheckCode) throws IOException {
        final OkHttpClient client1 = new OkHttpClient();
        Request request1 = new Request.Builder()
                .url(urlForCheckCode.getUrl())
                .get()
                .build();

        Response response1 = null;
        try {
            response1 = client1.newCall(request1).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
       // System.out.println(response1.code());

        if (response1.code()!=200 && !urlForCheckCode.sendMessage) {
            String s = "Код ответа api " + urlForCheckCode.getProvider() + " равен " + response1.code();
           // System.out.println(s);
            urlForCheckCode.setSendMessage(true);
            sendMessage(idBogdan, s);
        }
    }

    public static double average(double average , int amountOfNumbers, double number) {
        return ((double) average*amountOfNumbers + (double) number)/(amountOfNumbers+1);
    }

}
