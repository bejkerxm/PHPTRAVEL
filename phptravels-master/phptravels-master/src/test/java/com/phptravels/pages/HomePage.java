package com.phptravels.pages;
import com.phptravels.helpers.SeleniumHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchSpan;

    @FindBy(xpath = "//div[@id ='select2-drop']//input")
    private WebElement searchCityInput;

    @FindBy(name = "checkin")
    private WebElement checkinInput;

    @FindBy(name = "checkout")
    private  WebElement checkOutInput;

    @FindBy(id = "travellersInput")
    private  WebElement travellersInput;

    @FindBy(id = "adultPlusBtn")
    private  WebElement adultsPlusBtn;

    @FindBy(id = "childPlusBtn")
    private  WebElement childPlusBtn;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='select2-result-label']" )

    private WebElement selectResult;
    private SeleniumHelper helper;
    private WebDriver driver;

    public Logger log = Logger.getLogger(HomePage.class);

    // ten konstruktor inicjalizuje pola
    public HomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.helper = new SeleniumHelper(driver);
        this.driver = driver;
    }

    public HomePage setCityHotel(String cityName) {
        log.info("Setting city name");
        searchSpan.click();
        searchCityInput.sendKeys(cityName);
        helper.waitForElementToBeDisplayed(selectResult);
        searchCityInput.sendKeys(Keys.ENTER);
        log.info("City name set");
        return this;
    }

    public HomePage setDateRange(String checkinDate, String checkoutDate){
        log.info("Setting date range");
        checkinInput.sendKeys(checkinDate);
        checkOutInput.sendKeys(checkoutDate);
        checkOutInput.click();
        log.info("Date range is set");
        return this;
    }

    public HomePage openTravellersModel(){
        log.info("Opening travellers model");
        travellersInput.click();
        helper.waitForElementToBeDisplayed(adultsPlusBtn);
        log.info("Travellers modal is visible");
        return this;
    }

    public HomePage addAdult(){
        log.info("Adding adult passenger");
        adultsPlusBtn.click();
        log.info("Adult passenger added");
        return this;
    }

    public HomePage addChid(){
        log.info("Add kid passenger");
        childPlusBtn.click();
        log.info("Kid added");
        return this;
    }

    public ResultPage searchTravel(){
        log.info("Searching action performed");
        searchButton.click();
        log.info("City searched");
        return new ResultPage(driver);
    }

    }
