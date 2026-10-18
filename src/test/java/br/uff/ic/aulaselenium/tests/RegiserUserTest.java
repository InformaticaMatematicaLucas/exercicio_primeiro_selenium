package br.uff.ic.aulaselenium.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import br.uff.ic.aulaselenium.pages.AccountStatusPage;
import br.uff.ic.aulaselenium.pages.HomePage;
import br.uff.ic.aulaselenium.pages.LoginPage;
import br.uff.ic.aulaselenium.pages.RegisterPage;

public class RegiserUserTest {
	private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private AccountStatusPage accountStatusPage;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        accountStatusPage = new AccountStatusPage(driver);
    }

    @ParameterizedTest(name = "Cadastro {index}: Nome=''{0}'' | Senha=''{1}'' | Telefone=''{2}''")
    @DisplayName("Test Case 1: Register User - Fluxo Completo E2E (PCE + AVL)")
    @CsvSource({
        // Entrada 1 (PCE - Dados Padrão Válidos)
        "'Estagiario Alfa', 'SenhaFortissima123', '21999999999'",
        
        // Entrada 2 (AVL - Nome Curto / Limite Inferior: 2 letras)
        "'Jo', 'SenhaMinima12', '11988888888'",
        
        // Entrada 3 (AVL - Nome Completo Extenso / Limite Superior)
        "'Alexandre de Alcantara e Silva Santos Junior', 'SenhaEspecial!@#', '31977777777'",
        
        // Entrada 4 (AVL - Senha de Curta Extensão / Limite de Borda)
        "'Dev Tester', '123456', '41966666666'",
        
        // Entrada 5 (PCE - Telefone Formatado com Código de Área)
        "'Maria Oliveira', 'MinhaSenhaSegura2026', '5521955555555'"
    })
    public void testRegisterUser(String nome, String senha, String telefone) {
        // Gerar e-mail único por timestamp para não dar conflito no site
        String emailUnico = "qa_" + System.currentTimeMillis() + "@teste.com";

        // 1 e 2. Launch browser e acessar URL
        homePage.navigateToHomePage();

        // 3. Verify home page is visible
        assertTrue(homePage.isHomePageVisible(), "Home page não está visível!");

        // 4. Click 'Signup / Login'
        homePage.clickSignupLogin();

        // 5. Verify 'New User Signup!' is visible
        assertTrue(loginPage.isSignupHeaderVisible(), "'New User Signup!' não está visível!");

        // 6 e 7. Enter name and email address & Click 'Signup'
        loginPage.enterSignupCredentials(nome, emailUnico);
        loginPage.clickSignup();

        // 8. Verify 'ENTER ACCOUNT INFORMATION' is visible
        assertTrue(registerPage.isEnterAccountInfoVisible(), "'ENTER ACCOUNT INFORMATION' não está visível!");

        // 9. Fill details: Title, Name, Email, Password, Date of birth
        registerPage.fillAccountDetails(senha, "10", "4", "1995");

        // 10 e 11. Select checkboxes
        registerPage.selectCheckboxes();

        // 12. Fill details: Address, Country, State, City, Zipcode, Mobile Number
        registerPage.fillAddressDetails("Carlos", "Silva", "UFF Tech", "Rua Passo da Patria, 156",
                "Bloco D", "India", "Rio de Janeiro", "Niterói", "24210-240", telefone);

        // 13. Click 'Create Account' button
        registerPage.clickCreateAccount();

        // 14 e 15. Verify 'ACCOUNT CREATED!' and click 'Continue'
        assertTrue(accountStatusPage.isAccountCreatedVisible(), "'ACCOUNT CREATED!' não está visível!");
        accountStatusPage.clickContinue();

        // 16. Verify 'Logged in as username' is visible
        assertTrue(homePage.isLoggedInAsVisible(nome), "'Logged in as " + nome + "' não está visível!");

        // 17 e 18. Click 'Delete Account', verify 'ACCOUNT DELETED!' and click 'Continue'
        homePage.clickDeleteAccount();
        assertTrue(accountStatusPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' não está visível!");
        accountStatusPage.clickContinue();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
