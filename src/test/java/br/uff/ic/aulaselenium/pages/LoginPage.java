package br.uff.ic.aulaselenium.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	private WebDriver driver;
    private WebDriverWait wait;

    // Locators - Login
    private By loginHeader = By.xpath("//h2[text()='Login to your account']");
    private By loginEmailInput = By.cssSelector("input[data-qa='login-email']");
    private By loginPasswordInput = By.cssSelector("input[data-qa='login-password']");
    private By loginButton = By.cssSelector("button[data-qa='login-button']");
    private By errorMessage = By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");

    // Locators - Cadastro Inicial
    private By signupHeader = By.xpath("//h2[text()='New User Signup!']");
    private By signupNameInput = By.cssSelector("input[data-qa='signup-name']");
    private By signupEmailInput = By.cssSelector("input[data-qa='signup-email']");
    private By signupButton = By.cssSelector("button[data-qa='signup-button']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isLoginHeaderVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader)).isDisplayed();
    }

    public boolean isSignupHeaderVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(signupHeader)).isDisplayed();
    }

    public void enterLoginCredentials(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginEmailInput)).clear();
        driver.findElement(loginEmailInput).sendKeys(email);
        driver.findElement(loginPasswordInput).clear();
        driver.findElement(loginPasswordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isErrorMessageVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
    }

    public void enterSignupCredentials(String name, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(signupNameInput)).clear();
        driver.findElement(signupNameInput).sendKeys(name);
        driver.findElement(signupEmailInput).clear();
        driver.findElement(signupEmailInput).sendKeys(email);
    }

    public void clickSignup() {
        driver.findElement(signupButton).click();
    }
}
