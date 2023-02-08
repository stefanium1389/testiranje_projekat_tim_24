package UberAppTim24.tests;

import UberAppTim24.pages.DriverMainPage;
import UberAppTim24.pages.HomePage;
import UberAppTim24.pages.LogInPage;
import UberAppTim24.pages.PassengerMainPage;

import static org.testng.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver.WindowType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DriverEndRideTest  extends TestBase
{
    public static String driverMail = "vlafa@mail.com";
    public static String driverPassword = "admin";
    public static String passengerMail = "stefanium@mail.com";
    public static String passengerPassword = "admin";
    
    private WebDriver second_driver;

    @BeforeMethod
    public void loginPassengerMakeRideAndLogoutThenLoginDriver()
    {
    	
    	HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.enterMail(passengerMail);
        logIn.enterPassword(passengerPassword);
        logIn.clickLogInButton();
    	
        PassengerMainPage passengerMain = new PassengerMainPage(driver);
        passengerMain.waitForPageToOpen();
        passengerMain.enterStartLocation("bulevar jase tomica 6");
        passengerMain.searchStartLocation();
        passengerMain.enterEndLocation("petrovaradin");
        passengerMain.searchEndLocation();
        passengerMain.waitForEstimateToShow();
        passengerMain.clickBeginButton();
        
        passengerMain.clickOnProfileIcon();
        passengerMain.clickOnLogoutOption();
        
        home = new HomePage(driver);
        home.clickOnPrijavaButton();

        logIn = new LogInPage(driver);
        logIn.enterMail(driverMail);
        logIn.enterPassword(driverPassword);
        logIn.clickLogInButton();
        
        
    }
    @Test
    public void endRideHappyFlowTest() {
    	
    	System.setProperty("webdriver.chrome.driver", "chromedriver");
    	second_driver = new ChromeDriver();
    	second_driver.manage().window().maximize(); //otvori drugi prozor
    	
    	DriverMainPage driverMain = new DriverMainPage(driver);
    	String driverWindowHandle = driver.getWindowHandle();
    	
       	
    	LogInPage logIn = new LogInPage(second_driver); //uloguj se u putnik u drugom prozoru
        logIn.enterMail(passengerMail);
        logIn.enterPassword(passengerPassword);
        logIn.clickLogInButton();
        PassengerMainPage passengerMain = new PassengerMainPage(second_driver);
        passengerMain.waitForPageToOpen();
        String passengerWindowHandle = second_driver.getWindowHandle();
               
        driver.switchTo().window(driverWindowHandle); //prebaci se na prvi prozor
        driverMain.waitForStartButton();
    	driverMain.clickStartButton();
    	driverMain.waitForEndButton();    	
    	driverMain.clickEndButton(); //klikni stavec treba
    	second_driver.switchTo().window(passengerWindowHandle); //vrati se na drugi prozor
    	
    	assertTrue(passengerMain.isEndRatingDialogVisible()); //vidi je dijalog vidljiv
    	
    	second_driver.quit();
    	driver.quit(); //pogasi sve
    }
        
}
