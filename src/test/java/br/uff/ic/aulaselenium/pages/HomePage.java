package br.uff.ic.aulaselenium.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By signupLoginLink = By.xpath("//a[contains(text(), 'Signup / Login')]");
    private By deleteAccountLink = By.xpath("//a[contains(text(), 'Delete Account')]");
    private By loggedInAsUser = By.xpath("//li[contains(., 'Logged in as')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToHomePage() {
        driver.get("http://automationexercise.com");
    }

    public boolean isHomePageVisible() {
        return driver.getTitle().contains("Automation Exercise");
    }

    public void clickSignupLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(signupLoginLink)).click();
    }

    public boolean isLoggedInAsVisible(String username) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInAsUser))
                   .getText().contains(username);
    }

    public void clickDeleteAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteAccountLink)).click();
    }
}
