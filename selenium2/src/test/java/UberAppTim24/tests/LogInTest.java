package UberAppTim24.tests;

import UberAppTim24.pages.HomePage;
import UberAppTim24.pages.LogInPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class LogInTest extends TestBase
{
    public static String validMail = "rxx@mail.com";
    public static String validPassword = "admin";
    public static String wrongMail = "Relja";
    public static String wrongPassword = "Radeka";
    private WebElement profilePicture;
    private WebElement logOutButton;

    //@Test
    public void noInput()
    {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.clickLogInButton();

        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());
    }

    //@Test
    public void noPassword()
    {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.enterMail(validMail);
        logIn.clickLogInButton();

        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());
    }

    //@Test
    public void noMail()
    {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.enterPassword(validPassword);
        logIn.clickLogInButton();

        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());
    }

    //@Test
    public void wrongMail()
    {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.enterMail(wrongMail);
        logIn.enterPassword(validPassword);
        logIn.clickLogInButton();

        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());
    }

    //@Test
    public void wrongPassword()
    {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.enterMail(validMail);
        logIn.enterPassword(wrongPassword);
        logIn.clickLogInButton();

        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());
    }

    @Test
    public void valid() throws InterruptedException {
        HomePage home = new HomePage(driver);
        home.clickOnPrijavaButton();

        LogInPage logIn = new LogInPage(driver);
        logIn.enterMail(validMail);
        logIn.enterPassword(validPassword);
        logIn.clickLogInButton();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        assertEquals("http://localhost:4200/user-home", driver.getCurrentUrl());

        profilePicture.findElement(By.className("actual-image"));
        profilePicture.click();
        //logOutButton.findElement(By.id("logOut"));
        //logOutButton.click();

    }
}
