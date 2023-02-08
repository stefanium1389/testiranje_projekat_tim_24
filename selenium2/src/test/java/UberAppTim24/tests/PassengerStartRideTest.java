package UberAppTim24.tests;

import UberAppTim24.pages.HomePage;
import UberAppTim24.pages.LogInPage;
import UberAppTim24.pages.PassengerMainPage;
import jdk.jfr.StackTrace;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
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
import java.util.List;

import static org.testng.Assert.*;

public class PassengerStartRideTest
{
    public static WebDriver driver;
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
        int hour_of_day = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int amPm = calendar.get(Calendar.AM_PM);

        String formattedHour = String.format("%02d", hour);
        String formattedHourOfDay = String.format("%02d", hour_of_day);
        String formattedMinute = String.format("%02d", minute);
        String amPmString = (amPm == Calendar.AM) ? "am" : "pm";

        passengerMain.setTime(formattedHour,formattedMinute,amPmString);
        passengerMain.saveTime();

        passengerMain.waitForTimeDisplay();
        String display = passengerMain.getTimeDisplayText();
        assertEquals(display.split(" ")[2].trim().split(":")[0],formattedHourOfDay);
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

    @Test
    public void selectVehicleTypeRideTest()
    {
        passengerLogin();
        PassengerMainPage passengerMain = new PassengerMainPage(driver);
        passengerMain.waitForMapToBeClickable();

        passengerMain.clickLocationOnMap(200,-200);
        passengerMain.waitForStartInputToGetAddress();
        passengerMain.clickLocationTypeOptions("Odredište");
        passengerMain.clickLocationOnMap(300,-100);
        passengerMain.waitForEndInputToGetAddress();

        passengerMain.clickVehicleTypeOptions("Luksuzno");

        try {
            passengerMain.waitForEstimateToShow();
        } catch (TimeoutException ex) {
            org.testng.Assert.fail("Timeout!");
        }
        passengerMain.clickBeginButton();

        passengerMain.waitForSnackbarToAppear();
        assertEquals(passengerMain.getSnackBarText(),"Cant find best user!");

    }

    @Test
    public void linkUsersTest()
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
            passengerMain.waitForEstimateToShow();
        } catch (TimeoutException ex) {
            org.testng.Assert.fail("Timeout!");
        }

        passengerMain.clickOnLinkFriends();
        passengerMain.waitForLinkFriendsToShow();
        passengerMain.enterTextToUserInput("ra");
        passengerMain.clickOnUsersSubmitButton();
        passengerMain.waitForSearchResultsToShow();
        passengerMain.addFirstFewUsers(2);
        passengerMain.acceptLinkedUsers();
        passengerMain.waitForLinkedUsersToAppearAgain();


        passengerMain.clickBeginButton();

        passengerMain.waitForSnackbarToAppear();
        assertEquals(passengerMain.getSnackBarText(),"uspešno kreirana vožnja!");

        List<String> names = passengerMain.getNamesFromLinkedUsers();

        boolean containsRxlja = false;
        for (String name : names) {
            if (name.contains("rxlja")) {
                containsRxlja = true;
                break;
            }
        }
        assertTrue(containsRxlja);

        boolean containsMirko = false;
        for (String name : names) {
            if (name.contains("mirko")) {
                containsMirko = true;
                break;
            }
        }
        assertTrue(containsMirko);

        cancelRideAndExit();

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

    @Test
    public void alreadyInRideTest()
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
        passengerMain.waitForSnackbarToDisappear();

        passengerMain.clickBeginButton();
        passengerMain.waitForSnackbarToAppear();
        assertEquals(passengerMain.getSnackBarText(),"Putnik stefanium@mail.com je vec u aktivnoj voznji");

        try {
            passengerMain.waitForWithdrawToShow();
        } catch (TimeoutException ex) {
            org.testng.Assert.fail("Timeout!");
        }

        cancelRideAndExit();
    }





}
