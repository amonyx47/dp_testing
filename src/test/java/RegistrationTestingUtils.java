import HelperClasses.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationTestingUtils {

    public static boolean isMandatoryField(WebDriver driver, String id){
        return driver.findElement(By.xpath("//label[@for=" + "'" + id + "'" + "]")).findElement(By.tagName("sup")).getText().trim().equalsIgnoreCase("*");
    }

    public static void populateDate(WebDriver driver, int day, String month, int year){
        WebElement dayListSelect = driver.findElement(By.id("days"));
        new Select(dayListSelect).selectByValue(String.valueOf(day));

        WebElement monthListSelect = driver.findElement(By.id("months"));
        new Select(monthListSelect).selectByVisibleText(month + " ");

        WebElement yearListSelect = driver.findElement(By.id("years"));
        new Select(yearListSelect).selectByValue(String.valueOf(year));
    }

    public static boolean verifyUserLoggedIn(WebDriver driver, User user){
        WebElement accountLink = driver.findElement(By.xpath("//a[@class='account']"));
        return (user.getFirstname() + " " + user.getLastname()).equalsIgnoreCase(accountLink.getText().trim());
    }

    public static void registrateUser(WebDriver driver, User sampleUser){

        WebElement signInButton = driver.findElement(By.className("login"));
        signInButton.click();

        WebElement inputTextField = driver.findElement(By.id("email_create"));
        inputTextField.sendKeys(sampleUser.getEmail());

        WebElement submitButton = driver.findElement(By.id("SubmitCreate"));
        submitButton.click();

        RegistrationTesting.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submitAccount")));

        WebElement firstnameTextField = driver.findElement(By.id("customer_firstname"));
        if(RegistrationTestingUtils.isMandatoryField(driver, firstnameTextField.getAttribute("id"))){
            firstnameTextField.sendKeys(sampleUser.getFirstname());
        }

        WebElement lastnameTextField = driver.findElement(By.id("customer_lastname"));
        if(RegistrationTestingUtils.isMandatoryField(driver, lastnameTextField.getAttribute("id"))) {
            lastnameTextField.sendKeys(sampleUser.getLastname());
        }

        WebElement passwordTextField = driver.findElement(By.id("passwd"));
        passwordTextField.sendKeys(sampleUser.getPassword());

        RegistrationTestingUtils.populateDate(driver, sampleUser.getDayOfBirth(), sampleUser.getMonthOfBirth(), sampleUser.getYearOfBirth());

        WebElement firstnameAddress = driver.findElement(By.id("firstname"));
        firstnameAddress.sendKeys(sampleUser.getAddress().getFirstname());

        WebElement lastnameAddress = driver.findElement(By.id("lastname"));
        lastnameAddress.sendKeys(sampleUser.getAddress().getLastname());

        WebElement companyAddress = driver.findElement(By.id("company"));
        companyAddress.sendKeys(sampleUser.getAddress().getCompany());

        WebElement address1 = driver.findElement(By.id("address1"));
        address1.sendKeys(sampleUser.getAddress().getAddress());

        WebElement address2 = driver.findElement(By.id("address2"));
        address2.sendKeys(sampleUser.getAddress().getAddress2());

        WebElement cityAddress = driver.findElement(By.id("city"));
        cityAddress.sendKeys(sampleUser.getAddress().getCity());

        WebElement stateAddress = driver.findElement(By.id("id_state"));
        new Select(stateAddress).selectByVisibleText(sampleUser.getAddress().getState());

        WebElement postcodeAddress = driver.findElement(By.id("postcode"));
        postcodeAddress.sendKeys(sampleUser.getAddress().getPostalCode());

        WebElement countryAddress = driver.findElement(By.id("id_country"));
        countryAddress.sendKeys(sampleUser.getAddress().getCountry());

        WebElement otherinfAddress = driver.findElement(By.id("other"));
        otherinfAddress.sendKeys(sampleUser.getAddress().getAdditional());

        WebElement homenumberAddress = driver.findElement(By.id("phone"));
        homenumberAddress.sendKeys(sampleUser.getAddress().getHomePhone());

        WebElement mobilenumberAddress = driver.findElement(By.id("phone_mobile"));
        mobilenumberAddress.sendKeys(sampleUser.getAddress().getMobilePhone());

        WebElement aliasAddress = driver.findElement(By.id("alias"));
        aliasAddress.sendKeys(sampleUser.getAddress().getAddressAlias());

        WebElement confirmRegistrationButton = driver.findElement(By.id("submitAccount"));
        confirmRegistrationButton.click();
    }
}
