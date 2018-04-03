package Assert.CheckOffersBot;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static Assert.MyConfig.getPath;
import static Assert.CheckOffersBot.UrlForCheckCode.createPoolUrlApi;

public class CheckCodeApi {

    //проверка кода ответа Api
    public static void checkCode(UrlForCheckCode urlForCheckCode, GregorianCalendar calendar) throws IOException {
        SimpleDateFormat f1 = new SimpleDateFormat("HH:mm:ss");

        final OkHttpClient client1 = new OkHttpClient();
        Request request1 = new Request.Builder()
                .url(urlForCheckCode.getUrl())
                .get()
                .build();

        Response response1 = null;
        try {
            long l = System.currentTimeMillis();
            response1 = client1.newCall(request1).execute();
            double d = (double) (System.currentTimeMillis() - l)/1000;
            urlForCheckCode.setTimeRequest(d);
            urlForCheckCode.setCode(response1.code());
            urlForCheckCode.setTimeCheck(f1.format(calendar.getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UrlForCheckCode getUrlFromPool (Queue<UrlForCheckCode> queue) {
        UrlForCheckCode url = queue.peek();
        queue.poll();
        queue.add(url);
        return url;
    }

    public static void report(GregorianCalendar calendar, Queue <UrlForCheckCode> queue) throws IOException {
        Workbook workbook = null;
        Sheet sheet;

        Date date = calendar.getTime();
        SimpleDateFormat f = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat d = new SimpleDateFormat("HH:mm");

        if (d.format(date).equals("00:00")) {
            calendar.add(Calendar.DATE, -1);
            date = calendar.getTime();
        }
        String s = getPath() + "/reports/monitoringReportsOffers/" + f.format(date)+ ".xls";
        File file = new File(s);
        file.getParentFile().mkdirs();

        //если файла не существует
        if(!file.exists()) {
            FileOutputStream outFile = new FileOutputStream(file);
            workbook = new HSSFWorkbook();
            Sheet sheet1 = workbook.createSheet("Api");
            Row row1 = sheet1.createRow(0);
            sheet1 = workbook.getSheet("Api");
            sheet1.getRow(0).createCell(0).setCellValue("Url");
            sheet1.getRow(0).createCell(1).setCellValue("Время проверки");
            sheet1.getRow(0).createCell(2).setCellValue("Код ответа");
            sheet1.getRow(0).createCell(3).setCellValue("Время ответа");
            workbook.write(outFile);
        }

        //если листа не существует
        FileInputStream inputStream = new FileInputStream(new File(s));
        workbook = new HSSFWorkbook(inputStream);
        if (workbook.getNumberOfSheets()==1) {
            if (!workbook.getSheetAt(0).getSheetName().equals("Api")){
                FileOutputStream outFile = new FileOutputStream(file);
                Sheet sheet1 = workbook.createSheet("Api");
                Row row1 = sheet1.createRow(0);
                sheet1 = workbook.getSheet("Api");
                sheet1.getRow(0).createCell(0).setCellValue("Url");
                sheet1.getRow(0).createCell(1).setCellValue("Время проверки");
                sheet1.getRow(0).createCell(2).setCellValue("Код ответа");
                sheet1.getRow(0).createCell(3).setCellValue("Время ответа");
                workbook.write(outFile);
            }
        }
        sheet = workbook.getSheet("Api");

        //System.out.println("запись в отчет для " + queue.size() + " урлов");
        for (int i = 0; i < queue.size(); i++) {
            UrlForCheckCode url = getUrlFromPool(queue);
            try {
                Row row = sheet.createRow(sheet.getLastRowNum()+1);
                row.createCell(0).setCellValue(url.getUrl());
                row.createCell(1).setCellValue(url.getTimeCheck());
                row.createCell(2).setCellValue(url.getCode());
                row.createCell(3).setCellValue(url.getTimeRequest());
            } catch (java.lang.IllegalArgumentException e) {
            //    log.log(Level.SEVERE, "Exception: ", e);
            }
            url.setTimeRequest(-1);
            url.setCode(-1);
            url.setTimeCheck("");
        }
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
    }

    @Test
    public static void main() throws IOException, InterruptedException {
        Queue<UrlForCheckCode> queue = createPoolUrlApi();
        UrlForCheckCode url;
        while (true) {
            GregorianCalendar calendar = new GregorianCalendar();
            url = getUrlFromPool(queue);
            if (url.code==-1) {
                checkCode(url, calendar);
                Thread.sleep(1000);
            } else {
                report(calendar,queue);
                checkCode(url, calendar);
                Thread.sleep(1000);
            }
        }
    }
}

