package lucaslimb.com.github.leilao;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    public void beforeEach(){
        this.loginPage = new LoginPage();
    }

    @AfterEach
    public void afterEach(){
        this.loginPage.quit();
    }
    
    @Test
    public void validLogin(){
        loginPage.fillLoginForm("fulano", "pass");
        loginPage.sendLoginForm();

        Assert.assertFalse(loginPage.isLoginPage());
        Assert.assertEquals("fulano", loginPage.getLoginUsername());
    }

    @Test
    public void invalidLogin(){
       loginPage.fillLoginForm("invalido", "0000");
       loginPage.sendLoginForm();

        Assert.assertTrue(loginPage.isInvalidLoginPage());
        Assert.assertNull(loginPage.getLoginUsername());
        Assert.assertTrue(loginPage.containsText("Usuário e senha inválidos."));
    }

    @Test
    public void denyRestrictedPageAccess(){
        loginPage.goToLances();

        Assert.assertTrue(loginPage.isLoginPage());
        Assert.assertFalse(loginPage.containsText("Dados do Leilão"));
    }

}
