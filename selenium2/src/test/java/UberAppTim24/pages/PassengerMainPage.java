package UberAppTim24.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PassengerMainPage
{
    private WebDriver driver;

    @FindBy(id = "start-location-input")
    private WebElement startLocationInput;

    @FindBy(id = "end-location-input")
    private WebElement endLocationInput;

    @FindBy(id = "begin-button")
    private WebElement beginButton;

    @FindBy(id = "start-location-search")
    private WebElement startSearchButton;

    @FindBy(id = "end-location-search")
    private WebElement endSearchButton;

    @FindBy(id = "estimate-cost")
    private WebElement estimateCost;
    
    @FindBy(id="profile-pic-nav")
    private WebElement profilePicMenu;
    
    @FindBy(id="logout-option")
    private WebElement logoutOption;


    public PassengerMainPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void enterStartLocation(String address)
    {
        startLocationInput.click();
        startLocationInput.clear();
        startLocationInput.sendKeys(address);
    }

    public void searchStartLocation()
    {
        startSearchButton.click();
    }

    public void enterEndLocation(String address)
    {
        endLocationInput.click();
        endLocationInput.clear();
        endLocationInput.sendKeys(address);
    }

    public void searchEndLocation()
    {
        endSearchButton.click();
    }

    public void clickBeginButton()
    {
        beginButton.click();
    }

    public void waitForPageToOpen() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(beginButton));
    }

    public void waitForEstimateToShow() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(estimateCost));
    }
    
    public void clickOnProfileIcon() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(profilePicMenu)).click();
    }
    public void clickOnLogoutOption() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(logoutOption)).click();
    }
}
