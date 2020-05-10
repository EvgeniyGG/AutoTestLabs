import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;

public class MainPageTest {

    private ChromeOptions options;
    private WebDriver driver;
    private MainPage mainPage;
    User nonexistingUser;
    User existingUser;

    @Before
    public void setUp()
    {
        options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
        mainPage = new MainPage(driver);
        existingUser = new User("CreatedForLab", "securePass");
        nonexistingUser =  new User("nonexistingLogin", "nonexistingPass");
        driver.manage().window().maximize();
        driver.get("http://rik-i-morti.ru/");

    }

    @Test
    @Category({SuiteCategories.TransitionTests.class})
    public void clickSeasonButtonTest()
    {
        Integer seasonNum = 2;
        mainPage.chooseSeason(seasonNum);
        Assert.assertEquals("Рик и Морти - " + seasonNum.toString() + " сезон - смотреть онлайн", driver.getTitle());
        driver.quit();
    }

    @Test
    @Category({SuiteCategories.AuthorizationTests.class})
    public void SuccessAuthorizationTest()
    {
        HomePage homePage = mainPage.successAuthorization(existingUser);
        String login = homePage.getLogin();
        Assert.assertEquals("CreatedForLab", login);
        driver.quit();
    }

    @Test
    @Category({SuiteCategories.AuthorizationTests.class})
    public void WrongAuthorizationTest()
    {
        MainPage homePage = mainPage.wrongAuthorization(nonexistingUser);
        boolean isEqual = homePage.checkErrorMessage("Ошибка авторизации");
        Assert.assertTrue("Error messages do not match", isEqual);
        driver.quit();
    }





}
