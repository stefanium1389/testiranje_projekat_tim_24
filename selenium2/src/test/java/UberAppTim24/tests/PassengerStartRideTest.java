package UberAppTim24.tests;

import UberAppTim24.pages.HomePage;
import UberAppTim24.pages.LogInPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class PassengerStartRideTest extends TestBase
{
    public static String passengerMail = "stefanium@mail.com";
    public static String passengerPassword = "admin";

    @Test
    public void dynamicContent()
    {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.enterMail(passengerMail);
        logIn.enterPassword(passengerPassword);
        logIn.clickLogInButton();

        System.out.println("LogIn prosho!");
    }
}
