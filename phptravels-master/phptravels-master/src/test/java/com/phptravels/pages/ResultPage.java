package com.phptravels.pages;
import com.phptravels.helpers.SeleniumHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultPage {

    @FindBy(xpath = "//table[@class='bgwhite table table-striped']")
    private WebElement resultstable;

    private SeleniumHelper helper;

    private Logger log = Logger.getLogger(ResultPage.class);

    public ResultPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        helper = new SeleniumHelper(driver);
    }

    public List<String> getHotelNames(){
        log.info("Checking hotel names");
        List<String> hotelNames = new ArrayList<>();
        helper.waitForListOfWebelements(resultstable.findElements(By.xpath(".//h4//b")));
        List<WebElement> hotelNameWebelements = resultstable.findElements(By.xpath(".//h4//b"));
        for(WebElement hotelNameElement : hotelNameWebelements ){
            System.out.println(hotelNameElement.getText());
            hotelNames.add(hotelNameElement.getText());
        }
        return  hotelNames;
    }

    // mapowanie kazdego elementu na tekst, potem kolekcjonowanie go w liscie, syntax zwaraca liste wiec jest przypisany do listy
    public List<String> getHotelPrices(){
        List<WebElement> hotelPricesWebelements = resultstable.findElements(By.xpath("//div[contains(@class,'price_tab')]//b"));
        return hotelPricesWebelements.stream().map(WebElement::getText).collect(Collectors.toList());

    }

}
