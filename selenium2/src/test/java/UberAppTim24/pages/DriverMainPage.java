package UberAppTim24.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverMainPage {
	
    private WebDriver driver;
    
    @FindBy(id="start-button")
    private WebElement startButton;
    
    @FindBy(id="end-button")
    private WebElement endButton;
	
	public DriverMainPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
	
	public void clickStartButton()
    {
		startButton.click();
    }
	
	public void clickEndButton()
    {
		endButton.click();
    }
	
	public void waitForStartButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(startButton));
	}
	
	public void waitForEndButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(endButton));
	}

}
