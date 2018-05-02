import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GraphicalTesting {

    private final String SITE = "http://automationpractice.com/";
    private WebDriver driver;
    private static WebDriverWait wait;
    private byte MAX_TIMEOUT = 10;
    private ArrayList<WebElement> navigationItems;

    @Before
    public void initialize(){
        driver = new FirefoxDriver();
        driver.get(SITE);
        wait = new WebDriverWait(driver, MAX_TIMEOUT);

        navigationItems = (ArrayList<WebElement>) driver.findElements(By.xpath("//ul[contains(@class, 'menu-content')]//li"));

    }

    @Test
    public void navigationAAACompatible(){
        String parentMenuElementBackgroundColor = GraphicalTestingUtils.findParentElementBackgroundColorFromListWithDisplayVisile(driver, navigationItems);
        Color backgroundColorRGB = GraphicalTestingUtils.transformStringToColor(parentMenuElementBackgroundColor);

        ArrayList<Color> navigationItemsColors = new ArrayList<>();
        for(WebElement element : navigationItems){
            if(GraphicalTestingUtils.isNavigationItem(element)) {
                String frontLinkColor = element.findElement(By.xpath("//li//a")).getCssValue("color");
                Color frontLinkColorRGB = GraphicalTestingUtils.transformStringToColor(frontLinkColor);
                navigationItemsColors.add(frontLinkColorRGB);
            }
        }


        double finalContrast1 = GraphicalTestingUtils.contrast(backgroundColorRGB, navigationItemsColors.get(0));
        double finalContrast2 = GraphicalTestingUtils.contrast(backgroundColorRGB, navigationItemsColors.get(1));
        double finalContrast3 = GraphicalTestingUtils.contrast(backgroundColorRGB, navigationItemsColors.get(2));

        assertFalse(finalContrast1 > 7f);
        assertFalse(finalContrast2 > 7f);
        assertFalse(finalContrast3 > 7f);
    }

    @Test
    public void navigationItemsCount(){
        int counter = 0;
        for(WebElement element : navigationItems){
            if(GraphicalTestingUtils.isNavigationItem(element)) counter++;
        }
        assertEquals(3, counter / 2);
    }

    @Test
    public void navigationAACompatible(){
        String parentMenuElementBackgroundColor = GraphicalTestingUtils.findParentElementBackgroundColorFromListWithDisplayVisile(driver, navigationItems);
        Color backgroundColorRGB = GraphicalTestingUtils.transformStringToColor(parentMenuElementBackgroundColor);

        ArrayList<Color> navigationItemsColors = new ArrayList<>();
        for(WebElement element : navigationItems){
            if(GraphicalTestingUtils.isNavigationItem(element)) {
                String frontLinkColor = element.findElement(By.xpath("//li//a")).getCssValue("color");
                Color frontLinkColorRGB = GraphicalTestingUtils.transformStringToColor(frontLinkColor);
                navigationItemsColors.add(frontLinkColorRGB);
            }
        }


        double finalContrast1 = GraphicalTestingUtils.contrast(backgroundColorRGB, navigationItemsColors.get(0));
        double finalContrast2 = GraphicalTestingUtils.contrast(backgroundColorRGB, navigationItemsColors.get(1));
        double finalContrast3 = GraphicalTestingUtils.contrast(backgroundColorRGB, navigationItemsColors.get(2));

        assertFalse(finalContrast1 >= 4.5f && finalContrast1 <7f);
        assertFalse(finalContrast2 >= 4.5f && finalContrast2 <7f);
        assertFalse(finalContrast3 >= 4.5f && finalContrast3 <7f);
    }

    @Test
    public void navigationACompatible(){
        String parentMenuElementBackgroundColor = GraphicalTestingUtils.findParentElementBackgroundColorFromListWithDisplayVisile(driver, navigationItems);
        Color backgroundColorRGB = GraphicalTestingUtils.transformStringToColor(parentMenuElementBackgroundColor);

        ArrayList<Color> navigationItemsColors = new ArrayList<>();
        for(WebElement element : navigationItems){
            if(GraphicalTestingUtils.isNavigationItem(element)) {
                String frontLinkColor = element.findElement(By.xpath("//li//a")).getCssValue("color");
                Color frontLinkColorRGB = GraphicalTestingUtils.transformStringToColor(frontLinkColor);
                navigationItemsColors.add(frontLinkColorRGB);
            }
        }


        double finalContrast1 = GraphicalTestingUtils.contrast(backgroundColorRGB, navigationItemsColors.get(0));
        double finalContrast2 = GraphicalTestingUtils.contrast(backgroundColorRGB, navigationItemsColors.get(1));
        double finalContrast3 = GraphicalTestingUtils.contrast(backgroundColorRGB, navigationItemsColors.get(2));

        assertTrue(finalContrast1 >= 2f && finalContrast1 <4.5f);
        assertTrue(finalContrast2 >= 2f && finalContrast2 <4.5f);
        assertTrue(finalContrast3 >= 2f && finalContrast3 <4.5f);
    }

    @Test
    public void errorColorIsRed(){
        WebElement signInButton = driver.findElement(By.className("login"));
        signInButton.click();

        WebElement inputTextField = driver.findElement(By.id("email_create"));
        inputTextField.sendKeys("ewe@tpp.cz");

        WebElement submitButton = driver.findElement(By.id("SubmitCreate"));
        submitButton.click();

        WebElement errorDiv = driver.findElement(By.id("create_account_error"));
        assertEquals(errorDiv.getCssValue("background-color"), "rgb(243, 81, 92)");
    }

    @After
    public void destroy(){
        driver.quit();
    }

}
