import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private boolean successfulProfileTransition = false;
    private boolean successfulHomePageLeaving = false;

    @FindBy(linkText = "Мой профиль")
    private WebElement profile;

    @FindBy(linkText = "Выйти")
    private WebElement exit;

    @FindBy(linkText = "Сообщения")
    private WebElement messages;

    @FindBy(linkText = "Мои закладки")
    private WebElement bookmarks;

    @FindBy(linkText = "Непрочитанное")
    private WebElement unread;

    @FindBy(className = "login-name")
    private WebElement login;

    @FindBy(className = "sub-title")
    private WebElement subTitle;

    public HomePage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public ProfilePage getProfile()
    {
        driver.get(profile.getAttribute("href"));
        successfulProfileTransition = (new WebDriverWait(driver, 30))
            .until(ExpectedConditions.presenceOfElementLocated(By.linkText("редактировать профиль"))).isDisplayed();
        return new ProfilePage(driver);
    }

    public String getLogin()
    {
        return login.getText();
    }

    public boolean checkSuccessfulProfileTransition()
    {
        return successfulProfileTransition;
    }

    public boolean checkSuccessfulHomePageLeaving()
    {
        return successfulHomePageLeaving;
    }

    public boolean checkSubtitle(String expected)
    {
        return subTitle.getText().startsWith(expected);
    }

    public MainPage leaveHomePage()
    {
        String url = exit.getAttribute("href");
        driver.get(url);
        successfulHomePageLeaving = (new WebDriverWait(driver, 30))
            .until(ExpectedConditions.presenceOfElementLocated(By.className("login-button"))).isDisplayed();

        return new MainPage(driver);
    }

}
