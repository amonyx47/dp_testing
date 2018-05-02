import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class CartTestingUtils {

    public static WebElement addBlouse(WebDriver driver){
        WebElement blouseProductLink = driver.findElement(By.xpath("//img[@title='Blouse']"));
        blouseProductLink.click();

        CartTesting.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()[contains(., 'Blouse')]]")));

        WebElement submitButton = driver.findElement(By.name("Submit"));
        submitButton.click();

        return driver.findElement(By.xpath("//i[@class='icon-ok']"));
    }

    public static WebElement addTshirt(WebDriver driver) {
        List<WebElement> menuButton = driver.findElements(By.xpath("//a[@title='T-shirts']"));
        WebElement tshirtButton = null;
        for(WebElement element : menuButton){
            if(!element.getCssValue("display").equals("none"))
            tshirtButton = element;
        }
        tshirtButton.click();

        WebElement quickViewButton = driver.findElement(By.xpath("//div[@class='product-container']"));
        quickViewButton.click();

        WebElement submitButton = driver.findElement(By.name("Submit"));
        submitButton.click();

        return driver.findElement(By.xpath("//i[@class='icon-ok']"));
    }
}
