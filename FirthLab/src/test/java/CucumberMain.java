import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class CucumberMain {
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

    }


    @Given("^Open main page$")
    public void openMainPage() {
        driver.manage().window().maximize();
        driver.get("http://rik-i-morti.ru/");
    }

    @When("^Successfully authorization$")
    public void successfullyAuthorization(){
        mainPage.successAuthorization(existingUser);
    }

    @When("^Wrong authorization$")
    public void UnSuccessfullyAuthorization(){
        mainPage.wrongAuthorization(nonexistingUser);
    }

    @Then("^Authorization has been successfully$")
    public void authorizationHasBeenSuccessfully(){
        mainPage.checkAuthorization(existingUser.getLogin());
    }

    @When("^Authorization has been wrong$")
    public void unsuccessfullyAuthorization(){
        mainPage.checkErrorMessage("Ошибка авторизации");
    }

    @After
    public void clear()
    {
        driver.quit();
    }
}
