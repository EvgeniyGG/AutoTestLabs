import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProfilePage {
    private WebDriver driver;
    private Options options;
    private boolean optionsIsHidden = true;

    @FindBy(linkText = "редактировать профиль")
    private WebElement editProfile;

    @FindBy(className = "user-right")
    private WebElement userName;

    @FindBy(id = "options")
    private WebElement editor;

    @FindBy(linkText = "Главная")
    private WebElement mainButton;

    @FindBy(partialLinkText = "Сезон")
    private List<WebElement> elements;

    public ProfilePage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        options = new Options();
    }

    public boolean checkUsername(String username)
    {
        return username.startsWith("Пользователь: " + username);
    }

    public void chooseSeason(int seasonNum)
    {
        Assert.assertTrue("No such season here", elements.size() >= seasonNum
                && seasonNum > 0);
        String url = elements.get(seasonNum - 1).getAttribute("href");
        System.out.println(url);
        driver.get(url);
    }

    public HomePage getHomePage()
    {
        String url = mainButton.getAttribute("href");
        driver.get(url);
        return new HomePage(driver);
    }

    public void editProfile()
    {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("ShowOrHide('options')", editProfile);

        optionsIsHidden = !optionsIsHidden;

    }


    public void changeName(String newName)
    {
        if(optionsIsHidden)
        {
            editProfile();
            optionsIsHidden = !optionsIsHidden;
            options.setName(newName);
        }
        else
        {
            options.setName(newName);
        }

    }

    public boolean checkHometown(String expectedHometown)
    {
        WebElement hometown = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"userinfo\"]/div[1]/div[1]/div[2]/div[7]/div[2]")));
        return  hometown.getText().startsWith(expectedHometown);
    }

    public boolean checkName(String expectedName)
    {
        WebElement name = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"userinfo\"]/div[1]/div[1]/div[2]/div[6]/div[2]")));
        return  name.getText().startsWith(expectedName);
    }
    //TODO add changeEmail method here
    //public void changeEmail(String newEmail);


    public void changeHometown(String newHometown)
    {
        if(optionsIsHidden)
        {
            editProfile();
            optionsIsHidden = !optionsIsHidden;
            options.setHometown(newHometown);
        }
        else
        {
            options.setHometown(newHometown);
        }
    }
    private class Options
    {
        private WebElement name, email, hometown, send;

        public Options()
        {
            name = editor.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div[1]/div[2]/input"));
            email = editor.findElement(By.className("label"));
            hometown = editor.findElement(By.name("land"));
            send = editor.findElement(By.className("fbutton"));
        }

        public void setHometown(String hometown)
        {
            (new WebDriverWait(driver, 30))
                    .until(ExpectedConditions.elementToBeClickable(this.hometown));
            this.hometown.clear();
            this.hometown.sendKeys(hometown);
            this.send.submit();
        }

        public void setName(String name)
        {
            (new WebDriverWait(driver, 30))
                    .until(ExpectedConditions.elementToBeClickable(this.name));
            this.name.clear();
            this.name.sendKeys(name);
            this.send.submit();
        }

    }
}
