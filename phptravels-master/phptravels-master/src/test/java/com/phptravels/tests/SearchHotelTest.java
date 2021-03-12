package com.phptravels.tests;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.phptravels.helpers.ExcelHelper;
import com.phptravels.helpers.SeleniumHelper;
import com.phptravels.helpers.TestListener;
import com.phptravels.pages.HomePage;
import com.phptravels.pages.ResultPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Listeners(TestListener.class)
public class SearchHotelTest extends BaseSeleniumTest {


    @Test(dataProvider = "getData")
    public void SearchHotelTest(String city, String chekInDate, String checkOutDate, String fHotel, String fprice, String sHotel, String sPrice,String tHotel, String tPrice) throws IOException {

        ExtentTest test = reports.createTest("Search Hotel Test");
        HomePage homePage = new HomePage(driver);// tworzenie obiketu klasy HomePage


        test.info("On PHP Travels Home Page",getScreenshot());

        homePage.setCityHotel(city) // chain
                        .setDateRange(chekInDate,checkOutDate)
                        .openTravellersModel()
                        .addAdult()
                        .addChid()
                        .addChid();
        String infoText = "Before performing  search for with city %s, check in %s and check out %s";
        test.info(String.format(infoText,city,chekInDate,checkOutDate),getScreenshot());
        ResultPage resultPage = homePage.searchTravel();



        test.info("Checking hotel names",getScreenshot());
        List<String> hotelNames = resultPage.getHotelNames();
        Assert.assertEquals(fHotel, hotelNames.get(0));
        Assert.assertEquals(sHotel, hotelNames.get(1));
        Assert.assertEquals(tHotel, hotelNames.get(2));

        test.info("Checking hotel prices",getScreenshot());
        List<String> hotelPrices = resultPage.getHotelPrices();
        Assert.assertEquals(fprice, hotelPrices.get(0));
        Assert.assertEquals(sPrice, hotelPrices.get(1));
        Assert.assertEquals(tPrice, hotelPrices.get(2));


    }

    @DataProvider
    public Object[][] getData(){
        Object[][] data = null;
        try {
            data = ExcelHelper.readExcelFile(new File("C:\\Users\\laptop\\IdeaProjects\\phptravels\\src\\test\\resources\\files\\Dane.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
