package br.uff.ic.aulaselenium.pages;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountStatusPage {
	public WebDriver driver;
    private WebDriverWait wait;

    private By accountCreatedHeader = By.cssSelector("h2[data-qa='account-created']");
    private By accountDeletedHeader = By.cssSelector("h2[data-qa='account-deleted']");
    private By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public AccountStatusPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isAccountCreatedVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedHeader)).isDisplayed();
    }

    public boolean isAccountDeletedVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountDeletedHeader)).isDisplayed();
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }
}
