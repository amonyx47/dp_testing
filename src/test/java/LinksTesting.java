import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class LinksTesting {

    private final String SITE = "http://automationpractice.com/";
    private WebDriver driver;
    public static WebDriverWait wait;
    private byte MAX_TIMEOUT = 10;

    @Before
    public void initialize(){
        driver = new FirefoxDriver();
        driver.get(SITE);
        wait = new WebDriverWait(driver, MAX_TIMEOUT);
    }

    @Test
    public void allLinksVerify(){
        boolean flag = true;
        String resMessage = "";
        ArrayList<WebElement> links = (ArrayList<WebElement>) driver.findElements(By.tagName("a"));
        String possibleBrokenLinkText = "";

        for(WebElement link : links){
            System.out.println(link.toString() + " " + link.getText() + " " + link.getAttribute("href"));
            if(!LinksTestingUtils.verifyUrl(link.getAttribute("href").trim())){
                resMessage = link.getAttribute("href") + " " + link.getText();
                flag = false;
                possibleBrokenLinkText = link.getText();
                break;
            }
        }
        if("" == possibleBrokenLinkText){
            possibleBrokenLinkText = "N/A";
        }
        assertTrue("A link with text " + possibleBrokenLinkText + " on the page verified as incorrect URL. " + resMessage, flag);
    }

    @Test(expected = MalformedURLException.class)
    public void allLinksValidate() throws IOException{
        boolean flag = true;
        String resMessage = "";
        ArrayList<WebElement> links = (ArrayList<WebElement>) driver.findElements(By.tagName("a"));
        for(WebElement link : links){
            if(!LinksTestingUtils.validateUrl(link.getAttribute("href").trim())){
                resMessage = link.getAttribute("href") + " " + link.getText();
                flag = false;
                break;
            }
        }
        assertTrue("A link on the page validated as incorrect link. " + resMessage, flag);
    }

    @After
    public void destroy(){
        driver.quit();
    }

}
