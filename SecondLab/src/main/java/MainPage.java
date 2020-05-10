import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage {
    private WebDriver driver;

    @FindBy(linkText = "Главная")
    private WebElement mainButton;

    @FindBy(id = "login_name")
    private WebElement login;

    @FindBy(id = "login_password")
    private WebElement pass;

    @FindBy(className = "login-button")
    private WebElement enter;

    @FindBy(partialLinkText = "Сезон")
    private List<WebElement> elements;

    @FindBy(className = "berrors")
    private WebElement authorizationError;

    public MainPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void chooseSeason(int seasonNum)
    {
        Assert.assertTrue("No such season here", elements.size() >= seasonNum
                && seasonNum > 0);
        String url = elements.get(seasonNum - 1).getAttribute("href");
        driver.get(url);
    }

    private void authorization(User user)
    {
        login.sendKeys(user.getLogin());
        pass.sendKeys(user.getPass());
        enter.submit();
    }
    public HomePage successAuthorization(User user)
    {
        authorization(user);
        return new HomePage(driver);
    }

    public MainPage wrongAuthorization(User user)
    {
        authorization(user);
        authorizationError = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("berrors")));

        Assert.assertTrue("Such user exists", authorizationError.isDisplayed());
        return new MainPage(driver);
    }

    public boolean checkErrorMessage(String message)
    {
        if(authorizationError.isDisplayed())
        {
            return authorizationError.getText().startsWith(message);
        }
        else
            return false;
    }

    public MainPage getMainPage()
    {
        String url = mainButton.getAttribute("href");
        driver.get(url);
        return new MainPage(driver);
    }
}
