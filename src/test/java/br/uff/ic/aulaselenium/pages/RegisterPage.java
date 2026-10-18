package br.uff.ic.aulaselenium.pages;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
	private WebDriver driver;
    private WebDriverWait wait;

    // Locators - Detalhes da Conta
    private By enterAccountInfoHeader = By.xpath("//b[text()='Enter Account Information']");
    private By genderMrRadio = By.id("id_gender1");
    private By passwordInput = By.id("password");
    private By daysSelect = By.id("days");
    private By monthsSelect = By.id("months");
    private By yearsSelect = By.id("years");

    private By newsletterCheckbox = By.id("newsletter");
    private By specialOffersCheckbox = By.id("optin");

    // Locators - Endereço e Contato
    private By firstNameInput = By.id("first_name");
    private By lastNameInput = By.id("last_name");
    private By companyInput = By.id("company");
    private By address1Input = By.id("address1");
    private By address2Input = By.id("address2");
    private By countrySelect = By.id("country");
    private By stateInput = By.id("state");
    private By cityInput = By.id("city");
    private By zipcodeInput = By.id("zipcode");
    private By mobileNumberInput = By.id("mobile_number");

    private By createAccountButton = By.cssSelector("button[data-qa='create-account']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isEnterAccountInfoVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(enterAccountInfoHeader)).isDisplayed();
    }

    public void fillAccountDetails(String password, String day, String month, String year) {
        driver.findElement(genderMrRadio).click();
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);

        new Select(driver.findElement(daysSelect)).selectByValue(day);
        new Select(driver.findElement(monthsSelect)).selectByValue(month);
        new Select(driver.findElement(yearsSelect)).selectByValue(year);
    }

    public void selectCheckboxes() {
        driver.findElement(newsletterCheckbox).click();
        driver.findElement(specialOffersCheckbox).click();
    }

    public void fillAddressDetails(String firstName, String lastName, String company, String address1,
                                   String address2, String country, String state, String city,
                                   String zipcode, String mobileNumber) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(companyInput).sendKeys(company);
        driver.findElement(address1Input).sendKeys(address1);
        driver.findElement(address2Input).sendKeys(address2);

        new Select(driver.findElement(countrySelect)).selectByVisibleText(country);

        driver.findElement(stateInput).sendKeys(state);
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(zipcodeInput).sendKeys(zipcode);
        driver.findElement(mobileNumberInput).sendKeys(mobileNumber);
    }

    public void clickCreateAccount() {
        driver.findElement(createAccountButton).click();
    }
}
