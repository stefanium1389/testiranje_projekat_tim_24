package UberAppTim24.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage
{
    private WebDriver driver;

    private static String PAGE_URL="http://localhost:4200/login";

    @FindBy(id = "typeEmailX")
    private WebElement mailInput;

    @FindBy(id = "typePasswordX")
    private WebElement passwordInput;

    @FindBy(id = "logInButton")
    private WebElement logInButton;

    public LogInPage(WebDriver driver)
    {
        this.driver=driver;
        driver.get(PAGE_URL);

        PageFactory.initElements(driver, this);
    }

    public void enterMail(String mail)
    {
        mailInput.clear();
        mailInput.sendKeys(mail);
    }

    public void enterPassword(String password)
    {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogInButton()
    {
        logInButton.click();
    }
}
