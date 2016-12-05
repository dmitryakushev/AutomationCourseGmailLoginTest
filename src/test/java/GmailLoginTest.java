
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.Assert.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 16.03.2016.
 */
public class GmailLoginTest {

    private static WebDriver driver;
    private static WebElement emailField;
    private static WebElement nextButton;
    private static WebElement passwordField;
    private static WebElement signInBtn;
    private static WebElement createMessageBtn;

    private static void initEnterEmailPage(){
        emailField = driver.findElement(By.id("Email"));
        nextButton = driver.findElement(By.id("next"));
    }

    private static void initEnterPasswordPage(){
        passwordField = driver.findElement(By.id("Passwd"));
        signInBtn = driver.findElement(By.id("signIn"));
    }

    private static void initGmailInboxPage(){
        createMessageBtn = driver.findElement(By.xpath("//div[@gh='cm']"));
    }

    @BeforeClass
    public static void initFirefox(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    @Parameters({"page", "validEmailAddress", "password"})
    public void testLoginToGmail_validCredentials_successLogin(String page, String validEmailAddress, String password){
        driver.get(page);
        initEnterEmailPage();
        emailField.sendKeys(validEmailAddress);
        nextButton.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        initEnterPasswordPage();
        passwordField.sendKeys(password);
        signInBtn.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        initGmailInboxPage();
        Assert.assertTrue(createMessageBtn.isDisplayed());
    }


    @AfterClass
    public static void closeFirefox(){
        driver.quit();
    }
}
