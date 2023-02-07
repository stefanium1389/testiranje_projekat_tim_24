package UberAppTim24.tests;

import UberAppTim24.pages.DriverMainPage;
import UberAppTim24.pages.HomePage;
import UberAppTim24.pages.LogInPage;
import UberAppTim24.pages.PassengerMainPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DriverEndRideTest  extends TestBase
{
    public static String driverMail = "vlafa@mail.com";
    public static String driverPassword = "admin";
    public static String passengerMail = "stefanium@mail.com";
    public static String passengerPassword = "admin";

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
    	
    	DriverMainPage driverMain = new DriverMainPage(driver);
    	driverMain.waitForStartButton();
    	driverMain.clickStartButton();
    	driverMain.waitForEndButton();
    	driverMain.clickEndButton();
    }
    
}
