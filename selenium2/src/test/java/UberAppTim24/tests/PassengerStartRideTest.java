package UberAppTim24.tests;

import UberAppTim24.pages.HomePage;
import UberAppTim24.pages.LogInPage;
import UberAppTim24.pages.PassengerMainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert.*;

public class PassengerStartRideTest extends TestBase
{
    public static String passengerMail = "stefanium@mail.com";
    public static String passengerPassword = "admin";


    @BeforeMethod
    public void passengerLogin()
    {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.enterMail(passengerMail);
        logIn.enterPassword(passengerPassword);
        logIn.clickLogInButton();

    }


    @Test
    public void createRideTest()
    {
        PassengerMainPage passengerMain = new PassengerMainPage(driver);
        passengerMain.waitForPageToOpen();
        passengerMain.enterStartLocation("bulevar jase tomica 6");
        passengerMain.searchStartLocation();
        passengerMain.enterEndLocation("petrovaradin");
        passengerMain.searchEndLocation();
        try {
            passengerMain.waitForEstimateToShow();
        } catch (TimeoutException ex) {
            org.testng.Assert.fail("Timeout!");
        }
        passengerMain.clickBeginButton();


    }

    @AfterMethod
    public void cancelRide()
    {
        PassengerMainPage passengerMain = new PassengerMainPage(driver);
        passengerMain.waitForWithdrawToShow();
        passengerMain.clickWithdraw();
        passengerMain.removeFirstLinkedUser();
    }
}
