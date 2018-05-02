import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CartTesting {

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
    public void addBlouse(){
        WebElement blouse = CartTestingUtils.addBlouse(driver);
        assertNotNull(blouse);
    }

    @Test
    public void addTshirt(){
        WebElement tshirt = CartTestingUtils.addTshirt(driver);
        assertNotNull(tshirt);
    }

    @Test
    public void checkSumForBlouseAndTshirt(){
        CartTestingUtils.addBlouse(driver);
        driver.get(SITE);
        CartTestingUtils.addTshirt(driver);
        driver.get(SITE);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='View my shopping cart']")));

        WebElement viewCartButton = driver.findElement(By.xpath("//a[@title='View my shopping cart']"));
        viewCartButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("total_price")));

        WebElement totalSpan = driver.findElement(By.id("total_price"));

        assertEquals("$45.51", totalSpan.getText().trim());
    }

    @After
    public void destroy(){
        driver.quit();
    }

}
