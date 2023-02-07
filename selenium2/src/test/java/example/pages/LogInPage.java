package example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LogInPage
{
    private WebDriver driver;

    private static String PAGE_URL="http://localhost:4200/login";

    public LogInPage(WebDriver driver)
    {
        this.driver=driver;
        driver.get(PAGE_URL);

        PageFactory.initElements(driver, this);
    }
}
