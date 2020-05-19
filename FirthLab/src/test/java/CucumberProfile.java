import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CucumberProfile {

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
    }
    @Given("^Open profile page$")
    public void openProfilePage()
    {
        driver.manage().window().maximize();
        driver.get("http://rik-i-morti.ru/");
        homePage = mainPage.successAuthorization(user);
        profilePage = homePage.getProfile();
    }

    @When("^Change profile name first time$")
    public void changeProfileNameFirstTime()
    {
        profilePage.changeName("PreviousName");
    }

    @Then("^Change profile name again$")
    public void changeProfileNameAgain()
    {
        profilePage.changeName("CurrentName");
    }

    @Then("^Check profile name$")
    public void checkProfileName()
    {
        profilePage.checkName("NewName");
    }

    @When("^Changing profile hometown first time$")
    public void changingProfileHometownFirstTime()
    {
        profilePage.changeHometown("PreviousHometown");
    }

    @Then("^Changing profile hometown again$")
    public void changingProfileHometownAgain()
    {
        profilePage.changeHometown("NewHometown");
    }

    @Then("^Check profile hometown$")
    public void checkProfileHometown()
    {
        profilePage.checkHometown("NewHometown");
    }
    @After
    public void clear()
    {
        driver.quit();
    }
}
