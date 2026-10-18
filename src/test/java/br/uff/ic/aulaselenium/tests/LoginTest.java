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

import br.uff.ic.aulaselenium.pages.HomePage;
import br.uff.ic.aulaselenium.pages.LoginPage;

public class LoginTest {
	private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @ParameterizedTest(name = "Entrada {index}: Email=''{0}'' | Senha=''{1}''")
    @DisplayName("Test Case 3: Login User with incorrect email and password (PCE + AVL)")
    @CsvSource({
        // Entrada 1 (PCE - Email Sem @ / Formato Inválido)
        "'usuario_invalido_sem_arroba.com', 'Senha123456'",
        
        // Entrada 2 (AVL - Senha Limite Inferior Externa: 1 caractere)
        "'nao_cadastrado_avl1@teste.com', 'a'",
        
        // Entrada 3 (AVL - Email String Extremamente Longa / Limite Superior)
        "'usuario_com_email_extremamente_longo_para_testar_limite_maximo_no_campo_de_login_do_sistema@dominioexemplo.com', 'SenhaPadrão123'",
        
        // Entrada 4 (PCE - Email Válido Não Cadastrado / Senha Vazia)
        "'email_valido_nao_existente@dominio.com', ''",
        
        // Entrada 5 (PCE - Credenciais Sintaticamente Válidas, porém Não Cadastradas)
        "'usuario_desconhecido_2026@gmail.com', 'SenhaErrada999'"
    })
    public void testLoginWithIncorrectCredentials(String email, String password) {
        // 1 e 2. Launch browser & Navigate to URL
        homePage.navigateToHomePage();

        // 3. Verify home page is visible
        assertTrue(homePage.isHomePageVisible(), "Home page não está visível!");

        // 4. Click 'Signup / Login'
        homePage.clickSignupLogin();

        // 5. Verify 'Login to your account' is visible
        assertTrue(loginPage.isLoginHeaderVisible(), "'Login to your account' não está visível!");

        // 6. Enter incorrect email address and password
        loginPage.enterLoginCredentials(email, password);

        // 7. Click 'login' button
        loginPage.clickLogin();

        // 8. Verify error 'Your email or password is incorrect!' is visible
        assertTrue(loginPage.isErrorMessageVisible(), "A mensagem de erro de login incorreto não foi exibida!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
