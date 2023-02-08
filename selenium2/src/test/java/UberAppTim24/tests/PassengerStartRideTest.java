package UberAppTim24.tests;

import UberAppTim24.pages.HomePage;
import UberAppTim24.pages.LogInPage;
import UberAppTim24.pages.PassengerMainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static org.testng.Assert.*;

public class PassengerStartRideTest extends TestBase
{
    public static String passengerMail = "stefanium@mail.com";
    public static String passengerPassword = "admin";

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void passengerLogin()
    {

        if (driver==null)
        {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
            driver = new ChromeDriver();

            driver.manage().window().maximize();
        }
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
        passengerLogin();
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

        cancelRideAndExit();
    }

    @Test
    public void selectLocationsByClickingOnMapTest()
    {
        passengerLogin();
        PassengerMainPage passengerMain = new PassengerMainPage(driver);

        passengerMain.waitForMapToBeClickable();
        passengerMain.clickLocationOnMap(100,-200);
        passengerMain.waitForStartInputToGetAddress();
        passengerMain.clickLocationTypeOptions("Odredište");
        passengerMain.clickLocationOnMap(250,-100);
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


        cancelRideAndExit();
    }

    @Test
    public void addFavouriteRideTest()
    {
        passengerLogin();
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

    @Test
    public void createScheduledRideTest()
    {
        passengerLogin();
        PassengerMainPage passengerMain = new PassengerMainPage(driver);
        passengerMain.waitForMapToBeClickable();

        passengerMain.clickLocationOnMap(200,-200);
        passengerMain.waitForStartInputToGetAddress();
        passengerMain.clickLocationTypeOptions("Odredište");
        passengerMain.clickLocationOnMap(300,-100);
        passengerMain.waitForEndInputToGetAddress();

        passengerMain.clickBookButton();
        Date now = new Date();
        Date hourLater = Date.from(now.toInstant().plus(1, ChronoUnit.HOURS));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hourLater);

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int amPm = calendar.get(Calendar.AM_PM);

        String formattedHour = String.format("%02d", hour);
        String formattedMinute = String.format("%02d", minute);
        String amPmString = (amPm == Calendar.AM) ? "am" : "pm";

        passengerMain.setTime(formattedHour,formattedMinute,amPmString);
        passengerMain.saveTime();

        passengerMain.waitForTimeDisplay();
        String display = passengerMain.getTimeDisplayText();
        assertEquals(display.split(" ")[2].trim().split(":")[0],formattedHour);
        assertEquals(display.split(" ")[2].trim().split(":")[1],formattedMinute);


        try {
            passengerMain.waitForEstimateToShow();
        } catch (TimeoutException ex) {
            org.testng.Assert.fail("Timeout!");
        }
        passengerMain.clickBeginButton();

        try {
            passengerMain.waitForWithdrawToShow();
        } catch (TimeoutException ex) {
            org.testng.Assert.fail("Timeout!");
        }

        cancelScheduledRideAndExit();

    }

    @Test
    public void markBabiesAndPetsInRideTest()
    {
        passengerLogin();
        PassengerMainPage passengerMain = new PassengerMainPage(driver);
        passengerMain.waitForMapToBeClickable();

        passengerMain.clickLocationOnMap(200,-200);
        passengerMain.waitForStartInputToGetAddress();
        passengerMain.clickLocationTypeOptions("Odredište");
        passengerMain.clickLocationOnMap(300,-100);
        passengerMain.waitForEndInputToGetAddress();

        passengerMain.clickBabyCheckBox();
        passengerMain.clickPetsCheckBox();


        try {
            passengerMain.waitForEstimateToShow();
        } catch (TimeoutException ex) {
            org.testng.Assert.fail("Timeout!");
        }
        passengerMain.clickBeginButton();

        passengerMain.waitForSnackbarToAppear();
        assertEquals(passengerMain.getSnackBarText(),"Cant find best user!");

    }


    public void cancelRideAndExit()
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

    public void cancelScheduledRideAndExit()
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

    }



}
