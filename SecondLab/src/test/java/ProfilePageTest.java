import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ProfilePageTest {

    private ChromeOptions options;
    private WebDriver driver;
    private MainPage mainPage;
    private HomePage homePage;
    private ProfilePage profilePage;
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
        profilePage = homePage.getProfile();
    }

    @Test
    @Category({SuiteCategories.ChangingProfileOptionTests.class})
    public void editProfileOptionsTest1()
    {
        profilePage.editProfile();
        profilePage.changeName("Alibaba");
        Assert.assertTrue(profilePage.checkName("Alibaba"));
        driver.quit();
    }

    @Test
    @Category({SuiteCategories.ChangingProfileOptionTests.class})
    public void editProfileOptionsTest2()
    {
        profilePage.editProfile();
        profilePage.changeHometown("Orenburg");
        Assert.assertTrue(profilePage.checkHometown("Orenburg"));
        driver.quit();
    }

    @Test
    @Category({SuiteCategories.TransitionTests.class})
    public void goHomeTest()
    {
        homePage = profilePage.getHomePage();
        Assert.assertTrue("No such subtitle", homePage.checkSubtitle("Мультсериал «Рик и Морти» — праздник для поклонников нестандартного юмора"));
        driver.quit();
    }

}
