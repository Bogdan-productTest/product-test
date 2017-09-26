//package ghj;
//
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.FormulaEvaluator;
//import org.apache.poi.ss.usermodel.Row;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import java.io.*;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Scanner;
//import java.util.concurrent.TimeUnit;
//
//public class Offersxls {
//    static HSSFSheet sheet;
//    static Object [][] o;
//    static Row row;
//    static HSSFWorkbook workbook;
//
//    @BeforeClass
//    public static void parseXML() throws IOException {
//
//        // Read XSL file
//        FileInputStream inputStream = new FileInputStream(new File("C:/Users/Тестер/Desktop/ParseSmartfony.xls"));
//
//        // Get the workbook instance for XLS file
//        workbook = new HSSFWorkbook(inputStream);
//
//        // Get first sheet from the workbook
//        sheet = workbook.getSheetAt(0);
//
//        System.out.println(sheet.getLastRowNum());
//        o = new Object[sheet.getLastRowNum()+1][3];
//        // Get iterator to all the rows in current sheet
//        Iterator<Row> rowIterator = sheet.iterator();
//
//
//        for (int i = 0; i<sheet.getLastRowNum()+1; i++) {
//            row = rowIterator.next();
//            // Get iterator to all cells of current row
//            Iterator<Cell> cellIterator = row.cellIterator();
//
//            for  (int j = 0; cellIterator.hasNext() == true; j++) {
//                Cell c = cellIterator.next();
//                //System.out.println(c.getCellTypeEnum()==CellType.NUMERIC);
//                if (c.getCellType()==0) {
//                    Double f = c.getNumericCellValue();
//                    Integer l = f.intValue();
//                    String r =  l.toString();
//                    o[i][j]=r;
//                } else o[i][j] = c.toString();
//            }
//            o[i][2] = i+1;
//        }
//    }
//    @BeforeClass
//    public void config () {
//        driver = new ChromeDriver();
//    }
//
//    @AfterClass
//    public void tearDown () {
//        System.out.println(urlNotOffers);
//        driver.quit();
//    }
//
//    @Test(dataProvider = "url")
//    public void testOffers (String URL){
//        driver.get(URL);
//        driver.findElements(By.className("image-buy"));
//
//        WebElement offersMessageElement = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("buy_button")));
//        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//        WebElement d = driver.findElement(By.xpath("/html[@class='no-js html-adaptive desktop landscape']/body/div[@class='out']/div[@class='container']/div[@id='m-wrapper']/div[@class='center content-wrapper']/div[2]/div[@class='tabs h-block m-hide']/div[@class='tabs__item active']/span[@class='tabs__link']/span[@class='tabs__counter buy_tabs__counter']"));
////        if (d.getText().equals("(0)")) {
////        urlNotOffers.add(URL);
////        Assert.assertTrue(false);
////        } else System.out.println("предложения есть!");
//        System.out.println(d.getText());
//    }
//}