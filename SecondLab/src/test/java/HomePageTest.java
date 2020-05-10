import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HomePageTest {
    private ChromeOptions options;
    private WebDriver driver;
    private MainPage mainPage;
    private HomePage homePage;
    User user;
    @Before
    public void setUp()
    {
        options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
        user = new User("CreatedForLab", "securePass");
        mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        driver.get("http://rik-i-morti.ru/");
        homePage = mainPage.successAuthorization(user);
    }

    @Test
    @Category({SuiteCategories.TransitionTests.class})
    public void profileTransitionTest()
    {
        homePage.getProfile();
        Assert.assertTrue("Profile transition test failed", homePage.checkSuccessfulProfileTransition());
        driver.quit();
    }

    @Test
    @Category({SuiteCategories.TransitionTests.class})
    public void exitHomePageTest()
    {
        homePage.leaveHomePage();
        Assert.assertTrue("Exit button test failed", homePage.checkSuccessfulHomePageLeaving());
        driver.quit();
    }

}
