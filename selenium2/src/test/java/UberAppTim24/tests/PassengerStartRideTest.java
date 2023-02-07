package UberAppTim24.tests;

import UberAppTim24.pages.HomePage;
import UberAppTim24.pages.LogInPage;
import UberAppTim24.pages.PassengerMainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class PassengerStartRideTest extends TestBase
{
    public static String passengerMail = "stefanium@mail.com";
    public static String passengerPassword = "admin";


    @Test(priority = 1)
    public void passengerLoginTest()
    {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.enterMail(passengerMail);
        logIn.enterPassword(passengerPassword);
        logIn.clickLogInButton();

        System.out.println("LogIn prosho!");
    }


    @Test (priority = 2)
    public void createRideTest()
    {
        PassengerMainPage passengerMain = new PassengerMainPage(driver);
        passengerMain.waitForPageToOpen();
        passengerMain.enterStartLocation("bulevar jase tomica 6");
        passengerMain.searchStartLocation();
        passengerMain.enterEndLocation("petrovaradin");
        passengerMain.searchEndLocation();
        passengerMain.waitForEstimateToShow();
        passengerMain.clickBeginButton();

        

    }
}
