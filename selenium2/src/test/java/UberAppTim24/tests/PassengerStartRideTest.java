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

import static org.testng.Assert.*;

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
    public void simpleCreateRideTest()
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
        passengerMain.waitForSnackbarToAppear();
        assertEquals(passengerMain.getSnackBarText(),"uspešno kreirana vožnja!");

        try {
            passengerMain.waitForWithdrawToShow();
        } catch (TimeoutException ex) {
            org.testng.Assert.fail("Timeout!");
        }

        cancelRide();
    }

    @Test
    public void selectLocationsByClickingOnMapTest()
    {
        PassengerMainPage passengerMain = new PassengerMainPage(driver);

        passengerMain.waitForMapToBeClickable();
        passengerMain.clickLocationOnMap(200,-200);
        passengerMain.waitForStartInputToGetAddress();
        passengerMain.clickLocationTypeOptions("Odredište");
        passengerMain.clickLocationOnMap(300,-100);
        passengerMain.waitForEndInputToGetAddress();


        try {
            passengerMain.waitForEstimateToShow();
        } catch (TimeoutException ex) {
            org.testng.Assert.fail("Timeout!");
        }
        passengerMain.clickBeginButton();
        passengerMain.waitForSnackbarToAppear();
        assertEquals(passengerMain.getSnackBarText(),"uspešno kreirana vožnja!");

        try {
            passengerMain.waitForWithdrawToShow();
        } catch (TimeoutException ex) {
            org.testng.Assert.fail("Timeout!");
        }


        cancelRide();
    }

    @Test
    public void addFavouriteRideTest()
    {
        PassengerMainPage passengerMain = new PassengerMainPage(driver);
        passengerMain.waitForMapToBeClickable();
        passengerMain.clickLocationOnMap(200,-200);
        passengerMain.waitForStartInputToGetAddress();
        passengerMain.clickLocationTypeOptions("Odredište");
        passengerMain.clickLocationOnMap(300,-100);
        passengerMain.waitForEndInputToGetAddress();

        try {
            passengerMain.waitForFavouriteIcon();
        } catch (TimeoutException ex) {
            org.testng.Assert.fail("Timeout!");
        }
        passengerMain.clickOnAddFavourite();
        passengerMain.waitForDialogInput();
        passengerMain.typeInDialogInput("xd");
        passengerMain.acceptDialog();
        passengerMain.waitForSnackbarToAppear();
        assertEquals(passengerMain.getSnackBarText(),"Ruta dodata u omiljene!");

    }


    public void cancelRide()
    {
        PassengerMainPage passengerMain = new PassengerMainPage(driver);
        try {
            passengerMain.waitForWithdrawToShow();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        passengerMain.clickWithdraw();
        passengerMain.removeFirstLinkedUser();
    }


}
