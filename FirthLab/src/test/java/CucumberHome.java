import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CucumberHome {
    private ChromeOptions options;
    private WebDriver driver;
    private MainPage mainPage;
    private HomePage homePage;
    User user;

    @Before
    public void setUp()
    {
        options = new ChromeOptions().addArguments("--no-sandbox");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
        user = new User("CreatedForLab", "securePass");
        mainPage = new MainPage(driver);
    }
    @Given("^Open home page$")
    public void openHomePage()
    {
        driver.manage().window().maximize();
        driver.get("http://rik-i-morti.ru/");
        homePage = mainPage.successAuthorization(user);
    }

    @When("^Attempt to go to profile$")
    public void attemptToGoToProfile()
    {
        homePage.getProfile();
    }

    @Then("^Successfully profile transition$")
    public void successfullyProfileTransition()
    {
        homePage.checkSuccessfulProfileTransition();
    }

    @When("^Attempt to leave home page$")
    public void attemptToLeaveHomePage()
    {
        homePage.leaveHomePage();
    }

    @Then("^Check success$")
    public void checkSuccess()
    {
        homePage.checkSuccessfulHomePageLeaving();
    }

    @After
    public void clear()
    {
        driver.quit();
    }

}
