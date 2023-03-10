package UberAppTim24.tests;

import UberAppTim24.pages.HomePage;
import UberAppTim24.pages.LogInPage;
import UberAppTim24.pages.PassengerMainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class LogInTest extends TestBase
{
    public static String validMail = "rxx@mail.com";
    public static String validPassword = "admin";
    public static String wrongMail = "Relja";
    public static String wrongPassword = "Radeka";

    @Test(priority = 1)
    public void noInput()
    {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.clickLogInButton();

        try
        {
            logIn.waitForSnackBarToAppear();
        }
        catch (TimeoutException ex)
        {
            Assert.fail("Timeout!");
        }

        assertEquals(logIn.getSnackBarText(),"Bad credentials");
    }

    @Test(priority = 2)
    public void noPassword()
    {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.enterMail(validMail);
        logIn.clickLogInButton();

        try
        {
            logIn.waitForSnackBarToAppear();
        }
        catch (TimeoutException ex)
        {
            Assert.fail("Timeout!");
        }

        assertEquals(logIn.getSnackBarText(),"Bad credentials");
    }

    @Test(priority = 3)
    public void noMail()
    {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.enterPassword(validPassword);
        logIn.clickLogInButton();

        try
        {
            logIn.waitForSnackBarToAppear();
        }
        catch (TimeoutException ex)
        {
            Assert.fail("Timeout!");
        }

        assertEquals(logIn.getSnackBarText(),"Bad credentials");
    }

    @Test(priority = 4)
    public void wrongMail()
    {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.enterMail(wrongMail);
        logIn.enterPassword(validPassword);
        logIn.clickLogInButton();

        try
        {
            logIn.waitForSnackBarToAppear();
        }
        catch (TimeoutException ex)
        {
            Assert.fail("Timeout!");
        }

        assertEquals(logIn.getSnackBarText(),"Bad credentials");
    }

    @Test(priority = 5)
    public void wrongPassword()
    {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.enterMail(validMail);
        logIn.enterPassword(wrongPassword);
        logIn.clickLogInButton();

        try
        {
            logIn.waitForSnackBarToAppear();
        }
        catch (TimeoutException ex)
        {
            Assert.fail("Timeout!");
        }

        assertEquals(logIn.getSnackBarText(),"Bad credentials");
    }

    @Test(priority = 6)
    public void valid() throws InterruptedException
    {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.enterMail(validMail);
        logIn.enterPassword(validPassword);
        logIn.clickLogInButton();

        PassengerMainPage passengerMainPage = new PassengerMainPage(driver);
        passengerMainPage.waitForPageToOpen();
        assertEquals("http://localhost:4200/user-home", driver.getCurrentUrl());

        passengerMainPage.clickOnProfileIcon();
        passengerMainPage.clickOnLogoutOption();
        driver.quit();
    }
}
