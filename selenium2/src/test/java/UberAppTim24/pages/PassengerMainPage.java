package UberAppTim24.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PassengerMainPage
{
    private WebDriver driver;

    @FindBy(id = "start-location-input")
    private WebElement startLocationInput;

    @FindBy(id = "end-location-input")
    private WebElement endLocationInput;

    @FindBy(id = "begin-button")
    private WebElement beginButton;

    @FindBy(id = "book-button")
    private WebElement bookButton;

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

    @FindBy(id = "withdraw-ride-button")
    private WebElement withdrawRideButton;

    @FindBy(css = "i.fa.fa-minus")
    WebElement firstMinusIcon;

    @FindBy(css = "i.fa.fa-star-o")
    WebElement favouriteIcon;

    @FindBy(css = ".mat-simple-snack-bar-content")
    private WebElement snackBar;

    @FindBy(id ="map")
    private WebElement map;

    @FindBy(id= "dialog-input-text")
    private WebElement dialogInput;

    @FindBy(id= "dialog-accept-button")
    private WebElement dialogAcceptButton;

    @FindBy(id="dialog-time-input")
    private WebElement dialogTimeInput;

    @FindBy(id="dialog-time-submit")
    private WebElement dialogTimeSubmit;

    @FindBy(id="display-time")
    private WebElement displayTimeText;

    @FindBy(css="#baby-checkbox .mat-checkbox-inner-container")
    private WebElement babyCheckbox;

    @FindBy(css="#pets-checkbox .mat-checkbox-inner-container")
    private WebElement petsCheckbox;
    
    @FindBy(id="mat-dialog-0")
    private WebElement endRideRateDialog;

    @FindBy(id = "link-users-button")
    private WebElement linkUsersButton;

    @FindBy(id = "search-user-input")
    private WebElement searchUsersInput;

    @FindBy(css = "#user-search-submit .mat-button-wrapper")
    private WebElement userSearchSubmitButton;

    @FindBy(css = ".profil-pregled")
    private WebElement profileCard;

    @FindBy(css = ".user-result")
    private WebElement searchResultCard;


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

    public void waitForPageToOpen() throws TimeoutException {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(beginButton));
    }

    public void waitForEstimateToShow() throws TimeoutException {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(estimateCost));
    }

    public void waitForWithdrawToShow() throws TimeoutException {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(withdrawRideButton));
    }

    public void clickWithdraw()
    {
        withdrawRideButton.click();
    }

    public void removeFirstLinkedUser()
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(firstMinusIcon));
        firstMinusIcon.click();
    }
    
    public void clickOnProfileIcon() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(profilePicMenu)).click();
    }
    public void clickOnLogoutOption() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(logoutOption)).click();
    }

    public void waitForSnackbarToAppear() throws TimeoutException
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(snackBar));
    }

    public void waitForSnackbarToDisappear() throws TimeoutException
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".mat-simple-snack-bar-content"),0));
    }

    public void waitForMapToBeClickable() throws TimeoutException
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(map));
    }

    public String getSnackBarText()
    {
        return snackBar.getText();
    }

    public void clickLocationOnMap(int x_offset, int y_offset)
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(map, x_offset, y_offset).click().build().perform();
    }

    public void clickLocationTypeOptions(String text)
    {
        Select dropdown = new Select(driver.findElement(By.id("location-type-selection")));
        dropdown.selectByVisibleText(text);
    }

    public void clickVehicleTypeOptions(String text)
    {
        Select dropdown = new Select(driver.findElement(By.id("vehicle-type-selection")));
        dropdown.selectByVisibleText(text);
    }

    public void waitForStartInputToGetAddress() throws TimeoutException
    {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return startLocationInput.getAttribute("value").length() != 0;
            }
        });
    }

    public void waitForEndInputToGetAddress() throws TimeoutException
    {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return endLocationInput.getAttribute("value").length() != 0;
            }
        });
    }

    public void waitForFavouriteIcon() throws TimeoutException
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(favouriteIcon));
    }

    public void clickOnAddFavourite()
    {
        favouriteIcon.click();
    }

    public void waitForDialogInput() throws TimeoutException
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(dialogInput));
    }

    public void typeInDialogInput(String text)
    {
        dialogInput.sendKeys(text);
    }

    public void acceptDialog()
    {
        dialogAcceptButton.click();
    }

    public void clickBookButton()
    {
        bookButton.click();
    }

    public void setTime(String hours, String minutes, String amOrPm)
    {
        dialogTimeInput.sendKeys(hours);
        dialogTimeInput.sendKeys(minutes);
        dialogTimeInput.sendKeys(amOrPm);
    }

    public void saveTime()
    {
        dialogTimeSubmit.click();
    }

    public void waitForTimeDisplay() throws TimeoutException
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(displayTimeText));
    }

    public String getTimeDisplayText ()
    {
        return displayTimeText.getText();
    }

    public void clickBabyCheckBox()
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(babyCheckbox).click().build().perform();
    }

    public void clickPetsCheckBox()
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(petsCheckbox).click().build().perform();
    }

    public void clickOnLinkFriends()
    {
        linkUsersButton.click();
    }

    public void waitForLinkFriendsToShow() throws TimeoutException
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchUsersInput));
    }

    public void clickOnUsersSubmitButton()
    {
        userSearchSubmitButton.click();
    }


    public void enterTextToUserInput(String input)
    {
        searchUsersInput.sendKeys(input);
    }
    public void waitForSearchResultsToShow() throws TimeoutException
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchResultCard));
    }

    public void addFirstFewUsers(int howMany)
    {
        List<WebElement> userResults = driver.findElements(By.cssSelector(".user-result"));
        for (int i=0; i<userResults.size(); i++)
        {
            if (i>howMany) break;

            userResults.get(i).click();
        }
    }

    public void acceptLinkedUsers()
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(userSearchSubmitButton, -300, 0).click().build().perform();
    }

    public void waitForLinkedUsersToAppearAgain() throws TimeoutException
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(profileCard));
    }

    public List<String> getNamesFromLinkedUsers()
    {
        List<WebElement> userResults = driver.findElements(By.cssSelector(".profil-pregled"));
        List<String> list = new ArrayList<>();
        for (int i=0; i<userResults.size(); i++)
        {
            List<WebElement> text = userResults.get(i).findElements(By.tagName("p"));
            list.add(text.get(0).getText());
        }
        return list;
    }

	public boolean isEndRatingDialogVisible() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(endRideRateDialog));
        return endRideRateDialog.isDisplayed();
	}

}
