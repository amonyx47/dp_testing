import HelperClasses.Address;
import HelperClasses.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RegistrationTesting {

    private final String SITE = "http://automationpractice.com/";
    private WebDriver driver;
    public static WebDriverWait wait;
    private byte MAX_TIMEOUT = 10;

    private User sampleUser;
    private Address sampleAddress;

    @Before
    public void initialize(){
        driver = new FirefoxDriver();
        driver.get(SITE);
        wait = new WebDriverWait(driver, MAX_TIMEOUT);

        sampleAddress = new Address("Johny", "Minarciny", "DP", "Jegeho 10", "", "Bratislava", "Alaska", "84104", "United States", "", "09876554321", "1234567890", "MainAddress");
        sampleUser = new User("Mr", "Johny", "Minarciny", "ewe22@tpp.cz", "password", 27, "June", 1992, false, false, sampleAddress);

    }

    @Test
    public void verifySignInButton(){
        WebElement signInButton = driver.findElement(By.className("login"));
        assertNotNull("Is Sign In button null",signInButton);
    }

    @Test
    public void registrateUser(){
        RegistrationTestingUtils.registrateUser(driver, sampleUser);
    }


    @Test(expected = TimeoutException.class)
    public void registrateExistingUser(){
        RegistrationTestingUtils.registrateUser(driver, sampleUser);
    }


    @Test(expected = TimeoutException.class)
    public void registrateBlankUser(){
        User blankUser = new User();
        RegistrationTestingUtils.registrateUser(driver, blankUser);
    }


    @Test
    public void registrateUserOnlyMandatoryFields(){
        WebElement signInButton = driver.findElement(By.className("login"));
        signInButton.click();

        WebElement inputTextField = driver.findElement(By.id("email_create"));
        inputTextField.sendKeys(sampleUser.getEmail());

        WebElement submitButton = driver.findElement(By.id("SubmitCreate"));
        submitButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submitAccount")));

        WebElement firstnameTextField = driver.findElement(By.id("customer_firstname"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "customer_firstname")){
            firstnameTextField.sendKeys(sampleUser.getFirstname());
        }

        WebElement lastnameTextField = driver.findElement(By.id("customer_lastname"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "customer_lastname")) {
            lastnameTextField.sendKeys(sampleUser.getLastname());
        }

        WebElement passwordTextField = driver.findElement(By.id("passwd"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "passwd")) {
            passwordTextField.sendKeys(sampleUser.getPassword());
        }

        RegistrationTestingUtils.populateDate(driver, sampleUser.getDayOfBirth(), sampleUser.getMonthOfBirth(), sampleUser.getYearOfBirth());

        WebElement firstnameAddress = driver.findElement(By.id("firstname"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "firstname")) {
            firstnameAddress.sendKeys(sampleUser.getAddress().getFirstname());
        }

        WebElement lastnameAddress = driver.findElement(By.id("lastname"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "lastname")) {
            lastnameAddress.sendKeys(sampleUser.getAddress().getLastname());
        }

        WebElement companyAddress = driver.findElement(By.id("company"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "company")) {
            companyAddress.sendKeys(sampleUser.getAddress().getCompany());
        }

        WebElement address1 = driver.findElement(By.id("address1"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "address1")) {
            address1.sendKeys(sampleUser.getAddress().getAddress());
        }

        WebElement address2 = driver.findElement(By.id("address2"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "address2")) {
            address2.sendKeys(sampleUser.getAddress().getAddress2());
        }

        WebElement cityAddress = driver.findElement(By.id("city"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "city")) {
            cityAddress.sendKeys(sampleUser.getAddress().getCity());
        }

        WebElement stateAddress = driver.findElement(By.id("id_state"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "id_state")) {
            new Select(stateAddress).selectByVisibleText(sampleUser.getAddress().getState());
        }

        WebElement postcodeAddress = driver.findElement(By.id("postcode"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "postcode")) {
            postcodeAddress.sendKeys(sampleUser.getAddress().getPostalCode());
        }

        WebElement countryAddress = driver.findElement(By.id("id_country"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "id_country")) {
            countryAddress.sendKeys(sampleUser.getAddress().getCountry());
        }

        WebElement otherinfAddress = driver.findElement(By.id("other"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "other")) {
            otherinfAddress.sendKeys(sampleUser.getAddress().getAdditional());
        }

        WebElement homenumberAddress = driver.findElement(By.id("phone"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "phone")) {
            homenumberAddress.sendKeys(sampleUser.getAddress().getHomePhone());
        }

        WebElement mobilenumberAddress = driver.findElement(By.id("phone_mobile"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "phone_mobile")) {
            mobilenumberAddress.sendKeys(sampleUser.getAddress().getMobilePhone());
        }

        WebElement aliasAddress = driver.findElement(By.id("alias"));
        if(RegistrationTestingUtils.isMandatoryField(driver, "alias")) {
            aliasAddress.sendKeys(sampleUser.getAddress().getAddressAlias());
        }

        WebElement confirmRegistrationButton = driver.findElement(By.id("submitAccount"));
        confirmRegistrationButton.click();
    }


    @Test
    public void loginRegisteredUser(){
        WebElement signInButton = driver.findElement(By.className("login"));
        signInButton.click();

        WebElement inputTextField = driver.findElement(By.id("email"));
        inputTextField.sendKeys(sampleUser.getEmail());

        WebElement passwordTextField = driver.findElement(By.id("passwd"));
        passwordTextField.sendKeys(sampleUser.getPassword());

        WebElement confirmRegistrationButton = driver.findElement(By.id("SubmitLogin"));
        confirmRegistrationButton.click();

        assertTrue(RegistrationTestingUtils.verifyUserLoggedIn(driver, sampleUser));
    }

    @After
    public void destroy(){
        driver.quit();
    }
}
