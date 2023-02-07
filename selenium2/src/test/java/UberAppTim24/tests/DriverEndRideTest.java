package UberAppTim24.tests;

import UberAppTim24.pages.HomePage;
import UberAppTim24.pages.LogInPage;
import org.testng.annotations.Test;

public class DriverEndRideTest  extends TestBase
{
    public static String driverMail = "vlafa@mail.com";
    public static String driverPassword = "admin";

    @Test
    public void dynamicContent()
    {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.enterMail(driverMail);
        logIn.enterPassword(driverPassword);
        logIn.clickLogInButton();

        System.out.println("LogIn prosho!");
    }

}
