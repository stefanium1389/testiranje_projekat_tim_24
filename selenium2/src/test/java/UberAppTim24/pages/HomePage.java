package UberAppTim24.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;

    private static String PAGE_URL="http://localhost:4200";

    @FindBy(id = "prijava")
    private WebElement prijavaButton;

    public HomePage(WebDriver driver){
        this.driver=driver;
        driver.get(PAGE_URL);

        PageFactory.initElements(driver, this);
    }

    public void clickOnPrijavaButton(){
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(prijavaButton)).click();
    }
}
