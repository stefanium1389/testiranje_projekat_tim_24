package UberAppTim24.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @FindBy(css = ".mat-simple-snack-bar-content")
    private WebElement snackBar;

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

    public void waitForSnackBarToAppear() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(snackBar));
    }

    public String getSnackBarText()
    {
        return snackBar.getText();
    }

}
