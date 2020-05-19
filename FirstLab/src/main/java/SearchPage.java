import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchPage {

    private static final String xpathToFirstLink = "//*[@id=\"rso\"]/div[1]/div[1]/div/div[1]/div/div[2]/div/div[1]/a";
    private static final String searchRequest = "What is a deadline? Webster";
    private static final String expectedLink = "https://www.merriam-webster.com/dictionary/deadline";

    public static void main(String []args)
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        WebDriver driver = new ChromeDriver(options);

        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(searchRequest);
        element.submit();

        (new WebDriverWait(driver,10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().startsWith(searchRequest);
            }
        });

        System.out.println("Searching: " + driver.getTitle());
        WebElement firstSuggestion = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathToFirstLink)));
        System.out.println("First site is - " + firstSuggestion.getAttribute("href"));
        boolean isEqual = firstSuggestion.getAttribute("href").equals(expectedLink);
        Assert.assertTrue("Error: Result link is - " + firstSuggestion.getAttribute("href") + ". Expected link : " + expectedLink , isEqual);
        System.out.println("Expected Link was: " + expectedLink);
        driver.quit();



    }

}
